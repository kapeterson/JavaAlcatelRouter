package parser.alu.config.sr7x50.routerinterace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.*;

public class InterfaceParser extends ConfigurationSection {
	protected SRNetworkInterface iface = null;
	public InterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String intName){
		super("CONFIG.INTERFACE.INTERFACE", router, contextChangeHandler);
		this.iface = new SRNetworkInterface(intName);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^address ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)\\/([0-9]+)$"), new CommandHandler("setIPv4Address", true));
		this.commandHash.put(Pattern.compile("^port (.*)$"), new CommandHandler("setPortBinding", true));


	}
	
	
	public void setPortBinding(Matcher matcher){
		String bindingName = matcher.group(1);
		
		if ( bindingName.matches("[0-9]+\\/[0-9]+\\/[0-9]+" ) ){
			//System.out.println("Port binding " + bindingName);
			if ( this.router.Ports.hasPort(bindingName)){
				//System.out.println("We have port " + bindingName);
				try {
					this.iface.setBinding(this.router.Ports.getPort(bindingName));
				} catch ( Exception err){
					System.out.println(err.getMessage());
					System.exit(1);
				}
			}
		} else {
			
			//System.out.println("Lag binding " + bindingName);
		}
		
			
	}
	
	public void setIPv4Address(Matcher matcher){
		
		//System.out.println("IPv4 address = " + matcher.group(1) + " / " + matcher.group(2));
		this.iface.setIPv4Address(matcher.group(1));
	}
	
	public void setDescription(Matcher matcher){
		this.iface.setDescription(matcher.group(1));
	}
	
	/**
	 * Custom handler
	 */
	
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//System.out.format("Exited lag %s\n", this.lag.getLagNumber().toString());
			router.Interface.addInterface(this.iface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
	
	
}
