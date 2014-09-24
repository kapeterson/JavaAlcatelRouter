package parser.alu.config.sr7x50.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPObject;
import parser.CommandHandler;

public class SAPParser extends ConfigurationSection{
	
	protected SRSAPObject sap = null;
	
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
			
			((VPLSParser)this.getParent()).addSAP(this.sap);
			//System.out.println("Adding vpls " + this.vpls.getServiceNumber());
			//this.router.Services.addSDP(this.sdp);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
}
