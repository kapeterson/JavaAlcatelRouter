package router.alcatel.router.staticroute;
import router.alcatel.router.*;

/**
 * MOdel a configured static route on the 7x50
 * @author Kris Peterson
 *
 */
public abstract class SRStaticRouteObject extends AlcatelObject implements StaticRoute{
	
	/** Configured tag on the static route **/
	protected int tag = 0;
	
	/** Configured metric on the static route **/
	protected int metric = 1;
	
	/** configured preference on the static route **/
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
	
	/** set the tag of the static route **/
	public void setTag(int tag){
		this.tag = tag;
	}
	
	/** Set the metric of the static route **/
	public void setMetric(int metric){
		this.metric = metric;
	}
	
	/** get the configured tag of the static route **/
	public int getTag(){
		return this.tag;
	}
	
	/** get the configured metric of the static route **/
	public int getMetric(){
		return this.metric;
	}
	
	/** get the configured preference of the static route **/
	public int getPreference(){
		return this.preference;
	}
	
	/** Set the preference of the static route **/
	public void setPreference(int pref){
		this.preference = pref;
	}
	
	/** set the staitc route mode to disabled **/
	public void disableRoute(){
		this.enabled = false;
	}
	
	
	/** set the static route mode to enabled **/
	public void enableRoute(){
		this.enabled = true;
	}
	
	/** is the static route enabled **/
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
