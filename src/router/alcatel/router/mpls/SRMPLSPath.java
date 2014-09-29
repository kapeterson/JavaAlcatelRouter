package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMPLSPath extends AlcatelObject {

	public SRMPLSPath(String name){
		super(AlcatelObjectType.MPLSPATH);
		this.setName(name);
	}
}
