package router.alcatel.router.routerinterface;

import router.alcatel.router.AlcatelObjectType;

/**
 * MOdels a Network Interface
 * @author Kris Peterson
 *
 */
public class SRNetworkInterface extends SRRouterInterface{
	
	/** Qos policy applied to the interface **/
	protected Integer qos = 1;
	
	public SRNetworkInterface(String intName){
		super(intName, AlcatelObjectType.ROUTERNETWORKINTERFACE);
		this.bindingTypes = new AlcatelObjectType[] { AlcatelObjectType.PHYSICALPORT, AlcatelObjectType.LAG };
		
		// TODO: Figure out a way to set the qos policy to 1.  need a way to callback or access QOS configuration
	}
	
	public boolean isNetworkInterfaceObject(){
		return true;
	}
	
	
	/** Set the number of the qos policy applied to the interface **/
	public void setQOS(Integer networkPolicy){
		this.qos = networkPolicy;
	}

	
	/** Get the number of the applied QOS Policy **/
	public Integer getQOSPolicyNumber(){
		return this.qos;
	}
	
}
