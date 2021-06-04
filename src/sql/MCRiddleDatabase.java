package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class MCRiddleDatabase {
	private Connection myConnection;
	
	public MCRiddleDatabase() {
		myConnection = null;
		SQLiteDataSource ds = null;
		
		// Connect to database and check if the table exists already
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:mcriddles.db");
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
			  theStatement.executeUpdate("CREATE TABLE IF NOT EXISTS mcriddles ( question string, answer string, wrong_answer1 string, wrong_answer2 string, wrong_answer3 string )");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 1st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 2nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 3rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 4th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 5th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 6th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 7th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 8th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 9th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 10th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 11th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 12th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 13th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 14th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 15th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 16th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 17th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 18th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 19th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 20th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 21st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 22nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 23rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 24th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 25th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 26th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 27th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 28th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 29th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 30th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 1st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 2nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 3rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 4th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 5th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 6th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 7th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 8th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 9th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 10th mc question', 'right', 'wrong1', 'wrong2','wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 11th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 12th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 13th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 14th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 15th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 16th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 17th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 18th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 19th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 20th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 21st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 22nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 23rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 24th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 25th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 26th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 27th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 28th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 29th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 30th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 1st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 2nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 3rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 4th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 5th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 6th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 7th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 8th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 9th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 10th mc question', 'right', 'wrong1', 'wrong2','wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 11th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 12th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 13th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 14th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 15th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 16th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 17th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 18th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 19th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 20th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 21st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 22nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 23rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 24th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 25th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 26th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 27th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 28th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 29th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 30th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 1st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 2nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 3rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 4th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 5th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 6th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 7th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 8th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 9th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 10th mc question', 'right', 'wrong1', 'wrong2','wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 11th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 12th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 13th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 14th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 15th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 16th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 17th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 18th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 19th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 20th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 21st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 22nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 23rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 24th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 25th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 26th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 27th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 28th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 29th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 30th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 1st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 2nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 3rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 4th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 5th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 6th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 7th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 8th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 9th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 10th mc question', 'right', 'wrong1', 'wrong2','wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 11th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 12th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 13th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 14th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 15th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 16th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 17th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 18th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 19th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 20th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 21st mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 22nd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 23rd mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 24th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 25th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 26th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 27th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 28th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 29th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The 30th mc question', 'right', 'wrong1', 'wrong2', 'wrong3')");
		
		} catch(SQLException e) {	
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
	}
	
	public ResultSet getMCRiddleSet() {
		ResultSet rs = null;
		try {
			myConnection = DriverManager.getConnection("jdbc:sqlite:mcriddles.db");
			Statement statement = myConnection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			rs = statement.executeQuery("SELECT * FROM mcriddles ORDER BY RANDOM()");
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
