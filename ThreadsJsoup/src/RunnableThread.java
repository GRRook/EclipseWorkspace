import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class RunnableThread implements Runnable {

	private int time;
	private String name;
	private int transactionType;
	private int amount;
	
	

	public RunnableThread() {
	}

	public RunnableThread(String p_name, int p_type, int p_amount, int p_time) {
		name = p_name;
		amount = p_amount;
		time = p_time;
		transactionType = p_type;
	}

	public void run() {
		try {
			//JsoupConnect();
		} catch (Exception e) {

		}
	}	
	
	

	
	



}