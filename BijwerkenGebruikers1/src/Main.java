import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {
	private static String xml = "";
	
	static Node temp;
	
	public static void main(String[] args) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		/*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        builder = factory.newDocumentBuilder();  
        Document doc = builder.parse( new InputSource( new StringReader( xml )) );        
        doc.getDocumentElement().normalize();
        
        NodeList nodeList=doc.getElementsByTagName("*");
        
        for (int i=0; i<nodeList.getLength(); i++) 
        {
            // Get element
            Element element = (Element)nodeList.item(i);
            
            //System.out.println(element.getTextContent());
            //System.out.println(element.getNodeName());
            
            if(element.getNodeName().toString().equals("tuple")){
            	System.out.println(element.getNodeName());
            	
            	Transformer transformer = TransformerFactory.newInstance().newTransformer();
            	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            	transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            	//initialize StreamResult with File object to save to file
            	StreamResult result = new StreamResult(new StringWriter());
            	DOMSource source = new DOMSource(element);
            	transformer.transform(source, result);
            	String xmlString = result.getWriter().toString();
            	System.out.println(xmlString);
            	
            	temp = element.getFirstChild();
            	addNode("","",element);
            }
        } 
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
		transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);	
        
        StreamResult result = new StreamResult("test.xml");	
        transformer.transform(source, result);*/
        
        String text = "halo,lll";
	    
        StringBuilder sb = new StringBuilder(text);
        sb.replace(0, 1, ",");
        System.out.println(sb.toString());
	}
	
	protected static void addNode(String tagName, String value, Node parent) {
	    Document dom = parent.getOwnerDocument();
	     
	    // Create a new Node with the given tag name
	    Node node = dom.createElement("new");	    
	   
	    node.appendChild(temp.getFirstChild());
	     
	    // Add the new node structure to the parent node
	    parent.appendChild(node);
	    
	}



}
