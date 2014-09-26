package parser.alu.config.sr7x50.impm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class IMPMConfigurationParser extends ConfigurationSection {
	
	public IMPMConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.IMPM", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^bandwidth\\-policy \"(.*)\" create"), new CommandHandler("setBandwidthPolicyContext", true));

	}
	
	
	public void setBandwidthPolicyContext(Matcher matcher){
		//System.out.println("Create bandwidth policy " + matcher.group(1));
		BWPolicyParser parser = new BWPolicyParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
	
}
