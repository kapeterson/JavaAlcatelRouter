package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.*;



public class VPLSParser extends ConfigurationSection {

	protected SRVPLSObject vpls = null;
	public VPLSParser(SRChassisObject router, ContextChange contextChangeHandler, Integer vplsnumber){
		super("CONFIG.SERVICE.VPLS", router, contextChangeHandler);
		vpls = new SRVPLSObject(vplsnumber);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^sap (.*) create"), new CommandHandler("setSAPContext", true));
		this.commandHash.put(Pattern.compile("^spoke\\-sdp (.*) create"), new CommandHandler("setSpokeSDPContext", true));
		this.commandHash.put(Pattern.compile("^mesh\\-sdp (.*) create"), new CommandHandler("setMeshSDPContext", true));



	}
	
	public void setMeshSDPContext(Matcher matcher){
		//System.out.println("Spoke sdp " + matcher.group(1));
		SRServiceSDPObject sdp = new SRServiceSDPObject(AlcatelObjectType.MESHSDPOBJECT, matcher.group(1));
		this.vpls.addSDPObject(sdp);
	}
	
	
	public void setSpokeSDPContext(Matcher matcher){
		//System.out.println("Spoke sdp " + matcher.group(1));
		SRServiceSDPObject sdp = new SRServiceSDPObject(AlcatelObjectType.SPOKESDPOBJECT, matcher.group(1));
		this.vpls.addSDPObject(sdp);
	}
	public void setSAPContext(Matcher matcher){
		//System.out.println("SAP " + matcher.group(1));
		SRSAPObject newSAP = new SRSAPObject(matcher.group(1));
		this.vpls.addSAPObject(newSAP);
	}
	
	public void setDescription(Matcher matcher){
		this.vpls.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Adding vpls " + this.vpls.getServiceNumber());
			this.router.Services.addVPLS(this.vpls);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
}
