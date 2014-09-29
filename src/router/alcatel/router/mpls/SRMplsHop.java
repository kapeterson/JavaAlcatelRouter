package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMplsHop extends AlcatelObject {

	protected Integer hopNumber = -1;
	protected String hopAddress = "";
	
	public SRMplsHop(Integer hopnumber, String hopAddress){
		super(AlcatelObjectType.MPLSPATHHOP);
		this.hopNumber = hopnumber;
		this.hopAddress = hopAddress;
	}
	
	public String getAddress(){
		return this.hopAddress;
	}

	public Integer getHopNumber(){
		return this.hopNumber;
	}
}
