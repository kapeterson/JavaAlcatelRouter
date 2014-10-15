package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.igmp.SRIGMPStaticJoin;

public class StaticJoinGroupParser extends ConfigurationSection{

	SRIGMPStaticJoin join;
	public StaticJoinGroupParser(SRChassisObject router, ContextChange contextChangeHandler, String groupAddress){
		super("CONFIG.IGMP..STATIC.GROUP", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^starg"), new CommandHandler("setStarg", true));
		this.commandHash.put(Pattern.compile("^source (.*)"), new CommandHandler("setSource", true));

		this.join = new SRIGMPStaticJoin(groupAddress);
	}
	
	public void setSource(Matcher matcher){
		//System.out.println("\t\tSTARG");
		this.join.setSource(matcher.group(1));
	}
	
	
	public void setStarg(Matcher matcher){
		//System.out.println("\t\tSTARG");
		this.join.setSource("starg");
	}
	
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.join);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
}
