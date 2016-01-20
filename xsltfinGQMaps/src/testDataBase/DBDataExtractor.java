package testDataBase;

import java.sql.*;

public class DBDataExtractor {

	public ResultSet DBConnection(String testcase) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://52.23.175.4:3306/selenium_database", "root", "1");
		Statement st= con.createStatement();
		String query="select * from selenium_sample where tcid='"+testcase+"'";
	    ResultSet rs= st.executeQuery(query);
	    return rs;
	}
	
	
}
