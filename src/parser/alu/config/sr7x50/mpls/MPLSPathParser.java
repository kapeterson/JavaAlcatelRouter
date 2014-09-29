package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMPLSHop;
import router.alcatel.router.mpls.SRMPLSPath;

public class MPLSPathParser extends ConfigurationSection {

	protected SRMPLSPath path = null; 
	
	public MPLSPathParser(SRChassisObject router, ContextChange contextChangeHandler, String pathname){
		super("CONFIG.MPLS.PATH", router, contextChangeHandler);
		this.path = new SRMPLSPath(pathname);
		this.commandHash.put(Pattern.compile("^hop ([0-9]+) (.*) (strict|loose)?"), new CommandHandler("addHop", true));

	}
	
	public void addHop(Matcher matcher){
		SRMPLSHop hop = new SRMPLSHop(Integer.parseInt(matcher.group(1)), matcher.group(2));
		this.path.addHop(hop);
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.path);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
	
}
