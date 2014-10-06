package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSDPDelivery;


public class ServiceConfigurationParser extends ConfigurationSection {
	public ServiceConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SERVICE", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^vpls ([0-9]+) .*"), new CommandHandler("setVPLSContext", true));
		this.commandHash.put(Pattern.compile("^sdp ([0-9]+) (mpls )?create"), new CommandHandler("setSDPContext", true));
		this.commandHash.put(Pattern.compile("^ies ([0-9]+) customer .* create"), new CommandHandler("setIESContext", true));

	}
	
	
	public void setIESContext(Matcher matcher){

		//System.out.println("ies " + matcher.group(1) );
		
		
		
		Integer vplsnumber = Integer.parseInt(matcher.group(1));
		IESParser parser = new IESParser(this.router, this.getContextNotifier(), vplsnumber);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
		
	}
	
	public void setSDPContext(Matcher matcher){

		//System.out.println("sdp " + matcher.group(1) + " mpls = " + matcher.group(2));
	
		
		SRSDPDelivery delivery = ( matcher.group(2) == null) ? SRSDPDelivery.gre : SRSDPDelivery.mpls;

		Integer sdpnumber = Integer.parseInt(matcher.group(1));
		SDPParser parser = new SDPParser(this.router, this.getContextNotifier(), sdpnumber, delivery);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
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