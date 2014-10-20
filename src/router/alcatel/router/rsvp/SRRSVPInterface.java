package router.alcatel.router.rsvp;

import router.alcatel.router.*;

/**
 * Models an RSVP Interface on the 7x50
 * @author Kris Peterson
 *
 */
public class SRRSVPInterface extends AlcatelObject {
	public SRRSVPInterface(String interfacename){
		super(AlcatelObjectType.RSVPINTERFACE);
		this.setName(interfacename);
	}
}
