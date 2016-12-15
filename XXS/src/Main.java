import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Main {
	
	public static String unsafe = "<h1 class=\"what\">HALLO</h1><script>alter();</script><img src=\"javascript:alert();\"></img>";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String safe = Jsoup.clean(unsafe, Whitelist.none());
		System.out.println(safe);
		
	}

}
