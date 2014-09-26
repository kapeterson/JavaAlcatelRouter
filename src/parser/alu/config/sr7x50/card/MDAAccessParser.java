package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRMDAObject;

public class MDAAccessParser extends ConfigurationSection {

	public MDAAccessParser(SRChassisObject router, ContextChange contextChangeHandler, SRMDAObject mda){
		super("CONFIG.CARD.IOM.MDA.ACCESS", router, contextChangeHandler);
	}
	
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
