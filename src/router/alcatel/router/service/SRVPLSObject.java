package router.alcatel.router.service;

import java.util.TreeMap;
public class SRVPLSObject extends SRServiceObject {

	protected TreeMap<String, SRSAPObject> saps = null;
	protected TreeMap<String, SRServiceSDPObject> sdps = null;
	
	public SRVPLSObject(Integer vplsnumber){
		super(vplsnumber);
		this.setName(vplsnumber.toString());
		saps = new TreeMap<String, SRSAPObject>();
		sdps = new TreeMap<String, SRServiceSDPObject>();
	
	}
	
	public void addSAPObject(SRSAPObject sap){
		
		this.saps.put(sap.getSAPName(), sap);
	}
	
	public TreeMap<String, SRSAPObject> getSAPs(){
		return this.saps;
	}
	
	public SRSAPObject getSAP(String sapname){
		return this.saps.get(sapname);
	}
	
	public boolean isVPLSObject(){
		return true;
	}
	
	public void addSDPObject(SRServiceSDPObject sdp){
		
		SRSDPObject sdpObj = (SRSDPObject)sdp.getParent();
		sdpObj.addAssociation(sdp);
		this.sdps.put(sdp.getSDPName(), sdp);
	}
	
	public SRServiceSDPObject getSDP(String sdpname){
		return this.sdps.get(sdpname);
	}
	
	public TreeMap<String, SRServiceSDPObject> getSDPs(){
		return this.sdps;
	}
	
	public int getSAPCount(){
		return this.saps.size();
	}
}
