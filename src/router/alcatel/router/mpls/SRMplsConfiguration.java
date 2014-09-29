package router.alcatel.router.mpls;
import java.util.TreeMap;


public class SRMplsConfiguration {
	
	protected TreeMap<String, SRMplsInterface> interfaces = new TreeMap<String, SRMplsInterface>();

	protected TreeMap<String, SRMplsPath> paths = new TreeMap<String, SRMplsPath>();
	
	protected TreeMap<String, SRMplsLSP> lsps = new TreeMap<String, SRMplsLSP>();
	
	
	public SRMplsConfiguration(){
		
	}
	
	public void addInterface(SRMplsInterface mplsInterfaceObject){
		this.interfaces.put(mplsInterfaceObject.getName(), mplsInterfaceObject);
	}
	
	public SRMplsInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRMplsInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
	public void addPath(SRMplsPath path){
		this.paths.put(path.getName(), path);
	}
	
	public boolean hasPath(String pathname){
		return this.paths.containsKey(pathname);
	}
	
	public SRMplsPath getPath(String pathname){
		return this.paths.get(pathname);
	}
	
	public TreeMap<String, SRMplsPath> getPaths(){
		return this.paths;
	}
	
	
	public void addLSP(SRMplsLSP lsp){
		this.lsps.put(lsp.getName(), lsp);
	}
	
	public boolean hasLSP(String lspname){
		return this.lsps.containsKey(lspname);
	}
	
	public TreeMap<String, SRMplsLSP> getLSPs(){
		return this.lsps;
	}
	
	public SRMplsLSP getLSP(String lspname){
		return this.lsps.get(lspname);
	}
}
