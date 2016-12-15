import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
		System.out.println(IsBase64Encoded("JVBERi0xLjQKJeLjz9MKMyAwIG9iaiA8PC9MZW5ndGggMTMyL0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicVY69CsJAEIT7eYoptTn3/rEVtBAsAgcWYiFGDSEnJI2v712ChUzzMbPszIhdgjBuDVOLfUKDEaLERs8PDI8l7KGFJ1yuwhY2MPrADB8XGmayQYkrbP9wyTuc8S5/qqZXLdwcNHVpfELPrmY9dl4Zx5Sxyrfp/hjWqf8tavAFTEEi7wplbmRzdHJlYW0KZW5kb2JqCjEgMCBvYmo8PC9QYXJlbnQgNCAwIFIvQ29udGVudHMgMyAwIFIvVHlwZS9QYWdlL1Jlc291cmNlczw8L1Byb2NTZXQgWy9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUldL0ZvbnQ8PC9GMSAyIDAgUj4+Pj4vTWVkaWFCb3hbMCAwIDYxMiA3OTJdPj4KZW5kb2JqCjIgMCBvYmo8PC9CYXNlRm9udC9UaW1lcy1Sb21hbi9UeXBlL0ZvbnQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nL1N1YnR5cGUvVHlwZTE+PgplbmRvYmoKNCAwIG9iajw8L1R5cGUvUGFnZXMvQ291bnQgMS9LaWRzWzEgMCBSXT4+CmVuZG9iago1IDAgb2JqPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDQgMCBSPj4KZW5kb2JqCjYgMCBvYmo8PC9Qcm9kdWNlcihpVGV4dCAyLjAuOCBcKGJ5IGxvd2FnaWUuY29tXCkpL01vZERhdGUoRDoyMDE2MDUwNDA5MzUzMSswMicwMCcpL0NyZWF0aW9uRGF0ZShEOjIwMTYwNTA0MDkzNTMxKzAyJzAwJyk+PgplbmRvYmoKeHJlZgowIDcKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMjE0IDAwMDAwIG4gCjAwMDAwMDAzNzAgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAwNDU5IDAwMDAwIG4gCjAwMDAwMDA1MDkgMDAwMDAgbiAKMDAwMDAwMDU1MyAwMDAwMCBuIAp0cmFpbGVyCjw8L1Jvb3QgNSAwIFIvSUQgWzxlMWQ3NDNmZjIyYzJlM2U5MTY4ZWU1NDg5NTQxYWQ3ZT48Yzc4ZWE1OGQ2MmI5NWY5N2ZlZDQ2MTM4N2RkYTllMTg+XS9JbmZvIDYgMCBSL1NpemUgNz4+CnN0YXJ0eHJlZgo2ODQKJSVFT0YK"));
		
		//System.out.println(getAtt(2,"bestand", "true", "asfsdfdsfsdfsdfsdfs"));
		//getTest(getAtt(2,"bestand", "true", "asfsdfdsfsdfsdfsdfs"));
		//System.out.println(getAttachments("<return xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\" xml-ns=\"http://bop.mindef.nl/domain/notificatie\"><item><BsNotificatieSpecifiekBijlagen><NOTIFICATIE_SPEC_ID>ValueOf_NOTIFICATIE_SPEC_ID</NOTIFICATIE_SPEC_ID><BIJLAGE_ID>ValueOf_BIJLAGE_ID</BIJLAGE_ID><NAAM_BIJLAGE>ValueOf_NAAM_BIJLAGE</NAAM_BIJLAGE><BIJLAGE>ValueOf_BIJLAGE</BIJLAGE><GECODEERD>ValueOf_GECODEERD</GECODEERD></BsNotificatieSpecifiekBijlagen></item></return>"));
	}
	
	public static String getAtt(int aantal, String naamAttachment, String encoded, String value){
		String result = "";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < aantal; i ++){
			list.add("<attachment naam='"+naamAttachment+"' encoded='" + encoded + "'>"+value+"</attachment>");
		}
		
		for (int i = 0; i < list.size(); i++){
			result = result + list.get(i);
		}
		return result;
	}
	
	public static void getTest(String input){
		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><a name='marcel'><b></b><c></c></a>";  

	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    try  
	    {  
	        builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader( xmlString ) ) );  
	        System.out.println(document.getNodeName().length());
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } 
	}
	
	public static String getAttachments(String xml) throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = dbfactory.newDocumentBuilder();
		ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
		Document doc = parser.parse(is);
		doc.getDocumentElement().normalize();                   
        
        NodeList nodeList=doc.getElementsByTagName("item");
        List<String> list = new ArrayList<String>();
        for (int i=0; i<nodeList.getLength(); i++) 
        {
            // Get element
        	
            Element element = (Element)nodeList.item(i); 
            NodeList NAAMBIJLAGE = element.getElementsByTagName("NAAM_BIJLAGE");
            NodeList BIJLAGE = element.getElementsByTagName("BIJLAGE");
            NodeList GECODEERD = element.getElementsByTagName("GECODEERD");
            
            list.add("<attachment name=\""+NAAMBIJLAGE.item(0).getFirstChild().getTextContent()+"\" encoded=\""+GECODEERD.item(0).getFirstChild().getTextContent()+"\">" + BIJLAGE.item(0).getFirstChild().getTextContent() + "</attachment>");
            System.out.println("<attachment name=\""+NAAMBIJLAGE.item(0).getFirstChild().getTextContent()+"\" encoded=\""+GECODEERD.item(0).getFirstChild().getTextContent()+"\">" + BIJLAGE.item(0).getFirstChild().getTextContent() + "</attachment>");
            //System.out.println(""+NAAMBIJLAGE+""+BIJLAGE+""+GECODEERD);
        } 
		
        String result = "";
        for(int i=0; i < list.size(); i++){
        	result = result + list.get(i);
        }
        
        return result;
	}
	
	public static Boolean IsBase64Encoded(String str) throws UnsupportedEncodingException
	{
	    try
	    {
	    	String base64encodedString = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
	         System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
			
	        // The part that checks if the string was properly padded to the
	        // correct length was borrowed from d@anish's solution
	        return (str.replace(" ","").length() % 4 == 0);
	    }
	    catch (Error e)
	    {
	        // If exception is caught, then it is not a base64 encoded string
	    	System.out.println("errortje" + e);
	       return false;
	    }
	}
	
	public static String decode(String input){
		String result = new String(Native.decodeBinBase65(input.getBytes(), input.length()));
		return result;
	}
}
