package router.alcatel.router;

import java.util.ArrayList;
import java.util.List;

public class SRData {

	//public static ArrayList<String> PORT_ENET_ENCAP = new ArrayList<String>();
	public final static List<String> PORT_ENCAP_TYPES = new ArrayList<String>();
	
	public final static List<String> ETHERNET_MODES = new ArrayList<String>();
	
	static {
		PORT_ENCAP_TYPES.add("null");
		PORT_ENCAP_TYPES.add("dot1q");
		PORT_ENCAP_TYPES.add("qinq");
		
		ETHERNET_MODES.add("network");
		ETHERNET_MODES.add("access");
		
		
	}
	
}
