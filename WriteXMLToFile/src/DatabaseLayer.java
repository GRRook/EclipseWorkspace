
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class DatabaseLayer {	
	public void create(List<String> queries) throws SQLException{
		Database db = new Database();
		
		
		Connection connection = db.connect();
		Statement statement = connection.createStatement();

		for (String query : queries) {
			statement.addBatch(query);
		}
		statement.executeBatch();
		statement.close();
		connection.close();
	}	
	public String read() throws SQLException{
		Database db = new Database();
		Connection connection = db.connect();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from public.table1;");
		while (rs.next())
		{
		   System.out.print("Column 1 returned ");
		   System.out.println(rs.getString(1));
		} 
		rs.close();
		st.close();		
		connection.close();
		return "";
	}
	
	public void update(){
		
	}
	public void delete(){
		
	}

	
}
