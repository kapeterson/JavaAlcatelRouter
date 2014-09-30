package router.alcatel.router.ip;

/**
 * Models an IP Address. IPv4 and IPv6 will extend this class
 * @author Kris Peterson
 *
 */
public abstract class IPAddress {
	
	
	public boolean isIPv4Address(){
		return false;
	}
	
	public boolean isIPv6Address(){
		return false;
	}
	
	/** Get the string representation of the Host Address **/
	public abstract String getHostAddress();
	
	/**
	 * Get the bitmask of the address
	 * mask of 8 would be 11111111000000000000000000000000 in integer format
	 * @return
	 */
	public abstract int getBitMask();
	
	
	/** Get the mask of the ip addres represented as length in number of bits **/
	public abstract int getNetmask();
	
	/** Get the network for the address given by combining address and mask **/
	public abstract String getNetwork();
	
	
}
