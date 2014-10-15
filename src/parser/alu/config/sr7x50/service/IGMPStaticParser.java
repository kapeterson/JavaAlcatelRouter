package parser.alu.config.sr7x50.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.igmp.SRIGMPStaticJoin;

public class IGMPStaticParser extends ConfigurationSection{
	
	protected List<SRIGMPStaticJoin> joins = new ArrayList<SRIGMPStaticJoin>();
	public IGMPStaticParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.IGMP.STATIC", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^group (.*)"), new CommandHandler("setGroup", true));

	}
	
	

	public void addObject(AlcatelObject object){
		
		if ( object.isIGMPStaticJoin()){
			this.getParent().addObject((SRIGMPStaticJoin)object);
		}
	}
	
	
	public void setGroup(Matcher matcher){
		//System.out.println("\tgroup " + matcher.group(1));
		String groupAddress = matcher.group(1);
		StaticJoinGroupParser parser = new StaticJoinGroupParser(this.router, this.getContextNotifier(), groupAddress);
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
