package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;


/**
 * Models and MPLS LSP
 * @author kp109p
 *
 */
public class SRMplsLSP extends AlcatelObject {

	/** the configured to address **/
	protected String toAddress = "";
	
	/** the configured from address **/
	protected String fromAddress = "";
	
	/** Configured primary path name **/
	protected String primaryPath = null;
	
	/** configured secondary Path name **/
	protected String secondaryPath = null;
	
	public SRMplsLSP(String lspname){
		super(AlcatelObjectType.MPLSLSP);
		this.setName(lspname);
	}
	
	/** Sets the to address of the mpls LSP **/
	public void setToAddress(String toAddress){
		this.toAddress = toAddress;
	}
	
	/** Gets the to address of the MPLS LSP **/
	public String getToAddress(){
		return this.toAddress;
	}
	
	/** Sets the configured primary path name of the LSP **/
	public void setPrimaryPath(String pathName){
		this.primaryPath = pathName;
	}
	
	
	/** Sets the configured secondary path name **/
	public void setSecondaryPath(String pathName){
		this.secondaryPath = pathName;
	}
	
	
	/** Get the primary path name **/
	public String getPrimaryPath(){
		return this.primaryPath;
	}
	
	
	/** Get the secondary path name **/
	public String getSecondaryPath(){
		return this.secondaryPath;
	}
}
