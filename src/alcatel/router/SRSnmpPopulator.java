package alcatel.router;
import java.io.IOException;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeMap;
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
	
	public boolean hadConnectionError(){
		return this.connectionError;
	}
	
	public void populateHostName(){
		try {
			String sysDesc = targetHost.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
			//System.out.println(sysDesc);
			router.System.setHostName(sysDesc);
			

			
			
		} catch ( Exception err){
			System.out.println("Error populating host" + targetHost.address );
			connectionError = true;
		}
	}
	
	public void populateHardware(){
		try {
	
			populateHostName();
			populateCardTypes(targetHost, this.router);
			populateMDATypes(targetHost, this.router);
			
			Hashtable<Integer, String> equippedHash = getEquippedCards(targetHost);
			
			for ( Integer key : equippedHash.keySet()){
				String typeIndex = equippedHash.get(key);
				String typeName = router.Cards.getCardTypeByIndex(typeIndex);
				
				if ( typeName == null)
					typeName = "EMPTY";
				
				SRCardObject tcard = new SRCardObject(key, typeName);
				router.Cards.addCard(key, tcard);
			
			}
			
			//populateEquippedMDAs(targetHost, router);
			
			populateIOMIndex(targetHost, router);
			//populateMDAIndex(targetHost, router);
			populateMDA(targetHost, router);
			populateHardwareData(targetHost, router);

			
		} catch (  Exception e){
			System.out.println("There was an exeption " + e.getMessage() );
		}		
	}
	
	public SRChassisObject getRouter(){
		return router;
	}
	
	
	public static void populateEquippedMDAs(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[1];
		try {
			
			// tmnxMDAQuippedType
			oids[0] = new OID(".1.3.6.1.4.1.6527.3.1.2.2.3.8.1.5") ;
			Hashtable<String, String> mdaHash = walkOIDS(oids, host);
			
        	String ptrn = "\\.([0-9]+)\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
			
        	for ( String key : mdaHash.keySet()){
				
	        	Matcher m = p.matcher(key);

	        	
        	
	        	if ( m.find()){
	        		//thisTypeIndex = m.group(1);
	        		String card = m.group(1);
	        		String mda = m.group(2);
	        		
	        		SRCardObject routerCard = router.Cards.getCard(Integer.parseInt(card));
	        		
	        		String typeName = router.Cards.getMDATypeByIndex(mdaHash.get(key));
	        		SRMDAObject mdaObject = new SRMDAObject(typeName);

	        		routerCard.setMDA(Integer.parseInt(mda), mdaObject);

	        	} else {
	        		
	        		System.out.println("Error couldn't parse mda type");
	        		System.exit(1);
	        	}
	        	
	        }
			
			
			
		} catch ( RuntimeException ex ){
		      System.out.println("Exception ." + ex.getMessage());
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
	      System.out.println("OID is not specified correctly.");
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

	public static void populateIOMIndex(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[1];
		try {
			
			// tmnxCardHwIndex
			oids[0] = new OID(".1.3.6.1.4.1.6527.3.1.2.2.3.2.1.6") ;
		
		} catch ( RuntimeException ex ){
		      System.out.println("OID is not specified correctly.");
		      System.exit(1);
		}
		
	    TreeUtils treeUtils = new TreeUtils(host.getSNMP(), new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.walk(host.getTarget(), oids);
	    if(events == null || events.size() == 0){
	      System.out.println("No result returned.");
	      System.exit(1);
	    }
	    
	    // Get snmpwalk result.
	    for (TreeEvent event : events) {
		    if(events == null || events.size() == 0){
			      System.out.println("No result returned.");
			      System.exit(1);
			 }
		    
	        VariableBinding[] varBindings = event.getVariableBindings();
	        if(varBindings == null || varBindings.length == 0){
	          //System.out.println("No result returned.");
	        }
	        
	        for (VariableBinding varBinding : varBindings) {
	        	
	        	String fullOID = varBinding.getOid().toString();
	        	String indx = varBinding.getVariable().toString();
	        
	        	String ptrn = "\\.([0-9]+)$";
	        	Pattern p = Pattern.compile(ptrn);
	        	Matcher m = p.matcher(fullOID);
	        	
	        	String thisCard = fullOID;
	        	if ( m.find()){
	        		thisCard = m.group(1);
	        	}
	        	//System.out.println("CARD= " + thisCard + " indx= " + indx);
	        	SRCardObject card = router.Cards.getCard(Integer.parseInt(thisCard));
	        	card.setSNMPIndex(indx);
	        	router.Cards.addIndexMap(indx, card);
	        }
	      
	    }
	}
	
	public static void populateMDA(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[2];
		try {
			
			// tmnxMDAQuippedType
			String equippedMDAOID = "1.3.6.1.4.1.6527.3.1.2.2.3.8.1.5";
			// tmnxMDAHwIndex

			String hwIndexOID = "1.3.6.1.4.1.6527.3.1.2.2.3.8.1.6";
			
			oids[0] = new OID(equippedMDAOID) ;
			oids[1] = new OID(hwIndexOID) ;			
			
			Hashtable<String, String> oidMap = walkOIDS(oids, host);
        	String ptrn = "(.*)\\.[0-9]+\\.([0-9]+)\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	String[] oidKeys = (String[])oidMap.keySet().toArray(new String[0]);
        	Arrays.sort(oidKeys);
        	
			for ( String key : oidKeys){
				
	        	Matcher m = p.matcher(key);

	        	if (m.find()){
	        		String oid = m.group(1);
	        		String card = m.group(2);
	        		String mda = m.group(3);
	        		
	        		if ( oid.equals(equippedMDAOID)){
	        			//System.out.println("Match equpped oid");
		        		SRCardObject routerCard = router.Cards.getCard(Integer.parseInt(card));
		        		
		        		String typeName = router.Cards.getMDATypeByIndex(oidMap.get(key));
		        		SRMDAObject mdaObject = new SRMDAObject(typeName);

		        		routerCard.setMDA(Integer.parseInt(mda), mdaObject);
	        		} else if ( oid.equals(hwIndexOID)){

						
						SRCardObject srcard = router.Cards.getCard(Integer.parseInt(card));
						SRMDAObject mdaObj = srcard.getMDA(Integer.parseInt(mda));
						if ( mdaObj != null)
							router.Cards.addIndexMap(oidMap.get(key), mdaObj);
	        		}
	        	}

	        }
			
			
			
		} catch ( RuntimeException ex ){
		      System.out.println("Exception ." + ex.getMessage());
		      System.exit(1);
		}
	}
		
	public static void populateMDATypes(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[1];
		try {
			
			Hashtable<String, String> typeHash = new Hashtable<String, String>();
			oids[0] = new OID(".1.3.6.1.4.1.6527.3.1.2.2.3.10.1.2") ;
			Hashtable<String, String> mdaHash = walkOIDS(oids, host);
			
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
	
	public static void populateMDAIndex(SRSNMPTarget host, SRChassisObject router){
		
		OID[] oids = new OID[1];
		try {
			
			// tmnxMDAHwIndex
			oids[0] = new OID(".1.3.6.1.4.1.6527.3.1.2.2.3.8.1.6") ;
			Hashtable<String, String> mdaIndexes = walkOIDS(oids, host);
			
        	String ptrn = "\\.([0-9]+)\\.([0-9]+)$";
        	Pattern p = Pattern.compile(ptrn);
        	
        	
			for ( String key : mdaIndexes.keySet()){
				//System.out.println("index oid" + key);
				Matcher m = p.matcher(key);
				if ( m.find()){
					String card = m.group(1);
					String mda = m.group(2);
					
					SRCardObject srcard = router.Cards.getCard(Integer.parseInt(card));
					SRMDAObject mdaObj = srcard.getMDA(Integer.parseInt(mda));
					if ( mdaObj != null)
						router.Cards.addIndexMap(mdaIndexes.get(key), mdaObj);
				}

			}
		} catch ( RuntimeException ex ){
		      System.out.println("OID is not specified correctly.");
		      System.exit(1);
		}
		
	}
	
	public static Hashtable<Integer, String> getEquippedCards(SRSNMPTarget host){
		OID oid = null;
		
		Hashtable<Integer, String> equippedHash = new Hashtable<Integer, String>();
		
		String tmnxCardEquippedType = "1.3.6.1.4.1.6527.3.1.2.2.3.2.1.5";
		
	    try{
	    	// card types
	    	oid = new OID(tmnxCardEquippedType);

	    }
	    catch(RuntimeException ex){
	      System.out.println("OID is not specified correctly.");
	      System.exit(1);
	    }
	    
	    
	    TreeUtils treeUtils = new TreeUtils(host.getSNMP(), new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.getSubtree(host.getTarget(), oid);
	    if(events == null || events.size() == 0){
	      System.out.println("Exiting.  No result returned in getting equipped cards");
	      System.exit(1);
	    }
	    
	    // Get snmpwalk result.
	    for (TreeEvent event : events) {
	      if(event != null){
	        if (event.isError()) {
	            System.err.println("oid [" + oid + "] " + event.getErrorMessage());
	          }
	            
	        VariableBinding[] varBindings = event.getVariableBindings();
	        if(varBindings == null || varBindings.length == 0){
	          //System.out.println("No result returned. in getting equipped cards");
	        }
	        for (VariableBinding varBinding : varBindings) {
	        	String fullOID = varBinding.getOid().toString();
	        	String thisType = varBinding.getVariable().toString();
	        	String ptrn = "\\.([0-9]+)$";
	        	Pattern p = Pattern.compile(ptrn);
	        	Matcher m = p.matcher(fullOID);
	        	
	        	String thisOID = fullOID;
	        	if ( m.find()){
	        		thisOID = m.group(1);
	        	}
	        	//System.out.println("CARD= " + fullOID + " type= " + thisType);
	        	equippedHash.put(Integer.parseInt(thisOID), thisType.toString());
	        }
	      }
	    }
	    
	    return equippedHash;
	    		
	}

	
	public static TreeMap<String, String> walkOIDSTreeMap(OID[] oids, SRSNMPTarget host){
		
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
	
	
    public static void populateHardwareData(SRSNMPTarget host, SRChassisObject router){
		OID[] oids = new OID[3];
		
		String pnOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.4";  // Part number
		String snOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.5";  // serial number
		String mdOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.6";  // Manufacture DAte
	    try{
	    	// card types
	    	oids[0] = new OID(pnOID); // Board Number AKA Part Number
	    	oids[1] = new OID(snOID); // Serial Number
	    	oids[2] = new OID(mdOID); // Manufacture Date
	    	
	    	Hashtable<String, String> hwData = walkOIDS(oids, host);
	    	
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
				if ( !router.Cards.hasHardwareIndex(thisIndex)){

					continue;
    			} 
				
				hw = router.Cards.getHardwareByIndex(thisIndex);
				
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
	      System.out.println("OID is not specified correctly.");
	      System.exit(1);
	 
	    }
	    

		return;
	}


}
