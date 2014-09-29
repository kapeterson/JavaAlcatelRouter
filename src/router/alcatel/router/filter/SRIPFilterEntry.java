package router.alcatel.router.filter;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.ip.IPv4Address;

public class SRIPFilterEntry extends AlcatelObject {
	
	
	protected String description = "";
	
	protected IPv4Address srcIP = null;
	protected IPv4Address dstIP = null;
	protected SRFilterProtocol protocol = SRFilterProtocol.none;
	
	public SRIPFilterEntry(Integer filterNumber){
		super(AlcatelObjectType.SRIPFILTERENTRY);
		this.setName(filterNumber.toString());
		
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setSourceAddress(String ipString, String mask){
		this.srcIP = new IPv4Address(ipString, mask);

	}
	
	public void setDestAddress(String ipString, String mask){
		this.dstIP = new IPv4Address(ipString, mask);
	}
	
	public SRFilterProtocol getProtocol(){
		return this.protocol;
	}
	
	public String getSourceAddress(){
		return this.srcIP.getHostAddress();
	}
	
	public Integer getSourceMask(){
		return this.srcIP.getNetmask();
	}
	
	public String getDestAddress(){
		return this.srcIP.getHostAddress();
	}
	
	public Integer getDestMask(){
		return this.dstIP.getNetmask();
	}
	
	public void setProtocol(SRFilterProtocol protocol){
		this.protocol = protocol;
	}


}
