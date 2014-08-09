package examples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import alcatel.router.SRChassisObject;
import alcatel.router.SRSnmpPopulator;


public class ThreadedMySqlPopulatorTest {


	
	static void threadMessage(String msg){
		String threadName = Thread.currentThread().getName();
		System.out.format("%s %s%n", threadName, msg);
		
	
	}
	

	
	private static class MessageLoop 
		implements Runnable {
	
		ResultSet rSet;
		String comm;
		long startTime;
		SRChassisObject router;
		SRSnmpPopulator pop;
		private static Properties cProp = null;
		private static Connection conn = null;
		private static Statement statement = null;
		
		public void insertSN(Integer reportid, String ip, String name, String chassistype, String model, String objecttype, Integer slot, Integer complex, String cardtype, 
				String pn, String sn, String md){
			try {
				String query = "insert into java_sn (report_id, ip, host, chassis_type, model, object_type, slot, complex, card_type, ";
				String pt2 =  String.format("pn, sn, manufacture_date ) values('%d', '%s', '%s', '%s', '%s', '%s', '%d', '%d', '%s', '%s', '%s', '%s');", reportid, ip, name, chassistype, model, objecttype, slot, complex, cardtype, pn, sn, md);
				query = query + pt2;
				//System.out.println(query);
				statement.executeUpdate(query);
			
			} catch (Exception er){
				System.out.println("Error exeucting insert sn query: " + er.getMessage());
				System.exit(1);
			}
		}
		
		public MessageLoop(ResultSet rs,  String community){
			rSet = rs;
			comm = community;
			startTime = System.nanoTime();
			router = new SRChassisObject();
			if ( cProp == null) {
				cProp = new Properties();
				cProp.put("user", "kp109p");
				cProp.put("password", "uv3rs3");
				
			}
			
			try {
			if ( conn == null) {
				conn = DriverManager.getConnection("jdbc:mysql://68.253.91.179:3306/kp109p", cProp);
				statement = conn.createStatement();

			}
			
			} catch ( Exception e){
				System.out.println("ERROR CREATING CONNECTION IN TREHAD");
				System.exit(1);
			}

		}
		
		public void run(){
		    String tname = Thread.currentThread().getName();
			try {
			
				while ( rSet.next()){
					
					String hn = rSet.getString("hostname");

					String host = rSet.getString("ip");
					pop = new SRSnmpPopulator(host, comm);
					
					pop.populateHostName();
					pop.populateHardware();

					router = pop.getRouter();
					
					if ( pop.hasConnectionError()) {
						System.out.println("Had connection error to " + hn + " ip =" + host);
						this.insertSN(1, host, hn, "ERROR", "ERROR", "ERROR", 1, 1, "ERROR", "ERROR", "ERROR", "ERROR");
					} else {
						System.out.println("Reading host = " + router.System.getHostName() + " in thread " + tname);
						this.insertSN(1, host, hn, "7750", "CHASSIS", Thread.currentThread().getName(), 1, 1, "NA", router.getSerialNumber(), router.getPartNumber(), router.getManufactureDate());
					}
					
					pop.close();
				}

				long totalTime = ( System.nanoTime() - startTime );
				
				System.out.println("Total time = " + totalTime + " nanosecs");
				totalTime = totalTime / 1000000 / 1000;
				System.out.println("Total time = " + totalTime + "s to read card types in thread " + tname);
				
			} catch ( Exception e){
				threadMessage("Wasn't quite done yet: " + e.getMessage());
			}
		}
	}	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties connectionProps =  new Properties();
		connectionProps.put("user", "kp109p");
		connectionProps.put("password", "uv3rs3");
		
		try {
			if (args.length < 1){
				System.out.println("Error you must supply an snmp community");
				return;
			}
			String comm = args[0];


			
			Connection conn = DriverManager.getConnection("jdbc:mysql://68.253.91.179:3306/kp109p", connectionProps);
			
			//String query = "select * from nodes where ip REGEXP '[0-9]+.[0-9]+.[0-9]+.[0-9]+' order by hostname";
			String query = "select * from nodes where ip REGEXP '[0-9]+.[0-9]+.[0-9]+.[0-9]+' order by hostname limit 5";

			System.out.println("query = " + query);
			Statement stmt = conn.createStatement();

			ResultSet rs;
			rs = stmt.executeQuery(query);

			int tcount = 50;
			Thread[] tlist = new Thread[tcount];
			
			
			
			int i = 0;
			while( i < tcount){
				tlist[i] = new Thread(new MessageLoop(rs, comm));
			   i++;
			   
			}
			
			i = 0;
			while ( i < tcount){
				tlist[i].start();
				i++;
			}
			

			
		} catch (Exception ex ){
			System.out.println("Exception " + ex.getMessage());
		}
	}

}
