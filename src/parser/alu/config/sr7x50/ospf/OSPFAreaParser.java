package parser.alu.config.sr7x50.ospf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.ospf.*;

public class OSPFAreaParser extends ConfigurationSection {
	
	protected SROSPFArea ospfArea = null;
	
	public OSPFAreaParser(SRChassisObject router, ContextChange contextChangeHandler, String areaname){
		super("CONFIG.OSPF.AREA", router, contextChangeHandler);
		
		this.ospfArea = new SROSPFArea(areaname);
		
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));
		
	}
	
	public void setInterfaceContext(Matcher matcher){
		//System.out.println("OSPF interface " + matcher.group(1));
		SROSPFInterface iface = new SROSPFInterface(matcher.group(1));
		this.ospfArea.addInterface(iface);
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
			this.router.Router.OSPF.addArea(ospfArea);
		}
	}
}
