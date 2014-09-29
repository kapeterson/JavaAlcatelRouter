package router.alcatel.router.mpls;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMplsPath extends AlcatelObject {

	protected boolean isShutdown = true;
	protected TreeMap<Integer, SRMplsHop> hops = new TreeMap<Integer, SRMplsHop>();
	
	
	public SRMplsPath(String name){
		super(AlcatelObjectType.MPLSPATH);
		this.setName(name);
	}
	
	public void adminUp(){
		this.isShutdown = false;
	}
	
	public void adminDown(){
		this.isShutdown = true;
	}
	
	public void shutdown(){
		this.isShutdown = true;
	}
	
	public void noShutdown(){
		this.isShutdown = false;
	}
	
	public void setShutdown(boolean shut){
		this.isShutdown = shut;
	}
	
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	public boolean isAdminUp(){
		return !(this.isShutdown());
	}
	
	public void addHop(SRMplsHop hop){
		this.hops.put(hop.getHopNumber(), hop);
	}
	
	public TreeMap<Integer, SRMplsHop> getHops(){
		return this.hops;
	}
	
	public SRMplsHop getHop(Integer hopnumber){
		return this.hops.get(hopnumber);
	}
}
