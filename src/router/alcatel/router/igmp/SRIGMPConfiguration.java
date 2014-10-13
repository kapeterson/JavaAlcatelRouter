package router.alcatel.router.igmp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import router.alcatel.router.pim.SRPIMInterface;


/**
 * Models IGMP Configuration on 7x50
 * @author Kris Peterson
 *
 */
public class SRIGMPConfiguration {
	
	/** TreeMap of IGMP Interfaces.  Key=name value=SRIGMPInterface **/
	protected TreeMap<String, SRIGMPInterface> interfaces;
	protected ArrayList<SRSSMTranslation> ssmList = new ArrayList<SRSSMTranslation>();
	
	public SRIGMPConfiguration(){
		this.interfaces = new TreeMap<String, SRIGMPInterface>();
	}
	
	
	/** Adds the IGMPInterface to the configuration **/
	public void addInterface(SRIGMPInterface igmpInterface){
		
		this.interfaces.put(igmpInterface.getName(), igmpInterface);
	}
	
	
	/** Get the IGMPInterface with the given name **/
	public SRIGMPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	
	/** Get a TreeMap of all the IGMPInterfaces **/
	public TreeMap<String, SRIGMPInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
	public void addSSM(SRSSMTranslation ssmtranslation){
		this.ssmList.add(ssmtranslation);
	}
	
	public ArrayList<SRSSMTranslation> getTranslations(){
		return this.ssmList;
	}
}
