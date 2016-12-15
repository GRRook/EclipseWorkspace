import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Main {
	
	String test = "<fieldset xmlns=\"http://bop.mindef.nl/domain/common\"\r\n" + 
			"xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
			"  <input type=\"hidden\" id=\"event\" name=\"event\">\r\n" + 
			"    <div class=\"aq-page-content\">\r\n" + 
			"      <div class=\"aq-container\" id=\"P769-C0\">\r\n" + 
			"        <div class=\"aq-instance_selector\">\r\n" + 
			"          <table class=\"aq-instanceselector\">\r\n" + 
			"            <thead>\r\n" + 
			"              <tr>\r\n" + 
			"                <th class=\"aq-table\">Verlofsoort</th>\r\n" + 
			"                <th class=\"aq-table\">Begindatum</th>\r\n" + 
			"                <th class=\"aq-table\">Einddatum</th>\r\n" + 
			"                <th class=\"aq-table\">Toegekend</th>\r\n" + 
			"                <th class=\"aq-table\">Uitbetaald</th>\r\n" + 
			"                <th class=\"aq-table\">Opgenomen</th>\r\n" + 
			"                <th class=\"aq-table\">Resterend</th>\r\n" + 
			"              </tr>\r\n" + 
			"            </thead>\r\n" + 
			"            <tbody>\r\n" + 
			"              <tr class=\"aq-odd\">\r\n" + 
			"                <td>\r\n" + 
			"                  <div class=\"aq-answer-holder\">Vakantieverlof\r\n" + 
			"                  <br />\r\n" + 
			"                  </div>\r\n" + 
			"                </td>\r\n" + 
			"                <td>\r\n" + 
			"                  <div class=\"aq-dateholder\">01-01-2015</div>\r\n" + 
			"                </td>\r\n" + 
			"                <td>\r\n" + 
			"                  <div class=\"aq-dateholder\">31-12-2015</div>\r\n" + 
			"                </td>\r\n" + 
			"                <td>56,0</td>\r\n" + 
			"                <td>0,0</td>\r\n" + 
			"                <td>0,0</td>\r\n" + 
			"                <td>56,0</td>\r\n" + 
			"              </tr>\r\n" + 
			"            </tbody>\r\n" + 
			"          </table>\r\n" + 
			"        </div>\r\n" + 
			"        <div class=\"aq-container\"></div>\r\n" + 
			"      </div>\r\n" + 
			"      <input type=\"hidden\" id=\"ward-identifier\"\r\n" + 
			"      name=\"ward-identifier\" value=\"ward-1\"></input>\r\n" + 
			"    </div>\r\n" + 
			"  </input>\r\n" + 
			"</fieldset>";
	
	static String url = "https://www.google.com/";
	public static void main(String[] args) throws ClientProtocolException, IOException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		
		//httpClientTest();
		jsoupTest();
		urlConnectionTest();
	}
	
	
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	public static void httpClientTest() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
		
		System.out.println("HTTPCLIENT: " + sdf.format(new Date()));
		
		for(int i = 0; i < 100; i++){
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		}

		
		
	
		System.out.println("HTTPCLIENT: " + sdf.format(new Date()));
		
		
	}
	
	public static void jsoupTest() throws IOException{
		System.out.println("JSOUP: " + sdf.format(new Date()));
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
	
		
		Elements result = doc.select("table");
		String result1 = result.outerHtml();
		System.out.println(result1);
		System.out.println("JSOUP: " + sdf.format(new Date()));
		
	}


	public static void urlConnectionTest() throws IOException{
		System.out.println("URLCONNECTION: " + sdf.format(new Date()));
		for(int i = 0; i < 1; i++){
			URL url = new URL("https://www.google.com/");
	        URLConnection urlConnection = url.openConnection();
	        urlConnection.setConnectTimeout(10*4000);
	        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			try{
		        String message = new String();
		        String line=null;
		        while ((line = in.readLine()) != null) {
		            //buffer.append(line);
		            message += line;
		        }

		        //System.out.println(message);
		        org.jsoup.nodes.Document doc = Jsoup.parse(message);
		        Elements element = doc.select("table");
		        System.out.println(element.outerHtml());
			}catch (Error e) {
				
			}finally{
				in.close();
			}
			
	       
	        
	        
		}

		
        
        
        System.out.println("URLCONNECTION: " + sdf.format(new Date()));
	}
}
