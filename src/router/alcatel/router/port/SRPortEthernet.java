package router.alcatel.router.port;

import java.util.ArrayList;
import java.util.List;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRPortEthernet extends AlcatelObject {
 
	protected List<String> encapTypes = new ArrayList<String>(); 
	
	protected String encapsulation = "null";
	
	public SRPortEthernet(){
		super(AlcatelObjectType.PORTETHERNET);
		encapTypes.add("null");
		encapTypes.add("dot1q");
		encapTypes.add("qinq");

	}

	
	public void setEncapType(String encapType){
		if ( encapTypes.contains(encapType)){
			this.encapsulation = encapType;
		} else {
			System.out.println("ERROR: Invalid encap type " + encapType + " when setting port");
			System.exit(1);
		}
	}
	
	public String getEncapType(){
		return this.encapsulation;
	}

}
