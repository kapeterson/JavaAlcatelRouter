package parser.alu.config.sr7x50.ldp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.ldp.SRLDPInterface;
import parser.ConfigurationSection;

public class LDPConfigurationParser extends ConfigurationSection{
	
	
	public LDPConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.LDP", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setLDPInterfaceContext", true));
	}
	
	
	public void addObject(AlcatelObject obj){
		if ( obj.isLDPInterface()){
			//this.router.Router.LDP.addInterface( (SRLDPInterface)obj);
		}
	}
	
	public void setLDPInterfaceContext(Matcher matcher){
		
		LDPInterfaceParser parser = new LDPInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		//System.out.println("Exit ldpconfig parser");
		super.defaultExitHandler(matcher);
	}
}
