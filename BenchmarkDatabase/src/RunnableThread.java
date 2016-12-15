import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RunnableThread implements Runnable {

	private int time;
	private String name;
	private int transactionType;
	private int amount;

	public RunnableThread() {
	}

	public RunnableThread(String p_name, int p_type, int p_time) {
		name = p_name;
		time = p_time;
		transactionType = p_type;
	}

	public void run() {
		Connection conn;
		try {
			switch (transactionType) {
			case 0:		
				conn = connect(transactionType);	
				thread0(conn);
				break;
			case 1:
				conn = connect(transactionType);		
				thread1(conn);	
				break;
			case 2:
				conn = connect(transactionType);		
				thread2(conn);
				break;
			case 3:
				conn = connect(transactionType);		
				thread3(conn);
				break;
			case 4:
				conn = connect(transactionType);		
				thread4(conn);
				break;
			case 5:
				conn = connect(transactionType);		
				thread5(conn);
				break;
			case 6:
				conn = connect(transactionType);		
				thread6(conn);
				break;
			}
		} catch (Exception e) {

		}
	}	
	
	private void thread6(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT * FROM student";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		
		List studentid = new ArrayList();
		List studentnummer = new ArrayList();
		List fk_persoon_id = new ArrayList(); 
		
		while (rs.next()) 
		{
			studentid.add(rs.getInt(1));
			studentnummer.add(rs.getInt(2));
			fk_persoon_id.add(rs.getInt(3));
		}
		int randomnumber = random.nextInt(studentid.size());
		int chosenstudentid = (int) studentid.get(randomnumber);
		int chosenstudentnummer = (int) studentid.get(randomnumber);
		int chosenstudentfk_persoon_id = (int) studentid.get(randomnumber);
		String SQL1 = "SELECT * FROM persoon WHERE persoon.id = "+chosenstudentfk_persoon_id+"";	
		ResultSet rs1 = stmt.executeQuery(SQL1);
		
		String chosenvoornaam = "";
		String chosenachternaam = "";
		
		while (rs1.next()) 
		{ 
			chosenvoornaam = rs1.getString(2);
			chosenachternaam = rs1.getString(3);
		}
		
		try 
		{	for(int i = 0; i < 600; i++){
			
		
			long beginTijd = System.currentTimeMillis();
			String SQL2 = "SELECT voornaam, achternaam FROM persoon WHERE id = "+chosenstudentid+"";
			ResultSet rs2 = stmt.executeQuery(SQL2);
			
			while (rs2.next()) 
			{
				System.out.println("Thread5, Voornaam " + rs2.getString(1) + " Achternaam " + rs2.getString(2));
			}
			
			String SQL3 = "SELECT fk_module_id "
					+ "FROM rooster "
					+ "JOIN klas_student "
					+ "ON rooster.fk_klas_id = klas_student.fk_klas_id "
					+ "WHERE klas_student.fk_student_id = "+997+"";			
			ResultSet rs3 = stmt.executeQuery(SQL3);
			while (rs3.next()){
				System.out.println("Thread5, ModuleId van gekozen student " +rs3.getInt(1));
			}
			long eindTijd = System.currentTimeMillis();
			long duurInMS = eindTijd - beginTijd;
			total = total + duurInMS;
			conn.commit();		
		
			System.out.println("Thread5, Select succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
			Thread.sleep(100);
		}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	private void thread5(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT * FROM student";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		
		List studentid = new ArrayList();
		List studentnummer = new ArrayList();
		List fk_persoon_id = new ArrayList(); 
		
		while (rs.next()) 
		{
			studentid.add(rs.getInt(1));
			studentnummer.add(rs.getInt(2));
			fk_persoon_id.add(rs.getInt(3));
		}
		int randomnumber = random.nextInt(studentid.size());
		int chosenstudentid = (int) studentid.get(randomnumber);
		int chosenstudentnummer = (int) studentid.get(randomnumber);
		int chosenstudentfk_persoon_id = (int) studentid.get(randomnumber);
		String SQL1 = "SELECT * FROM persoon WHERE persoon.id = "+chosenstudentfk_persoon_id+"";	
		ResultSet rs1 = stmt.executeQuery(SQL1);
		
		String chosenvoornaam = "";
		String chosenachternaam = "";
		
		while (rs1.next()) 
		{ 
			chosenvoornaam = rs1.getString(2);
			chosenachternaam = rs1.getString(3);
		}
		
		try 
		{	for(int i = 0; i < 600; i++){
			
		
			long beginTijd = System.currentTimeMillis();
			String SQL2 = "SELECT voornaam, achternaam FROM persoon WHERE id = "+chosenstudentid+"";
			ResultSet rs2 = stmt.executeQuery(SQL2);
			
			while (rs2.next()) 
			{
				System.out.println("Thread5, Voornaam " + rs2.getString(1) + " Achternaam " + rs2.getString(2));
			}
			
			String SQL3 = "SELECT fk_module_id "
					+ "FROM rooster "
					+ "JOIN klas_student "
					+ "ON rooster.fk_klas_id = klas_student.fk_klas_id "
					+ "WHERE klas_student.fk_student_id = "+997+"";			
			ResultSet rs3 = stmt.executeQuery(SQL3);
			while (rs3.next()){
				System.out.println("Thread5, ModuleId van gekozen student " +rs3.getInt(1));
			}
			long eindTijd = System.currentTimeMillis();
			long duurInMS = eindTijd - beginTijd;
			total = total + duurInMS;
			conn.commit();		
		
			System.out.println("Thread5, Select succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
			Thread.sleep(100);
		}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	
	private void thread3(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT * FROM student";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		
		List studentid = new ArrayList();
		List studentnummer = new ArrayList();
		List fk_persoon_id = new ArrayList(); 
		
		while (rs.next()) 
		{
			studentid.add(rs.getInt(1));
			studentnummer.add(rs.getInt(2));
			fk_persoon_id.add(rs.getInt(3));
		}
		int randomnumber = random.nextInt(studentid.size());
		int chosenstudentid = (int) studentid.get(randomnumber);
		int chosenstudentnummer = (int) studentid.get(randomnumber);
		int chosenstudentfk_persoon_id = (int) studentid.get(randomnumber);
		String SQL1 = "SELECT * FROM persoon WHERE persoon.id = "+chosenstudentfk_persoon_id+"";	
		ResultSet rs1 = stmt.executeQuery(SQL1);
		
		String chosenvoornaam = "";
		String chosenachternaam = "";
		
		while (rs1.next()) 
		{ 
			chosenvoornaam = rs1.getString(2);
			chosenachternaam = rs1.getString(3);
		}
		
		try 
		{	for(int i = 0; i < 600; i++){
			
		
			long beginTijd = System.currentTimeMillis();
			String SQL2 = "SELECT voornaam, achternaam FROM persoon WHERE voornaam = '"+chosenvoornaam+"' AND achternaam = '"+chosenachternaam+"'";
			ResultSet rs2 = stmt.executeQuery(SQL2);
			
			while (rs2.next()) 
			{
				System.out.println("Thread3, Voornaam " + rs2.getString(1) + " Achternaam " + rs2.getString(2));
			}
			
			String SQL3 = "SELECT fk_module_id "
					+ "FROM rooster "
					+ "JOIN klas_student "
					+ "ON rooster.fk_klas_id = klas_student.fk_klas_id "
					+ "WHERE klas_student.fk_student_id = "+997+"";			
			ResultSet rs3 = stmt.executeQuery(SQL3);
			while (rs3.next()){
				System.out.println("Thread3, ModuleId van gekozen student " +rs3.getInt(1));
			}
			long eindTijd = System.currentTimeMillis();
			long duurInMS = eindTijd - beginTijd;
			total = total + duurInMS;
			conn.commit();		
		
			System.out.println("Thread3, Select succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
			Thread.sleep(100);
		}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	private void thread4(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT * FROM student";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		
		List studentid = new ArrayList();
		List studentnummer = new ArrayList();
		List fk_persoon_id = new ArrayList(); 
		
		while (rs.next()) 
		{
			studentid.add(rs.getInt(1));
			studentnummer.add(rs.getInt(2));
			fk_persoon_id.add(rs.getInt(3));
		}
		int randomnumber = random.nextInt(studentid.size());
		int chosenstudentid = (int) studentid.get(randomnumber);
		int chosenstudentnummer = (int) studentid.get(randomnumber);
		int chosenstudentfk_persoon_id = (int) studentid.get(randomnumber);
		String SQL1 = "SELECT * FROM persoon WHERE persoon.id = "+chosenstudentfk_persoon_id+"";	
		ResultSet rs1 = stmt.executeQuery(SQL1);
		
		String chosenvoornaam = "";
		String chosenachternaam = "";
		
		while (rs1.next()) 
		{ 
			chosenvoornaam = rs1.getString(2);
			chosenachternaam = rs1.getString(3);
		}
		
		try 
		{	for(int i = 0; i < 600; i++){
			
		
			long beginTijd = System.currentTimeMillis();
			String SQL2 = "SELECT voornaam, achternaam FROM persoon WHERE voornaam = '"+chosenvoornaam+"' AND achternaam = '"+chosenachternaam+"'";
			ResultSet rs2 = stmt.executeQuery(SQL2);
			
			while (rs2.next()) 
			{
				System.out.println("Thread4, Voornaam " + rs2.getString(1) + " Achternaam " + rs2.getString(2));
			}
			
			String SQL3 = "SELECT fk_module_id "
					+ "FROM rooster "
					+ "JOIN klas_student "
					+ "ON rooster.fk_klas_id = klas_student.fk_klas_id "
					+ "WHERE klas_student.fk_student_id = "+818+"";			
			ResultSet rs3 = stmt.executeQuery(SQL3);
			while (rs3.next()){
				System.out.println("Thread4, ModuleId van gekozen student " +rs3.getInt(1));
			}
			long eindTijd = System.currentTimeMillis();
			long duurInMS = eindTijd - beginTijd;
			total = total + duurInMS;
			conn.commit();		
		
			System.out.println("Thread4, Select succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
			Thread.sleep(100);
		}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void thread0(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT MAX(id) FROM persoon";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) 
		{
			start = rs.getInt(1) + 600;
		}
		try 
		{	for (int i = start; i < start + 600; i++){
				
				long beginTijd = System.currentTimeMillis();	
				String SQL0 = "INSERT INTO persoon VALUES (" + i + ",'"+randomString(10)+"', '"+randomString(10)+"', 'de', '06-04-1988', 'man', 'Spui', '2741', 'Waddinxveen', '12345678901')";
				String SQL1 = "INSERT INTO student VALUES (" + i + ",1234567," + i + ")";
				String SQL2 = "INSERT INTO docent VALUES (" + i + ",'MDWCODE'," + i + ")";
				Statement statement = conn.createStatement();
				statement.executeUpdate(SQL0);
				statement.executeUpdate(SQL1);
				statement.executeUpdate(SQL2);
				if(random.nextInt(31) == 1)
				{
					System.out.println("Kans 1 op 30");
					String SQL3 = "INSERT INTO klas VALUES (" + i + ",'DINF" + i + "', '20150101', '20160101')";
					statement.executeUpdate(SQL3);
					String SQL4 = "INSERT INTO klas_student VALUES (" + i + "," + i + "," + i + ")";
					statement.executeUpdate(SQL4);
					String SQL5 = "INSERT INTO module VALUES (" + i + ",'MODULE', 'MODULECODE1', '20150101', '20160101'," + i + ")";
					statement.executeUpdate(SQL5);
					
					if(random.nextInt(100) <= 15){
						System.out.println("Kans op 15%");
						String SQL6 = "INSERT INTO rooster VALUES (" + i + ", 'lokaal1', '20150101', '20160101'," + i + "," + i + "," + i + ")";
						statement.executeUpdate(SQL6);
					}
					
				}				
				conn.commit();				
				long eindTijd = System.currentTimeMillis();
				long duurInMS = eindTijd - beginTijd;
				total = total + duurInMS;
				
				System.out.println(name + " " + i + " insert succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
				Thread.sleep(100);
			}
				
			
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
		
	private void thread1(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT MAX(id) FROM persoon";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) 
		{
			start = rs.getInt(1) + 2000;
		}
		try 
		{	for (int i = start; i < start + 600; i++){
				
				long beginTijd = System.currentTimeMillis();	
				String SQL0 = "INSERT INTO persoon VALUES (" + i + ",'"+randomString(10)+"', '"+randomString(10)+"', 'de', '06-04-1988', 'man', 'Spui', '2741', 'Waddinxveen', '12345678901')";
				String SQL1 = "INSERT INTO student VALUES (" + i + ",1234567," + i + ")";
				String SQL2 = "INSERT INTO docent VALUES (" + i + ",'MDWCODE'," + i + ")";
				Statement statement = conn.createStatement();
				statement.executeUpdate(SQL0);
				statement.executeUpdate(SQL1);
				statement.executeUpdate(SQL2);
				if(random.nextInt(31) == 1)
				{
					System.out.println("Kans 1 op 30");
					String SQL3 = "INSERT INTO klas VALUES (" + i + ",'DINF" + i + "', '20150101', '20160101')";
					statement.executeUpdate(SQL3);
					String SQL4 = "INSERT INTO klas_student VALUES (" + i + "," + i + "," + i + ")";
					statement.executeUpdate(SQL4);
					String SQL5 = "INSERT INTO module VALUES (" + i + ",'MODULE', 'MODULECODE1', '20150101', '20160101'," + i + ")";
					statement.executeUpdate(SQL5);
					
					if(random.nextInt(100) <= 15){
						System.out.println("Kans op 15%");
						String SQL6 = "INSERT INTO rooster VALUES (" + i + ", 'lokaal1', '20150101', '20160101'," + i + "," + i + "," + i + ")";
						statement.executeUpdate(SQL6);
					}
					
				}				
				conn.commit();				
				long eindTijd = System.currentTimeMillis();
				long duurInMS = eindTijd - beginTijd;
				total = total + duurInMS;
				
				System.out.println(name + " " + i + " insert succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
				Thread.sleep(100);
			}
				
			
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	
	private void thread2(Connection p_conn) throws SQLException{
		Connection conn = p_conn;
		Random random = new Random();
		float total = 1;
		int start = 0;
		
		String SQL = "SELECT MAX(id) FROM persoon";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) 
		{
			start = rs.getInt(1) + 3000;
		}
		try 
		{	for (int i = start; i < start + 600; i++){
				long beginTijd = System.currentTimeMillis();	
				String SQL0 = "INSERT INTO persoon VALUES (" + i + ",'"+randomString(10)+"', '"+randomString(10)+"', 'de', '06-04-1988', 'man', 'Spui', '2741', 'Waddinxveen', '12345678901')";
				String SQL1 = "INSERT INTO student VALUES (" + i + ",1234567," + i + ")";
				String SQL2 = "INSERT INTO docent VALUES (" + i + ",'MDWCODE'," + i + ")";
				Statement statement = conn.createStatement();
				statement.executeUpdate(SQL0);
				statement.executeUpdate(SQL1);
				statement.executeUpdate(SQL2);
				if(random.nextInt(31) == 1)
				{
					System.out.println("Kans 1 op 30");
					String SQL3 = "INSERT INTO klas VALUES (" + i + ",'DINF" + i + "', '20150101', '20160101')";
					statement.executeUpdate(SQL3);
					String SQL4 = "INSERT INTO klas_student VALUES (" + i + "," + i + "," + i + ")";
					statement.executeUpdate(SQL4);
					String SQL5 = "INSERT INTO module VALUES (" + i + ",'MODULE', 'MODULECODE1', '20150101', '20160101'," + i + ")";
					statement.executeUpdate(SQL5);
					
					if(random.nextInt(100) <= 15){
						System.out.println("Kans op 15%");
						String SQL6 = "INSERT INTO rooster VALUES (" + i + ", 'lokaal1', '20150101', '20160101'," + i + "," + i + "," + i + ")";
						statement.executeUpdate(SQL6);
					}
					
				}				
				conn.commit();				
				long eindTijd = System.currentTimeMillis();
				long duurInMS = eindTijd - beginTijd;
				total = total + duurInMS;
				
				System.out.println(name + " " + i + " insert succeeded " + "Query tijd in MS " + duurInMS + " Totale tijd is MS: " + total + " Gemiddelde tijd is MS: " + (total/i));
				Thread.sleep(100);
			}		
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rnd = new Random();	
	
	public String randomString(int len) {
		
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}		
	
	private Connection connect(int p_type) throws ClassNotFoundException, SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=AdvancedDatabase1;integratedSecurity=true;";
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(connectionUrl);
		switch(p_type){
			case 0:
				conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				break;
			case 1:
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				break;
			case 2:
				conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				break;
		}
		conn.setAutoCommit(false);
		return conn;
	}



}