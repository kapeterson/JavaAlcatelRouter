package examples;

public class ThreadedInterruptionTest {
	
	static void threadMessage(String msg){
		String threadName = Thread.currentThread().getName();
		System.out.format("%s %s%n", threadName, msg);
		
	}
	
	private static class MessageLoop 
		implements Runnable {
		
		public void run(){
			String mymsg = "Message";
			
			try {
				
				for ( int i = 0; i < 5; i++){
					Thread.sleep(4000);
					threadMessage("Sending " + mymsg + i);
				}
				
			} catch ( InterruptedException e){
				threadMessage("Wasn't quite done yet");
			}
		}
	}
	
	public static void main(String args[])
		throws InterruptedException {
		
		long patience = 1000 * 60 * 60;
		
		if ( args.length > 0){
			try {
				patience = Long.parseLong(args[0]) * 1000;
				
			} catch ( NumberFormatException e){
	             System.err.println("Argument must be an integer.");
	             System.exit(1);
			}
		}
		
		threadMessage("Starting message loop");
        long startTime = System.currentTimeMillis();

		Thread t = new Thread(new MessageLoop());
		t.start();
		
		while ( t.isAlive()){
			long diff = System.currentTimeMillis() - startTime;
			threadMessage("Waiting yo total = " + diff);
			t.join(1000);
			
			if (((System.currentTimeMillis() - startTime ) > patience ) && t.isAlive()) {
				threadMessage("Tired of waiting");
				t.interrupt();
				t.join();
			}
		}
		
		threadMessage("Finally");
	
	}
}