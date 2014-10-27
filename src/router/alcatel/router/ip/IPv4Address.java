package router.alcatel.router.ip;

import java.util.regex.*;

public class IPv4Address extends IPAddress {

	/** Value of the ip addres in long format.  Using long instead of integer so that sign isn't messed up **/
	protected long loweraddress = 0;
	
	/** Host string of the IP address in dotted decimal string format **/
	protected String hostString = "";
	
	/** netmask in bit length **/
	protected int netmask = 0;
	
	/** netmask as a bitmask in binary formatted integer **/
	protected int bitmask = 0;
	
	/** REgular expression to match ip address format **/
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	
	/** Compiled regex pattern for Ip address **/
	protected Pattern ipPattern = Pattern.compile(IPADDRESS_PATTERN);
	
	public IPv4Address(String ipAddress, String mask){
		
		if ( !validateIP(ipAddress)){
			System.out.println("Error with ip addres ");
			System.exit(1);
		} 
		
		/** Convert the ip address to long **/
		this.loweraddress = convertIPToLong(ipAddress);
		this.hostString = ipAddress;
		
		this.netmask = Integer.parseInt(mask);
		
		// Conver the bitmask to binary encoded integer
		if (this.netmask > 0) {
			this.bitmask = 0x01;
			this.bitmask = this.bitmask << 31;
			this.bitmask = this.bitmask >> 31;
			//System.out.println("Full bitmask = " + Integer.toBinaryString(this.bitmask));
			
			int shiftamount = ( 32 - this.netmask );
			this.bitmask = (this.bitmask >> (shiftamount ) ) << ( shiftamount );
			this.bitmask = this.bitmask & (( 0x01 << 31) >> 31);
		}
		//System.out.println("init bitmask to " + Integer.toBinaryString(this.bitmask) + " for " + ipAddress + "/" + mask);
	}
	
	
	/** conver the Dotted decimal ip address to a binary encoded long **/
	private long convertIPToLong(String ipstring){
		
		Matcher matcher = ipPattern.matcher(ipstring);
		long address = 0;
		if (matcher.find()){
			
			
			long o1 = Long.parseLong(matcher.group(1));
			long o2 = Long.parseLong(matcher.group(2));
			long o3 = Long.parseLong(matcher.group(3));
			long o4 = Long.parseLong(matcher.group(4));
			address = (o1 << 24 ) | ( o2 << 16) | ( o3 << 8) | o4;
			
			
		} else {
			System.out.println("Error with ip addres ");
			System.exit(1);
		}
		
		return address;
	}
	
	/** get the Dotted Decimarl string representation of a binary encoded long ip address **/
	private String convertLongToIPString(long ip){
		String o1 = String.valueOf(( ip >> 24 ) & 0xFF);
		String o2 = String.valueOf((ip >> 16) & 0xFF);
		String o3 = String.valueOf((ip >> 8) & 0xFF);
		String o4 = String.valueOf(ip & 0xFF);
		return (o1 + "." + o2 + "." + o3 + "." + o4);
	}
	
	/** returns the bit pattern bitmask **/
	public int getBitMask(){
		return this.bitmask;
	}
	
	/** returns the integer netmask **/
	public int getNetmask(){
		return this.netmask;
	}
	
	/** GEt the dotted decimal Network address **/
	public String getNetwork(){
		// mask the host and bitmask
		//System.out.println("Mas is " + Integer.toBinaryString(this.bitmask));
		long nw = this.loweraddress & this.bitmask;
		return convertLongToIPString( nw );
	}
	
	/** Get the full host ip address in Dotted Decimal String format **/
	public String getHostAddress(){
		return this.hostString;
	}
	
	/** Is the Dotted Decimal String ip valid? **/
	public static boolean validateIP(String ip){
		return ip.matches(IPADDRESS_PATTERN);
	}

	/** Get the address in binary encoded long format **/
	public long getAddress(){
		return this.loweraddress;
	}
	
	
	/** is the ip address on the same network ? **/
	public boolean isNetworkMatch(IPv4Address addr){
		
		long bmask = this.getBitMask();
		long addr1 = (this.loweraddress & this.getBitMask()) & 0xFFFFFFFF;
		long addr2 = (addr.loweraddress & this.getBitMask()) & 0xFFFFFFFF;
		
		return (addr1 == addr2);
	}
	

}
