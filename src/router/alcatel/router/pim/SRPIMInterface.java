package router.alcatel.router.pim;

import router.alcatel.router.*;

/** Models a PIM Interface **
 * 
 * @author Kris Peterson
 *
 */
public class SRPIMInterface extends AlcatelObject {
	
	public SRPIMInterface(String interfacename){
		super(AlcatelObjectType.PIMINTERFACE);
		this.setName(interfacename);
	}
}
