package examples;
import java.sql.*;
import java.util.Properties;


public class SQLTest {

	public static void main(String[] args) {
		try {
			
			Properties connectionProps = new Properties();

			connectionProps.put("user", "kp109p");
			connectionProps.put("password", "uv3rs3");

			
			Connection conn = DriverManager.getConnection("jdbc:mysql://68.253.91.179:3306/kp109p", connectionProps);
			
			String query = "select * from nodes";
			Statement stmt = conn.createStatement();
			
			ResultSet rs;
			rs = stmt.executeQuery(query);
			
		
			while ( rs.next()){
				String hostname = rs.getString("hostname");
				String ip = rs.getString("ip");
				System.out.println(hostname + ", " + ip);
			}
			rs.close();
			conn.close();
		} catch (Exception ex ){
			System.out.println("Exception " + ex.getMessage());
		}

	}

}
