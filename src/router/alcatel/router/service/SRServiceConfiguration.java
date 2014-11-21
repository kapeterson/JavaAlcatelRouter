package router.alcatel.router.service;

import java.util.TreeMap;

/**
 * Models the Service configuration on the 7x50 
 * @author kp109p
 *
 */
public class SRServiceConfiguration {
	
	/** Map of all configured VPLS **/
	protected TreeMap<Integer, SRVPLSObject> vpls;
	
	/** Map of all configured IES **/
	protected TreeMap<Integer, SRIESObject> ies;
	
	/** Map of all configured SDP **/
	protected TreeMap<Integer, SRSDPObject> sdp;

	public SRServiceConfiguration(){
		this.vpls = new TreeMap<Integer, SRVPLSObject>();
		this.ies = new TreeMap<Integer, SRIESObject>();
		this.sdp = new TreeMap<Integer, SRSDPObject>();

	}
	
	/** Add the VPLS Object to the configuration **/
	public void addVPLS(SRVPLSObject service){
		this.vpls.put(service.getServiceNumber(), service);
	}
	
	/**
	 * Get the Map of all configured VPLS
	 * key = vplsnumber
	 * value = vplsobject
	 * @return TreeMap of all configured VPLS
	 */
	public TreeMap<Integer, SRVPLSObject> getVPLSs(){
		return this.vpls;
	}
	
	/** Get the vpls with the provided vpls number **/
	public SRVPLSObject getVPLS(Integer serviceNumber){
		return this.vpls.get(serviceNumber);
	}
	
	/** Add the vpls object to the configuration **/
	public void addIES(SRIESObject ies){
		this.ies.put(ies.getServiceNumber(), ies);
	}
	
	/**
	 * Get a Map of all configured IES
	 * key = IES Number
	 * value = IES Object
	 * @return TreeMap of all configured IES
	 */
	public TreeMap<Integer, SRIESObject> getIESs(){
		return this.ies;
	}
	
	/** Get the IES Object with the provided ies number **/
	public SRIESObject getIES(Integer iesNumber){
		return this.ies.get(iesNumber);
	}
	
	/** Add the sdp to the service configuration **/
	public void addSDP(SRSDPObject sdp){
		this.sdp.put(sdp.getServiceNumber(), sdp);
	}
	
	/** Get the SDP with the provided sdp number **/
	public SRSDPObject getSDP(Integer snum){
		return this.sdp.get(snum);
	}
	
	/**
	 * Map of all configured SDP
	 * key = sdp number
	 * value = SDP Object
	 * @return TreeMap of all configured SDP
	 */
	public TreeMap<Integer, SRSDPObject> getSDPs(){
		return this.sdp;
	}
	
	/** Is an sdp configured with the provided sdp number **/
	public boolean hasSDP(String sdpnumberstring){
		return this.sdp.containsKey(Integer.parseInt(sdpnumberstring));
	}
	
	/** Is an sdp configured with the provided sdp numbrer in string format **/
	public boolean hasSDP(Integer sdpnumber){
		return this.sdp.containsKey(sdpnumber);
	}
	
	public boolean hasIES(Integer iesNumber){
		return this.ies.containsKey(iesNumber);
	}
}
