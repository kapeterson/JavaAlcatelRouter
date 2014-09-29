package parser.alu.config.sr7x50.igmp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.*;
import router.alcatel.router.igmp.*;


public class IGMPInterfaceParser extends ConfigurationSection{
	
	protected SRIGMPInterface igmpInterface = null;
	
	public IGMPInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.IGMP.INTERFACE", router, contextChangeHandler);
		this.igmpInterface = new SRIGMPInterface(interfaceName);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a pim int");
			this.router.Router.IGMP.addInterface(this.igmpInterface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
