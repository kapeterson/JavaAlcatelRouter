package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRSAPEgress extends AlcatelObject {

	protected Integer sapIngressQOS = 1;
	protected Integer sapIngressFilter = -1;
	
	protected SRSAPEgress(){
		super(AlcatelObjectType.SAPEGRESSOBJECT);
		
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
