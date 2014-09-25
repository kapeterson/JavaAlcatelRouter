package router.alcatel.router.service;

import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.routerinterface.SRRouterInterface;

public class SRServiceInterface extends SRRouterInterface {
	
	protected SRServiceObject parentService = null;

	public SRServiceInterface(String interfaceName){
		
		super(interfaceName);
		this.setName(interfaceName);
		this.setObjectType(AlcatelObjectType.SERVICEINTERFACE);
	}
	
	public SRServiceInterface(String interfaceName, SRServiceObject parentService){
		
		super(interfaceName);
		this.setName(interfaceName);
		this.setObjectType(AlcatelObjectType.SERVICEINTERFACE);
		this.parentService  = parentService;
	}
	
	public void setParentService(SRServiceObject parentService){
		this.parentService = parentService;
	}
	
	public SRServiceObject getParentService(){
		return this.parentService;
	}
}
