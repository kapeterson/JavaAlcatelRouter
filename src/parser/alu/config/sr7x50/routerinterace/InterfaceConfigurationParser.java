package parser.alu.config.sr7x50.routerinterace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;


public class InterfaceConfigurationParser extends ConfigurationSection {
	public InterfaceConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.INTERFACE", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));

	}
	
	public void setInterfaceContext(Matcher matcher){

		InterfaceParser parser = new InterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
