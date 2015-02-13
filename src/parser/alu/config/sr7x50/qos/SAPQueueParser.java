package parser.alu.config.sr7x50.qos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.SRSAPQueue;

public class SAPQueueParser extends ConfigurationSection {
	
	SRSAPQueue queue = null;
	
	public SAPQueueParser(SRChassisObject router, ContextChange contextChangeHandler, int queuenumber, String parentContextName){
		super(parentContextName+".QUEUE", router, contextChangeHandler);
		queue = new SRSAPQueue(queuenumber);
		
		this.commandHash.put(Pattern.compile("^rate (.*) cir (.*)"), new CommandHandler("setRate", true));
		this.commandHash.put(Pattern.compile("^mbs (.*)"), new CommandHandler("setMBS", true));
		this.commandHash.put(Pattern.compile("^cbs (.*)"), new CommandHandler("setCBS", true));
	}
	
	
	public void setMBS(Matcher matcher){
		this.queue.setMBS(matcher.group(1));
	}
	
	public void setCBS(Matcher matcher){
		this.queue.setCBS(matcher.group(1));
	}
	
	public void setRate(Matcher matcher){
		//System.out.println("Got us a rate = " + matcher.group(1) + " cir = " + matcher.group(2));
		this.queue.setRate(matcher.group(1));
		this.queue.setCIR(matcher.group(2));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		//ystem.out.println("Checking exit egress policy" + this.policy.getPolicyNumber() + " depth 1 = " + this.getSectionDepth() + " depth2 = " + this.getLastCommandDepth());

		if ( this.getSectionDepth() == this.getLastCommandDepth() || this.getSectionDepth() >= this.getLastCommandDepth() ) {
			//System.out.println("Exiting this policy bro " + this.policy.getPolicyNumber());
			this.getParent().addObject(this.queue);

			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
