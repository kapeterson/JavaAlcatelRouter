package parser.alu.config.sr7x50.system;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;


public class SystemSNMPParser extends ConfigurationSection {
	
	public SystemSNMPParser(SRChassisObject routerObject, ContextChange contextChangeHandler ){
		super("CONFIG.SYSTEM.SNMP", routerObject, contextChangeHandler);
		this.commandHash.put(Pattern.compile("packet\\-size ([0-9]+)"), new CommandHandler("setPacketSize", false));
	}
	
	public void setPacketSize(Matcher matcher){
		//System.out.println("set packet size to  invoked " + matcher.group(1));
		
	}
	
	
	public void exitSection(Matcher matcher){
		super.defaultExitHandler(matcher);
		// if an exit command was received at the same depth as the current section
		// hand back to parent
		//if ( this.getLastCommandDepth() == this.sectionDepth) {
		//	contextChange.contextChangeCallback(this, this.getParent());
		//}
		
	}
	
	
}
