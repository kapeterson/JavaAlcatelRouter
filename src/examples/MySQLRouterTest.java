package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import router.alcatel.router.SRChassisObject;
import router.alcatel.router.SRSnmpPopulator;

public class MySQLRouterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			if (args.length < 3){
				System.out.println("Error you must supply <snmpcommunity> <user> <password> user /and password for kp109p database");
				return;
			}

			String comm = args[0];

			Properties connectionProps = new Properties();
			String userName = args[1];
			String psswd = args[2];
			
			connectionProps.put("user", userName);
			connectionProps.put("password", psswd);


			
			Connection conn = DriverManager.getConnection("jdbc:mysql://68.253.91.179:3306/kp109p", connectionProps);
			
			String query = "select * from nodes where ip REGEXP '[0-9]+.[0-9]+.[0-9]+.[0-9]+' and hostname like '%LVH11'";
			System.out.println("query = " + query);
			Statement stmt = conn.createStatement();
			
			ResultSet rs;
			rs = stmt.executeQuery(query);
			
		
			while ( rs.next()){
				String hostname = rs.getString("hostname");
				String ip = rs.getString("ip");
				System.out.println("Populating: " + hostname + ", " + ip);
				try {
				SRSnmpPopulator pop = new SRSnmpPopulator(ip, comm);
				pop.populateHardware();
				SRChassisObject router = pop.getRouter();
				System.out.println("System name = " + router.System.getHostName());
				} catch ( Exception e){
					System.out.println("ERROR: Could not populate host " + hostname);
				}
				
			}
			rs.close();
			conn.close();
		} catch (Exception ex ){
			System.out.println("Exception " + ex.getMessage());
		}
	}

}
