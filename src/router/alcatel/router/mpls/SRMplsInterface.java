package router.alcatel.router.mpls;

import router.alcatel.router.*;
import router.alcatel.router.AlcatelObjectType;
/**
 * Models an MPLS Interface
 * @author Kris Peterson
 *
 */
public class SRMplsInterface extends AlcatelObject{
	
	public SRMplsInterface(String interfacename){
		super(AlcatelObjectType.MPLSINTERFACE);
		this.setName(interfacename);
	}
}
