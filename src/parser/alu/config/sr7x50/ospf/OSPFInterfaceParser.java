package parser.alu.config.sr7x50.ospf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.ospf.SROSPFInterface;

public class OSPFInterfaceParser extends ConfigurationSection{

	protected SROSPFInterface ospfInterface = null;
	
	public OSPFInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String intName){
		super("CONFIG.OSPF.AREA.INTERFACE", router, contextChangeHandler);
		//System.out.println("Interface " + intName);
		this.ospfInterface = new SROSPFInterface(intName);
		this.commandHash.put(Pattern.compile("^metric ([0-9]+)"), new CommandHandler("setMetric", true));
		this.commandHash.put(Pattern.compile("^interface\\-type (.*)"), new CommandHandler("setInterfaceType", true));
		this.commandHash.put(Pattern.compile("^passive$"), new CommandHandler("setPassive", true));

	}
	
	
	public void setPassive(Matcher matcher ){
		this.ospfInterface.setPassiveValue(true);
	}
	
	public void setInterfaceType(Matcher matcher){
		this.ospfInterface.setInterfaceType(matcher.group(1));
	}
	
	public void setMetric(Matcher matcher){
		int metric = Integer.parseInt(matcher.group(1));
		this.ospfInterface.setMetric(metric);
		
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
			this.getParent().addObject(this.ospfInterface);
		}
	}
}
