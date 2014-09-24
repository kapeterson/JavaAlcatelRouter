package parser.alu.config.sr7x50.lag;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.lag.*;
import router.alcatel.router.port.*;


public class LagParser extends ConfigurationSection {
	
	protected SRLagObject lag = null;
	public LagParser(SRChassisObject router, ContextChange contextChangeHandler, Integer lagNumber){
		super("CONFIG.LAG.LAG", router, contextChangeHandler);
		lag = new SRLagObject(lagNumber);
		this.commandHash.put(Pattern.compile("^port ([0-9]+\\/[0-9]+\\/[0-9]+)"), new CommandHandler("addPort", true));
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^shutdown"), new CommandHandler("adminDown", true));
		this.commandHash.put(Pattern.compile("^no shutdown"), new CommandHandler("adminUp", true));



	}
	
	public void adminUp(Matcher matcher){
		this.lag.adminUp();
	}
	
	public void adminDown(Matcher matcher){
		this.lag.adminDown();
	}
	
	public void setDescription(Matcher matcher){
		lag.setDescription(matcher.group(1));
	}
	
	public void addPort(Matcher matcher){
		String portName = matcher.group(1);
		SRPortObject p = this.router.Ports.getPort(portName);
		
		// now add the association
		p.addAssociation(this.lag);
		this.lag.addPort(p);
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.format("Exited lag %s\n", this.lag.getLagNumber().toString());
			router.Lags.addLag(lag);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
}
