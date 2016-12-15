public class Test {
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
						Thread.sleep(wachtTijd * 1000);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "Thread 1").start();
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
						Thread.sleep(wachtTijd * 1000);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "Thread 2").start();
	}
}
