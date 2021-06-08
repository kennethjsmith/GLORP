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
		  rs.close();
		} catch(SQLException e) {
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
	}
		
	private void addRiddles(Statement theStatement) {
		try {
			  theStatement.executeUpdate("CREATE TABLE IF NOT EXISTS fbriddles ( question string, answer string, explanation string )");
			  
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('A sphere has three, a circle has two, and a point has zero. What is it?', 'Dimension', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The more you take, the more you leave behind. What am I?', 'Footsteps', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 8 letter word can have a letter taken away and it still makes a word. Take another letter away and it still makes a word. Keep on doing that until you have one letter left. What is the word?', 'Starting', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has a head, a tail, is brown, and has no legs?', 'Penny', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has many keys, but cant even open a single door?', 'Piano', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Re-arrange the letters, O O U S W T D N E J R, to spell just one word. What is it?', 'Just one word', 'JUST ONE WORD!' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What can point in every direction but cant reach the destination by itself?', 'Finger', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('My life can be measured in hours, I serve by being devoured. Thin, I am quick. Fat, I am slow. Wind is my foe. What am I?', 'Candle', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What is black when you buy it, red when you use it, and gray when you throw it away?', 'Charcoal', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Poor people have it. Rich people need it. If you eat it you die. What is it?', 'Nothing', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 7 letter word is spelled the same way backwards and forwards?', 'Racecar', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('I am full of holes but I can still hold water. What am I?', 'Sponge', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 5 letter word typed in all capital letters can be read the same upside down?', 'SWIMS', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What travels around the world but stays in one spot?', 'Stamp', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Im white, and used for cutting and grinding. When Im damaged, humans usually remove me or fill me. "
			  		+ "For most animals I am a useful tool. What am I?', 'Tooth', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has six faces, but does not wear makeup, has twenty-one eyes, but cannot see? What is it?', 'Dice', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('I am a word of six; my first three letters refer to an automobile; my last three letters refer to a household animal; "
			  		+ "my first four letters is a fish; I am found in your room. What am I?', 'Carpet', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What number would you have to count before you would use the letter A in the English language spelling of a whole number.', '1000', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 4-letter capitalized word can be written forward, backward or upside down, and can still be read from left to right?', 'NOON', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What kind of goose fights with snakes?', 'mongoose', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What word is always pronounced wrong?', 'Wrong', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Only one color, but not one size, Stuck at the bottom, yet easily flies."
			  		+ "Present in sun, but not in rain. Doing no harm, and feeling no pain. What is it?', 'Shadow', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What English word retains the same pronunciation, even after you take away four of its five letters?', 'Queue', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('A time when they are green, a time when theyre brown, but both of these times, cause me to frown. But just in between, for a very short while, Theyre perfect and yellow and cause me to smile!\n"
			  		+ "What are they?', 'Bananas', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('There is an ancient invention, still used in some parts of the world today, that allows people to see through walls. What is it?', 'Window', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What kind of nut has no shell?', 'Doughnut', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What day would yesterday be if Thursday was four days before the day after tommorow?', 'Friday', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('When the ancient Egyptians held a party, what did they call it?', 'a House of Beer', ' ' )");
			 
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('A sphere has three, a circle has two, and a point has zero. What is it?', 'Dimension', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The more you take, the more you leave behind. What am I?', 'Footsteps', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 8 letter word can have a letter taken away and it still makes a word. Take another letter away and it still makes a word. Keep on doing that until you have one letter left. What is the word?', 'Starting', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has a head, a tail, is brown, and has no legs?', 'Penny', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has many keys, but cant even open a single door?', 'Piano', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Re-arrange the letters, O O U S W T D N E J R, to spell just one word. What is it?', 'Just one word', 'JUST ONE WORD!' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What can point in every direction but cant reach the destination by itself?', 'Finger', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('My life can be measured in hours, I serve by being devoured. Thin, I am quick. Fat, I am slow. Wind is my foe. What am I?', 'Candle', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What is black when you buy it, red when you use it, and gray when you throw it away?', 'Charcoal', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Poor people have it. Rich people need it. If you eat it you die. What is it?', 'Nothing', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 7 letter word is spelled the same way backwards and forwards?', 'Racecar', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('I am full of holes but I can still hold water. What am I?', 'Sponge', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 5 letter word typed in all capital letters can be read the same upside down?', 'SWIMS', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What travels around the world but stays in one spot?', 'Stamp', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Im white, and used for cutting and grinding. When Im damaged, humans usually remove me or fill me. "
			  		+ "For most animals I am a useful tool. What am I?', 'Tooth', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has six faces, but does not wear makeup, has twenty-one eyes, but cannot see? What is it?', 'Dice', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('I am a word of six; my first three letters refer to an automobile; my last three letters refer to a household animal; "
			  		+ "my first four letters is a fish; I am found in your room. What am I?', 'Carpet', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What number would you have to count before you would use the letter A in the English language spelling of a whole number.', '1000', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 4-letter capitalized word can be written forward, backward or upside down, and can still be read from left to right?', 'NOON', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What kind of goose fights with snakes?', 'mongoose', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What word is always pronounced wrong?', 'Wrong', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Only one color, but not one size, Stuck at the bottom, yet easily flies."
			  		+ "Present in sun, but not in rain. Doing no harm, and feeling no pain. What is it?', 'Shadow', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What English word retains the same pronunciation, even after you take away four of its five letters?', 'Queue', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('A time when they are green, a time when theyre brown, but both of these times, cause me to frown. But just in between, for a very short while, Theyre perfect and yellow and cause me to smile!\n"
			  		+ "What are they?', 'Bananas', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('There is an ancient invention, still used in some parts of the world today, that allows people to see through walls. What is it?', 'Window', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What kind of nut has no shell?', 'Doughnut', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What day would yesterday be if Thursday was four days before the day after tommorow?', 'Friday', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('When the ancient Egyptians held a party, what did they call it?', 'a House of Beer', ' ' )");
			  
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('A sphere has three, a circle has two, and a point has zero. What is it?', 'Dimension', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('The more you take, the more you leave behind. What am I?', 'Footsteps', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 8 letter word can have a letter taken away and it still makes a word. Take another letter away and it still makes a word. Keep on doing that until you have one letter left. What is the word?', 'Starting', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has a head, a tail, is brown, and has no legs?', 'Penny', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has many keys, but cant even open a single door?', 'Piano', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Re-arrange the letters, O O U S W T D N E J R, to spell just one word. What is it?', 'Just one word', 'JUST ONE WORD!' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What can point in every direction but cant reach the destination by itself?', 'Finger', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('My life can be measured in hours, I serve by being devoured. Thin, I am quick. Fat, I am slow. Wind is my foe. What am I?', 'Candle', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What is black when you buy it, red when you use it, and gray when you throw it away?', 'Charcoal', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Poor people have it. Rich people need it. If you eat it you die. What is it?', 'Nothing', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 7 letter word is spelled the same way backwards and forwards?', 'Racecar', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('I am full of holes but I can still hold water. What am I?', 'Sponge', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 5 letter word typed in all capital letters can be read the same upside down?', 'SWIMS', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What travels around the world but stays in one spot?', 'Stamp', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Im white, and used for cutting and grinding. When Im damaged, humans usually remove me or fill me. "
			  		+ "For most animals I am a useful tool. What am I?', 'Tooth', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What has six faces, but does not wear makeup, has twenty-one eyes, but cannot see? What is it?', 'Dice', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('I am a word of six; my first three letters refer to an automobile; my last three letters refer to a household animal; "
			  		+ "my first four letters is a fish; I am found in your room. What am I?', 'Carpet', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What number would you have to count before you would use the letter A in the English language spelling of a whole number.', '1000', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What 4-letter capitalized word can be written forward, backward or upside down, and can still be read from left to right?', 'NOON', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What kind of goose fights with snakes?', 'mongoose', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What word is always pronounced wrong?', 'Wrong', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('Only one color, but not one size, Stuck at the bottom, yet easily flies."
			  		+ "Present in sun, but not in rain. Doing no harm, and feeling no pain. What is it?', 'Shadow', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What English word retains the same pronunciation, even after you take away four of its five letters?', 'Queue', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('A time when they are green, a time when theyre brown, but both of these times, cause me to frown. But just in between, for a very short while, Theyre perfect and yellow and cause me to smile!\n"
			  		+ "What are they?', 'Bananas', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('There is an ancient invention, still used in some parts of the world today, that allows people to see through walls. What is it?', 'Window', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What kind of nut has no shell?', 'Doughnut', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('What day would yesterday be if Thursday was four days before the day after tommorow?', 'Friday', ' ' )");
			  theStatement.executeUpdate("INSERT INTO fbriddles VALUES('When the ancient Egyptians held a party, what did they call it?', 'a House of Beer', ' ' )");
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
