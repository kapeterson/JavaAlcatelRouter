package router.alcatel.router.port;

import router.alcatel.router.*;

/**
 * Models an SR Physical Port Object
 * @author pete
 *
 */
public class SRPortObject extends AlcatelObject {
	
	/** Boolean values storing shutdown state **/
	protected boolean isShutdown = false;
	
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
	
	
}
