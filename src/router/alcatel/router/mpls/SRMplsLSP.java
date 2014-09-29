package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMplsLSP extends AlcatelObject {

	protected String toAddress = "";
	protected String fromAddress = "";
	protected String primaryPath = null;
	protected String secondaryPath = null;
	
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
	
	public void setPrimaryPath(String pathName){
		this.primaryPath = pathName;
	}
	
	public void setSecondaryPath(String pathName){
		this.secondaryPath = pathName;
	}
	
	public String getPrimaryPath(){
		return this.primaryPath;
	}
	
	public String getSecondaryPath(){
		return this.secondaryPath;
	}
}
