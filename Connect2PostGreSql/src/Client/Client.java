package Client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		URL url = new URL("http://localhost:8080/cal?wsdl");
		QName qName = new QName("http://app.ws.com/", "WebserviceImplService");
		
		Service service = Service.create(url, qName);
		//RestService webservice = service.getPort(Webservice.class);
		//System.out.println(webservice.add(4354354, 10));
	}

}
