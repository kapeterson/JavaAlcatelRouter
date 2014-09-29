package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMplsLSP extends AlcatelObject {

	public SRMplsLSP(String lspname){
		super(AlcatelObjectType.MPLSLSP);
		this.setName(lspname);
	}
}
