package router.alcatel.router;
import java.util.Hashtable;
import router.alcatel.router.system.*;
import router.alcatel.router.port.*;
import router.alcatel.router.lag.*;


/**
 * Top level object for the router model.  exposes all configuration sections including System, Card etc.
 * 
 * @author pete
 *
 */
public class SRChassisObject extends AlcatelHardwareObject  {

	
	//protected SRCardConfiguration = null;
	public SRCardConfiguration Cards = null;
	public SRSystemConfiguration System = null;
	public SRPortConfiguration Ports = null;
	public SRLagConfiguration Lags = null;
	
	public String chassisType;
	protected Hashtable<String, AlcatelHardwareObject> hardwareIndexMap = null;
	
	public SRChassisObject(){

		super(AlcatelObjectType.CHASSIS);
		Cards = new SRCardConfiguration();
		System = new SRSystemConfiguration();
		Ports = new SRPortConfiguration();
		Lags = new SRLagConfiguration();
		
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
}
