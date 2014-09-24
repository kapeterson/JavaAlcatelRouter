package parser.alu.config.sr7x50.policy;


import java.util.regex.Matcher;

import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.policy.*;
import parser.ConfigurationSection;


public class PolicyStatementParser extends ConfigurationSection {
	protected SRPolicyStatement statement = null;
	public PolicyStatementParser(SRChassisObject router, ContextChange contextChangeHandler, String policyName){
		super("CONFIG.POLICY.STATEMENT", router, contextChangeHandler);
		this.statement = new SRPolicyStatement(policyName);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a statement " + this.statement.getName());
			this.router.Policy.addStatement(this.statement);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
