package parser.alu.config.sr7x50.routerinterace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import parser.alu.config.sr7x50.service.SAPParser;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.*;
import router.alcatel.router.lag.*;
import router.alcatel.router.port.*;

public class InterfaceParser extends ConfigurationSection {
	protected SRNetworkInterface iface = null;
	protected Pattern lagPattern = Pattern.compile("lag\\-([0-9]+)(:)?([0-9]+)?");
	protected Pattern portPattern = Pattern.compile("([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?");

	public InterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String intName){
		super("CONFIG.INTERFACE.INTERFACE", router, contextChangeHandler);
		this.iface = new SRNetworkInterface(intName);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^address ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)\\/([0-9]+)$"), new CommandHandler("setIPv4Address", true));
		this.commandHash.put(Pattern.compile("^port (.*)$"), new CommandHandler("setPortBinding", true));
		this.commandHash.put(Pattern.compile("^qos ([0-9]+)$"), new CommandHandler("setQOS", true));
		this.commandHash.put(Pattern.compile("^vrrp ([0-9]+)$"), new CommandHandler("setVRRPContext", true));


	}
	
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isVRRPObject()){
			System.out.println("VRRP added");
			this.iface.setVRRPObject((SRVRRPObject)obj);
		}
	}
	
	public void setQOS(Matcher matcher){
		this.iface.setQOS(Integer.parseInt(matcher.group(1)));
	}
	
	public void setVRRPContext(Matcher matcher ){
		//System.out.println("Found vrrp instance " + matcher.group(1));
		VRRPParser parser = new VRRPParser(router, this.contextChange, matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);	
	} 
	
	public void setPortBinding(Matcher matcher) throws Exception {
		
		String bindingName = matcher.group(1);
		
		// if it's a port
		// create a binding and add it
		
		if ( bindingName.matches("[0-9]+\\/[0-9]+\\/[0-9]+(:)?([0-9]+)?" ) ){
			
			Matcher m = portPattern.matcher(bindingName);
			if ( m.find()){
				
				
				if ( this.router.Ports.hasPort(m.group(1))){
					
					
					SRPortObject port = this.router.Ports.getPort(m.group(1));
				
					final int tag = ( m.group(3) == null ) ? -1 : Integer.parseInt(m.group(3));
					SRInterfaceBinding binding = new SRInterfaceBinding(port, tag);
					this.iface.setInterfaceBinding(binding);
					
					
				} else {
					throw new Exception("ERROR: Port  "  + bindingName + " could not be retried from port configuration when binding to interface in the parser");

				}
				
				
			} else {
				throw new Exception("ERROR: Port  "  + bindingName + " was not found when trying to set port binding to an interface in the parser");
			}
			

			
		} else if ( bindingName.matches("lag\\-[0-9]+(:)?([0-9]+)?") ){

			
			Matcher m = lagPattern.matcher(bindingName);
			
			if ( m.find()){
				
				if ( this.router.Lags.hasLag(Integer.parseInt(m.group(1)))) {
					
					SRLagObject lag = this.router.Lags.getLag(Integer.parseInt(m.group(1)));
					
					final int tag = ( m.group(3) == null ) ? -1 : Integer.parseInt(m.group(3));
					SRInterfaceBinding binding = new SRInterfaceBinding(lag, tag);
					this.iface.setInterfaceBinding(binding);
					
				} else {
					throw new Exception("ERROR: could not get lag object in lag  " + bindingName + " when binding to interface in parser");
				}
				
				

				
			} else {
				throw new Exception("ERROR: Error matching lag value when trying to bind an interface to a lag : " + bindingName);

			}
			
		} else {
			System.out.println("No match for binding for interface  " + bindingName);
			//throw new Exception("ERROR: Binding interface to object with name " + bindingName + " did not match lag or port types searched");
		}
		
			
	}
	
	public void setIPv4Address(Matcher matcher){
		
		//System.out.println("IPv4 address = " + matcher.group(1) + " / " + matcher.group(2));
		this.iface.setIPv4Address(matcher.group(1), matcher.group(2));
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
			router.Router.Interfaces.addInterface(this.iface);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
		//super.defaultExitHandler(matcher);
	}
	
	
}
