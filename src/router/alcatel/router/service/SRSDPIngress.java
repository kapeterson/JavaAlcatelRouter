package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.filter.SRFilterType;

/**
 * Model the Ingress component of a Service SDP
 * @author Kris Peterson
 *
 */
public class SRSDPIngress extends AlcatelObject {
	
	/** Filter number configured on the SDP Ingress **/
	protected Integer filterNumber = null;
	
	/** filter type configured on the SDP Ingress **/
	protected SRFilterType filterType = SRFilterType.none;
	
	/** vc label configured on the SDP INgress **/
	protected Integer vclabel = null;
	
	public SRSDPIngress(){
		super(AlcatelObjectType.SDPINGRESS);
	}
	
	/** set the filter number on the SDP Ingress **/
	public void setFilterNumber(Integer filterNumber){
		this.filterNumber = filterNumber;
	}
	
	/** Set the filter type on the sdp ingress **/
	public void setFilterType(SRFilterType ftype){
		this.filterType = ftype;
	}
	
	/** set the vc label on the sdp ingress **/
	public void setVCLabel(Integer label){
		this.vclabel = label;
	}
	
	
	/** get the configured filter number on the sdp ingress **/
	public Integer getFilterNumber(){
		return this.filterNumber;
	}
	
	/** get the configured filter type on the sdp ingress **/
	public SRFilterType getSRFilterType(){
		return this.filterType;
	}
	
	/** get the configured vc label on the sdp ingress **/
	public Integer getVCLabel(){
		return this.vclabel;
	}
}
