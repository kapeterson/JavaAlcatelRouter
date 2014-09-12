package router.alcatel.router.port;

import router.alcatel.router.*;

/**
 * Models an SR Physical Port Object
 * @author Kris Peterson
 *
 */
public class SRPortObject extends AlcatelObject {
	
	/** Boolean values storing shutdown state **/
	protected boolean isShutdown = true;
	
	
	/**
	 *	Instantiate an SRPortObject
	 */
	public SRPortObject(){
		super(AlcatelObjectType.PHYSICALPORT);
		
	}
	
	/**
	 * Set the port to a shutdown state
	 */
	public void setAdminDown(){
		this.isShutdown = true;
	}
	
	/**
	 * Set the port to a 'no shutdown' state
	 */
	public void setAdminUp(){
		this.isShutdown = false;
	}
	
	/**
	 * Sets the value of the shutdown
	 * @param shutdown boolean value telling whether or not the port is shut
	 */
	public void setShutdown(boolean shutdown){
		this.isShutdown = shutdown;
	}
	
	
	/**
	 * 
	 * @return boolean value stating whether or not the port is Shutdown
	 */
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	/**
	 * Status of admin up
	 * @return boolean value telling whether or not the port is up
	 */
	public boolean isAdminUp(){
		return !this.isShutdown();
	}
	
}
