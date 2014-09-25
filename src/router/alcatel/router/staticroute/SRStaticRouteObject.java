package router.alcatel.router.staticroute;
import router.alcatel.router.*;

public abstract class SRStaticRouteObject extends AlcatelObject implements StaticRoute{
	
	protected int tag = 0;
	protected int metric = 1;
	protected int preference = 5;
	protected boolean enabled = true;
	
	public SRStaticRouteObject(){
		super(AlcatelObjectType.STATICROUTE);
	}
	
	public boolean isStaticRoute(){
		return true;
	}
	
	public boolean isIPv4Route(){
		return false;
	}
	
	public boolean isIPv6Route(){
		return false;
	}
	
	public void setTag(int tag){
		this.tag = tag;
	}
	
	public void setMetric(int metric){
		this.metric = metric;
	}
	
	public int getTag(){
		return this.tag;
	}
	
	public int getMetric(){
		return this.metric;
	}
	
	public int getPreference(){
		return this.preference;
	}
	
	public void setPreference(int pref){
		this.preference = pref;
	}
	
	public void disableRoute(){
		this.enabled = false;
	}
	
	public void enableRoute(){
		this.enabled = true;
	}
	
	public boolean isEnabled(){
		return this.enabled;
		
	}
	
	public boolean isNextHopRoute(){
		return false;
	}
	
	public boolean isBlackHoleRoute(){
		return false;
	}
	
	public boolean isIndirectRoute(){
		return false;
	}
	
}
