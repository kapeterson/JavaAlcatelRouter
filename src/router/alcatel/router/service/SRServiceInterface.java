package router.alcatel.router.service;

import java.util.Arrays;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.routerinterface.SRRouterInterface;

/**
 * interface for all base services vpls, ies, sdp that are bound to
 * service children
 * @author Kris Peterson
 *
 */
public class SRServiceInterface extends SRRouterInterface implements SRServiceChild {
	
	
	/** Parent service of the Interface **/
	protected SRServiceObject parentService = null;
	
	/** Binding of the interface ( SAP, spoke-sdp etc) **/
	protected AlcatelObject serviceBinding = null;
	
	public SRServiceInterface(String interfaceName){
		
		super(interfaceName);
		this.setName(interfaceName);
		this.setObjectType(AlcatelObjectType.SERVICEINTERFACE);
		setBindingTypes();
	}
	
	/** set the list of valid binding types **/
	private void setBindingTypes(){
		this.bindingTypes = new AlcatelObjectType[] { AlcatelObjectType.SAPOBJECT, AlcatelObjectType.MESHSDPOBJECT, AlcatelObjectType.SPOKESDPOBJECT };
	}
	
	
	public SRServiceInterface(String interfaceName, SRServiceObject parentService){
		
		super(interfaceName);
		this.setName(interfaceName);
		this.setObjectType(AlcatelObjectType.SERVICEINTERFACE);
		this.parentService  = parentService;
		setBindingTypes();

	}
	
	/** Set the binding of the interface to the provided object **/
	public void setServiceBinding(AlcatelObject serviceBinding){
		if (Arrays.asList(this.bindingTypes).contains(serviceBinding.getObjectType()) ){
			this.serviceBinding = serviceBinding;
		} else {
			System.out.println("ERROR binding service type " + serviceBinding.getObjectType() + " to an ies interface");
			System.exit(1);
		}
	}
	
	/** Set the parent service for the interface to the provided service object**/
	public void setParentService(SRServiceObject parentService){
		this.parentService = parentService;
	}
	
	
	/** Get the parent object of the interface **/
	public SRServiceObject getParentService(){
		return this.parentService;
	}
	

}
