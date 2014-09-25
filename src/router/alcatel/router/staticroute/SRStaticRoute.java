package router.alcatel.router.staticroute;
import router.alcatel.router.*;

public class SRStaticRoute extends AlcatelObject {
	
	public SRStaticRoute(String interfacename){
		super(AlcatelObjectType.STATICROUTE);
		this.setName(interfacename);
	}
}
