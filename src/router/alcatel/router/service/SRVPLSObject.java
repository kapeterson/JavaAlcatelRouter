package router.alcatel.router.service;

import java.util.TreeMap;
public class SRVPLSObject extends SRServiceObject {

	protected TreeMap<String, SRSAPObject> saps = null;
	public SRVPLSObject(Integer vplsnumber){
		super(vplsnumber);
		saps = new TreeMap<String, SRSAPObject>();
	
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
}
