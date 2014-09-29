package router.alcatel.router.mpls;
import java.util.TreeMap;


public class SRMPLSConfiguration {
	protected TreeMap<String, SRMPLSInterface> interfaces = new TreeMap<String, SRMPLSInterface>();

	protected TreeMap<String, SRMPLSPath> paths = new TreeMap<String, SRMPLSPath>();
	public SRMPLSConfiguration(){
		
	}
	
	public void addInterface(SRMPLSInterface mplsInterfaceObject){
		this.interfaces.put(mplsInterfaceObject.getName(), mplsInterfaceObject);
	}
	
	public SRMPLSInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRMPLSInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
	public void addPath(SRMPLSPath path){
		this.paths.put(path.getName(), path);
	}
	
	public boolean hasPath(String pathname){
		return this.paths.containsKey(pathname);
	}
	
	public SRMPLSPath getPath(String pathname){
		return this.paths.get(pathname);
	}
	
	public TreeMap<String, SRMPLSPath> getPaths(){
		return this.paths;
	}
}
