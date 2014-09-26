package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRMDAObject;

public class MDANetworkParser extends ConfigurationSection{

	public MDANetworkParser(SRChassisObject router, ContextChange contextChangeHandler, SRMDAObject mda){
		super("CONFIG.CARD.IOM.MDA.NETWORK", router, contextChangeHandler);
	}
	
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
