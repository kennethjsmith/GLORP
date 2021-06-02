package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RiddleDatabase {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			// creates database connection
			connection = DriverManager.getConnection("jdbc:sqlite:riddles.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 seconds
			statement.executeUpdate("drop table if exists riddle");
			statement.executeUpdate("create table riddle (question string, type string, answer string");
			statement.executeUpdate("insert into riddle value('This is the first riddle question', 'multiple choice', 'true')");
		ResultSet rs = statement.executeQuery("select * from riddle");
		while(rs.next()) {
			System.out.println("riddle question: " + rs.getString("question"));
			System.out.println("Question type: " + rs.getString("type"));
			System.out.println("Answer: " + rs.getString("answer"));
		
		}
		
		} catch (SQLException e){
			System.err.println(e.getMessage());
			
		} finally {
			try {
				if(connection != null) connection.close();
			} catch (SQLException e) {
		        System.err.println(e.getMessage());
			}
			
		}
	}
}
