package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRIOMForwardingPath;
import router.alcatel.router.card.SRMcastPathManagement;

public class FPParser extends ConfigurationSection {
	
	protected SRIOMForwardingPath fp = new SRIOMForwardingPath();
	
	public FPParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD.IOM.FP", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^mcast\\-path\\-management$"), new CommandHandler("setIMPMContext",false));

	}
	
	
	public void setIMPMContext(Matcher matcher){
		//System.out.println("MCASTMGMT context");
		PathManagementParser parser = new PathManagementParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getParent().addObject(this.fp);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isPathManagment()){
			this.fp.INGRESS.PATHMGMT = (SRMcastPathManagement)obj;
		}
	}
	
}
