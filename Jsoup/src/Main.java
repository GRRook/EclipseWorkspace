import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	private static String url = "https://www.google.nl/";
	private static String param = "";
	
	private static String test = "<form><fieldset xmlns=\"http://bop.mindef.nl/domain/common\" xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\"> \r\n" +
            " <input type=\"hidden\" id=\"event\" name=\"event\"></input>\r\n" +
            " <div class=\"aq-page-content\"> \r\n" +
            "  <div class=\"aq-container\" id=\"P769-C0\"> \r\n" +
            "   <div class=\"aq-instance_selector\"> \r\n" +
            "    <table class=\"aq-instanceselector\"> \r\n" +
            "     <thead> \r\n" +
            "      <tr> \r\n" +
            "       <th class=\"aq-table\">Verlofsoort</th> \r\n" +
            "       <th class=\"aq-table\">Begindatum</th> \r\n" +
            "       <th class=\"aq-table\">Einddatum</th> \r\n" +
            "       <th class=\"aq-table\">Toegekend</th> \r\n" +
            "       <th class=\"aq-table\">Uitbetaald</th> \r\n" +
            "       <th class=\"aq-table\">Opgenomen</th> \r\n" +
            "       <th class=\"aq-table\">Resterend</th> \r\n" +
            "      </tr> \r\n" +
            "     </thead> \r\n" +
            "     <tbody> \r\n" +
            "      <tr class=\"aq-odd\"> \r\n" +
            "       <td> \r\n" +
            "        <div class=\"aq-answer-holder\">\r\n" +
            "         Vakantieverlof\r\n" +
            "         <br> </br>\r\n" +
            "        </div> </td> \r\n" +
            "       <td> \r\n" +
            "        <div class=\"aq-dateholder\">\r\n" +
            "         01-01-2015\r\n" +
            "        </div> </td> \r\n" +
            "       <td> \r\n" +
            "        <div class=\"aq-dateholder\">\r\n" +
            "         31-12-2015\r\n" +
            "        </div> </td> \r\n" +
            "       <td>56,0</td> \r\n" +
            "       <td>0,0</td> \r\n" +
            "       <td>0,0</td> \r\n" +
            "       <td>56,0</td> \r\n" +
            "      </tr> \r\n" +
            "     </tbody> \r\n" +
            "    </table> \r\n" +
            "   </div> \r\n" +
            "   <div class=\"aq-container\"></div> \r\n" +
            "  </div> \r\n" +
            "  <input type=\"hidden\" id=\"ward-identifier\" name=\"ward-identifier\" value=\"ward-1\"></input>\r\n" +
            " </div> \r\n" +
            "</fieldset></form>";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		trustHostnamesAndCertificates();
		String encodedUrl = getEncodedUrl(url, param);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		//System.out.println(encodedUrl);
		for(int i = 0; i < 20; i++){
			System.out.println(sdf.format(new Date()) + " " + i);
			Document doc = Jsoup.connect(encodedUrl).maxBodySize(0).get();
			getElements(doc);
			if(i == 500){
				System.out.println(sdf.format(new Date()) + " " + i);
				System.out.println(sdf.format(new Date()) + " " + i);
				System.out.println(sdf.format(new Date()) + " " + i);
				Thread.sleep(10000);
			}
		}
		
		
		
		while(true){
			System.out.println("work");
		}
		
	}
	
	private static void getElements(Document doc){
		
		Elements table = doc.select("table");
		
	    System.out.println(table.html());
	    //System.out.println(doc.body());
		/*Elements id = doc.select("div#cookieContainer");
		Elements li = doc.select("li");
		Elements inputElements = doc.select("input");*/
		
		/*System.out.println(id.html());
	    System.out.println(li.html());*/
	    /*for	(Element element : inputElements){
	    	System.out.println(element.attr("value"));
	    }*/
	}
	
	public static void trustHostnamesAndCertificates(){
		SSLUtilities.trustAllHostnames();
		SSLUtilities.trustAllHttpsCertificates();
	}
	
	public static String getEncodedUrl(String url, String param) throws UnsupportedEncodingException{
		String encodedParam = URLEncoder.encode(param, "UTF-8");		
		String result = url + encodedParam;
		return result;
	}
}
