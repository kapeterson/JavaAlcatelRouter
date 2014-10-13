package router.alcatel.router.igmp;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.ip.IPv4Address;

public class SRSSMTranslation extends AlcatelObject {

	protected IPv4Address startAddress;
	protected IPv4Address endAddress;
	protected IPv4Address sourceAddress;
	public SRSSMTranslation(String startAddress, String endAddress){
		super(AlcatelObjectType.IGMPSSM);
		//this.setName(interfacename);
		this.startAddress = new IPv4Address(startAddress, "32");
		this.endAddress = new IPv4Address(endAddress, "32");
	}
	
	public void setSource(String sourceAddress){
		this.sourceAddress = new IPv4Address(sourceAddress, "32");
	}
	
	public String getRangeStart(){
		return this.startAddress.getHostAddress();
	}
	
	public String getEndRange(){
		return this.endAddress.getHostAddress();
	}
	
	public String getSource(){
		return this.sourceAddress.getHostAddress();
	}
	
}
