package parser.alu.config.sr7x50.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPObject;
import parser.CommandHandler;
import router.alcatel.router.port.*;

public class SAPParser extends ConfigurationSection{
	
	protected SRSAPObject sap = null;
	
	protected Pattern portPattern = Pattern.compile("([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?");
	protected Pattern lagPattern = Pattern.compile("lag\\-([0-9]+)(:)?([0-9]+)?");

	public SAPParser(SRChassisObject router, ContextChange contextChangeHandler, String sapName){
		super("CONFIG.SERVICE.SAP", router, contextChangeHandler);
		sap = new SRSAPObject(sapName);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
	}
	

	public void setDescription(Matcher matcher){
		this.sap.setDescription(matcher.group(1));
	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			
			// set the assocation to the port now
			String sname = this.sap.getName();
			Matcher m = portPattern.matcher(this.sap.getName());
					
			boolean foundBinding = false;
			
			if ( m.find()){
				foundBinding = true;
				String portNumber = m.group(1);
				//System.out.println("Found port binding on sap for " + portNumber);
				if ( this.router.Ports.hasPort(portNumber)){
					
					SRPortObject port = this.router.Ports.getPort(portNumber);
					port.addAssociation(this.sap);
					
				} else {
					System.out.println("ERROR: Error finding port " + portNumber + " when adding association in sap config ");
				}
			}
			
			Matcher mlag = lagPattern.matcher(this.sap.getName());
			if ( mlag.find()){
				foundBinding = true;
			}
			
			 if(!foundBinding) {
					System.out.println("Error parsing port value in sap parser " + sname);
					System.exit(1);
			}
			((VPLSParser)this.getParent()).addSAP(this.sap);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
			
			
		}
	}
}
