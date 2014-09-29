package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMplsLSP;
import router.alcatel.router.mpls.SRMplsPath;

public class MPLSConfigurationParser extends ConfigurationSection{
	
	public MPLSConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.MPLS", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setMPLSInterfaceContext", true));
		this.commandHash.put(Pattern.compile("^path \"(.*)\""), new CommandHandler("setPathMode", true));
		this.commandHash.put(Pattern.compile("^lsp \"(.*)\""), new CommandHandler("setLSPMode", true));

	}
	
	
	public void setLSPMode(Matcher matcher){
		MplsLSPParser parser = new MplsLSPParser(this.router, this.contextChange, matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void setPathMode(Matcher matcher){
		MPLSPathParser parser = new MPLSPathParser(this.router, this.contextChange, matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isMPLSPath()){
			this.router.MPLS.addPath((SRMplsPath)obj);
		} else if ( obj.isMPLSLSP()) {
			
			this.router.MPLS.addLSP((SRMplsLSP)obj);
		} else {
			System.out.println("ERROR error adding obj " + obj.getName()  + " to mpls in parser");
		}
	}
	
	public void setMPLSInterfaceContext(Matcher matcher){
		//System.out.println("MPLS Interface " + matcher.group(1));
		
		MPLSInterfaceParser parser = new MPLSInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
