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

public class RouterSnmpTest02 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		SRChassisObject router = new SRChassisObject();
		//router.System.setHostName("LIO1");
		//System.out.println("Host name is " + router.System.getHostName());
		
		try {
			if (args.length < 2){
				System.out.println("Error you must supply ip and community");
				return;
			}
			String ip = args[0];
			String comm = args[1];
			SRSNMPTarget targetHost = new SRSNMPTarget("udp:" + ip + "/161", comm);
			targetHost.start();
			String sysDesc = targetHost.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
			System.out.println(sysDesc);
			
			long start = System.nanoTime();
			Hashtable<String, String> thash = getCardTypeHash(targetHost);
			router.Cards.setCardTypes(thash);

			long elapsedTime = ( System.nanoTime() - start );
			
			System.out.println("Total time = " + elapsedTime + " nanosecs");
			elapsedTime = elapsedTime / 1000000;
			System.out.println("Total time = " + elapsedTime + "ms to read card types");
			
			Hashtable<Integer, String> equippedHash = getEquippedCards(targetHost);
			
			for ( Integer key : equippedHash.keySet()){
				String typeIndex = equippedHash.get(key);
				String typeName = router.Cards.getCardTypeByIndex(typeIndex);
				
				if ( typeName == null)
					typeName = "EMPTY";
				
				SRCardObject tcard = new SRCardObject(key, typeName);
				router.Cards.addCard(key, tcard);
			
			}
			
			for ( Integer key : router.Cards.getCards().keySet()){
				
				SRCardObject card = router.Cards.getCard(key);
				
				System.out.println("Card " + card.getSlotNumber() + " CardType= " + card.getCardType() );
			}
			
			
		} catch (  IOException e){
			System.out.println("There was an exeption " + e.getMessage() );
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
	      System.out.println("No result returned.");
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
	          System.out.println("No result returned.");
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

	public static Hashtable<String, String> getCardTypeHash(SRSNMPTarget host) throws IOException{
		
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
	          System.out.println("No result returned.");
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
	    		
		
		return typeHash;
		
	}

	public static void populateHardwareData(SRChassisObject router){
		
	}
}
