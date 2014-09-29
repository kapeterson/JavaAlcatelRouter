package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMplsLSP;

public class MplsLSPParser extends ConfigurationSection {

	protected SRMplsLSP lsp = null;
	
	public MplsLSPParser(SRChassisObject router, ContextChange contextChangeHandler, String lspname){
		super("CONFIG.MPLS.LSP", router, contextChangeHandler);
		this.lsp = new SRMplsLSP(lspname);
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
