import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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
		Connection conn;
		try {
			switch (transactionType) {
			case 0:				
				conn = connect(transactionType);			
				if(name == "deleteThread"){		
					dirtyReadDelete(conn, amount);
				} else if (name == "dirtyread"){
					dirtyRead(conn, amount);
				}
				break;
			case 1:
				conn = connect(transactionType);		
				if(name == "deleteThread"){
					unrepeatableReadDelete(conn, amount);
				} else if (name == "unrepeatableRead"){
					unrepeatableRead(conn, amount);
				}			
				break;
			case 2:
				conn = connect(transactionType);		
				if(name == "phantomInsert"){
					phantomInsert(conn, amount);
				} else if (name == "phantomRead"){
					phantomRead(conn, amount);
				}
				break;
			case 3:
				conn = connect(transactionType);		
				if(name == "deadlockdelete"){
					deadLockDelete(conn, amount);
				} else if (name == "deadlockadd"){
					deadLockAdd(conn, amount);
				}
				break;
			}
		} catch (Exception e) {

		}
	}	
	
	
	private void deadLockAdd(Connection p_conn, int p_amount){
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			Thread.sleep(200);
			String SQL = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);
			updateValue = updateValue + p_amount;
			
			Thread.sleep(1500);
			String SQL1 = "UPDATE product SET voorraad = " + updateValue + " WHERE id = 1";
			System.out.println("Threadname: " + name + ". -> Try to update with " + updateValue);		
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQL1);
			
			conn.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	private void deadLockDelete(Connection p_conn, int p_amount){
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			String SQL = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);
			updateValue = updateValue + p_amount;
			
			Thread.sleep(1000);
			String SQL1 = "UPDATE product SET voorraad = " + updateValue + " WHERE id = 1";
			System.out.println("Threadname: " + name + ". -> Try to update with " + updateValue);		
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQL1);
			
			conn.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	private void phantomInsert(Connection p_conn, int p_amount){
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			Thread.sleep(500);				
			String SQL1 = "INSERT INTO product1 (naam, voorraad) VALUES ('Razer Blade', 70)";
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQL1);
			conn.commit();
			
			System.out.println("Inserted a new row");	
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	private void phantomRead(Connection p_conn, int p_amount){
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int voorraad = 0;		
		String naam = "";
		int index = 0;
		int index1 = 0;		
		try 
		{			
			String SQL = "SELECT * FROM product1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				index1++;				
			}
			System.out.println("amount of rows in table: " + index1);
			
			Thread.sleep(1000);
			String SQL1 = "SELECT * FROM product1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL1);
			while (rs.next()) 
			{
				index++;
				//naam = rs.getString(2);
				//voorraad = rs.getInt(3);					
			}		
			System.out.println("amount of rows in table: " +  index);	
			conn.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}	
	
	private void unrepeatableRead(Connection p_conn, int p_amount) {
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			String SQL = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);			
			Thread.sleep(1000);			
			String SQL1 = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL1);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);
			conn.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}		
	}
	
	
	private void unrepeatableReadDelete(Connection p_conn, int p_amount) {
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			Thread.sleep(500);
			String SQL = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);			
			updateValue = updateValue - p_amount;
			String SQL1 = "UPDATE product SET voorraad = " + updateValue + " WHERE id = 1";
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQL1);
			System.out.println("Threadname: " + name + ". -> Update Succeeded with, new voorraad " + updateValue);
			conn.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}	
	
	private void dirtyRead(Connection p_conn, int p_amount) throws ClassNotFoundException, SQLException {
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			Thread.sleep(200);
			String SQL = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);			
			conn.commit();			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	private void dirtyReadDelete(Connection p_conn, int p_amount) throws ClassNotFoundException, SQLException {
		Connection conn = p_conn;
		Statement stmt = null;
		ResultSet rs = null;
		int updateValue = 0;		
		try 
		{
			String SQL = "SELECT voorraad FROM product WHERE id = 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) 
			{
				updateValue = rs.getInt(1);
			}
			System.out.println("Threadname: " + name + ". -> Current voorraad is " + updateValue);
			updateValue = updateValue - p_amount;
			
			// Update new voorraad
			String SQL1 = "UPDATE product SET voorraad = " + updateValue + " WHERE id = 1";
			System.out.println("Threadname: " + name + ". -> Try to update with " + updateValue);		
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQL1);
			
			Thread.sleep(500);
			conn.rollback();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}	

	private Connection connect(int p_type) throws ClassNotFoundException, SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=Opdracht2;integratedSecurity=true;";
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(connectionUrl);
		switch(p_type){
			case 0:
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				break;
			case 1:
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				break;
			case 2:
				conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				break;
			case 3:
				conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				break;
		}
		conn.setAutoCommit(false);
		return conn;
	}



}