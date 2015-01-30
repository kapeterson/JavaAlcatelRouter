package parser.alu.config.sr7x50.qos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.ForwardingClass;

public class ForwardingClassParser extends ConfigurationSection {

	ForwardingClass fc;
	public ForwardingClassParser(SRChassisObject router, ContextChange contextChangeHandler, String fcname){
		super("CONFIG.QOS.SAPEGRESS.FC", router, contextChangeHandler);
		this.fc = new ForwardingClass(fcname);
		//System.out.println("In the fc parser bro " + fcname);
		this.commandHash.put(Pattern.compile("^queue ([0-9]+)"), new CommandHandler("setQueue", true));

	}
	
	public void setQueue(Matcher matcher){
		this.fc.setQueue(Integer.parseInt(matcher.group(1)));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){

		if ( this.getSectionDepth() == this.getLastCommandDepth() || this.getSectionDepth() == (this.getLastCommandDepth()-1)) {
			//System.out.println("Exiting FC at depth =  " + this.getLastCommandDepth());
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
			this.getParent().addObject(this.fc);

		}
	}
}
