package parser.alu.config.sr7x50.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.*;

import parser.CommandHandler;
import router.alcatel.router.port.*;
import router.alcatel.router.lag.*;

public class SAPParser extends ConfigurationSection{
	
	protected SRSAPObject sap = null;
	
	protected Pattern portPattern = Pattern.compile("([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?");
	protected Pattern lagPattern = Pattern.compile("lag\\-([0-9]+)(:)?([0-9]+)?");

	public SAPParser(SRChassisObject router, ContextChange contextChangeHandler, String sapName, AlcatelObject parentService){
		super("CONFIG.SERVICE.SAP", router, contextChangeHandler);
		System.out.println("SAP : " + sapName);
		sap = new SRSAPObject(sapName, parentService);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^ingress$"), new CommandHandler("changeIngressContext", true));
		this.commandHash.put(Pattern.compile("^egress$"), new CommandHandler("changeEgressContext", true));

	}
	

	public void changeIngressContext(Matcher matcher){
		SAPIngressParser parser = new SAPIngressParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void changeEgressContext(Matcher matcher){
		SAPEgressParser parser = new SAPEgressParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	
	public void setDescription(Matcher matcher){
		this.sap.setDescription(matcher.group(1));
	}
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isSAPIngressObject()){
			this.sap.INGRESS = (SRSAPIngress)obj;
		} else if ( obj.isSAPEgressObject()){
			this.sap.EGRESS = (SRSAPEgress)obj;
		} else {
			System.out.println("ERROR: Could not add " + obj.getObjectType() + " : " + obj.getName() + " to sap");
			System.exit(1);
		}
		
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
				Integer lagNumber = Integer.parseInt(mlag.group(1));
				if ( this.router.Lags.hasLag(lagNumber)){
					
					SRLagObject lag = this.router.Lags.getLag(lagNumber);
					lag.addAssociation(this.sap);
					
				} else {
					System.out.println("ERROR: Error finding lag " + lagNumber + " when adding association in sap config ");

				}
			}
			
			 if(!foundBinding) {
					System.out.println("Error parsing port value in sap parser " + sname);
					System.exit(1);
			}
		 
			this.getParent().addObject(this.sap);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
			
			
		}
	}
}
