package router.alcatel.router.port;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRData;

public class SRPortEthernet extends AlcatelObject {
 
	
	protected String encapsulation = "null";
	protected String mode = "network";
	public SREthernetNetwork NETWORK = new SREthernetNetwork();
	
	public SRPortEthernet(){
		super(AlcatelObjectType.PORTETHERNET);


	}

	
	public void setEncapType(String encapType){
		
		if ( SRData.PORT_ENCAP_TYPES.contains(encapType)){
			this.encapsulation = encapType;
		} else {
			System.out.println("ERROR: Invalid encap type " + encapType + " when setting port");
			System.exit(1);
		}
	}
	
	public String getEncapType(){
		return this.encapsulation;
	}
	
	public void setMode(String mode){
		if ( SRData.ETHERNET_MODES.contains(mode))
			this.mode = mode;
		else{
			System.out.println("ERROR setting mode to " + mode + " on port ");
		}
	}
	
	public String getMode(){
		return this.mode;
	}

}
