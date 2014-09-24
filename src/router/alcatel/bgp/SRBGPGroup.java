package router.alcatel.bgp;

import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.*;

public class SRBGPGroup extends AlcatelObject {
	
	public SRBGPGroup(String groupName){
		super(AlcatelObjectType.BGPGROUP);
		this.setName(groupName);
	}
	
}
