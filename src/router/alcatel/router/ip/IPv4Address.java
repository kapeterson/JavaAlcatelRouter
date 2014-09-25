package router.alcatel.router.ip;

import java.util.regex.*;

public class IPv4Address extends IPAddress {

	protected int loweraddress = 0;
	protected String hostString = "";
	protected int netmask = 0;
	protected int bitmask = 0;
	
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	protected Pattern ipPattern = Pattern.compile(IPADDRESS_PATTERN);
	
	public IPv4Address(String ipAddress, String mask){
		
		if ( !validateIP(ipAddress)){
			System.out.println("Error with ip addres ");
			System.exit(1);
		} 
		
		this.loweraddress = convertIPToInt(ipAddress);
		this.hostString = ipAddress;
		
		this.netmask = Integer.parseInt(mask);
		
		this.bitmask = 0x01;
		this.bitmask = this.bitmask << 31;
		this.bitmask = this.bitmask >> 31;
		this.bitmask = (this.bitmask >> (31 - this.netmask) ) << ( 31 - this.netmask );
		
	}
	
	private int convertIPToInt(String ipstring){
		
		Matcher matcher = ipPattern.matcher(ipstring);
		int address = 0;
		if (matcher.find()){
			
			int o1 = Integer.parseInt(matcher.group(1));
			int o2 = Integer.parseInt(matcher.group(2));
			int o3 = Integer.parseInt(matcher.group(3));
			int o4 = Integer.parseInt(matcher.group(4));
			address = (o1 << 24 ) | ( o2 << 16) | ( o3 << 8) | o4;
			
			
		} else {
			System.out.println("Error with ip addres ");
			System.exit(1);
		}
		
		return address;
	}
	
	/** returns the bit pattern bitmask **/
	public int getBitMask(){
		return this.netmask;
	}
	
	/** returns the integer netmask **/
	public int getNetmask(){
		return this.netmask;
	}
	
	public String getNetwork(){
		return "";
	}
	
	public String getHostAddress(){
		return this.hostString;
	}
	
	public static boolean validateIP(String ip){
		return ip.matches(IPADDRESS_PATTERN);
	}
}
