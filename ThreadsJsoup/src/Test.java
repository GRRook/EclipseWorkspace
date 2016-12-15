import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {
	
	private static String encodedUrl = "https://www.google.nl/";
	
	public static void main(String[] args) {
		
		// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// Schrijf hier je eigen code
					// Random wachttijd
					try {
						// Genereer een getal tussen de 0 t/m 10.
						int wachtTijd = (int) (Math.random() * 11);
						System.out.println(Thread.currentThread().getName()
								+ ": Slaap " + wachtTijd + " sec");
						// Slaap wachtTijd seconden
						try {
							JsoupConnect();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread.sleep(wachtTijd * 50);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "Thread 1").start();
		
		// Maak en start thread 2. Deze draait tegelijkertijd met thread 1,2,3,4
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// Schrijf hier je eigen code
					// Random wachttijd
					try {
						// Genereer een getal tussen de 0 t/m 10.
						int wachtTijd = (int) (Math.random() * 11);
						System.out.println(Thread.currentThread().getName()
								+ ": Slaap " + wachtTijd + " sec");
						// Slaap wachtTijd seconden
						try {
							JsoupConnect();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread.sleep(wachtTijd * 100);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "Thread 2").start();
		
		// Maak en start thread 2. Deze draait tegelijkertijd met thread 1
		new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							// Schrijf hier je eigen code
							// Random wachttijd
							try {
								// Genereer een getal tussen de 0 t/m 10.
								int wachtTijd = (int) (Math.random() * 11);
								System.out.println(Thread.currentThread().getName()
										+ ": Slaap " + wachtTijd + " sec");
								// Slaap wachtTijd seconden
								try {
									JsoupConnect();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Thread.sleep(wachtTijd * 20);
							} catch (InterruptedException e) {
							}
						}
					}
				}, "Thread 3").start();
				
		// Maak en start thread 2. Deze draait tegelijkertijd met thread 1
		new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							// Schrijf hier je eigen code
							// Random wachttijd
							try {
								// Genereer een getal tussen de 0 t/m 10.
								int wachtTijd = (int) (Math.random() * 11);
								System.out.println(Thread.currentThread().getName()
										+ ": Slaap " + wachtTijd + " sec");
								// Slaap wachtTijd seconden
								try {
									JsoupConnect();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Thread.sleep(wachtTijd * 20);
							} catch (InterruptedException e) {
							}
						}
					}
				}, "Thread 4").start();
		
	}
	
	public static void JsoupConnect() throws IOException{
		Document doc = Jsoup.connect(encodedUrl).maxBodySize(0).get();
		getElements(doc);
	}
	
	private static void getElements(Document doc){
		
		Elements table = doc.select("table");
		
	    	System.out.println(table.html());
	    
	}
}
