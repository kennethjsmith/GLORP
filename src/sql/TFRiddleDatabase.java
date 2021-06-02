package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Riddle;

public class TFRiddleDatabase {
	private Connection myConnection;
	
	public TFRiddleDatabase() {
		addRiddles();
		myConnection = null;
	}
		
	private void addRiddles() {
		
		try {
		  // create a database connection
		  myConnection = DriverManager.getConnection("jdbc:sqlite:tfriddles.db");
		  Statement statement = myConnection.createStatement();
		  statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
		  statement.executeUpdate("drop table if exists tfriddles");
		  statement.executeUpdate("create table tfriddles (question string, answer string)");
		  statement.executeUpdate("insert into tfriddles values('The first question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The second question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The third question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		  
		  statement.executeUpdate("insert into tfriddles values('The first question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The second question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The third question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		  
		  statement.executeUpdate("insert into tfriddles values('The first question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The second question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The third question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		  
		  statement.executeUpdate("insert into tfriddles values('The first question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The second question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The third question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		  
		  statement.executeUpdate("insert into tfriddles values('The first question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The second question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The third question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		  
		  statement.executeUpdate("insert into tfriddles values('The first question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The second question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The third question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventh question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The eighth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The ninth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The tenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eleventh question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twelfth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The thirteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The fourteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The fifteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The sixteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The seventeenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The eighteenth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The nineteenth question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The twentieth question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 21st question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 22nd question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 23rd question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 24th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 25th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 26th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 27th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 28th question', 'true')");
		  statement.executeUpdate("insert into tfriddles values('The 29th question', 'false')");
		  statement.executeUpdate("insert into tfriddles values('The 30th question', 'true')");
		} catch(SQLException e) {
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
//		} finally {
//		  try {
//		    if(myConnection != null)
//		      myConnection.close();
//		  } catch(SQLException e) {
//		    // connection close failed.
//		        System.err.println(e.getMessage());
//	      }
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
}