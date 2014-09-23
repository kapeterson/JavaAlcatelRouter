package router.alcatel.router;
import java.util.Hashtable;
import router.alcatel.router.system.*;
import router.alcatel.router.port.*;
import router.alcatel.router.lag.*;
import router.alcatel.router.qos.*;
import router.alcatel.router.filter.*;
import router.alcatel.router.routerinterface.*;
import router.alcatel.router.service.*;
import router.alcatel.router.ospf.*;
import router.alcatel.router.pim.*;

/**
 * Top level object for the router model.  exposes all configuration sections including System, Card etc.
 * 
 * @author Kris Peterson
 *
 */
public class SRChassisObject extends AlcatelHardwareObject  {

	
	//protected SRCardConfiguration = null;
	public SRCardConfiguration Cards = null;
	public SRSystemConfiguration System = null;
	public SRPortConfiguration Ports = null;
	public SRLagConfiguration Lags = null;
	public SRQOSConfiguration QOS = null;
	public SRFilterConfiguration Filters = null;
	public SRInterfaceConfiguration Interface = null;
	public SRServiceConfiguration Services = null;
	public SROSPFConfiguration OSPF = null;
	public SRPIMConfiguration PIM = null;
	
	public String chassisType;
	protected Hashtable<String, AlcatelHardwareObject> hardwareIndexMap = null;
	
	public SRChassisObject(){

		super(AlcatelObjectType.CHASSIS);
		Cards = new SRCardConfiguration();
		System = new SRSystemConfiguration();
		Ports = new SRPortConfiguration();
		Lags = new SRLagConfiguration();
		QOS = new SRQOSConfiguration();
		Filters = new SRFilterConfiguration();
		Interface = new SRInterfaceConfiguration();
		Services = new SRServiceConfiguration();
		OSPF = new SROSPFConfiguration();
		PIM = new SRPIMConfiguration();
		
		chassisType = "NA";
		hardwareIndexMap = new Hashtable<String, AlcatelHardwareObject>();
	}
	
    public boolean hasHardwareIndex(String indx){
    	return hardwareIndexMap.containsKey(indx);
    }
    
    public AlcatelHardwareObject getHardwareByIndex(String indx){
    	return hardwareIndexMap.get(indx);
    }
    public void addHardwareIndexMap(String indx, AlcatelHardwareObject hw){
    		hardwareIndexMap.put(indx, hw);
    }
	public String getChassisType(){
		return chassisType;
	}
	
	/**
	 * Sets the chassis type
	 * @param chassis  String value for the chassis type
	 */
	public void setChassisType(String chassis){
		chassisType = chassis;
	}
	
	/**
	 * Returns whether or not the object is a chassis Object
	 * O
	 * @return True for all instances
	 */
	@Override
	public boolean isChassisObject(){
		return true;
	}
	
	
	/**
	 * Is the router a Multicast Backbone Router (LVH01/02) ?
	 * @return boolean
	 */
	public boolean isBB(){
		return this.System.getHostName().matches(".*LVH0[1-2]$");
	}
	
	/**
	 * Is the router an IDR Router (LVH07/08) ?
	 * @return boolean
	 */
	public boolean isIDR(){
		return this.System.getHostName().matches(".*LVH0[7-8]$");
	}
	
	/**
	 * Is the router an ODR Router ( LVH11, 12, 13, 14 ...)
	 * @return boolean
	 */
	public boolean isODR(){
		return this.System.getHostName().matches(".*LVH1[1-9]$");
	}
	
	/**
	 * Is the router a Collection Router (LVH21/22/23/24 ...) ?
	 * @return boolean
	 */
	public boolean isCR(){
		return this.System.getHostName().matches(".*LVH2[1-8]$");
	}
	
	
	/**
	 * Is the router an IO Router (*LIO) ?
	 * @return boolean
	 */
	public boolean isIO(){
		return this.System.getHostName().matches(".*LIO[0-9]$");
	}
	
	
	/**
	 * Is the router a CO Router (*LSW) ?
	 * @return boolean
	 */
	public boolean isCO(){
		return this.System.getHostName().matches(".*LSW[0-9]$");
	}
}
