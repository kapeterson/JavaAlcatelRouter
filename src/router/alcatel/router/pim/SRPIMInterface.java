package router.alcatel.router.pim;

import router.alcatel.router.*;
public class SRPIMInterface extends AlcatelObject {
	
	public SRPIMInterface(String interfacename){
		super(AlcatelObjectType.PIMINTERFACE);
		this.setName(interfacename);
	}
}
