package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class FBRiddleDatabase {
	private Connection myConnection;
	
	public FBRiddleDatabase() {
		myConnection = null;
		SQLiteDataSource ds = null;
		
		// Connect to database and check if the table exists already
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:fbriddles.db");
		  myConnection = ds.getConnection();
		  Statement statement = myConnection.createStatement();
		  statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
		  DatabaseMetaData md = myConnection.getMetaData();
		  ResultSet rs = md.getTables(null, null, "fbriddles", null);
		  rs.next();
		  if(!(rs.getRow() > 0)) addRiddles(statement);
		} catch(SQLException e) {
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
		//closeConnection();
	}
		
	private void addRiddles(Statement theStatement) {
		SQLiteDataSource ds = null;
		try {
			  theStatement.executeUpdate("CREATE TABLE IF NOT EXISTS fbriddles ( question string, answer string )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 1st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 2nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 3rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 4th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 5th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 6th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 7th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 8th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 9th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 10th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 11th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 12th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 13th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 14th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 15th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 16th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 17th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 18th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 19th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 20th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 21st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 22nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 23rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 24th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 25th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 26th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 27th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 28th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 29th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 30th fb question', 'right' )");
			  
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 1st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 2nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 3rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 4th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 5th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 6th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 7th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 8th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 9th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 10th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 11th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 12th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 13th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 14th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 15th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 16th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 17th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 18th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 19th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 20th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 21st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 22nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 23rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 24th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 25th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 26th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 27th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 28th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 29th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 30th fb question', 'right' )");
			  
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 1st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 2nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 3rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 4th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 5th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 6th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 7th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 8th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 9th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 10th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 11th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 12th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 13th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 14th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 15th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 16th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 17th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 18th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 19th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 20th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 21st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 22nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 23rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 24th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 25th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 26th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 27th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 28th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 29th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 30th fb question', 'right' )");
			  
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 1st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 2nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 3rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 4th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 5th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 6th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 7th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 8th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 9th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 10th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 11th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 12th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 13th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 14th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 15th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 16th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 17th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 18th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 19th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 20th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 21st fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 22nd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 23rd fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 24th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 25th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 26th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 27th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 28th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 29th fb question', 'right' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The 30th fb question', 'right' )");
		
		} catch(SQLException e) {	
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
	}
	
	public ResultSet getFBRiddleSet() {
		ResultSet rs = null;
		try {
			myConnection = DriverManager.getConnection("jdbc:sqlite:fbriddles.db");
			Statement statement = myConnection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			rs = statement.executeQuery("SELECT * FROM fbriddles ORDER BY RANDOM()");
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
