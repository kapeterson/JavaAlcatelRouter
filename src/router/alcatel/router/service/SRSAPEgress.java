package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.filter.SRFilterType;

/**
 * Models the SAP Egress configuration component
 * @author KRis Peterson
 *
 */
public class SRSAPEgress extends AlcatelObject {

	/** SAP Egress QOS Policy configured **/
	protected Integer sapEgressQOS = 1;
	
	/** Configured SAP Egress filter number **/
	protected Integer sapEgressFilter = null;
	
	/** Configure SAP Ingress filter type **/
	protected SRFilterType filtertype = SRFilterType.none;

	public SRSAPEgress(){
		super(AlcatelObjectType.SAPEGRESSOBJECT);
		
	}
	
	
	/** Set the configured filter number on the SAP Egress **/
	public void setFilter(Integer filternumber){
		this.sapEgressFilter = filternumber;
	}
	
	/** Set the qos polcy number ont he SAP Ingress **/
	public void setQOS(Integer qosPolicyNumber){
		this.sapEgressQOS = qosPolicyNumber;
	}
	
	/** Get the QOS policy number configured on teh SAP Egress **/
	public Integer getQosPolicyNumber(){
		return this.sapEgressQOS;
	}
	
	
	/** Get the filter number configured on the SAP Egress **/
	public Integer getFilterNumber(){
		return this.sapEgressFilter;
	}
	
	
	/** get the filter type configured on the SAP Egress **/
	public SRFilterType getFilterType(){
		return this.filtertype;
	}
	
	
	/** Set the filter type configured on the SAP Egress **/
	public void setFilterType(SRFilterType type){
		this.filtertype = type;
	}
}
