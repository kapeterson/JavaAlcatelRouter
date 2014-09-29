package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class SQLVHOClliQuery {
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

			
			Connection conn = DriverManager.getConnection("jdbc:mysql://68.253.91.181:3306/cacti", connectionProps);
			
			String query = "select * from v3_clli where vho_type='vho' or vho_type='vho,sho' or vho_type='noc' order by clli";
			Statement stmt = conn.createStatement();
			
			ResultSet rs;
			rs = stmt.executeQuery(query);
			
			
			while ( rs.next()){
				String clli_id = rs.getString("clli_id");
				String clli = rs.getString("clli");
				System.out.println(clli_id + " : " + clli);
			}
			
			rs.close();
			conn.close();
		} catch (Exception ex ){
			System.out.println("Exception " + ex.getMessage());
		}

	}
}
