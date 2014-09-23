package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.*;
import router.alcatel.router.mpls.*;


public class MPLSInterfaceParser extends ConfigurationSection {
	
	protected SRMPLSInterface mplsInterface = null;
	
	public MPLSInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.MPLS.INTERFACE", router, contextChangeHandler);
		this.mplsInterface = new SRMPLSInterface(interfaceName);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a mpls int");
			this.router.MPLS.addInterface(this.mplsInterface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
