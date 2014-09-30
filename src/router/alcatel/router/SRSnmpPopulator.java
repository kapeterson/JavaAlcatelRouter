package router.alcatel.router;
import java.io.IOException;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;







import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

import router.alcatel.router.card.SRCPMObject;
import router.alcatel.router.card.SRCardObject;
import router.alcatel.router.card.SRIOMObject;
import router.alcatel.router.card.SRMDAObject;

import java.util.regex.Matcher;
import java.util.TreeMap;

/**
 * Class that will be used to populate a router object through SNMP.
 * @author Kris Peterson
 *
 */
public class SRSnmpPopulator {

	private String hostIP;
	private String snmpCommunity;
	private SRSNMPTarget targetHost;
	SRChassisObject router;
	private boolean connectionError;
	
	public SRSnmpPopulator(String ip, String community){
		hostIP = ip;
		snmpCommunity = community;
		connectionError = false;
		
		router = new SRChassisObject();
		
		try {
		targetHost = new SRSNMPTarget("udp:" + hostIP + "/161", snmpCommunity);
		targetHost.start();
		} catch ( IOException e){
			System.out.println("There was an exeption " + e.getMessage() );
			System.exit(1);
		}
		
		//targetHost.close();

	}
	
	
	public void close(){
		targetHost.close();
	}
	
	public boolean hasConnectionError(){
		return this.connectionError;
	}
	
	public void populateHostName(){
		try {
			String sysDesc = targetHost.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
			//System.out.println(sysDesc);
			router.System.setHostName(sysDesc);
			

			
			
		} catch ( Exception err){
			System.out.println("Error populating host name " + targetHost.address );
			this.connectionError = true;
		}
	}
	
	public void populateHardware(){
		try {
	
			populateHostName();
			
			if (this.connectionError)
				return;
			
			populateChassis(targetHost, this.router);
			readMiscChassisInfo(targetHost, this.router);
			
			populateCardTypes(targetHost, this.router);
			populateMDATypes(targetHost, this.router);
			
			populateIOM(targetHost, this.router);
			populateMDA(targetHost, this.router);
			populateCPM(targetHost, this.router);
			
			populateHardwareData(targetHost, router);

			
		} catch (  Exception e){
			System.out.println("There was an exeption " + e.getMessage() );
		}		
	}
	
	
	public SRChassisObject getRouter(){
		return router;
	}

	
	public void readMiscChassisInfo(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[1];
		try {
			
			//tmnxHwName
			String tmnxHwClass = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.8";

			
			oids[0] = new OID(tmnxHwClass) ;
		
			
			TreeMap<String, String> oidMap = walkOIDS(oids, host);

        	String ptrn = ".*\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	
			for ( String key : oidMap.keySet()){
				
			
	        	//System.out.println("Key: " + key + " val: " + oidMap.get(key));
	        	Matcher m = p.matcher(key);
	        	String val = oidMap.get(key);
	        	if ( val.equals("chassis")){
	        		if ( m.find()){
	        		
	        			String hwIndex = m.group(1);
	        			router.addHardwareIndexMap(hwIndex,router);
	        		
	        		} else {
	        			System.out.println("Error parsing chassis hw index" );
	        			System.exit(1);
	        		}
	        	}
	        }
			
			
			
		} catch ( RuntimeException ex ){
		      System.out.println("Exception in misc chassis info ." + ex.getMessage());
		      System.exit(1);
		}
		return;
	}
	public void populateChassis(SRSNMPTarget host, SRChassisObject router){

		OID[] oids = new OID[1];
	    try{
	    	// tmnxchassisTypeName
	    	oids[0] = new OID("1.3.6.1.4.1.6527.3.1.2.2.1.6.1.2");
	    	
	    	TreeMap<String, String> chassisTypes = walkOIDS(oids, host);
	    	Hashtable<String, String> chassisLookup = new Hashtable<String, String>();
	    	
        	String ptrn = ".*\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	
			for ( String key : chassisTypes.keySet()){
	          	Matcher m = p.matcher(key);
	        	
	        	
	        	if ( m.find()){
	        		
	        		String thisType = m.group(1);
	        		chassisLookup.put(thisType, chassisTypes.get(key));
	        		//System.out.println("Added key = " + thisType);
	        		
	        	} else {
	        		System.out.println("Error getting chassis type");
	        		System.exit(1);
	        	}
				
			}
			
			
		    try {
		    	
				// tmnsChassistype
		    	String ctype = targetHost.getAsString(new OID("1.3.6.1.4.1.6527.3.1.2.2.1.3.1.4.1"));
		    	router.setChassisType(chassisLookup.get(ctype));
		    	//System.out.println("Ctype = " + chassisLookup.get(ctype) + " for " + ctype);
			
		    } catch ( IOException e ){
		    	System.out.println("Error getting chassis type " + e.getMessage());
		    	
		    }
	    } 
	    catch(RuntimeException ex){
	      System.out.println("OID is not specified correctly in populate chassis for host " + host.getAddress() + " msg= " + ex.getMessage());
	      System.exit(1);
	    }
	    
	    
	
	}
	
