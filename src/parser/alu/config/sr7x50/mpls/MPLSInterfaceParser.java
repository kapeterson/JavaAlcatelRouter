package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.*;
import router.alcatel.router.mpls.*;


public class MPLSInterfaceParser extends ConfigurationSection {
	
	protected SRMplsInterface mplsInterface = null;
	
	public MPLSInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.MPLS.INTERFACE", router, contextChangeHandler);
		this.mplsInterface = new SRMplsInterface(interfaceName);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.println("Added a mpls int");
			this.router.Router.MPLS.addInterface(this.mplsInterface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
}
