package router.alcatel.router.igmp;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRIGMPStaticJoin extends AlcatelObject {

	String groupAddress = null;
	String groupSource = null;
	public SRIGMPStaticJoin(String groupAddress){
		super(AlcatelObjectType.IGMPSTATICJOIN);
		this.groupAddress = groupAddress;

	}
	
	public SRIGMPStaticJoin(String groupAddress, String src){
		super(AlcatelObjectType.IGMPSTATICJOIN);
		this.groupAddress = groupAddress;
		this.groupSource = src;
	}
	public String getGroup(){
		return this.groupAddress;
	}
	
	public String getSource(){
		return this.groupSource;
	}
	
	public void setSource(String src){
		this.groupSource = src;
	}
}
