package parser.alu.config.sr7x50.port;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.port.SREthernetNetwork;
import router.alcatel.router.port.SRPortEthernet;

public class EthernetParser extends ConfigurationSection {

	protected SRPortEthernet ethernet = new SRPortEthernet();
	
	public EthernetParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.PORT.ETHERNET", router, contextChangeHandler);	
		this.commandHash.put(Pattern.compile("^encap\\-type (.*)"), new CommandHandler("setEncapType", false));
		this.commandHash.put(Pattern.compile("^network$"), new CommandHandler("setNetworkContext", false));

	
	}
	
	public void setNetworkContext(Matcher matcher){
		EthernetNetworkParser parser = new EthernetNetworkParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}

	
	public void setEncapType(Matcher matcher){
		//System.out.println("Encap type " + matcher.group(1));
		this.ethernet.setEncapType(matcher.group(1));
	}
	

	public void addObject(AlcatelObject obj){
		if ( obj.isEthernetNetwork()){
			this.ethernet.NETWORK = (SREthernetNetwork)obj;
		}
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		if ( this.getLastCommandDepth() == this.sectionDepth) {
			//System.out.println("Exiting port " + port.getName());
			this.getParent().addObject(this.ethernet);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
