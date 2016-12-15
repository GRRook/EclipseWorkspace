import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.Map;
import java.awt.List;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main{
	
   public static void main(String[] args) throws ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerException, InterruptedException
   {
        new Main().testIt();
   }
	
   private void testIt() throws ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerException, InterruptedException{
	  SSLUtilities.trustAllHostnames();
	  SSLUtilities.trustAllHttpsCertificates();
      String https_url = "https://bol.com";
      URL url;
      HttpsURLConnection con = null;
      try {

	     url = new URL(https_url);
	     con = (HttpsURLConnection)url.openConnection();
	    
	     //dumpl all cert info
	     //print_https_cert(con);
	     //dump all the content
	     
	     //Document doc = getHttpContent(con);
	     
	     Transformer tf = TransformerFactory.newInstance().newTransformer();
	     tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	     tf.setOutputProperty(OutputKeys.INDENT, "yes");
	     Writer out = new StringWriter();
	     //tf.transform(new DOMSource(doc), new StreamResult(out));
	     //System.out.println(out.toString());
	     
	     //System.out.println(getHttpContent(con));
	     
	     org.jsoup.nodes.Document doc = Jsoup.connect("https://tweakers.net/").get();
	     System.out.println(doc.html());
	     System.out.println(doc.data());
	     
	    // DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    // DocumentBuilder builder;  
	    // builder = factory.newDocumentBuilder();  
	    // Document document = builder.parse( new InputSource( new StringReader( result ) ) ); 
	     
	    // writeToFile(result);
			
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      } finally {
    	  con.disconnect();
      }

   }
	
   private void print_https_cert(HttpsURLConnection con){
     
    if(con!=null){
			
      try {				
		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Cipher Suite : " + con.getCipherSuite());
		System.out.println("\n");
				
		Certificate[] certs = con.getServerCertificates();
		for(Certificate cert : certs){
		   System.out.println("Cert Type : " + cert.getType());
		   System.out.println("Cert Hash Code : " + cert.hashCode());
		   System.out.println("Cert Public Key Algorithm : " 
		                                + cert.getPublicKey().getAlgorithm());
		   System.out.println("Cert Public Key Format : " 
		                                + cert.getPublicKey().getFormat());
		   System.out.println("\n");
		}
				
		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

     }
	
   }
	
   private String getHttpContent(HttpsURLConnection con) throws IOException{
	   String line;
       StringBuilder builder = new StringBuilder();
       builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
       BufferedReader br = null;
       InputStreamReader isr = null;
	   if(con!=null){			
		   try {		
			   isr = new InputStreamReader(con.getInputStream());
			   br = new BufferedReader(isr);			
			   while (br.readLine() != null){
				   line = br.readLine();
				   builder.append(line);
			   }  
						
			} catch (IOException e) {			   
			   e.printStackTrace();
			} finally{
				br.close();
				isr.close();
			}
	   }
	  /* final Tidy tidy = new Tidy();
	   tidy.setQuiet(false);
	   tidy.setShowWarnings(true);
	   tidy.setShowErrors(0);
	   tidy.setMakeClean(true);
	   tidy.setForceOutput(true);
	   tidy.setXmlOut(true);
	   Document document = tidy.parseDOM(con.getInputStream(), null);*/
	   String html = builder.toString();
	   return html; 		
   }
   
   private void writeToFile(String content){
	   try {

			
			File file = new File("D:/Test.html");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
   }
	
}