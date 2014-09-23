package router.alcatel.router.igmp;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRIGMPInterface extends AlcatelObject{
	
	public SRIGMPInterface(String interfacename){
		super(AlcatelObjectType.IGMPINTERFACE);
		this.setName(interfacename);
	}
}
