package router.alcatel.router.ospf;

import router.alcatel.router.*;

/**
 * Models an OSPF Interface 
 * @author Kris Peterson
 *
 */
public class SROSPFInterface extends AlcatelObject{

	protected int metric = 0;
	protected String interfaceType = "broadcast";
	protected boolean isPassive = false;
	
	public SROSPFInterface(String interfacename){
		super(AlcatelObjectType.OSPFINTERFACE);
		this.setName(interfacename);
	}
	
	
	/** get the configured metric of the ospf interface **/
	public int getMetric(){
		return this.metric;
	}
	
	/** set the ospf interface metric **/
	public void setMetric(int metric){
		this.metric = metric;
	}
	
	
	/** Does the interface have a configured metric **/
	public boolean hasConfiguredMetric(){
		return (this.metric == 0);
	}
	
	
	/** set the interface type to broadcast or point-to-point **/
	public void setInterfaceType(String type){
		this.interfaceType = type;
	}
	
	/** get teh interface type **/
	public String getInterfaceType(){
		return this.interfaceType;
	}
	
	/** turn passive mode on or off **/
	public void setPassiveValue(boolean isPassive){
		this.isPassive = isPassive;
	}
	
	
	/** is the interface passive **/
	public boolean isPassive(){
		return this.isPassive;
	}
}
