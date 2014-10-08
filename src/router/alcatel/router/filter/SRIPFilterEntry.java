package router.alcatel.router.filter;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.ip.IPv4Address;


/**
 * Models an IPv4 Filter Entry.  
 * @author Kris Peterson
 *
 */
public class SRIPFilterEntry extends AlcatelObject {
	
	/** Description of the filter entry **/
	protected String description = "";
	
	/** Source address to match for the filter entry **/
	protected IPv4Address srcIP = null;
	
	/** Destination address to match for the filter entry **/
	protected IPv4Address dstIP = null;
	
	/** Protocol to match for the filter entry.  Valid FilterProtocol enum type **/
	protected String protocol = "none";
	
	public SRIPFilterEntry(Integer filterNumber){
		super(AlcatelObjectType.SRIPFILTERENTRY);
		this.setName(filterNumber.toString());
		
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
		return this.srcIP.getHostAddress();
	}
	
	
	/** GEt the addres mask length of the destination address **/
	public Integer getDestMask(){
		return this.dstIP.getNetmask();
	}
	
	/** Set the match protocol of the filter entry.  must be valid SRFilterProtocol enum type **/
	public void setProtocol(String protocol){
		this.protocol = protocol;
	}


}
