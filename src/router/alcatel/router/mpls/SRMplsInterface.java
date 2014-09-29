package router.alcatel.router.mpls;

import router.alcatel.router.*;
import router.alcatel.router.AlcatelObjectType;

public class SRMplsInterface extends AlcatelObject{
	
	public SRMplsInterface(String interfacename){
		super(AlcatelObjectType.MPLSINTERFACE);
		this.setName(interfacename);
	}
}
