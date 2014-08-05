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
		
		public MessageLoop(ResultSet rs,  String community){
			rSet = rs;
			comm = community;
			startTime = System.nanoTime();
			router = new SRChassisObject();
		}
		public void run(){
		    String tname = Thread.currentThread().getName();
			try {
			
				while ( rSet.next()){
					
					String hn = rSet.getString("hostname");

					String host = rSet.getString("ip");
					pop = new SRSnmpPopulator(host, comm);
					
					pop.populateHostName();
					//pop.populateHardware();

					router = pop.getRouter();
					
					if ( pop.hadConnectionError())
						System.out.println("Had connection error to " + hn + " ip =" + host);
					else
						System.out.println("System name = " + router.System.getHostName() + " in thread " + tname);
					
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
		try {
			if (args.length < 1){
				System.out.println("Error you must supply an snmp community");
				return;
			}
			String comm = args[0];

			Properties connectionProps = new Properties();

			connectionProps.put("user", "kp109p");
			connectionProps.put("password", "uv3rs3");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://68.253.91.179:3306/kp109p", connectionProps);
			
			String query = "select * from nodes where ip REGEXP '[0-9]+.[0-9]+.[0-9]+.[0-9]+' order by hostname";
			System.out.println("query = " + query);
			Statement stmt = conn.createStatement();

			ResultSet rs;
			rs = stmt.executeQuery(query);

			int tcount = 25;
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
