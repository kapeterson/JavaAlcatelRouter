package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.*;



public class VPLSParser extends ConfigurationSection {

	protected SRVPLSObject vpls = null;
	public VPLSParser(SRChassisObject router, ContextChange contextChangeHandler, Integer vplsnumber){
		super("CONFIG.SERVICE.VPLS", router, contextChangeHandler);
		vpls = new SRVPLSObject(vplsnumber);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
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
			this.router.Services.addService(this.vpls);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
}
