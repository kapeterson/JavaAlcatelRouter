package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;


public class ServiceConfigurationParser extends ConfigurationSection {
	public ServiceConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SERVICE", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^vpls ([0-9]+) .*"), new CommandHandler("setVPLSContext", true));

	}
	
	public void setVPLSContext(Matcher matcher){

		//System.out.println("Service " + matcher.group(1));
		
		Integer vplsnumber = Integer.parseInt(matcher.group(1));
		VPLSParser parser = new VPLSParser(this.router, this.getContextNotifier(), vplsnumber);
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