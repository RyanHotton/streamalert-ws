/**
 * 
 */
package pm1.xmlexercise;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AlexGenio
 *
 */
public class XMLParser {

	/**
	 * @param args
	 */
	
	private enum Markup {
		RSS,
		NN,
		MARFCAT_IN,
		MARFCAT_OUT,
		WSDL
	}
	
	private Map<Markup, String> defaultUriMap = new HashMap<Markup, String>()
	{{
	     put(Markup.RSS, "http://www.ledevoir.com/rss/edition_complete.xml");
	     put(Markup.NN, "https://users.encs.concordia.ca/~yzs487_4/xml/test1.xml");
	     put(Markup.MARFCAT_IN, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-in.xml");
	     put(Markup.MARFCAT_OUT, "https://users.encs.concordia.ca/~yzs487_4/xml/marfcat-out.xml");
	     put(Markup.WSDL, "http://users.encs.concordia.ca/~s487_4/examples/soap/faultmessage/faultSample.wsdl");
	}};
	
	public static void main(String[] args) {
		
		XMLParser parser = new XMLParser();
		
		// parse the xml documents with an empty uri (will use the default strings)
		
		System.out.println("Parsing the default URIs...\n");
		parser.parseXML(Markup.RSS, "");
		parser.parseXML(Markup.NN, "");
		parser.parseXML(Markup.MARFCAT_IN, "");
		parser.parseXML(Markup.MARFCAT_OUT, "");
		//parser.parseXML(Markup.WSDL, "");
	}
	
	/**
	 * Creates an XML input stream for a given markup and URI.
	 * If the URI is empty or null, it uses the default URIs.
	 * @param markup
	 * @param uri
	 */
	public void parseXML(Markup markup, String uri) {
		
		// if the uri passed in is null or empty, use the default xml for the markup
		if (uri == null || uri.trim().isEmpty()) {
			uri = defaultUriMap.get(markup);
		}
		
		InputStream xml = null;
		
		// open the xml into a stream
		try {
			
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			xml = connection.getInputStream();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (xml != null) {
			// call the appropriate helper method
			switch (markup) {
				case RSS:         parseRSSXML(xml);        break;
				case NN:          parseNNXML(xml);         break;
				case MARFCAT_IN:  parseMARFCATINXML(xml);  break;
				case MARFCAT_OUT: parseMARFCATOUTXML(xml); break;
				case WSDL:        parseWSDLXML(xml);       break;
				default: System.out.println("Error: Invalid markup was entered.");
			}
		} else {
			System.out.println("Error: Could not open the url for xml parsing.");
		}
		
	}
	
	/**
	 * Parses XML input stream using appropriate parsing techniques
	 * and prints out elements and attributes in 'name:value' pairs
	 * @param xml
	 */
	public void parseRSSXML(InputStream xml) {
		// TODO: Stub for parsing RSS xml documents
		System.out.println("About to parse RSS XML!");
	}
	
	/**
	 * Parses XML input stream using appropriate parsing techniques
	 * and prints out elements and attributes in 'name:value' pairs
	 * @param xml
	 */
	public void parseNNXML(InputStream xml) {
		// TODO: Stub for parsing NN xml documents
		System.out.println("About to parse NN XML!");
	}
	
	/**
	 * Parses XML input stream using appropriate parsing techniques
	 * and prints out elements and attributes in 'name:value' pairs
	 * @param xml
	 */
	public void parseMARFCATINXML(InputStream xml) {
		// TODO: Stub for parsing MARFCATIN xml documents
		System.out.println("About to parse MARFCATIN XML!");
	}
	
	/**
	 * Parses XML input stream using appropriate parsing techniques
	 * and prints out elements and attributes in 'name:value' pairs
	 * @param xml
	 */
	public void parseMARFCATOUTXML(InputStream xml) {
		// TODO: Stub for parsing MARFCATOUT xml documents
		System.out.println("About to parse MARFCATOUT XML!");
	}
	
	/**
	 * Parses XML input stream using appropriate parsing techniques
	 * and prints out elements and attributes in 'name:value' pairs
	 * @param xml
	 */
	public void parseWSDLXML(InputStream xml) {
		// TODO: Stub for parsing WSDL xml documents
		System.out.println("About to parse WSDL XML!");
	}

}
