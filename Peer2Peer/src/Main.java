import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.P2PSocket;
import java.net.P2PNetwork;
public class Main {
	public static void main(String args[]) {
	      try {
	         // sign into the peer-to-peer network,
	         // using the username "clientpeer", the password "clientpeerpassword",
	         // and find a network named "TestNetwork"
	         System.out.println("Signing into the P2P network..");
	         P2PNetwork.signin("clientpeer", "clientpeerpassword", "TestNetwork");
			
	         // create a socket to connect to the
	         // domain "www.nike.laborpolicy" on port 100
	         System.out.println("Connecting to server socket! at " + "www.nike.laborpolicy:100...");
	         Socket socket = new P2PSocket("www.nike.laborpolicy", 100);
			 System.out.println("Connected.");
			 
	         // now communicate with this server
	         DataInputStream in = new DataInputStream(socket.getInputStream());
	         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	         String results = in.readUTF();
	         System.out.println("Message from server: " + results);
	         out.writeUTF("Hello server world!");
			 
	         // shut everything down
	         socket.close();
	      }
		  
	      catch (Exception e) {
	         e.printStackTrace();
	         System.exit(1);
	      }
	   }
}
