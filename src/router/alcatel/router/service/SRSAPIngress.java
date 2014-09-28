package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRSAPIngress extends AlcatelObject {

	protected Integer sapIngressQOS = 1;
	protected Integer sapIngressFilter = -1;
	
	protected SRSAPIngress(){
		super(AlcatelObjectType.SAPINGRESSOBJECT);
		
	}
	
	public void setFilter(Integer filternumber){
		this.sapIngressFilter = filternumber;
	}
	
	public void setQOS(Integer qosPolicyNumber){
		this.sapIngressQOS = qosPolicyNumber;
	}
	
	public Integer getQosPolicyNumber(){
		return this.sapIngressQOS;
	}
	
	public Integer getFilterNumber(){
		return this.sapIngressFilter;
	}
}
