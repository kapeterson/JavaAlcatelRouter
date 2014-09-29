package parser.alu.config.sr7x50.rsvp;

import java.util.regex.Matcher;

import parser.ConfigurationSection;

import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

import router.alcatel.router.rsvp.SRRSVPInterface;

public class RSVPInterfaceParser extends ConfigurationSection {
	
	protected SRRSVPInterface rsvpInterface = null;
	
	public RSVPInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.RSVP.INTERFACE", router, contextChangeHandler);
		this.rsvpInterface = new SRRSVPInterface(interfaceName);
		//this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));
	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.router.Router.RSVP.addInterface(this.rsvpInterface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
