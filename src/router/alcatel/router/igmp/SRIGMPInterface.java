package router.alcatel.router.igmp;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models an IGMP Interface Configuration
 * @author Kris Peterson
 *
 */
public class SRIGMPInterface extends AlcatelObject{
	
	public SRIGMPInterface(String interfacename){
		super(AlcatelObjectType.IGMPINTERFACE);
		this.setName(interfacename);
	}
}
