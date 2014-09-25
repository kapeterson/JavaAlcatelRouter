package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRServiceSDPObject;

public class ServiceSDPParser extends ConfigurationSection {
	
	 
	protected SRServiceSDPObject sdp = null;
	
	public ServiceSDPParser(SRChassisObject router, ContextChange contextChangeHandler, String sdpName, AlcatelObjectType sdpType){
		super("CONFIG.SERVICE.IES.INTERFACE.SDP", router, contextChangeHandler);
		
		if ( sdpType != AlcatelObjectType.MESHSDPOBJECT && sdpType != AlcatelObjectType.SPOKESDPOBJECT){
			System.out.println("Error invalid sdp type in ServiceSDP Parser");
			System.exit(1);
		}
		
		this.sdp = new SRServiceSDPObject(sdpType, sdpName);
		// = new SRSAPObject(sapName);
		//this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
	}
	

	@Override
	public void exitSection(Matcher matcher) {
		// TODO Auto-generated method stub
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			
			this.getParent().addObject(this.sdp);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	


	
}
