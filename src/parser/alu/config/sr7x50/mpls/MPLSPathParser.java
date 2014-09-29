package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMplsHop;
import router.alcatel.router.mpls.SRMplsPath;

public class MPLSPathParser extends ConfigurationSection {

	protected SRMplsPath path = null; 
	
	public MPLSPathParser(SRChassisObject router, ContextChange contextChangeHandler, String pathname){
		super("CONFIG.MPLS.PATH", router, contextChangeHandler);
		this.path = new SRMplsPath(pathname);
		this.commandHash.put(Pattern.compile("^hop ([0-9]+) (.*) (strict|loose)?"), new CommandHandler("addHop", true));

	}
	
	public void addHop(Matcher matcher){
		SRMplsHop hop = new SRMplsHop(Integer.parseInt(matcher.group(1)), matcher.group(2));
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
