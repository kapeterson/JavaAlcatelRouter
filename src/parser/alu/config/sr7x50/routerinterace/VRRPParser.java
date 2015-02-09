package parser.alu.config.sr7x50.routerinterace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRVRRPObject;

public class VRRPParser extends ConfigurationSection {
	
	protected SRVRRPObject vrrp = null;
	public VRRPParser(SRChassisObject router, ContextChange contextChangeHandler, String vrrpInstance){
		super("CONFIG.ROUTER.INTERFACE.VRRP", router, contextChangeHandler);
		this.vrrp = new SRVRRPObject(Integer.parseInt(vrrpInstance));
		this.commandHash.put(Pattern.compile("^backup ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)$"), new CommandHandler("setBackup", true));
		this.commandHash.put(Pattern.compile("^priority ([0-9]+)$"), new CommandHandler("setPriority", true));

	}
	
	public void setBackup(Matcher matcher){
		//System.out.println("Found backup " + matcher.group(1));
		this.vrrp.setBackup(matcher.group(1));
	}
	
	public void setPriority(Matcher matcher){
		this.vrrp.setPriority(Integer.parseInt(matcher.group(1)));
	}
	
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.vrrp);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}

	}
}
