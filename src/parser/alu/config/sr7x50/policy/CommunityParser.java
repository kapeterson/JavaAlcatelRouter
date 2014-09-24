package parser.alu.config.sr7x50.policy;

import java.util.regex.Matcher;

import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.policy.*;
import parser.ConfigurationSection;

public class CommunityParser extends ConfigurationSection{
	protected SRPolicyCommunity community = null;
	public CommunityParser(SRChassisObject router, ContextChange contextChangeHandler, String communityName){
		super("CONFIG.POLICY.COMMUNITY", router, contextChangeHandler);
		this.community = new SRPolicyCommunity(communityName);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a community ");
			this.router.Policy.addCommunity(this.community);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
