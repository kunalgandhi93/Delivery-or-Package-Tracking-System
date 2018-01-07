package application;

import java.sql.*;

public class DataConnection {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public Connection getConnection() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/Fedex","root", null);
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connect;
	}
	
	public void closeConnection()
	{
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
