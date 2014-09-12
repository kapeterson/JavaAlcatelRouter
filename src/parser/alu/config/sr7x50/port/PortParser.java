package parser.alu.config.sr7x50.port;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.port.*;
public class PortParser extends ConfigurationSection {

	protected SRPortObject port = null;
	public PortParser(SRChassisObject router, ContextChange contextChangeHandler, String portName){
		super("CONFIG.PORT.PORT", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^no shutdown$"), new CommandHandler("enablePort", true));
		this.commandHash.put(Pattern.compile("^shutdown$"), new CommandHandler("disablePort", true));

		port = new SRPortObject();
		port.setObjectName(portName);
		this.router = router;
	}
	public void disablePort(Matcher matcher){
		//System.out.format("\tSet port to admin up for %s\n", port.getName());
		port.setAdminDown();
	}
	
	public void enablePort(Matcher matcher){
		System.out.format("\tSet port to admin up for %s\n", port.getName());
		port.setAdminUp();
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		if ( this.getLastCommandDepth() == this.sectionDepth) {
			//System.out.println("Exiting port " + port.getName());
			this.router.Ports.addPort(port);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
}
