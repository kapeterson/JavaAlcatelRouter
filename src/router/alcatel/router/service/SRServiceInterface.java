package router.alcatel.router.service;

import java.util.Arrays;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.routerinterface.SRRouterInterface;

public class SRServiceInterface extends SRRouterInterface {
	
	
	protected SRServiceObject parentService = null;
	protected AlcatelObject serviceBinding = null;
	
	public SRServiceInterface(String interfaceName){
		
		super(interfaceName);
		this.setName(interfaceName);
		this.setObjectType(AlcatelObjectType.SERVICEINTERFACE);
		setBindingTypes();
	}
	
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
	
	public void setServiceBinding(AlcatelObject serviceBinding){
		if (Arrays.asList(this.bindingTypes).contains(serviceBinding.getObjectType()) ){
			this.serviceBinding = serviceBinding;
		} else {
			System.out.println("ERROR binding service type " + serviceBinding.getObjectType() + " to an ies interface");
			System.exit(1);
		}
	}
	public void setParentService(SRServiceObject parentService){
		this.parentService = parentService;
	}
	
	public SRServiceObject getParentService(){
		return this.parentService;
	}
}
