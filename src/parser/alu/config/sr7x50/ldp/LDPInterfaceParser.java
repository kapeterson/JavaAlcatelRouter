package parser.alu.config.sr7x50.ldp;

import java.util.regex.Matcher;

import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.ldp.SRLDPInterface;

import parser.ConfigurationSection;

public class LDPInterfaceParser extends ConfigurationSection{
	protected SRLDPInterface ldpInterface = null;
	
	public LDPInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.LDP.INTERFACE", router, contextChangeHandler);
		this.ldpInterface = new SRLDPInterface(interfaceName);
		//this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));
	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.router.LDP.addInterface(this.ldpInterface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
