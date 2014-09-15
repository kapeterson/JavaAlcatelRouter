package router.alcatel.router.routerinterface;

public class SRRouterInterface {
	
	protected String interfaceName = "";
	protected String description = "";
	
	public SRRouterInterface(String interfaceName){
		this.interfaceName = interfaceName;
		
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public boolean isInterfaceObject(){
		return true;
	}
	
	public String getName(){
		return this.interfaceName;
	}
	
	public void setName(String ifaceName){
		this.interfaceName = ifaceName;
	}
}
