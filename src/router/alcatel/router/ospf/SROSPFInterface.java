package router.alcatel.router.ospf;

import router.alcatel.router.*;

public class SROSPFInterface extends AlcatelObject{

	public SROSPFInterface(String interfacename){
		super(AlcatelObjectType.OSPFINTERFACE);
		this.setName(interfacename);
	}
	
}
