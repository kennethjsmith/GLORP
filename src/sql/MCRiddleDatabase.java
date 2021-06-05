package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

// Trivia & Riddle credits: 
//	https://egypt.mrdonn.org/trivia.html
//	https://www.riddles.com/
//	https://conversationstartersworld.com/ancient-egypt-trivia-questions/
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
		  ResultSet rs = md.getTables(null, null, "mcriddles", null);
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
			  theStatement.executeUpdate("CREATE TABLE IF NOT EXISTS mcriddles ( question string, answer string, wrong_answer1 string, "
			  		+ "wrong_answer2 string, wrong_answer3 string, explanation string )");
			  
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('Lily is a lilypad in a small pond. Lilly doubles her size each day, "
			  		+ "On the 20th day she covers the whole pond. On what day was Lily half the size of the pond?', "
			  		+ " '19', '15', '10', '17')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('How many seconds are there in one year?', '12', '416853', '32536000', '92471900', "
			  		+ "'January 2nd, February 2nd, March 2nd, April 2nd, May 2nd, June 2nd, July 2nd, August 2nd, September 2nd, October 2nd, November 2nd, December 2nd.' )");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('What is the next letter in the sequence: bcdfgh__', 'j', 'm', 'i', 'r', 'this is a sequential list of consonants' )");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('Ancient Egypt was responsible for the earliest known peace treaty which was between Egypt and what group of people', 'The Hittites', 'The Villanovans', 'The Paracas', 'The Assyrians')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('The Great Pyramids of Giza consists of how many pyramids?', '3', '2', '5', '6')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('What did Pharaoh Pepi II cover slaves in to attract flies away from himself?', 'Honey', 'Agave', 'Maple Syrup', 'Molasses')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('How many years did King Tut rule?', '9 years', '25 years', '4 years', '15 years', 'He ruled from 1332-1323 BC during the Eighteenth Dynasty')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('How old was King Tut when he became pharaoh?', '9 years old', '17 years old', '25 years old', '45 years old')");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('How many items were found in King Tut’s tomb?', '5,398', '153', '38', '2560', 'King Tut’s tomb is among the best preserved and the artifacts found there are the most exhibited and traveled. Among the items were a solid gold coffin, the iconic face mask, a meteorite dagger, and fresh linen underwear. Even pharaohs need clean undies in the afterlife.' )");
			  theStatement.executeUpdate("INSERT INTO mcriddles VALUES('10th mc q.', 'right', 'wrong1', 'wrong2', 'wrong3')");
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
