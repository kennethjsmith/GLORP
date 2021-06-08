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
		  rs.close();

		} catch(SQLException e) {
		  // if the error message is "out of memory",
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
	    }
	}
		
	private void addRiddles(Statement theStatement) {
		try {
			  theStatement.executeUpdate("CREATE TABLE IF NOT EXISTS tfriddles ( question string, answer string, wrong_answer string, explanation string )");
			 
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('You live in a one story house made entirely of redwood. True or false: the stairs are red.', 'False', 'True', 'You live in a one-story house, there are no stairs!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Sphinx is the oldest known monumental sculpture in Egypt.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Christian cross was developed from the djed, an ancient Egyptian symbol.', 'False', 'True', 'The Christian cross is based on the ankh, the key of life. The djed is a pillar-like symbol.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was Egyptian.', 'False', 'True', 'Cleopatra was actually part of a long line of Greek Macedonians.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was famous for being one of the first members of the Ptolemaic dynasty to speak the Egyptian language.', 'True', 'False', 'Most people in the Ptolemaic dynasty were Greek.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians loved card games.', 'False', 'True', 'They loved board games, such as Senet, not card games!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women were not allowed to divorce their husbands.', 'False', 'True', 'While they may have been publicly and socially viewed as inferior to men, ancient Egyptian women enjoyed a great deal of legal and financial independence.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women made less than men when they worked outside of the home.', 'False', 'True', 'The ancient Egyptians were not barbarians! Women usually made the same pay as men.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian workers organized labor strikes.', 'True', 'False', 'When laborers were building the royal necropolis at Deir el-Medina and did not receive their usual payment of grain, they organized one of the first recorded strikes in history.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian pharos were usually obese.', 'True', 'False', 'Examinations of mummies have indicated that many Egyptian rulers were unhealthy and overweight, and even suffered from diabetes : (' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The pyramids were built by slaves.', 'False', 'True', 'They were built by a mix of skilled artisans and temporary hands, and some appear to have taken great pride in their craft.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('King Tut died of heart disease.', 'False', 'True', 'Evidence suggests he may have been killed by a hippopotamus' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians respected and revered all wild animals.', 'False', 'True', 'Okay so maybe they were a little barbaric. They hunted large african game for sport :′(' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Some Egyptian doctors had specialized fields of study.', 'True', 'False', 'These specialists even had specific names. Dentists were called “doctors of the tooth,” while the term for proctologists literally translates to “shepherd of the anus.”' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian police officers used trained dogs and monkeys while out on patrol.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Egyptians believed their makeup had magical healing powers.', 'True', 'False', 'They weren’t entirely wrong: Research has shown that the lead-based cosmetics worn along the Nile actually helped stave off eye infections!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Both men and women ancient Egyptians were known to wear copious amounts of makeup.', 'True', 'False', ' ' )");
		
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('You live in a one story house made entirely of redwood. True or false: the stairs are red.', 'False', 'True', 'You live in a one-story house, there are no stairs!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Sphinx is the oldest known monumental sculpture in Egypt.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Christian cross was developed from the djed, an ancient Egyptian symbol.', 'False', 'True', 'The Christian cross is based on the ankh, the key of life. The djed is a pillar-like symbol.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was Egyptian.', 'False', 'True', 'Cleopatra was actually part of a long line of Greek Macedonians.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was famous for being one of the first members of the Ptolemaic dynasty to speak the Egyptian language.', 'True', 'False', 'Most people in the Ptolemaic dynasty were Greek.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians loved card games.', 'False', 'True', 'They loved board games, such as Senet, not card games!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women were not allowed to divorce their husbands.', 'False', 'True', 'While they may have been publicly and socially viewed as inferior to men, ancient Egyptian women enjoyed a great deal of legal and financial independence.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women made less than men when they worked outside of the home.', 'False', 'True', 'The ancient Egyptians were not barbarians! Women usually made the same pay as men.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian workers organized labor strikes.', 'True', 'False', 'When laborers were building the royal necropolis at Deir el-Medina and did not receive their usual payment of grain, they organized one of the first recorded strikes in history.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian pharos were usually obese.', 'True', 'False', 'Examinations of mummies have indicated that many Egyptian rulers were unhealthy and overweight, and even suffered from diabetes : (' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The pyramids were built by slaves.', 'False', 'True', 'They were built by a mix of skilled artisans and temporary hands, and some appear to have taken great pride in their craft.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('King Tut died of heart disease.', 'False', 'True', 'Evidence suggests he may have been killed by a hippopotamus' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians respected and revered all wild animals.', 'False', 'True', 'Okay so maybe they were a little barbaric. They hunted large african game for sport :′(' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Some Egyptian doctors had specialized fields of study.', 'True', 'False', 'These specialists even had specific names. Dentists were called “doctors of the tooth,” while the term for proctologists literally translates to “shepherd of the anus.”' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian police officers used trained dogs and monkeys while out on patrol.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Egyptians believed their makeup had magical healing powers.', 'True', 'False', 'They weren’t entirely wrong: Research has shown that the lead-based cosmetics worn along the Nile actually helped stave off eye infections!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Both men and women ancient Egyptians were known to wear copious amounts of makeup.', 'True', 'False', ' ' )");
	   
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('You live in a one story house made entirely of redwood. True or false: the stairs are red.', 'False', 'True', 'You live in a one-story house, there are no stairs!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Sphinx is the oldest known monumental sculpture in Egypt.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Christian cross was developed from the djed, an ancient Egyptian symbol.', 'False', 'True', 'The Christian cross is based on the ankh, the key of life. The djed is a pillar-like symbol.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was Egyptian.', 'False', 'True', 'Cleopatra was actually part of a long line of Greek Macedonians.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was famous for being one of the first members of the Ptolemaic dynasty to speak the Egyptian language.', 'True', 'False', 'Most people in the Ptolemaic dynasty were Greek.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians loved card games.', 'False', 'True', 'They loved board games, such as Senet, not card games!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women were not allowed to divorce their husbands.', 'False', 'True', 'While they may have been publicly and socially viewed as inferior to men, ancient Egyptian women enjoyed a great deal of legal and financial independence.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women made less than men when they worked outside of the home.', 'False', 'True', 'The ancient Egyptians were not barbarians! Women usually made the same pay as men.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian workers organized labor strikes.', 'True', 'False', 'When laborers were building the royal necropolis at Deir el-Medina and did not receive their usual payment of grain, they organized one of the first recorded strikes in history.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian pharos were usually obese.', 'True', 'False', 'Examinations of mummies have indicated that many Egyptian rulers were unhealthy and overweight, and even suffered from diabetes : (' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The pyramids were built by slaves.', 'False', 'True', 'They were built by a mix of skilled artisans and temporary hands, and some appear to have taken great pride in their craft.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('King Tut died of heart disease.', 'False', 'True', 'Evidence suggests he may have been killed by a hippopotamus' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians respected and revered all wild animals.', 'False', 'True', 'Okay so maybe they were a little barbaric. They hunted large african game for sport :′(' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Some Egyptian doctors had specialized fields of study.', 'True', 'False', 'These specialists even had specific names. Dentists were called “doctors of the tooth,” while the term for proctologists literally translates to “shepherd of the anus.”' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian police officers used trained dogs and monkeys while out on patrol.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Egyptians believed their makeup had magical healing powers.', 'True', 'False', 'They weren’t entirely wrong: Research has shown that the lead-based cosmetics worn along the Nile actually helped stave off eye infections!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Both men and women ancient Egyptians were known to wear copious amounts of makeup.', 'True', 'False', ' ' )");

			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('You live in a one story house made entirely of redwood. True or false: the stairs are red.', 'False', 'True', 'You live in a one-story house, there are no stairs!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Sphinx is the oldest known monumental sculpture in Egypt.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Christian cross was developed from the djed, an ancient Egyptian symbol.', 'False', 'True', 'The Christian cross is based on the ankh, the key of life. The djed is a pillar-like symbol.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was Egyptian.', 'False', 'True', 'Cleopatra was actually part of a long line of Greek Macedonians.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Cleopatra was famous for being one of the first members of the Ptolemaic dynasty to speak the Egyptian language.', 'True', 'False', 'Most people in the Ptolemaic dynasty were Greek.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians loved card games.', 'False', 'True', 'They loved board games, such as Senet, not card games!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women were not allowed to divorce their husbands.', 'False', 'True', 'While they may have been publicly and socially viewed as inferior to men, ancient Egyptian women enjoyed a great deal of legal and financial independence.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptian women made less than men when they worked outside of the home.', 'False', 'True', 'The ancient Egyptians were not barbarians! Women usually made the same pay as men.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian workers organized labor strikes.', 'True', 'False', 'When laborers were building the royal necropolis at Deir el-Medina and did not receive their usual payment of grain, they organized one of the first recorded strikes in history.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian pharos were usually obese.', 'True', 'False', 'Examinations of mummies have indicated that many Egyptian rulers were unhealthy and overweight, and even suffered from diabetes : (' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The pyramids were built by slaves.', 'False', 'True', 'They were built by a mix of skilled artisans and temporary hands, and some appear to have taken great pride in their craft.' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('King Tut died of heart disease.', 'False', 'True', 'Evidence suggests he may have been killed by a hippopotamus' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Ancient Egyptians respected and revered all wild animals.', 'False', 'True', 'Okay so maybe they were a little barbaric. They hunted large african game for sport :′(' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Some Egyptian doctors had specialized fields of study.', 'True', 'False', 'These specialists even had specific names. Dentists were called “doctors of the tooth,” while the term for proctologists literally translates to “shepherd of the anus.”' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Egyptian police officers used trained dogs and monkeys while out on patrol.', 'True', 'False', ' ' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('The Egyptians believed their makeup had magical healing powers.', 'True', 'False', 'They weren’t entirely wrong: Research has shown that the lead-based cosmetics worn along the Nile actually helped stave off eye infections!' )");
			  theStatement.executeUpdate("INSERT INTO tfriddles VALUES('Both men and women ancient Egyptians were known to wear copious amounts of makeup.', 'True', 'False', ' ' )");

		
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