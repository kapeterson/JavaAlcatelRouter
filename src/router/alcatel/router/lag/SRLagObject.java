package router.alcatel.router.lag;
import router.alcatel.router.*;
import router.alcatel.router.port.*;

import java.util.Hashtable;
/**
 * Models SR 7x50 Lag 
 * @author Kris Peterson
 *
 */
public class SRLagObject extends AlcatelObject{
	
	/** Lag description **/
	protected String description = "";
	
	/** Admin status of the lag **/
	protected boolean isShutdown = true;
	
	/** Lag number */
	protected Integer lagnumber = 0;
	
	protected Hashtable<String, SRPortObject> ports = null;
	
	public SRLagObject(){
		super(AlcatelObjectType.LAG);
		this.ports = new Hashtable<String, SRPortObject>();
		this.lagnumber = 0;
	}
	
	public SRLagObject(Integer lagNumber){
		super(AlcatelObjectType.LAG);
		this.ports = new Hashtable<String, SRPortObject>();
		this.lagnumber = lagNumber;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}
	
	public void addPort(SRPortObject port){
		ports.put(port.getName(), port);
	}
	
	
	/**
	 * Returns the port hash for the lag key=portname value=portObject
	 * @return hashtable with portname nad port object
	 */
	public Hashtable<String, SRPortObject> getPorts(){
		return this.ports;
	}
	
	public void setLagNumber(Integer lagnumber){
		this.lagnumber = lagnumber;
	}
	
	public Integer getLagNumber(){
		return this.lagnumber;
	}
}
