package parser.alu.config.sr7x50.port;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.port.SREthernetNetwork;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class EthernetNetworkParser extends ConfigurationSection {

	protected SREthernetNetwork network = new SREthernetNetwork();
	
	public EthernetNetworkParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.PORT.ETHERNET.NETWORK", router, contextChangeHandler);	
		this.commandHash.put(Pattern.compile("^queue-policy \"(.*)\""), new CommandHandler("setQueuePolicy", false));

	}
	
	
	public void setQueuePolicy(Matcher matcher){
		SRNetworkQueueQOSPolicy policy = this.router.QOS.getNetworkQueueQOSPolicy(matcher.group(1));
		if ( policy == null){
			System.out.println("ERROR: could not find queue policy " + matcher.group(1) + " to apply to port ");
			System.exit(1);
		}
		this.network.setQueuePolicy(policy);
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		if ( this.getLastCommandDepth() == this.sectionDepth) {

			this.parent.addObject(this.network);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
