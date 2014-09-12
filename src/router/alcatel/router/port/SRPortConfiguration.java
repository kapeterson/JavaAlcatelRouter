package router.alcatel.router.port;

import java.util.TreeMap;

public class SRPortConfiguration {

	protected TreeMap<String, SRPortObject> ports = null;
	public SRPortConfiguration(){
		ports = new TreeMap<String, SRPortObject>();
		
	}
	
	public void addPort(SRPortObject port){
		this.ports.put(port.getName(), port);
	}
}
