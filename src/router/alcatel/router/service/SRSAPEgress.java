package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.filter.SRFilterType;

public class SRSAPEgress extends AlcatelObject {

	protected Integer sapIngressQOS = 1;
	protected Integer sapIngressFilter = null;
	protected SRFilterType filtertype = null;

	public SRSAPEgress(){
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
	
	public SRFilterType getFilterType(){
		return this.filtertype;
	}
	
	public void setFilterType(SRFilterType type){
		this.filtertype = type;
	}
}
