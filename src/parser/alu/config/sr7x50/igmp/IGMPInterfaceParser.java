package parser.alu.config.sr7x50.igmp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import parser.alu.config.sr7x50.service.IGMPStaticParser;
import router.alcatel.router.*;
import router.alcatel.router.igmp.*;
import router.alcatel.router.igmp.SRIGMPStaticJoin;


public class IGMPInterfaceParser extends ConfigurationSection{
	
	protected SRIGMPInterface igmpInterface = null;
	
	public IGMPInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.IGMP.INTERFACE", router, contextChangeHandler);
		this.igmpInterface = new SRIGMPInterface(interfaceName);
		this.commandHash.put(Pattern.compile("^static"), new CommandHandler("setStaticContext", true));

	}
	
	public void setStaticContext(Matcher matcher){
		//System.out.println("\tSTatic son");
		IGMPStaticParser parser = new IGMPStaticParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isIGMPStaticJoin()){
			this.igmpInterface.addStaticJoin((SRIGMPStaticJoin)obj);
		} else {
			System.out.println("ERROR: Invalid type added to igmp interface " + obj.getObjectType());
			System.exit(1);
		}
		
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
