package router.alcatel.router.mpls;

import router.alcatel.router.*;
import router.alcatel.router.AlcatelObjectType;

public class SRMPLSInterface extends AlcatelObject{
	
	public SRMPLSInterface(String interfacename){
		super(AlcatelObjectType.MPLSINTERFACE);
		this.setName(interfacename);
	}
}
