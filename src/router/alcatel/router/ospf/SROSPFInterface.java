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
	
	
	public void setInterfaceType(String type){
		this.interfaceType = type;
	}
	
	public String getInterfaceType(){
		return this.interfaceType;
	}
}
