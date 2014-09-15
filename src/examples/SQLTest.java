package examples;
import java.sql.*;
import java.util.Properties;


public class SQLTest {

	public static void main(String[] args) {
		try {
			
			if (args.length < 2){
				System.out.println("Error you must supply user /and password for kp109p database");
				return;
			}

			Properties connectionProps = new Properties();
			String userName = args[0];
			String psswd = args[1];
			
			connectionProps.put("user", userName);
			connectionProps.put("password", psswd);

			
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
