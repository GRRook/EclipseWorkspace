//=====================================================================
//
//  File:    connectURL.java      
//  Summary: This Microsoft JDBC Driver for SQL Server sample application
//	     demonstrates how to connect to a SQL Server database by using
//	     a connection URL. It also demonstrates how to retrieve data 
//	     from a SQL Server database by using an SQL statement.
//
//---------------------------------------------------------------------
//
//  This file is part of the Microsoft JDBC Driver for SQL Server Code Samples.
//  Copyright (C) Microsoft Corporation.  All rights reserved.
//
//  This source code is intended only as a supplement to Microsoft
//  Development Tools and/or on-line documentation.  See these other
//  materials for detailed information regarding Microsoft code samples.
//
//  THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF 
//  ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO 
//  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
//  PARTICULAR PURPOSE.
//
//===================================================================== 

import java.sql.*;
import java.util.Random;


public class connectURL {

	public static void main(String[] args) throws InterruptedException  {
		//TRANSACTION_READ_COMMITTED
	    //TRANSACTION_READ_UNCOMMITTED
	    //TRANSACTION_REPEATABLE_READ
	    //TRANSACTION_SERIALIZABLE 
    	//TESTING TRANSACTION LEVELS WITH THREADS IN JAVA       
		
		int DIRTY = 0;
		int UNREPEATABLE = 1;
		int PHANTOM = 2;
		int DEADLOCK = 3;
		
		int errType = DEADLOCK;
		
		switch (errType){
		
		case 0:
			Thread dirtyreaddelete = new Thread(new RunnableThread("deleteThread", DIRTY, 1, 0));
	    	Thread dirtyread = new Thread(new RunnableThread("dirtyread", DIRTY, 1, 0));	    	
	    	dirtyreaddelete.start();
	    	dirtyread.start();
	    	break;
	    	
		case 1:
			Thread unrepeatablereaddelete = new Thread(new RunnableThread("deleteThread", UNREPEATABLE, 1, 0));
	    	Thread unrepeatablereadadd = new Thread(new RunnableThread("unrepeatableRead", UNREPEATABLE, 1, 0));
	    	unrepeatablereaddelete.start();
	    	unrepeatablereadadd.start();
	    	break;
	    	
		case 2:
			Thread phantomInsert = new Thread(new RunnableThread("phantomInsert", PHANTOM, 5, 0));
	    	Thread phantomRead = new Thread(new RunnableThread("phantomRead", PHANTOM, 1, 0));
	    	phantomInsert.start();
	    	phantomRead.start();
	    	break;
	    	
		case 3:
			Thread deadlockdelete = new Thread(new RunnableThread("deadlockdelete", DEADLOCK, 1, 0));
	    	Thread deadlockadd = new Thread(new RunnableThread("deadlockadd", DEADLOCK, 1, 0));
	    	deadlockdelete.start();
	    	deadlockadd.start();
	    	break;
		}
    	
    	
    
    	
    	
    	
    	
    	
    	
    	
	}
	

}

