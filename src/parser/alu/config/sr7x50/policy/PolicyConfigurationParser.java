package parser.alu.config.sr7x50.policy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.policy.SRPolicyCommunity;

public class PolicyConfigurationParser extends ConfigurationSection {
	protected Pattern memberPattern = Pattern.compile("\"([0-9]+:[0-9]+)");

	public PolicyConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.POLICY", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^community \"(.*)\" members (.*)"), new CommandHandler("setCommunityContext", true));
		this.commandHash.put(Pattern.compile("^policy\\-statement \"(.*)\""), new CommandHandler("setStatementContext", true));

	}
	
	
	public void setStatementContext(Matcher matcher){
		
		PolicyStatementParser parser = new PolicyStatementParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void setCommunityContext(Matcher matcher){
		
		SRPolicyCommunity comm = new SRPolicyCommunity(matcher.group(1));
		
		Matcher m = memberPattern.matcher(matcher.group(2));
		while ( m.find()){
			comm.addMember(m.group(1));
		}
		
		router.Policy.addCommunity(comm);
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
