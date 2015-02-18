package router.alcatel.router.service;

import java.util.TreeMap;

/**
 * MOdels a VPLS on the 7x50
 * @author Kris Peterson
 *
 */
public class SRVPLSObject extends SRServiceObject {

	/** Map of all the SAP configured in the VPLS **/
	protected TreeMap<String, SRSAPObject> saps = null;
	
	/** Map of all SDP configured in the VPLS **/
	protected TreeMap<String, SRServiceSDPObject> sdps = null;
	
	public SRVPLSObject(Integer vplsnumber){
		super(vplsnumber);
		this.setName(vplsnumber.toString());
		saps = new TreeMap<String, SRSAPObject>();
		sdps = new TreeMap<String, SRServiceSDPObject>();
	
	}
	
	/** Add the SAP Object to the configuration **/
	public void addSAPObject(SRSAPObject sap){
		
		this.saps.put(sap.getSAPName(), sap);
	}
	
	
	/**
	 * GEt the full map of all SAP Objects in the VPLS
	 * key = sapName
	 * value = sapObject
	 * @return TreeMap of all configured SAP
	 */
	public TreeMap<String, SRSAPObject> getSAPs(){
		return this.saps;
	}
	
	/** Get the SAP Object with the provided name **/
	public SRSAPObject getSAP(String sapname){
		return this.saps.get(sapname);
	}
	
	public boolean isVPLSObject(){
		return true;
	}
	
	/** Add the Service SDP to the vpls **/
	public void addSDPObject(SRServiceSDPObject sdp){
		
		SRSDPObject sdpObj = (SRSDPObject)sdp.getParent();
		sdpObj.addAssociation(sdp);
		this.sdps.put(sdp.getSDPName(), sdp);
	}
	
	/** Get the Service SDP Object with the provided sdp name - name shoulod include sdp and vcid ( sdp:vcid )**/
	public SRServiceSDPObject getSDP(String sdpname){
		return this.sdps.get(sdpname);
	}
	
	/**
	 * Get the full Map of all configured sdp
	 * key = sdpname
	 * value = service sdp
	 * @return TreeMap of all configured service sdp
	 */
	public TreeMap<String, SRServiceSDPObject> getSDPs(){
		return this.sdps;
	}
	
	/** GEt the number of configured saps on the vpls **/
	public int getSAPCount(){
		return this.saps.size();
	}
	
	
	/** Does the vpls have the sap **/
	public boolean hasSAP(String sapName){
		
		return this.saps.containsKey(sapName);
	}
}
