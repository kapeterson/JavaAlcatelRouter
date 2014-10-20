package router.alcatel.router.ospf;

import router.alcatel.router.*;

/**
 * Models an OSPF Interface 
 * @author Kris Peterson
 *
 */
public class SROSPFInterface extends AlcatelObject{

	public SROSPFInterface(String interfacename){
		super(AlcatelObjectType.OSPFINTERFACE);
		this.setName(interfacename);
	}
	
}
