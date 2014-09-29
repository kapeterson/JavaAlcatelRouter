package parser.alu.config.sr7x50.bgp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.bgp.*;
import parser.ConfigurationSection;


public class BGPGroupParser extends ConfigurationSection {
	protected SRBGPGroup group;
	
	public BGPGroupParser(SRChassisObject router, ContextChange contextChangeHandler, String groupName){
		super("CONFIG.BGP.GrOUP", router, contextChangeHandler);
		this.group = new SRBGPGroup(groupName);
		this.commandHash.put(Pattern.compile("^neighbor (.*)"), new CommandHandler("addNeighbor", true));

	}
	
	
	public void addNeighbor(Matcher matcher){
		this.group.addNeighbor(matcher.group(1));
	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a group " + this.group.getName());
			this.router.BGP.addBGPGroup(this.group);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
	

}
