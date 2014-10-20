package router.alcatel.router.mpls;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models an MPLS Path
 * @author Kris Peterson
 *
 */
public class SRMplsPath extends AlcatelObject {

	/** is the mpls path shutdown **/
	protected boolean isShutdown = true;
	
	/** Map of all the hops with hop index as the key and the hop object as the value **/
	protected TreeMap<Integer, SRMplsHop> hops = new TreeMap<Integer, SRMplsHop>();
	
	
	public SRMplsPath(String name){
		super(AlcatelObjectType.MPLSPATH);
		this.setName(name);
	}
	
	
	/** is the mpls path enabled **/
	public void adminUp(){
		this.isShutdown = false;
	}
	
	/** is the mpls path admin down **/
	public void adminDown(){
		this.isShutdown = true;
	}
	
	/** set the state of the path to shutdown **/
	public void shutdown(){
		this.isShutdown = true;
	}
	
	/** set the state of the path to 'no shutdown' **/
	public void noShutdown(){
		this.isShutdown = false;
	}
	
	/** set the state of the path to shutdown **/
	public void setShutdown(boolean shut){
		this.isShutdown = shut;
	}
	
	
	/** Is the MPLS Path shutdown ? **/
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	/** is the mpls path admin up ? **/
	public boolean isAdminUp(){
		return !(this.isShutdown());
	}
	
	
	/** add the hop to the MPLS Path **/
	public void addHop(SRMplsHop hop){
		this.hops.put(hop.getHopNumber(), hop);
	}
	
	/** Return a list of all the HOPs configured on the MPLS Path **/
	public TreeMap<Integer, SRMplsHop> getHops(){
		return this.hops;
	}
	
	
	/** get the hop with the provided hop number **/
	public SRMplsHop getHop(Integer hopnumber){
		return this.hops.get(hopnumber);
	}
}
