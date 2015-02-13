package parser.alu.config.sr7x50.qos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.ForwardingClass;
import router.alcatel.router.qos.SAPEgressQOSPolicy;
import router.alcatel.router.qos.SAPIngressQOSPolicy;

public class SapIngressParser extends ConfigurationSection{
	SAPIngressQOSPolicy policy; 

	public SapIngressParser(SRChassisObject router, ContextChange contextChangeHandler, int policyNumber, int depth){
		super("CONFIG.QOS.SAPINGRESS", router, contextChangeHandler);
		this.policy = new SAPIngressQOSPolicy();
		this.policy.setPolicyNumber(policyNumber);
		this.commandHash.put(Pattern.compile("^fc \"?([a-z]+)\"? create"), new CommandHandler("setFCContext", true));
		
		//System.out.println("Enter SAPIngress parser at depth = " + depth + " policy = " + policyNumber);
		
		this.router = router;
	}
	public void addObject(AlcatelObject obj){
		
		if ( obj.isForwardingClass()){
			this.policy.addFC( (ForwardingClass)obj);
		}
	}
	
	

	public void setFCContext(Matcher matcher){
		//System.out.println("Policy " + this.policy.getPolicyNumber() + " has a fc");
		ForwardingClassParser parser = new ForwardingClassParser(router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		//ystem.out.println("Checking exit egress policy" + this.policy.getPolicyNumber() + " depth 1 = " + this.getSectionDepth() + " depth2 = " + this.getLastCommandDepth());

		if ( this.getSectionDepth() == this.getLastCommandDepth() || this.getSectionDepth() >= this.getLastCommandDepth() ) {
			//System.out.println("Exiting this ingress policy bro " + this.policy.getPolicyNumber());
			this.getParent().addObject(this.policy);

			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
