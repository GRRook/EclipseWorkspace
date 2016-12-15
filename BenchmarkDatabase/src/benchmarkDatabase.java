
public class benchmarkDatabase {

	public static void main(String[] args) {		
		int insertData = 0;
		int readDataWithoutIndexes = 1;
		int readDataWithIndexes = 2;
		
		int casus = readDataWithIndexes;
		
		switch(casus){
		case 0:
			int THREAD0 = 0;
			int THREAD1 = 1;
			int THREAD2 = 2;	
			
	    	Thread thread0 = new Thread(new RunnableThread("thread0", THREAD0, 0));
	    	thread0.start();		   
	    	Thread thread1 = new Thread(new RunnableThread("thread1", THREAD1, 0));
	    	thread1.start();    
	    	Thread thread2 = new Thread(new RunnableThread("thread2", THREAD2, 0));
	    	thread2.start();
		    	
			break;
		case 1:
			int THREAD3 = 3;
			int THREAD4 = 4;
			Thread thread3 = new Thread(new RunnableThread("thread3", THREAD3, 0));
			thread3.start();	   
	    	Thread thread4 = new Thread(new RunnableThread("thread4", THREAD4, 0));
	    	thread4.start();
			
			break;
		case 2:
			int THREAD5 = 5;
			int THREAD6 = 6;
			
	    	Thread thread5 = new Thread(new RunnableThread("thread5", THREAD5, 0));
	    	thread5.start();
	    	Thread thread6 = new Thread(new RunnableThread("thread6", THREAD5, 0));
	    	thread6.start();
			
			break;
		}
		
		}

	}


