package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
			connection.setAutoCommit(false);
			
			createTable(connection);
			insertData(connection);
			readData(connection);
			
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createTable(Connection connection) throws SQLException {
		String sqlDrop = "DROP TABLE IF EXISTS employees";
		String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY,"
				+ "name TEXT, address TEXT, salary REAL)";
		
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlDrop);
		statement.executeUpdate(sqlCreate);
		statement.close();
		connection.commit();
	}
	
	private static void insertData(Connection connection) throws SQLException {
		String sqlInsert = "INSERT INTO employees VALUES(1, 'Popescu Ion', 'Bucharest', 4000)";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlInsert);
		statement.close();
		
		String sqlInsertWithParams = "INSERT INTO employees VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = 
				connection.prepareStatement(sqlInsertWithParams);
		preparedStatement.setInt(1, 2);
		preparedStatement.setString(2, "Ionescu Vasile");
		preparedStatement.setString(3, "Brasov");
		preparedStatement.setDouble(4, 4500);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
		connection.commit();
	}
	
	private static void readData(Connection connection) throws SQLException {
		String sqlSelect = "SELECT * FROM employees";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlSelect);
		while(rs.next()) {
			int id = rs.getInt("id");
			System.out.println("id: " + id);
			String name = rs.getString(2);
			System.out.println("name: " + name);
			String address = rs.getString("address");
			System.out.println("address: " + address);
			double salary = rs.getDouble("salary");
			System.out.println("salary: " + salary);
		}
		rs.close();
		statement.close();
	}
}
