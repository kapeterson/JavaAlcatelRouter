package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.filter.SRFilterType;

/**
 * Models the SDP Egress of a service SDP 
 * @author Kris Peterson
 *
 */
public class SRSDPEgress extends AlcatelObject {

	/** Filter number configured on the SDP Egress default = null **/
	protected Integer filterNumber = null;
	
	/** Filter type configured on the SDP Egress **/
	protected SRFilterType filterType = SRFilterType.none;
	
	/** VC Label configured for the SDP **/
	protected Integer vclabel = null;
	
	public SRSDPEgress(){
		super(AlcatelObjectType.SDPEGRESS);
	}
	
	
	/** SEt the filter number applied to the SDP Egress **/
	public void setFilterNumber(Integer filterNumber){
		this.filterNumber = filterNumber;
	}
	
	
	/** Set the filter type configured on the SDP Egress **/
	public void setFilterType(SRFilterType ftype){
		this.filterType = ftype;
	}
	
	/** Set the configured vc label **/
	public void setVCLabel(Integer label){
		this.vclabel = label;
	}
	
	/** get the filter number configured on the SDP Egress **/
	public Integer getFilterNumber(){
		return this.filterNumber;
	}
	
	/** get the filter type configured on the sdp egress **/
	public SRFilterType getSRFilterType(){
		return this.filterType;
	}
	
	/** get the VC Label configured on the sdp **/
	public Integer getVCLabel(){
		return this.vclabel;
	}
}
