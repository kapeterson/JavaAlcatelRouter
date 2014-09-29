package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMplsLSP extends AlcatelObject {

	protected String toAddress = "";
	protected String fromAddress = "";
	
	public SRMplsLSP(String lspname){
		super(AlcatelObjectType.MPLSLSP);
		this.setName(lspname);
	}
	
	public void setToAddress(String toAddress){
		this.toAddress = toAddress;
	}
	
	public String getToAddress(){
		return this.toAddress;
	}
}
