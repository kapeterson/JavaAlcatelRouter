package parser.alu.config.sr7x50.impm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.impm.SRBandwidthPolicy;

public class BWPolicyParser extends ConfigurationSection {
	
	protected SRBandwidthPolicy bwPolicy = null;
	
	public BWPolicyParser(SRChassisObject router, ContextChange contextChangeHandler, String policyName){
		super("CONFIG.IMPM.BWPOLICY", router, contextChangeHandler);
		//this.commandHash.put(Pattern.compile("^bandwidth\\-policy \"(.*)\" create"), new CommandHandler("setBandwidthPolicyContext", true));
		this.bwPolicy = new SRBandwidthPolicy(policyName);
	}
	
	/**
	 * Use customer exit
	 */
	public void exitSection(Matcher matcher){
		 
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getParent().addObject(this.bwPolicy);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}	
	}
}
