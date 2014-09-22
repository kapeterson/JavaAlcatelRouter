package router.alcatel.router.routerinterface;


import router.alcatel.router.*;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class SRRouterInterface extends AlcatelObject {
	
	protected InetAddress v4Address;
	protected Inet6Address v6Address;
	
	protected String interfaceName = "";
	protected String description = "";
	
	protected Pattern ipv4Pattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)");
	public SRRouterInterface(String interfaceName){
		super(AlcatelObjectType.ROUTERINTERFACE);
		this.interfaceName = interfaceName;
		//this.v4Address = InetAddress.getByAddress(new byte[] { 10, 10, 1, 1} );
		this.v4Address = null;
		this.v6Address = null;
		
	}
	
	public void setIPv4Address(String ipaddr){
		try {

			this.v4Address = InetAddress.getByName(ipaddr);

			
		} catch (Exception err){
			System.out.println("Error parsing ipv4 address from string " + err.getMessage());
			System.exit(1);
		}
		
	}
	
	
	/** returns ip address as ipaddress object **/
	public InetAddress getIPv4Address(){
		return this.v4Address;
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
