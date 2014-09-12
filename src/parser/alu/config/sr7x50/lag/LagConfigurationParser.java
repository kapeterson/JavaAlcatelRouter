package parser.alu.config.sr7x50.lag;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class LagConfigurationParser extends ConfigurationSection {

	public LagConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.LAG", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^lag ([0-9]+)"), new CommandHandler("setLagContext", true));

	}
	
	
	public void setLagContext(Matcher matcher){
		//System.out.println("OKaaaaaaaaaaaaaaay " + matcher.group(1));
		//System.out.println(this.getCurrentLine());
		//System.out.format("Lag %s\n", matcher.group(1));
		Integer lagnumber = Integer.parseInt(matcher.group(1));
		LagParser lagparser = new LagParser(this.router, this.getContextNotifier(), lagnumber);
		lagparser.setSectionDepth(this.getLastCommandDepth());
		lagparser.setParent(this);
		this.getContextNotifier().contextChangeCallback(this, lagparser);
	}
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
