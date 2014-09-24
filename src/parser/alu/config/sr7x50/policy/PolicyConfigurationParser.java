package parser.alu.config.sr7x50.policy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;

import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class PolicyConfigurationParser extends ConfigurationSection {
	
	public PolicyConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.POLICY", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^community \"(.*)\" members (.*)"), new CommandHandler("setCommunityContext", true));
	}
	
	
	public void setCommunityContext(Matcher matcher){
		
		Pattern memberPattern = Pattern.compile("\"([0-9]+):([0-9]+)");
		System.out.println("Community weeeee " + matcher.group(1));
		//System.out.println("\t members " + matcher.group(2));
		String[] members = matcher.group(2).split(" ");
		Matcher m = memberPattern.matcher(matcher.group(2));
		//String[] matches = matcher.group(2).match("\"[0-9]+:[0-9]+\"");
		/*
		CommunityParser parser = new CommunityParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		*/
		
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
