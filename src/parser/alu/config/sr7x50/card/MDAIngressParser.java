package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class MDAIngressParser extends ConfigurationSection {

	public MDAIngressParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD.IOM.MDA.INGRESS",router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^mcast\\-path\\-management$"), new CommandHandler("setIMPMContext",false));
	}
	
	
	public void setIMPMContext(Matcher matcher){
		System.out.println("MCASTMGMT context");
	}
	
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
	
}
