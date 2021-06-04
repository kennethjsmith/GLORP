package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class TFRiddleDatabase {
	private Connection myConnection;
	
	public TFRiddleDatabase() {
		myConnection = null;
		SQLiteDataSource ds = null;
		
		// Connect to database and check if the table exists already
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:tfriddles.db");
		  myConnection = ds.getConnection();
		  Statement statement = myConnection.createStatement();
		  statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
		  DatabaseMetaData md = myConnection.getMetaData();
		  ResultSet rs = md.getTables(null, null, "tfriddles", null);
		  rs.next();
		  if(!(rs.getRow() > 0)) addRiddles(statement);
		} catch(SQLException e) {
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
		closeConnection();
	}
		
	private void addRiddles(Statement theStatement) {
		SQLiteDataSource ds = null;
		try {
			  theStatement.executeUpdate("CREATE TABLE IF NOT EXISTS tfriddles ( question string, answer string )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The first question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The second question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The third question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
			  
			  theStatement.executeUpdate("insert into tfriddles values('The first question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The second question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The third question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
			  
			  theStatement.executeUpdate("insert into tfriddles values('The first question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The second question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The third question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
			  
			  theStatement.executeUpdate("insert into tfriddles values('The first question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The second question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The third question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
			  
			  theStatement.executeUpdate("insert into tfriddles values('The first question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The second question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The third question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
			  
			  theStatement.executeUpdate("insert into tfriddles values('The first question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The second question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The third question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
			  theStatement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
			  theStatement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		
		} catch(SQLException e) {	
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
	}
	
	public ResultSet getTFRiddleSet() {
		ResultSet rs = null;
		try {
			myConnection = DriverManager.getConnection("jdbc:sqlite:tfriddles.db");
			Statement statement = myConnection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			rs = statement.executeQuery("SELECT * FROM tfriddles ORDER BY RANDOM()");
		} catch (SQLException e) {
	        System.err.println(e.getMessage());
		}
		return rs;	
	}
	
	public void closeConnection() {
		try {
		    if(myConnection != null)
		      myConnection.close();
		  } catch(SQLException e) {
		    // connection close failed.
		        System.err.println(e.getMessage());
	      }
	}
}