package connection;

import java.sql.*;

import org.w3c.dom.Document;


/**
 * 
 * Class prepared to conecct to a database
 * @author Aitor Angulo
 *
 */
public class MySqlAccess {
	
	/**
	 * Gets connection class in order to access to database
	 */
	public static Connection getConnection(Document config) {
		
		String driver 		= config.getElementsByTagName("driver").item(0).getTextContent();
		String url 			= config.getElementsByTagName("url").item(0).getTextContent();
		String database 	= config.getElementsByTagName("database").item(0).getTextContent();
		String user			= config.getElementsByTagName("user").item(0).getTextContent();
		String pwd  		= config.getElementsByTagName("password").item(0).getTextContent();
		
		String connectName = driver + "://" + url + database + "?characterEncoding=latin1";
		
		Connection conn = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectName, user, pwd);
			
			return conn;
	
		} catch(SQLException | ClassNotFoundException e) {
		   System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	/**
	 * Returns the resultSet from a query
	 * @param conn
	 * @param query
	 * @param fields
	 * @return
	 */
	public static ResultSet proccessQuery(Connection conn, String query)  {
		//List<String> rows = new ArrayList<String>();
		
		Statement st;
		ResultSet result = null;
		
		try {
			st = conn.createStatement();
			result 	= st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		return result;
		
		
	}

	/**
	 * Closes current connection
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if(conn == null) return;
		
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Closes current result set
	 * @param conn
	 */
	public static void closeResultSet(ResultSet rs) {
		if(rs == null) return;
		
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

}
