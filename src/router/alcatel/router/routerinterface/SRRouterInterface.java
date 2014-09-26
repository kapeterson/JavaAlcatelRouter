package router.alcatel.router.routerinterface;


import router.alcatel.router.*;
import router.alcatel.router.ip.*;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.regex.Pattern;


public class SRRouterInterface extends AlcatelObject implements BindingParent, AssociationParent  {
	
	protected IPv4Address v4Address;
	//protected InetAddress v4Address;
	protected InetAddress v6Address;
	
	protected String interfaceName = "";
	protected String description = "";
	
	protected AlcatelObjectType[] bindingTypes = new AlcatelObjectType[] {  };
	protected SRInterfaceBinding binding = null;
	
	protected Pattern ipv4Pattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)");
	

	public SRRouterInterface(String interfaceName){
		super(AlcatelObjectType.ROUTERINTERFACE);
		this.interfaceName = interfaceName;
		//this.v4Address = InetAddress.getByAddress(new byte[] { 10, 10, 1, 1} );
		this.v4Address = null;
		this.v6Address = null;
		
	}
	
	
	public SRInterfaceBinding getBinding(){
		return this.binding;
	}
	
	public synchronized void setBinding(SRInterfaceBinding bindingObject){
		
		if ( !(bindingObject.getBinding() instanceof BindingChild)){
			System.out.println("ERROR: Could not bind " + bindingObject.getObjectType() + " to " + this.getObjectType());
			System.exit(1);
		}
		
		if ( Arrays.asList(this.bindingTypes).contains(bindingObject.getObjectType()) ) {
			
			this.binding = bindingObject;
			
			// Check to see if the child needs to track associations
			if ( bindingObject.getBinding().isAssociationChild()) {
				AssociationChild aObj = (AssociationChild)bindingObject.getBinding();
				aObj.addAssociation(this);
			}
			
		} else {
			System.out.println("Could not set binding on interface to " + bindingObject.getBinding().getObjectType());
			//throw new Exception("ERROR: Attempting to bind invalid object type "  + bindingObject.getObjectType().toString() + " to interface");
			System.exit(1);
		}
	}
	
	public void setIPv4Address(String ipaddr, String netmask){
		try {

			//this.v4Address = InetAddress.getByName(ipaddr);
			this.v4Address = new IPv4Address(ipaddr, netmask);
			
		} catch (Exception err){
			System.out.println("Error parsing ipv4 address from string ");
			System.exit(1);
		}
		
	}
	
	
	/** returns ip address as ipaddress object **/
	public IPv4Address getIPv4Address(){
		return this.v4Address;
	}
	
	/** return netmask of the ipv4 address **/
	public int getIPv4Mask(){
		return this.v4Address.getNetmask();
	}
	/** Returns ip address as string value **/
	public String getIPv4HostAddress(){
		return this.v4Address.getHostAddress();
	}
	public SRRouterInterface(String interfaceName, AlcatelObjectType oType){
		super(oType);
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
