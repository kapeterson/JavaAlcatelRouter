package parser.alu.config.sr7x50.pim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.*;
import router.alcatel.router.pim.*;

public class PIMInterfaceParser extends ConfigurationSection{
	
	protected SRPIMInterface pimInterface = null;
	public PIMInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.PIM.INTERFACE", router, contextChangeHandler);
		this.pimInterface = new SRPIMInterface(interfaceName);
		//System.out.println("Instantiated port configuration parser");
		//this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));
	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a pim int");
			this.router.Router.PIM.addInterface(this.pimInterface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
