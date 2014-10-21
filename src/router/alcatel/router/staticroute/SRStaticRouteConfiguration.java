package router.alcatel.router.staticroute;

import java.util.Hashtable;

/**
 * MOdels the static route configuration section
 * @author kp109p
 *
 */
public class SRStaticRouteConfiguration {

	protected Hashtable<String, SRIPv4StaticRoute> ipv4Routes = new Hashtable<String, SRIPv4StaticRoute>();

	public SRStaticRouteConfiguration(){
		
	}
	
	
	/**
	 * Adds an IPv4 Static route to static route HashTable
	 * The key is
	 * @param route an SRIPv4StaticRoute
	 */
	public void addIPv4StaticRoute(SRIPv4StaticRoute route){
		
		String keystring = route.getNetwork() + "/" + route.getMask() + "/" + route.getNextHop();
		//System.out.println("Adding route with key " + keystring);
		this.ipv4Routes.put(keystring, route);
	}
	
	/**
	 * Get the full map of all ipv4 static routes
	 * key = hash using network, mask and nexthop
	 * value = static route object
	 * @return Hashtable of all configured ipv4 static routes
	 */
	public Hashtable<String, SRIPv4StaticRoute> getIPv4StaticRoutes(){
		return this.ipv4Routes;
	}
	
	/** Is a static route configured with the provided network, mask and next hop **/
	public boolean hasStaticRoute(String network, String mask, String nexthop){
		String keystring = network + "/" + mask + "/" + nexthop;
		return this.ipv4Routes.containsKey(keystring);
		
	}
	
	/** get the static route with the provide key hash **/
	public SRIPv4StaticRoute getIPv4StaticRoute(String routekey){
		return this.ipv4Routes.get(routekey);
	}
}
