package parser.alu.config.sr7x50.routerinterace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.*;

public class InterfaceParser extends ConfigurationSection {
	protected SRNetworkInterface iface = null;
	public InterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String intName){
		super("CONFIG.INTERFACE.INTERFACE", router, contextChangeHandler);
		this.iface = new SRNetworkInterface(intName);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));

	}
	
	
	public void setDescription(Matcher matcher){
		this.iface.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.format("Exited lag %s\n", this.lag.getLagNumber().toString());
			router.Interface.addInterface(this.iface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
	
	
}
