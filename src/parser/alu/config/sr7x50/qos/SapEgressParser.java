package parser.alu.config.sr7x50.qos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.SAPEgressQOSPolicy;

public class SapEgressParser  extends ConfigurationSection{
	SAPEgressQOSPolicy policy; 

	public SapEgressParser(SRChassisObject router, ContextChange contextChangeHandler, int policyNumber){
		super("CONFIG.QOS.SAPEGRESS", router, contextChangeHandler);
		this.policy = new SAPEgressQOSPolicy();
		this.policy.setPolicyNumber(policyNumber);

		this.router = router;
	}
	
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.policy);

			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
}
