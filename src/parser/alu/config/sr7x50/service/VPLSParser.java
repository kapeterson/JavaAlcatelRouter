package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.*;



public class VPLSParser extends ConfigurationSection {

	protected SRVPLSObject vpls = null;
	
	public VPLSParser(SRChassisObject router, ContextChange contextChangeHandler, Integer vplsnumber){
		super("CONFIG.SERVICE.VPLS", router, contextChangeHandler);
		vpls = new SRVPLSObject(vplsnumber);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^sap ([0-9]+\\/[0-9]+\\/[0-9]+.*) (split.*)? create"), new CommandHandler("setSAPContext", true));

		this.commandHash.put(Pattern.compile("^sap (lag\\-[0-9]+.*) (split.*)? create"), new CommandHandler("setSAPContext", true));
		this.commandHash.put(Pattern.compile("^sap (lag\\-[0-9]+.*) create"), new CommandHandler("setSAPContext", true));

		this.commandHash.put(Pattern.compile("^spoke\\-sdp (.*) create"), new CommandHandler("setSpokeSDPContext", true));
		this.commandHash.put(Pattern.compile("^mesh\\-sdp (.*) create"), new CommandHandler("setMeshSDPContext", true));



	}
	
	

	public void setMeshSDPContext(Matcher matcher){
		//System.out.println("Spoke sdp " + matcher.group(1));
		SRSDPObject sdpObject = this.router.Services.getSDP(Integer.parseInt(matcher.group(1).split(":")[0]));
		SRServiceSDPObject sdp = new SRServiceSDPObject(AlcatelObjectType.MESHSDPOBJECT, matcher.group(1), sdpObject);
		this.vpls.addSDPObject(sdp);
	}
	
	public void addSAP(SRSAPObject sap){
		this.vpls.addSAPObject(sap);
	}
	
	public void setSpokeSDPContext(Matcher matcher){
		//System.out.println("Spoke sdp " + matcher.group(1));
		//SRServiceSDPObject sdp = new SRServiceSDPObject(AlcatelObjectType.SPOKESDPOBJECT, matcher.group(1));
		//this.vpls.addSDPObject(sdp);
		
		SRSDPObject sdpObj = this.router.Services.getSDP(Integer.parseInt(matcher.group(1).split(":")[0]));
		ServiceSDPParser parser = new ServiceSDPParser(router, this.contextChange, matcher.group(1), AlcatelObjectType.SPOKESDPOBJECT, sdpObj);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	public void setSAPContext(Matcher matcher){
		//System.out.println("SAP " + matcher.group(1));
		//SRSAPObject newSAP = new SRSAPObject(matcher.group(1));
		//this.vpls.addSAPObject(newSAP);
		SAPParser parser = new SAPParser(router, this.contextChange, matcher.group(1), this.vpls);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	

	public void setDescription(Matcher matcher){
		this.vpls.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.router.Services.addVPLS(this.vpls);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
	public void addObject(AlcatelObject object){
		
		if ( object.isSAPObject()){
			this.vpls.addSAPObject((SRSAPObject)object);
		} else if ( object.isServiceSDPObject()){
			//System.out.println("Adding objectd  " + object.getName());
			this.vpls.addSDPObject((SRServiceSDPObject)object);
		}
	}
}
