package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class MDAIngressParser extends ConfigurationSection {

	public MDAIngressParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD.IOM.MDA.INGRESS",router, contextChangeHandler);

	}
	
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
	
}
