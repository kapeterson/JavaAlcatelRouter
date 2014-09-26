package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRMDANetwork;
import router.alcatel.router.card.SRMDAObject;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class MDANetworkParser extends ConfigurationSection{

	protected SRMDANetwork network = null;
	
	public MDANetworkParser(SRChassisObject router, ContextChange contextChangeHandler, SRMDAObject mda){
		super("CONFIG.CARD.IOM.MDA.NETWORK", router, contextChangeHandler);
		this.network = new SRMDANetwork(mda);
		this.commandHash.put(Pattern.compile("^queue\\-policy \"(.*)\"$"), new CommandHandler("setIngressQueuePolicy",false));

	}
	
	public void setIngressQueuePolicy(Matcher matcher){
		
		SRNetworkQueueQOSPolicy policy = this.router.QOS.getNetworkQueueQOSPolicy(matcher.group(1));
		if ( policy == null){
			System.out.println("ERROR: QUEUE policy " + matcher.group(1) + " was not found when adding to mda ingress");
			System.exit(1);
		}
		
		this.network.INGRESS.setIngressQOS(policy);
		
	}
	
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getParent().addObject(this.network);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
	
}