	public static boolean populateCardTypes(SRSNMPTarget host, SRChassisObject rtr) throws IOException{
		
		Hashtable<String, String> typeHash = new Hashtable<String, String>();
		OID oid = null;
	    try{
	    	// card types
	    	oid = new OID(".1.3.6.1.4.1.6527.3.1.2.2.3.9.1.2");
	    }
	    catch(RuntimeException ex){
	      System.out.println("OID is not specified correctly for populatecardtypes host " + rtr.System.getHostName() + " " + host.getAddress() );
	      System.exit(1);
	    }
	    
	    
	    TreeUtils treeUtils = new TreeUtils(host.getSNMP(), new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.getSubtree(host.getTarget(), oid);
	    if(events == null || events.size() == 0){
	      System.out.println("No result returned.");
	      System.exit(1);
	    }
	    
	    // Get snmpwalk result.
    	String ptrn = "\\.([0-9]+)$";
    	Pattern p = Pattern.compile(ptrn);
  
	    for (TreeEvent event : events) {
	      if(event != null){
	        if (event.isError()) {
	            System.err.println("oid [" + oid + "] " + event.getErrorMessage());
	          }
	            
	        VariableBinding[] varBindings = event.getVariableBindings();
	        if(varBindings == null || varBindings.length == 0){
	          System.out.println("No result returned in cardtypehash");
	        }
	        
	        for (VariableBinding varBinding : varBindings) {
	        	String fullOID = varBinding.getOid().toString();
	        	String thisType = varBinding.getVariable().toString();
	        	
	          	Matcher m = p.matcher(fullOID);
	        	
	        	String thisOID = fullOID;
	        	if ( m.find()){
	        		thisOID = m.group(1);
	        	}
	        	
	        	typeHash.put(thisOID, thisType);
	        	//System.out.println("OID= " + fullOID + "   type = " + thisType);
	        }
	      }
	    }
	    		
		
		rtr.Cards.setCardTypes(typeHash);
		return true;
	}


	
	public void populateCPM(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[2];
		try {
			
			//tmnxCpmCardEquippedType
			String tmnxCpmCardEquippedType = "1.3.6.1.4.1.6527.3.1.2.2.3.4.1.6";;

			// tmnxCpmCardHwIndex
			String hwIndexOID = "1.3.6.1.4.1.6527.3.1.2.2.3.4.1.7";
			
			oids[0] = new OID(tmnxCpmCardEquippedType) ;
			oids[1] = new OID(hwIndexOID) ;			
			
			TreeMap<String, String> oidMap = walkOIDS(oids, host);
        	String ptrn = "(.*)\\.([0-9]+)\\.([0-9]+)\\.[0-9]+$";
        	Pattern p = Pattern.compile(ptrn);
        	
			for ( String key : oidMap.keySet()){
				
				
				
	        	Matcher m = p.matcher(key);

	        	if (m.find()){
	        		String oid = m.group(1);
	        		String slot = m.group(3);

	        		
	        		if ( oid.equals(tmnxCpmCardEquippedType)){
	        			
	        			//System.out.println("Equipped Key: " + key + " valu= " + oidMap.get(key));
	    				SRCPMObject cpm = new SRCPMObject(Integer.parseInt(slot), router.Cards.getCardTypeByIndex(oidMap.get(key)));
	    				router.Cards.addCard(cpm.getSlotNumber(), cpm);
	    				
	        		} else if ( oid.equals(hwIndexOID)){
	        			//System.out.println("Index Key: " + key + " valu= " + oidMap.get(key));
	        			SRCPMObject cpm = (SRCPMObject)router.Cards.getCard(Integer.parseInt(slot));
	        			router.addHardwareIndexMap(oidMap.get(key), cpm);
	        		}
	        	}
				
	        }
			
			
			
		} catch ( RuntimeException ex ){
		      System.out.println("Exception ." + ex.getMessage());
		      System.exit(1);
		}
		return;
		
	}
	
	public void populateIOM(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[2];
		try {
			
			//
			String tmnxCardEquippedType = "1.3.6.1.4.1.6527.3.1.2.2.3.2.1.5";;

			// tmnxCardHwIndex
			String hwIndexOID = "1.3.6.1.4.1.6527.3.1.2.2.3.2.1.6";
			
			oids[0] = new OID(tmnxCardEquippedType) ;
			oids[1] = new OID(hwIndexOID) ;			
			
			TreeMap<String, String> oidMap = walkOIDS(oids, host);
        	String ptrn = "(.*)\\.[0-9]+\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	
			for ( String key : oidMap.keySet()){
				
	        	Matcher m = p.matcher(key);

	        	if (m.find()){
	        		String oid = m.group(1);
	        		String card = m.group(2);

	        		
	        		if ( oid.equals(tmnxCardEquippedType)){
	        			
	    				String typeIndex = oidMap.get(key);
	    				String typeName = router.Cards.getCardTypeByIndex(typeIndex);
	    				
	    				if ( typeName == null)
	    					typeName = "EMPTY";
	    				
	    				SRCardObject tcard = new SRIOMObject(Integer.parseInt(card), typeName);
	    				router.Cards.addCard(tcard.getSlotNumber(), tcard);
	    				
	        		} else if ( oid.equals(hwIndexOID)){

						
						SRCardObject srcard = router.Cards.getCard(Integer.parseInt(card));
						
						if ( srcard != null)
							router.addHardwareIndexMap(oidMap.get(key), srcard);
	        		}
	        	}

	        }
			
			
			
		} catch ( RuntimeException ex ){
		      System.out.println("Exception ." + ex.getMessage());
		      System.exit(1);
		}
	}
		
	
	public void populateMDA(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[2];
		try {
			
			// tmnxMDAQuippedType
			String equippedMDAOID = "1.3.6.1.4.1.6527.3.1.2.2.3.8.1.5";
			// tmnxMDAHwIndex

			String hwIndexOID = "1.3.6.1.4.1.6527.3.1.2.2.3.8.1.6";
			
			oids[0] = new OID(equippedMDAOID) ;
			oids[1] = new OID(hwIndexOID) ;			
			
			TreeMap<String, String> oidMap = walkOIDS(oids, host);
        	String ptrn = "(.*)\\.[0-9]+\\.([0-9]+)\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	
			for ( String key : oidMap.keySet()){
				
	        	Matcher m = p.matcher(key);

	        	if (m.find()){
	        		String oid = m.group(1);
	        		String card = m.group(2);
	        		String mda = m.group(3);
	        		
	        		if ( oid.equals(equippedMDAOID)){
	        			//System.out.println("Match equpped oid");
		        		SRIOMObject routerCard = (SRIOMObject)router.Cards.getCard(Integer.parseInt(card));
		        		
		        		
		        		String typeName = router.Cards.getMDATypeByIndex(oidMap.get(key));
		        		SRMDAObject mdaObject = new SRMDAObject(typeName);

		        		routerCard.setMDA(Integer.parseInt(mda), mdaObject);
	        		} else if ( oid.equals(hwIndexOID)){

						
						SRIOMObject srcard = (SRIOMObject)router.Cards.getCard(Integer.parseInt(card));
						SRMDAObject mdaObj = srcard.getMDA(Integer.parseInt(mda));
						if ( mdaObj != null)
							router.addHardwareIndexMap(oidMap.get(key), mdaObj);
	        		}
	        	}

	        }
			
			
			
		} catch ( RuntimeException ex ){
		      System.out.println("Exception ." + ex.getMessage());
		      System.exit(1);
		}
	}
	
	
	public void populateMDATypes(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[1];
		try {
			
			Hashtable<String, String> typeHash = new Hashtable<String, String>();
			oids[0] = new OID(".1.3.6.1.4.1.6527.3.1.2.2.3.10.1.2") ;
			TreeMap<String, String> mdaHash = walkOIDS(oids, host);
			
	    	String ptrn = "\\.([0-9]+)$";
	    	Pattern p = Pattern.compile(ptrn);
	    	
			for ( String key : mdaHash.keySet()){
			    //System.out.println("OID: " + key + " val = " + mdaHash.get(key));	
				// have to extract the type from the oid, then the string as the value
	          	Matcher m = p.matcher(key);
	        	
	        	String thisOID = key;
	        	if ( m.find()){
	        		thisOID = m.group(1);
	        	}
	        	
	        	
	        	typeHash.put(thisOID, mdaHash.get(key));
			}
			
			router.Cards.setMDATypes(typeHash);
			
		} catch ( RuntimeException ex ){
		      System.out.println("OID is not specified correctly.");
		      System.exit(1);
		}
	}
	
	
	
	

	
	public TreeMap<String, String> walkOIDS(OID[] oids, SRSNMPTarget host){
		
		TreeMap<String, String> walkHash = new TreeMap<String, String>();
		
		TreeUtils treeUtils = new TreeUtils(host.getSNMP(), new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.walk(host.getTarget(), oids);
	    if(events == null || events.size() == 0){
	      System.out.println("No result returned in WalkOIDS.");
	      System.exit(1);
	    }
	    
	    for (TreeEvent event : events) {
		      if(event != null){
		        if (event.isError()) {
		            System.err.println("Error walking OIDS " + event.getErrorMessage() + " for host " + host.getAddress());
		            this.connectionError = true;
		          }
		            
		        VariableBinding[] varBindings = event.getVariableBindings();
		        if(varBindings == null || varBindings.length == 0){
		         // System.out.println("No result returned.");
		        }
		        
		        for (VariableBinding varBinding : varBindings) {
		        	
		        	String fullOID = varBinding.getOid().toString();
		        	String val = varBinding.getVariable().toString();

		        	walkHash.put(fullOID, val);
		        }
		      }
	    }
	    
	    return walkHash;	
	
	}
		
	/*
	public static Hashtable<String, String> walkOIDS(OID[] oids, SRSNMPTarget host){
		
		Hashtable<String, String> walkHash = new Hashtable<String, String>();
		
		TreeUtils treeUtils = new TreeUtils(host.getSNMP(), new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.walk(host.getTarget(), oids);
	    if(events == null || events.size() == 0){
	      System.out.println("No result returned in WalkOIDS.");
	      System.exit(1);
	    }
	    
	    for (TreeEvent event : events) {
		      if(event != null){
		        if (event.isError()) {
		            System.err.println("Error walking OIDS " + event.getErrorMessage());
		          }
		            
		        VariableBinding[] varBindings = event.getVariableBindings();
		        if(varBindings == null || varBindings.length == 0){
		         // System.out.println("No result returned.");
		        }
		        
		        for (VariableBinding varBinding : varBindings) {
		        	
		        	String fullOID = varBinding.getOid().toString();
		        	String val = varBinding.getVariable().toString();

		        	walkHash.put(fullOID, val);
		        }
		      }
	    }
	    
	    return walkHash;
	}
	
	*/
	
    public void populateHardwareData(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[3];
		
		String pnOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.4";  // Part number
		String snOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.5";  // serial number
		String mdOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.6";  // Manufacture DAte
	    try{
	    	// card types
	    	oids[0] = new OID(pnOID); // Board Number AKA Part Number
	    	oids[1] = new OID(snOID); // Serial Number
	    	oids[2] = new OID(mdOID); // Manufacture Date
	    	
	    	TreeMap<String, String> hwData = walkOIDS(oids, host);
	    	
	       	String ptrn = "(.*)\\.([0-9]+)\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	
	    	for ( String key : hwData.keySet()){
	    		
	    		Matcher m = p.matcher(key);
	        	
	        	String thisIndex = key;
	        	String oidval = key;
	        	if ( m.find()){
	        		thisIndex = m.group(3);
	        		oidval = m.group(1);
	        	}
	        	AlcatelHardwareObject hw = null;
	        	
	        	// continue if router doesn't have the index in the map
				if ( !router.hasHardwareIndex(thisIndex)){

					continue;
    			} 
				
				hw = router.getHardwareByIndex(thisIndex);
				
				if ( hw == null)
					continue;
    	
	        	
	        	
	    		String val = hwData.get(key);
	        	if ( oidval.equals(pnOID) ) {
	        		hw.setPartNumber(val);
	        	} else if ( oidval.equals(snOID)){
	        		hw.setSerialNumber(val);
	        	} else if ( oidval.equals(mdOID)){
	        		hw.setManufactureDate(val);
	        	}
	    	}
	    	
	    }
	    catch(RuntimeException ex){
	      System.out.println("OID is not specified correctly in populate Hardware data for host " + host.getAddress());
	      System.exit(1);
	 
	    }
	    

		return;
	}


}
