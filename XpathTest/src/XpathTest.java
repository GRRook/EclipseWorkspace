import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;

public class XpathTest {
	
	static String xml = "<?xml version=\"1.0\"?>\r\n" + 
			"<Employees>\r\n" + 
			"	<Employee emplid=\"1111\" type=\"admin\">\r\n" + 
			"		<firstname>John</firstname>\r\n" + 
			"		<lastname>Watson</lastname>\r\n" + 
			"		<age>30</age>\r\n" + 
			"		<email>johnwatson@sh.com</email>\r\n" + 
			"	</Employee>\r\n" + 
			"	<Employee emplid=\"2222\" type=\"admin\">\r\n" + 
			"		<firstname>Sherlock</firstname>\r\n" + 
			"		<lastname>Homes</lastname>\r\n" + 
			"		<age>32</age>\r\n" + 
			"		<email>sherlock@sh.com</email>\r\n" + 
			"	</Employee>\r\n" + 
			"	<Employee emplid=\"3333\" type=\"user\">\r\n" + 
			"		<firstname>Jim</firstname>\r\n" + 
			"		<lastname>Moriarty</lastname>\r\n" + 
			"		<age>52</age>\r\n" + 
			"		<email>jim@sh.com</email>\r\n" + 
			"	</Employee>\r\n" + 
			"	<Employee emplid=\"4444\" type=\"user\">\r\n" + 
			"		<firstname>Mycroft</firstname>\r\n" + 
			"		<lastname>Holmes</lastname>\r\n" + 
			"		<age>41</age>\r\n" + 
			"		<email>mycroft@sh.com</email>\r\n" + 
			"	</Employee>\r\n" + 
			"</Employees>";
	
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static void main(String[] args) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException{
		//...
		System.out.println("Tijd 1: " + sdf.format(new Date()));
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		/*try {
		    //Document document = builder.parse(new FileInputStream("c:\\employees.xml"));
		} catch (SAXException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}*/
			
		Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		
		System.out.println("Tijd 2: " + sdf.format(new Date()));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		
		///Employees/Employee[@emplid='3333']/email
		String expression = "Employees/Employee/firstname";

		//read a string value
		String email = xPath.compile(expression).evaluate(xmlDocument);
		
		//read an xml node using xpath
		Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);

		//read a nodelist using xpath
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		
		System.out.println("Tijd 3: " + sdf.format(new Date()));
		
		for (int i = 0; i < nodeList.getLength(); i++) {
		    System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
		}
		System.out.println("Aantal nodes in nodelist: " + nodeList.getLength()); 
		System.out.println("Tijd 4: " + sdf.format(new Date()));
	}
}
