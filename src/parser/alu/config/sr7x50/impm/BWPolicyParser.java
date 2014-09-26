package parser.alu.config.sr7x50.impm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class BWPolicyParser extends ConfigurationSection {

	public BWPolicyParser(SRChassisObject router, ContextChange contextChangeHandler, String policyName){
		super("CONFIG.IMPM.BWPOLICY", router, contextChangeHandler);
		//this.commandHash.put(Pattern.compile("^bandwidth\\-policy \"(.*)\" create"), new CommandHandler("setBandwidthPolicyContext", true));
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
