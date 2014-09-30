package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRFPIngress;
import router.alcatel.router.card.SRMcastPathManagement;
import router.alcatel.router.card.SRMDAObject;

public class MDAIngressParser extends ConfigurationSection {

	SRFPIngress mdaingress = null;
	
	public MDAIngressParser(SRChassisObject router, ContextChange contextChangeHandler, SRMDAObject mda){
		super("CONFIG.CARD.IOM.MDA.INGRESS",router, contextChangeHandler);
		mdaingress = new SRFPIngress(mda);
		this.commandHash.put(Pattern.compile("^mcast\\-path\\-management$"), new CommandHandler("setIMPMContext",false));
		
	}
	
	

	public void setIMPMContext(Matcher matcher){
		//System.out.println("MCASTMGMT context");
		PathManagementParser parser = new PathManagementParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	
	public void addObject(AlcatelObject obj){
		if ( obj.isPathManagment()){
			this.mdaingress.PATHMGMT = (SRMcastPathManagement)obj;
			
		}
	}
	
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getParent().addObject(this.mdaingress);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
	
}
