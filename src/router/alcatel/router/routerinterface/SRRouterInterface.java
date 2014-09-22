package router.alcatel.router.routerinterface;


import router.alcatel.router.*;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public class SRRouterInterface extends AlcatelObject {
	
	protected Inet4Address v4Address;
	protected Inet6Address v6Address;
	
	protected String interfaceName = "";
	protected String description = "";
	
	public SRRouterInterface(String interfaceName){
		super(AlcatelObjectType.ROUTERINTERFACE);
		this.interfaceName = interfaceName;
		//this.v4Address = InetAddress.getByAddress(new byte[] { 10, 10, 1, 1} );
		this.v4Address = null;
		this.v6Address = null;
		
	}
	
	public void setIPv4Address(String ipaddr){
		try {
		this.v4Address = (Inet4Address)Inet4Address.getByName(ipaddr);
		} catch (Exception err){
			System.out.println("Error parsing ipv4 address from string");
		}
		
	}
	
	public Inet4Address getIPv4Address(){
		return this.v4Address;
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
