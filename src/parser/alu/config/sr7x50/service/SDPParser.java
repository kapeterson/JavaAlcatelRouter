package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;

import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSDPObject;;

public class SDPParser extends ConfigurationSection{
	protected SRSDPObject sdp = null;
	public SDPParser(SRChassisObject router, ContextChange contextChangeHandler, Integer vplsnumber){
		super("CONFIG.SERVICE.SDP", router, contextChangeHandler);
		sdp = new SRSDPObject(vplsnumber);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
	}
	

	public void setDescription(Matcher matcher){
		this.sdp.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Adding vpls " + this.vpls.getServiceNumber());
			this.router.Services.addSDP(this.sdp);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
}
