package xml.parse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import xml.abstractions.Parser;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Sophia Kavousifard
 */
public class ParseDOM extends Parser{
	
	private DocumentBuilderFactory docBuilderFactory;
	private DocumentBuilder docBuilder;
	
	/**
	 * Default constructor for DomParser
	 * @param docBuilderFactory
	 */
	public ParseDOM() {
		super();
		
		try {
			this.docBuilderFactory = DocumentBuilderFactory.newInstance();
			this.docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, e.getMessage());
		}
	}

	/**
	 * Parses the XML source and gets ready to parse the nodes
	 * 
	 * @param xml
	 */
	public String parse(InputStream xml) {
		String output = null;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			Document doc = docBuilder.parse(xml);
			doc.normalize();
			output = keyValueNodes(doc, stringBuilder).toString();
		}
		catch(Exception e) {
			getLogger().log(Level.WARNING, e.getMessage());
		}
		
		return output;
	}
	
	/**
	 * Iterates through each node of the XML document and 
	 * prints out attribute-value pairs
	 * 
	 * @param doc object that has parsed file
	 * @param stringBuilder
	 */
	public  StringBuilder findChildNodes(Document doc, StringBuilder stringBuilder) {
		NodeList entries = doc.getElementsByTagName("*");
		
		for(int i=0; i < entries.getLength(); i++) {
			Element tag = (Element) entries.item(i);
			stringBuilder.append(tag.getNodeName() + ":\n");
			
			for(int j=0; j < tag.getAttributes().getLength(); j++) {
				Node attribute = tag.getAttributes().item(j);

				if(attribute != null) {
					stringBuilder.append(attribute.getNodeName() + " = " + attribute.getNodeValue() + "\n");
				}
			}
		}
		return stringBuilder;
	}
	
	/**
	 * Iterates through each node of the XML document and 
	 * stores value in tag=RootNode attrname=name attrvalue=value
	 * @param doc object that has parsed file
	 * @param stringBuilder
	 * @return
	 */
	public String keyValueNodes(Document doc, StringBuilder stringBuilder) {
		NodeList entries = doc.getElementsByTagName("*");
		String stringTags = "tag=";
		String stringAttributes = "attrname=";
		String stringValues = "attrvalue=";
		
		for(int i=0; i < entries.getLength(); i++) {
			Element tag = (Element) entries.item(i);
			
			stringTags += tag.getNodeName().replaceAll("\\s+", "") + ",";
			
			for(int j=0; j < tag.getAttributes().getLength(); j++) {
				Node attribute = tag.getAttributes().item(j);

				if(attribute != null) {
					stringAttributes += attribute.getNodeName().replaceAll("\\s+", "") + ",";
					stringValues += attribute.getNodeValue().replaceAll("\\s+", "") + ",";
				}
			}
		}
		return stringTags + " " + stringAttributes + " " + stringValues;
	}
}
