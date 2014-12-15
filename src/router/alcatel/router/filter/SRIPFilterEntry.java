package router.alcatel.router.filter;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.ip.IPv4Address;


/**
 * Models an IPv4 Filter Entry.  
 * @author Kris Peterson
 *
 */
public class SRIPFilterEntry extends SRFilterEntryObject {
	
	/** Description of the filter entry **/
	protected String description = "";
	
	/** Source address to match for the filter entry **/
	protected IPv4Address srcIP = null;
	
	/** Destination address to match for the filter entry **/
	protected IPv4Address dstIP = null;
	
	/** Protocol to match for the filter entry.  Valid FilterProtocol enum type **/
	protected String protocol = "none";
	
	/** start port for match port range **/
	protected int startSrcPort = -1;
	
	/** end port for match port range **/
	protected int endSrcPort = -1;
	
	/** start port for dst port range **/
	protected int startDstPort = -1;
	
	/** end port for dst port range **/
	protected int endDstPort = -1;
	
	
	/** tcp syn match **/
	protected boolean tcpSYN = false;
	protected boolean checkTCPSYN = false;
	
	/** tcp ack match **/
	protected boolean tcpACK = false;
	protected boolean checkTCPACK = false;
	
	
	public SRIPFilterEntry(Integer filterNumber){
		super(filterNumber);
		this.setName(filterNumber.toString());
		this.setSourceAddress("0.0.0.0", "0");
		this.setDestAddress("0.0.0.0", "0");
		
	}
	
	/** set the ip filter entry description **/
	public void setDescription(String desc){
		this.description = desc;
	}
	
	
	/** Get the description of the IP Filter Entry **/
	public String getDescription(){
		return this.description;
	}
	
	/** Set the Source Address of the IP Filter Entry **/
	public void setSourceAddress(String ipString, String mask){
		if ( ipString.equals("68.95.208.0")){
			//System.out.println("Pause");
		}
		this.srcIP = new IPv4Address(ipString, mask);

	}
	
	/** Set the Destination Address of the IP Filter Entry **/
	public void setDestAddress(String ipString, String mask){
		this.dstIP = new IPv4Address(ipString, mask);
	}
	
	
	/** Get the match protocol for the filter entry **/
	public String getProtocol(){
		return this.protocol;
	}
	
	/** get the source address of the Filter Entry represented as a String **/
	public String getSourceAddress(){
		return this.srcIP.getHostAddress();
	}
	
	/** Get the IP mask length of the Source Match address **/
	public Integer getSourceMask(){
		return this.srcIP.getNetmask();
	}
	
	/** Get the destination IP address represented as a string **/
	public String getDestAddress(){
		return this.dstIP.getHostAddress();
	}
	
	
	/** GEt the addres mask length of the destination address **/
	public Integer getDestMask(){
		return this.dstIP.getNetmask();
	}
	
	/** Set the match protocol of the filter entry.  must be valid SRFilterProtocol enum type **/
	public void setProtocol(String protocol){
		this.protocol = protocol;
	}
	
	
	/** Do the source and destination IP addresses match the entry **/
	public boolean isIPMatch(String sourceIP, String destinationIP){
		
		String entry = this.getName();
		int entryNumber = this.getNumber();
		

		IPv4Address srcIP = new IPv4Address(sourceIP, "0");
		IPv4Address dstIP = new IPv4Address(destinationIP, "0");
		
		boolean srcMatch = ( sourceIP.equals("0.0.0.0") ) ? true : this.srcIP.isNetworkMatch(srcIP);
		boolean dstMatch = (destinationIP.equals("0.0.0.0") ) ? true : this.dstIP.isNetworkMatch(dstIP);
		return ( srcMatch && dstMatch);
		
		//return ( this.srcIP.isNetworkMatch(srcIP) && this.dstIP.isNetworkMatch(dstIP));

	}
	
	
	/** Does the protocol match **/
	public boolean isProtocolMatch(String packetProtocol){
		return ( this.protocol.toLowerCase().equals("none") || this.getProtocol().toLowerCase().equals(packetProtocol.toLowerCase()));
	}

	
	/** Is the port within the configured port range **/
	public boolean isSrcPortMatch(int port){
		
		if ( this.protocol.toLowerCase().equals("none"))
			return true;
		
		return ( this.startSrcPort == -1 || ( port >= this.startSrcPort && port <= this.endSrcPort));
	}
	
	
	/** Is the port within the configured port range **/
	public boolean isDstPortMatch(int port){
		if ( this.protocol.toLowerCase().equals("none"))
			return true;
		
		return ( this.startDstPort == -1 || ( port >= this.startDstPort && port <= this.endDstPort));
	}
	
	
	/** set the source port ranges **/
	public void setSrcPorts(int startPort, int endPort){
		this.startSrcPort = startPort;
		this.endSrcPort = endPort;
	}

	/** set the dest port ranges **/
	public void setDstPorts(int startPort, int dstPort){
		
		if ( this.getNumber() == 3656){
			//System.out.println("Check setting");
		}
		this.startDstPort = startPort;
		this.endDstPort = dstPort;
	}
	
	public int getStartSourcePort(){
		return this.startSrcPort;
	}
	
	
	public int getEndSourcePort(){
		
		return this.endDstPort;
	}
	
	public int getStartDestPort(){
		return this.startDstPort;
	}
	
	public int getEndDestPort(){
		return this.endDstPort;
	}
	
	public String getSourcePortString(){
		
		if ( this.startSrcPort == -1)
			return "none";
		
		if ( this.startSrcPort == this.endSrcPort){
			return String.valueOf(startSrcPort);
		} else {
			return ( startSrcPort + ".." + endSrcPort);
		}
		
	}
	
	public String getDestPortString(){
		if ( this.startDstPort == -1)
			return "none";
		
		if ( this.startDstPort == this.endDstPort){
			return String.valueOf(startDstPort);
		} else {
			return ( startDstPort + ".." + endDstPort);
		}
		
	}
	
	public void setTCPSYN(boolean val){
		this.tcpSYN = val;
	}
	
	public boolean getTCPSYN(){
		return this.tcpSYN;
	}
	
	public void setTCPACK(boolean val){
		this.tcpACK = val;
	}
	
	public boolean getTCPACK(){
		return this.tcpACK;
	}
	
	public void setCheckTCPACK(boolean val){
		this.checkTCPACK = val;
	}
	
	public boolean checkTCPACK(){
		return this.checkTCPACK;
	}
	
	public void setCheckTCPSYN(boolean val){
		this.checkTCPSYN = val;
	}
	
	public boolean checkTCPSYN(){
		return this.checkTCPSYN;
	}
}
