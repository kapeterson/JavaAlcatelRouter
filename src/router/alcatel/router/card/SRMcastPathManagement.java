package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.impm.SRBandwidthPolicy;


public class SRMcastPathManagement extends AlcatelObject {
	
	protected SRBandwidthPolicy bwPolicy = null;
	
	protected boolean isShutdown = true;
	
	public SRMcastPathManagement(){
		super(AlcatelObjectType.MCASTPATHMANAGEMENT);
	}
	
	public void setBandwidthPolicy(SRBandwidthPolicy bwPolicy){
		this.bwPolicy = bwPolicy;
	}
	
	public SRBandwidthPolicy getBandwidthPolicy(){
		return this.bwPolicy;
	}
	
	public String getBandwidthPolicyName(){
		
		if ( this.bwPolicy != null)
			return this.bwPolicy.getName();
		else
			return "";
	}
	
	public boolean isPathManagment(){
		return true;
	}
	
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	public void setShutdown(boolean val){
		this.isShutdown = val;
	}
	
	public void enable(){
		this.isShutdown = false;
	}
	
	public void disable(){
		this.isShutdown = true;
	}
	
	public boolean isEnabled(){
		return !this.isShutdown();
	}
	
	
	
	
}
