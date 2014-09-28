package router.alcatel.router.routerinterface;

import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQOSPolicy;

public class SRNetworkInterface extends SRRouterInterface{
		
	protected SRNetworkQOSPolicy qos = null;
	
	public SRNetworkInterface(String intName){
		super(intName, AlcatelObjectType.ROUTERNETWORKINTERFACE);
		this.bindingTypes = new AlcatelObjectType[] { AlcatelObjectType.PHYSICALPORT, AlcatelObjectType.LAG };
		
		// TODO: Figure out a way to set the qos policy to 1.  need a way to callback or access QOS configuration
	}
	
	public boolean isNetworkInterfaceObject(){
		return true;
	}
	
	public void setQOS(SRNetworkQOSPolicy policyObject){
		this.qos = policyObject;
	}

	public SRNetworkQOSPolicy getQOSPolicy(){
		return this.qos;
	}
	
}
