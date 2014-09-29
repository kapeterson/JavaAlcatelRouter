package router.alcatel.router.service;

import router.alcatel.router.filter.SRFilterType;

public class SRSDPIngress {
	
	protected Integer filterNumber = null;
	protected SRFilterType filterType = SRFilterType.none;
	protected Integer vclabel = null;
	
	public SRSDPIngress(){
		
	}
	
	public void setFilterNumber(Integer filterNumber){
		this.filterNumber = filterNumber;
	}
	
	public void setFilterType(SRFilterType ftype){
		this.filterType = ftype;
	}
	
	public void setVCLabel(Integer label){
		this.vclabel = label;
	}
	
	public Integer getFilterNumber(){
		return this.filterNumber;
	}
	
	public SRFilterType getSRFilterType(){
		return this.filterType;
	}
	
	public Integer getVCLabel(){
		return this.vclabel;
	}
}
