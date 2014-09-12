package parser.alu.config.sr7x50.card;

import parser.*;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MDAParser extends ConfigurationSection {

	SRMDAObject mda = null;
	SRIOMObject iom = null;
	
	int complex = -1;
	public MDAParser(SRChassisObject router, ContextChange contextChangeHandler, SRIOMObject iom,  int mdaNumber){
		super("CONFIG.CARD.IOM.MDA",router, contextChangeHandler);
		
		this.mda = new SRMDAObject(mdaNumber);
		this.iom = iom;
		this.complex = mdaNumber;
		this.commandHash.put(Pattern.compile("mda\\-type (.*)"), new CommandHandler("setMDAType",false));
	}
	
	public void setMDAType(Matcher matcher){
		this.mda.setMDAType(matcher.group(1));
	}
	
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			//System.out.println("exit");
			this.iom.setMDA(this.mda.getComplex(), this.mda);
			//super.defaultExitHandler(matcher);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
}
