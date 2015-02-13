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
import router.alcatel.router.qos.SRSAPQueue;

public class SapEgressParser  extends ConfigurationSection{
	SAPEgressQOSPolicy policy; 

	public SapEgressParser(SRChassisObject router, ContextChange contextChangeHandler, int policyNumber, int depth){
		super("CONFIG.QOS.SAPEGRESS", router, contextChangeHandler);
		this.policy = new SAPEgressQOSPolicy();
		this.policy.setPolicyNumber(policyNumber);
		this.commandHash.put(Pattern.compile("^fc ([a-z]+) create"), new CommandHandler("setFCContext", true));
		this.commandHash.put(Pattern.compile("^queue ([0-9]+).*"), new CommandHandler("setQueueContext", true));
		//System.out.println("Enter SAPEgress parser at depth = " + depth + " policy = " + policyNumber);
		
		this.router = router;
	}
	
	
	public void setQueueContext(Matcher matcher){
		SAPQueueParser parser = new SAPQueueParser(router, this.getContextNotifier(), Integer.parseInt(matcher.group(1)), "CONFIG.QOS.SAPEGRESS");
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isForwardingClass()){
			this.policy.addFC( (ForwardingClass)obj);
		} else if ( obj.isSAPQueue()){
			this.policy.addQueue((SRSAPQueue)obj);
		}
	}
	
	

	public void setFCContext(Matcher matcher){
		//System.out.println("Policy " + this.policy.getPolicyNumber());
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
			//System.out.println("Exiting this policy bro " + this.policy.getPolicyNumber());
			this.getParent().addObject(this.policy);

			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
}
