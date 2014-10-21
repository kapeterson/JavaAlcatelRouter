package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.filter.SRFilterType;

/**
 * MOdel the SAP Ingress component on the 7x50
 * @author kp109p
 *
 */
public class SRSAPIngress extends AlcatelObject {

	/** Configured SAP INgress QOS Policy number **/
	protected Integer sapIngressQOS = 1;
	
	/** Configured SAP INgress filter configured on the SAP Ingress **/
	protected Integer sapIngressFilter = null;
	
	/** Configured filter type on the SAP Ingress **/
	protected SRFilterType filtertype = null;
	
	public SRSAPIngress(){
		super(AlcatelObjectType.SAPINGRESSOBJECT);
		
	}
	
	/** set the configured filter number on the SAP Ingress **/
	public void setFilter(Integer filternumber){
		this.sapIngressFilter = filternumber;
	}
	
	/** Set the configured SAP Ingress qos policy number on the sap Ingress **/
	public void setQOS(Integer qosPolicyNumber){
		this.sapIngressQOS = qosPolicyNumber;
	}
	
	/** Get the configured QOS Policy number on the sap ingress **/
	public Integer getQosPolicyNumber(){
		return this.sapIngressQOS;
	}
	
	/** Get the configured filter number on the SAP INgress **/
	public Integer getFilterNumber(){
		return this.sapIngressFilter;
	}
	
	/** Get the filter type configured on the SAP Ingress **/
	public SRFilterType getFilterType(){
		return this.filtertype;
	}
	
	/** Set the filter type configured on the SAP ingress **/
	public void setFilterType(SRFilterType type){
		this.filtertype = type;
	}
}
