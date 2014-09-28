package router.alcatel.router.routerinterface;

import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQOSPolicy;

public class SRNetworkInterface extends SRRouterInterface{
		
	protected Integer qos = 1;
	
	public SRNetworkInterface(String intName){
		super(intName, AlcatelObjectType.ROUTERNETWORKINTERFACE);
		this.bindingTypes = new AlcatelObjectType[] { AlcatelObjectType.PHYSICALPORT, AlcatelObjectType.LAG };
		
		// TODO: Figure out a way to set the qos policy to 1.  need a way to callback or access QOS configuration
	}
	
	public boolean isNetworkInterfaceObject(){
		return true;
	}
	
	public void setQOS(Integer networkPolicy){
		this.qos = networkPolicy;
	}

	public Integer getQOSPolicyNumber(){
		return this.qos;
	}
	
}
