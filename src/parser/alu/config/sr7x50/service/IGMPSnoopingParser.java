package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;

public class IGMPSnoopingParser extends ConfigurationSection{

	public IGMPSnoopingParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SERVICE.SAP.IGMPSNOOPING", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^static"), new CommandHandler("setStaticContext", true));


	}
	

	public void addObject(AlcatelObject object){
		
	}
	
	public void setStaticContext(Matcher matcher){
		//System.out.println("\tSTatic son");
		IGMPStaticParser parser = new IGMPStaticParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
