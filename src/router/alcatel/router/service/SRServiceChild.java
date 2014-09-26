package router.alcatel.router.service;

import router.alcatel.router.AlcatelObject;

/**
 * Interface for any child of a service.  Intended to enforce
 * pointer to parent service.  SRService Childred include sap, spoke-sdp, mesh-sdp, ies interface
 * or anything that needs to point to a parent service.  Each SRServiceChild
 * @author kp109p
 *
 */

public interface SRServiceChild {

	/**
	 * Returns the parent service of the 
	 * @return AlcatelObject
	 */
	public AlcatelObject getParentService();
	

	
}
