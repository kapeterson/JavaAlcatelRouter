package router.alcatel.router.routerinterface;


import router.alcatel.router.*;
import router.alcatel.router.ip.*;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.regex.Pattern;

import router.alcatel.router.service.SRSDPObject;
import router.alcatel.router.service.SRServiceSDPObject;

/**
 * Models a base interface object.  Network and Service interfaces will extend this class
 * @author Kris Peterson
 *
 */
public class SRRouterInterface extends AlcatelObject implements  AssociationParent   {
	
	
	/** IPv4 address configured on the interface **/
	protected IPv4Address v4Address;

	/** IPv6 Address of the interface ... NOT IMPLEMENTED YET **/
	protected InetAddress v6Address;
	
	/** configured interface name **/
	protected String interfaceName = "";
	
	/** Configured interface description **/
	protected String description = "";
	
	/** List of valid binding types for the interface **/
	protected AlcatelObjectType[] bindingTypes = new AlcatelObjectType[] {  };
	
	/** Object that the interface is bound to **/
	protected SRInterfaceBinding binding = null;
	
	/** Simple ipv4 address regex **/
	protected Pattern ipv4Pattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)");
	
	protected SRVRRPObject vrrp = null;

	public SRRouterInterface(String interfaceName){
		super(AlcatelObjectType.ROUTERINTERFACE);
		this.interfaceName = interfaceName;
		//this.v4Address = InetAddress.getByAddress(new byte[] { 10, 10, 1, 1} );
		this.v4Address = null;
		this.v6Address = null;
		
	}
	
	
	/** Get the binding of the interface **/
	public SRInterfaceBinding getBinding(){
		return this.binding;
	}
	
	
	/** Set the binding of the itnerface to the provided object **/
	public void setInterfaceBinding(SRInterfaceBinding bindingObject){
		
		if ( !(bindingObject.getBindingObject() instanceof SRInterfaceBindingObject)){
			System.out.println("ERROR: Could not bind " + bindingObject.getObjectType() + " to " + this.getObjectType());
			System.exit(1);
		}
		
		if ( Arrays.asList(this.bindingTypes).contains(bindingObject.getObjectType()) ) {
			
			this.binding = bindingObject;
			
			// Check to see if the child needs to track associations
			if ( bindingObject.getBindingObject().isAssociationChild()) {
				AssociationChild aObj = (AssociationChild)bindingObject.getBindingObject();
				aObj.addAssociation(this);
			} else if (bindingObject.getBindingObject().isServiceSDPObject()){
				SRSDPObject sdp = (SRSDPObject)((SRServiceSDPObject)bindingObject.getBindingObject()).getParent();
				sdp.addAssociation(this);
			}
			
		} else {
			System.out.println("Could not set binding on interface to " + bindingObject.getBindingObject().getObjectType());
			System.exit(1);
		}
	}
	
	
	/** Set the ipv4 address of the interface **/
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
		if ( this.v4Address == null)
			return 0;
		else
			return this.v4Address.getNetmask();
	}
	/** Returns ip address as string value **/
	public String getIPv4HostAddress(){
		if ( v4Address != null)
			return this.v4Address.getHostAddress();
		else
			return "0.0.0.0";
	}
	
	public SRRouterInterface(String interfaceName, AlcatelObjectType oType){
		super(oType);
		this.interfaceName = interfaceName;
	}
	
	/** Set the description of the interface **/
	public void setDescription(String desc){
		this.description = desc;
	}
	
	/** Get the description of the interface **/
	public String getDescription(){
		return this.description;
	}
	
	
	public boolean isInterfaceObject(){
		return true;
	}
	
	/** get the name of the interface **/
	public String getName(){
		return this.interfaceName;
	}
	
	
	/** set the name of the interface **/
	public void setName(String ifaceName){
		this.interfaceName = ifaceName;
	}
	
	public void setVRRPObject(SRVRRPObject vrrp){
		this.vrrp = vrrp;
	}
	
	public SRVRRPObject getVRRPObject(){
		return this.vrrp;
	}
	
}
