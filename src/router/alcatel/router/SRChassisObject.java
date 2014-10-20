package router.alcatel.router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import router.alcatel.router.system.*;
import router.alcatel.router.port.*;
import router.alcatel.router.lag.*;
import router.alcatel.router.qos.*;
import router.alcatel.router.filter.*;
import router.alcatel.router.router.SRRouterConfiguration;
import router.alcatel.router.routerinterface.*;
import router.alcatel.router.service.*;
import router.alcatel.router.ospf.*;
import router.alcatel.router.pim.*;
import router.alcatel.router.igmp.*;
import router.alcatel.router.mpls.*;
import router.alcatel.router.ldp.*;
import router.alcatel.router.rsvp.*;
import router.alcatel.router.policy.*;
import router.alcatel.router.bgp.*;
import router.alcatel.router.card.SRCardConfiguration;
import router.alcatel.router.staticroute.*;
import router.alcatel.router.impm.*;


/**
 * Top level object for the router model.  exposes all configuration sections including System, Card etc.
 * 
 * @author Kris Peterson
 *
 */
public class SRChassisObject extends AlcatelHardwareObject {

	
	/** Exposes the card configuration **/
	public SRCardConfiguration Cards = null;
	
	/** Exposes the System configuration **/
	public SRSystemConfiguration System = null;
	
	/** Exposes the Port Configuration **/
	public SRPortConfiguration Ports = null;
	
	/** Exposes the Lag Configuration **/
	public SRLagConfiguration Lags = null;
	
	/** Exposes the QOS Configuration section **/
	public SRQOSConfiguration QOS = null;
	
	/** Exposes Filter Configuration section **/
	public SRFilterConfiguration Filters = null;
	
	/** Exposes Service Configuration Section **/
	public SRServiceConfiguration Services = null;
	
	/** Exposes the Policy configuration section **/
	public SRPolicyConfiguration Policy = null;
	
	/** Exposes the IMPM Configuraiton section **/
	public SRIMPMConfiguration IMPM = null;
	
	
	/** Exposes router configuratin including MPLS, Interfaces, OSPF etc **/
	public SRRouterConfiguration Router = null;
	
	/** Chassis Type **/
	public String chassisType;
	
	/** Maps index to the hardware object **/
	protected Hashtable<String, AlcatelHardwareObject> hardwareIndexMap = null;
	
	public SRChassisObject(){

		super(AlcatelObjectType.CHASSIS);
		Cards = new SRCardConfiguration();
		System = new SRSystemConfiguration();
		Ports = new SRPortConfiguration();
		Lags = new SRLagConfiguration();
		QOS = new SRQOSConfiguration();
		Filters = new SRFilterConfiguration();
		Services = new SRServiceConfiguration();
		Policy = new SRPolicyConfiguration();

		this.IMPM = new SRIMPMConfiguration();
		this.Router = new SRRouterConfiguration();
		
		chassisType = "NA";
		hardwareIndexMap = new Hashtable<String, AlcatelHardwareObject>();
	}
	
	
	/**
	 * Does the chassis have an object with the provided index
	 * @param indx - SNMP Index to search
	 * @return boolean
	 */
    public boolean hasHardwareIndex(String indx){
    	return hardwareIndexMap.containsKey(indx);
    }
    
    /**
     * Returns a reference to the hardware object provided in index
     * @param indx
     * @return Hardware Object
     */
    public AlcatelHardwareObject getHardwareByIndex(String indx){
    	return hardwareIndexMap.get(indx);
    }
    
    /**
     * adds a hardware to index mapping
     * @param indx
     * @param hw
     */
    public void addHardwareIndexMap(String indx, AlcatelHardwareObject hw){
    		hardwareIndexMap.put(indx, hw);
    }
    
    /** Returns the chassis type **/
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

}
