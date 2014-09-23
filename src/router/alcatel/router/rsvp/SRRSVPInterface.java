package router.alcatel.router.rsvp;

import router.alcatel.router.*;

public class SRRSVPInterface extends AlcatelObject {
	public SRRSVPInterface(String interfacename){
		super(AlcatelObjectType.RSVPINTERFACE);
		this.setName(interfacename);
	}
}
