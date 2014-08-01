package alcatel.router;
import java.io.IOException;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SRSnmpPopulator {

	private String hostIP;
	private String snmpCommunity;
	private SRSNMPTarget targetHost;
	SRChassisObject router;
	
	public SRSnmpPopulator(String ip, String community){
		hostIP = ip;
		snmpCommunity = community;
		
		router = new SRChassisObject();
		try {
		targetHost = new SRSNMPTarget("udp:" + hostIP + "/161", snmpCommunity);
		targetHost.start();
		} catch ( IOException e){
			System.out.println("There was an exeption " + e.getMessage() );
			System.exit(1);
		}

	}
	
	public void populateHardware(){
		try {
			
			

			String sysDesc = targetHost.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
			//System.out.println(sysDesc);
			router.System.setHostName(sysDesc);
			getCardTypeHash(targetHost, this.router);

			
			
			Hashtable<Integer, String> equippedHash = getEquippedCards(targetHost);
			
			for ( Integer key : equippedHash.keySet()){
				String typeIndex = equippedHash.get(key);
				String typeName = router.Cards.getCardTypeByIndex(typeIndex);
				
				if ( typeName == null)
					typeName = "EMPTY";
				
				SRCardObject tcard = new SRCardObject(key, typeName);
				router.Cards.addCard(key, tcard);
			
			}
			
			populateIOMIndex(targetHost, router);
			populateHardwareData(targetHost, router);

			
		} catch (  IOException e){
			System.out.println("There was an exeption " + e.getMessage() );
		}		
	}
	
	public SRChassisObject getRouter(){
		return router;
	}
	
	
	public static boolean getCardTypeHash(SRSNMPTarget host, SRChassisObject rtr) throws IOException{
		
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
	      System.out.println("No result returned in getting equipped cards");
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
	          System.out.println("No result returned. in getting equipped cards");
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

	public static void populateHardwareData(SRSNMPTarget host, SRChassisObject router){
		OID oid = null;
		OID[] oids = new OID[3];
		
		String pnOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.4";  // Part number
		String snOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.5";  // serial number
		String mdOID = "1.3.6.1.4.1.6527.3.1.2.2.1.8.1.6";  // Manufacture DAte
	    try{
	    	// card types
	    	oids[0] = new OID(pnOID); // Board Number AKA Part Number
	    	oids[1] = new OID(snOID); // Serial Number
	    	oids[2] = new OID(mdOID); // Manufacture Date
	    	
	    	
	    }
	    catch(RuntimeException ex){
	      System.out.println("OID is not specified correctly.");
	      System.exit(1);
	    }
	    
	    //OID[] oids = { oid };
	    
	    TreeUtils treeUtils = new TreeUtils(host.getSNMP(), new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.walk(host.getTarget(), oids);
	    if(events == null || events.size() == 0){
	      System.out.println("No result returned.");
	      System.exit(1);
	    }
	    

	    
	    for (TreeEvent event : events) {
	      if(event != null){
	        if (event.isError()) {
	            System.err.println("oid [" + oid + "] " + event.getErrorMessage());
	          }
	            
	        VariableBinding[] varBindings = event.getVariableBindings();
	        if(varBindings == null || varBindings.length == 0){
	         // System.out.println("No result returned.");
	        }
	        
	        for (VariableBinding varBinding : varBindings) {
	        	String fullOID = varBinding.getOid().toString();
	        	String val = varBinding.getVariable().toString();

	        	String ptrn = "(.*)\\.([0-9]+)\\.([0-9]+)$";
	        	Pattern p = Pattern.compile(ptrn);
	        	Matcher m = p.matcher(fullOID);
	        	
	        	String thisIndex = fullOID;
	        	String oidval = fullOID;
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


	        	if ( oidval.equals(pnOID) ) {
	        		hw.setPartNumber(val);
	        	} else if ( oidval.equals(snOID)){
	        		hw.setSerialNumber(val);
	        	} else if ( oidval.equals(mdOID)){
	        		hw.setManufactureDate(val);
	        	}

	        	
	        }
	      }
	    }
	    		
	    		
		
		return;
	}


}
