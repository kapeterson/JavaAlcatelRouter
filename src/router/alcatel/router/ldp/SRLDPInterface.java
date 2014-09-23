package router.alcatel.router.ldp;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRLDPInterface extends AlcatelObject {
	
	public SRLDPInterface(String interfacename){
		super(AlcatelObjectType.LDPINTERFACE);
		this.setName(interfacename);
	}
}
