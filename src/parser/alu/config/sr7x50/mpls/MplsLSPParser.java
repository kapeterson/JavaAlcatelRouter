package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMplsLSP;

public class MplsLSPParser extends ConfigurationSection {

	protected SRMplsLSP lsp = null;
	
	public MplsLSPParser(SRChassisObject router, ContextChange contextChangeHandler, String lspname){
		super("CONFIG.MPLS.LSP", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^to (.*)$"), new CommandHandler("setTo", true));
		this.commandHash.put(Pattern.compile("^primary \"(.*)\"$"), new CommandHandler("setPrimary", true));
		this.commandHash.put(Pattern.compile("^secondary \"(.*)\"$"), new CommandHandler("setSecondary", true));

		this.lsp = new SRMplsLSP(lspname);
	}
	
	
	
	public void setPrimary(Matcher matcher){
		this.lsp.setPrimaryPath(matcher.group(1));
	}
	
	public void setSecondary(Matcher matcher){
		this.lsp.setSecondaryPath(matcher.group(1));
	}
	
	public void setTo(Matcher matcher){
		this.lsp.setToAddress(matcher.group(1));
	}
	
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.lsp);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());

		}
	}
	
}
