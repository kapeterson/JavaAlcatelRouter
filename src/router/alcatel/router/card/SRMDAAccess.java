package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMDAAccess extends AlcatelObject{

	public SRMDAAccess(AlcatelObject parent){
		super(AlcatelObjectType.MDAACCESS);

		this.setParent(parent);
	}
	
}
