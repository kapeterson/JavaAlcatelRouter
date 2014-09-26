package parser.alu.config.sr7x50.card;

import parser.*;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MDAParser extends ConfigurationSection {

	SRMDAObject mda = null;
	SRIOMObject iom = null;
	
	int complex = -1;
	
	public MDAParser(SRChassisObject router, ContextChange contextChangeHandler, SRIOMObject iom,  int mdaNumber){
		super("CONFIG.CARD.IOM.MDA",router, contextChangeHandler);
		
		this.mda = new SRMDAObject(mdaNumber, iom);
		this.iom = iom;
		this.complex = mdaNumber;
		this.commandHash.put(Pattern.compile("mda\\-type (.*)"), new CommandHandler("setMDAType",false));
		this.commandHash.put(Pattern.compile("^no shutdown$"), new CommandHandler("adminUp",false));
		this.commandHash.put(Pattern.compile("^shutdown$"), new CommandHandler("adminDown",false));
		this.commandHash.put(Pattern.compile("^ingress$"), new CommandHandler("setIngressContext",false));
		this.commandHash.put(Pattern.compile("^network$"), new CommandHandler("setNetworkContext",false));
		this.commandHash.put(Pattern.compile("^access$"), new CommandHandler("setAccessContext",false));


	}
	
	public void setNetworkContext(Matcher matcher){
		MDANetworkParser parser = new MDANetworkParser(this.router, this.getContextNotifier(), this.mda);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void setAccessContext(Matcher matcher){
		MDAAccessParser parser = new MDAAccessParser(this.router, this.getContextNotifier(), this.mda);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void setIngressContext(Matcher matcher){
		MDAIngressParser parser = new MDAIngressParser(this.router, this.getContextNotifier(), this.mda);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);

	}
	
	public void adminDown(Matcher matcher){
		this.mda.adminDown();
	}
	
	public void adminUp(Matcher matcher){
		this.mda.adminUp();
	}
	
	
	public void setMDAType(Matcher matcher){
		this.mda.setMDAType(matcher.group(1));
	}
	
	public void addObject(AlcatelObject obj){
		//System.out.println("Add item " + obj.getObjectType() + " to mda ");
		if ( obj.isMDAIngressObject()){
			this.mda.INGRESS = (SRMDAIngress)obj;
		}
	}
	public void exitSection(Matcher matcher){
		if ( this.getSectionDepth() == this.lastDepth) {
			//System.out.println("exit");
			this.iom.setMDA(this.mda.getComplex(), this.mda);
			//super.defaultExitHandler(matcher);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}
	}
	
}
