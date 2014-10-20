package router.alcatel.router.port;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRData;

public class SRPortEthernet extends AlcatelObject {
 
	
	/** encapsulation configured on the Port Ethernet **/
	protected String encapsulation = "null";
	
	/** configured mode of the Port Ethernet default = "network" **/
	protected String mode = "network";
	
	/** Expose access to the port ethernet network component **/
	public SREthernetNetwork NETWORK = new SREthernetNetwork();
	
	
	public SRPortEthernet(){
		super(AlcatelObjectType.PORTETHERNET);


	}

	
	/** Sets the encapsulation type on the port Ethernet **/
	public void setEncapType(String encapType){
		
		if ( SRData.PORT_ENCAP_TYPES.contains(encapType)){
			this.encapsulation = encapType;
		} else {
			System.out.println("ERROR: Invalid encap type " + encapType + " when setting port");
			System.exit(1);
		}
	}
	
	
	/** Get the encapsulation type on the Port Ethernet component **/
	public String getEncapType(){
		return this.encapsulation;
	}
	
	
	/** set the Port Ethernet mode - (network/access) **/
	public void setMode(String mode){
		if ( SRData.ETHERNET_MODES.contains(mode))
			this.mode = mode;
		else{
			System.out.println("ERROR setting mode to " + mode + " on port ");
		}
	}
	
	/** get the configured mode on the Port Ethernet **/
	public String getMode(){
		return this.mode;
	}

}
