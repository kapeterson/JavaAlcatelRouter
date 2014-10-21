package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models the Multicast Path Management configuration.  IOM3/IMM and IOMb MDA
 * will have Path Management configurations
 * @author Kris Peterson
 *
 */
public class SRMcastPathManagement extends AlcatelObject {
	
	/** Bandwidth policy applied to the Multicast Path **/
	protected String bwPolicy = "NA";
	
	/** Is path management enabled **/
	protected boolean isShutdown = true;
	
	public SRMcastPathManagement(){
		super(AlcatelObjectType.MCASTPATHMANAGEMENT);
	}
	
	/**
	 * Set the bandwidth policy for the multicast path
	 * @param bwPolicy
	 */
	public void setBandwidthPolicyName(String bwPolicy){
		this.bwPolicy = bwPolicy;
	}
	
	

	
	/**
	 * Get the name of the bandwidth policy applied to the multicast path management
	 * @return
	 */
	public String getBandwidthPolicyName(){
		
		return this.bwPolicy;
	}
	
	
	public boolean isPathManagment(){
		return true;
	}
	
	/** Is IMPM shutdown? **/
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	/** Set the IMPM status to the provided value **/
	public void setShutdown(boolean val){
		this.isShutdown = val;
	}
	
	/** Enable IMPM **/
	public void enable(){
		this.isShutdown = false;
	}
	
	
	/** Disable IMPM **/
	public void disable(){
		this.isShutdown = true;
	}
	
	/** Is IMPM Enabled? **/
	public boolean isEnabled(){
		return !this.isShutdown();
	}
	
	
	
	
}
