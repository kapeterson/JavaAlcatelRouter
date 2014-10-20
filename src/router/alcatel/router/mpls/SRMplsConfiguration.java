package router.alcatel.router.mpls;
import java.util.TreeMap;

/**
 * Model of the MPLS configuration section on the 7x50
 * @author Kris Peterson
 *
 */
public class SRMplsConfiguration {
	
	/** Map of all the MPLS interfaces **/
	protected TreeMap<String, SRMplsInterface> interfaces = new TreeMap<String, SRMplsInterface>();

	/** Map of all the MPLS Paths **/
	protected TreeMap<String, SRMplsPath> paths = new TreeMap<String, SRMplsPath>();
	
	
	/** Map of all the MPLS LSPs **/
	protected TreeMap<String, SRMplsLSP> lsps = new TreeMap<String, SRMplsLSP>();
	
	
	public SRMplsConfiguration(){
		
	}
	
	
	/** Add the MPLS interface to the configuration **/
	public void addInterface(SRMplsInterface mplsInterfaceObject){
		this.interfaces.put(mplsInterfaceObject.getName(), mplsInterfaceObject);
	}
	
	/** GEt the MPLS Interface with the provided name **/
	public SRMplsInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	/** Get a TreeMap of all the MPLS Interfaces **/
	public TreeMap<String, SRMplsInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
	
	/** Add the MPLS Path to the MPLS Configuration **/
	public void addPath(SRMplsPath path){
		this.paths.put(path.getName(), path);
	}
	
	/** Does the MPLS configuration have a path with the provided name **/
	public boolean hasPath(String pathname){
		return this.paths.containsKey(pathname);
	}
	
	
	/** Get the MPLS Path with the provided path name **/
	public SRMplsPath getPath(String pathname){
		return this.paths.get(pathname);
	}
	
	/** Return a list of all MPLS Paths **/
	public TreeMap<String, SRMplsPath> getPaths(){
		return this.paths;
	}
	
	
	/** Add the LSP to the MPLS Configuration **/
	public void addLSP(SRMplsLSP lsp){
		this.lsps.put(lsp.getName(), lsp);
	}
	
	/** Is an LSP configured with the provided name **/
	public boolean hasLSP(String lspname){
		return this.lsps.containsKey(lspname);
	}
	
	
	/** get a list of all the configured MPLS LSPs **/
	public TreeMap<String, SRMplsLSP> getLSPs(){
		return this.lsps;
	}
	
	
	/** Return the MPLS LSP with the provided name **/
	public SRMplsLSP getLSP(String lspname){
		return this.lsps.get(lspname);
	}
}
