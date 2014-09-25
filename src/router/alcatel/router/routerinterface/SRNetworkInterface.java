package router.alcatel.router.routerinterface;

import router.alcatel.router.AlcatelObjectType;

public class SRNetworkInterface extends SRRouterInterface{
		
	public SRNetworkInterface(String intName){
		super(intName, AlcatelObjectType.ROUTERNETWORKINTERFACE);
		this.bindingTypes = new AlcatelObjectType[] { AlcatelObjectType.PHYSICALPORT, AlcatelObjectType.LAG };
	}
	
	public boolean isNetworkInterfaceObject(){
		return true;
	}
	
}
