package examples;


import java.lang.InterruptedException;

public class ThreadSleepTest {
	public static void main(String args[]) 
			throws InterruptedException{
		
		String tstring = "test";
		System.out.println("Testing threaded sleep");
		
		for (int i = 0; i < 4; i++){
			// pause for 4 seconds
			Thread.sleep(4000);
			System.out.println(tstring + " " + i);
		}
			
	}
}
