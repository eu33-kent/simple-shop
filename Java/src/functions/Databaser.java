package functions;

import java.sql.*;
import java.util.ArrayList;

public class Databaser {

	private static ResultSet result;

	/**
	 * Checks the user's credentials to see if they're valid
	 * @param login the login the user entered
	 * @param pass the password the user entered
	 * @return whether the login was successful
	 */
	public static boolean login(String login, String pass) {
		String hashed = Hash.getHash(pass);
		if (query("SELECT * FROM users WHERE login = '" + login + "' AND passHash = '" + hashed + "';").size() < 1) return false;
		return true;
	}
	
	/**
	 * Attempts to register a new user
	 * @param login the login the user wants to register with
	 * @param pass the password the user wants to register with
	 * @return a string indicating the reason for failure, or "success"
	 */
	public static String register(String login, String pass, String first, String last) {
		if (login.length() < 5) {
			return "Your user login is too short.";
		}
		if (pass.length() < 8) {
			return "Your password is too short.";
		}
		if (query("SELECT * FROM users WHERE login = '" + login + "';").size() > 0) {
			return "This user already exists.";
		}

		String hashed = Hash.getHash(pass);
		Boolean success = modify(
			"INSERT INTO users (login, passHash, firstName, lastName)"
			+ "VALUES ('" + login + "', '" + hashed + "', '" + first + "', '" + last + "')"
		); // add new user to database
		return success ? "success" : "fail";
	}

	/**
	 * Queries the database and returns the results
	 * @param query the SQL query as a string
	 * @return an ArrayList of rows with a nested ArrayList of strings for each value in the row
	 */
	public static ArrayList<ArrayList<String>> query(String query) { // return query results
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/simpleShop", "root", "pass");
			result = connect.createStatement().executeQuery(query);

			ResultSetMetaData rsmd = result.getMetaData();
			ArrayList<String> columns = new ArrayList<String>(); // stores the names of columns in the table
			for (int i = 1; i <= rsmd.getColumnCount(); i++) { // iterate over columns in table
				columns.add(rsmd.getColumnLabel(i));
			}

			while (result.next()) { // iterates over the returned rows
				ArrayList<String> curr = new ArrayList<String>();
				for (int j = 1; j <= columns.size(); j++) {
					curr.add(result.getString(j));
				}
				results.add(curr);
			}
			// System.out.println(results);

			// close the database connection
			connect.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	/**
	 * Allows insertion to, updating, or deletion from the database
	 * @param query the SQL query to execute
	 * @return true if successful, false if not
	 */
	public static boolean modify(String query) {
		Connection connect;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/simpleShop", "root", "pass");
			connect.createStatement().executeUpdate(query);
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the names of the columns in the query's resulting table
	 * @param query the SQL query to execute
	 * @return the names of the columns
	 */
	public static ArrayList<String> getColumns(String query) {
		ArrayList<String> columns = new ArrayList<String>();
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/simpleShop", "root", "pass");
			result = connect.createStatement().executeQuery(query);
			
			ResultSetMetaData rsmd = result.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) { // iterate over columns in table
				columns.add(rsmd.getColumnLabel(i));
			}
			// close the database connection
			connect.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columns;
	}
	
	/**
	 * A method for logging actions taken by a user
	 * @param pid the user's ID
	 * @param action the action the user just took
	 * @return whether the query succeeded or failed
	 */
	public static boolean log(String uid, String action) {
		return modify("INSERT INTO logs VALUES ('" + action + "', CURRENT_TIMESTAMP, " + uid + ")");
	}
}