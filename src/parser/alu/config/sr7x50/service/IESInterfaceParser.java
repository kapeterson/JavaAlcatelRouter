package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRInterfaceBinding;
import router.alcatel.router.service.SRSDPObject;
import router.alcatel.router.service.SRServiceInterface;
import router.alcatel.router.service.SRServiceSDPObject;

public class IESInterfaceParser extends ConfigurationSection {
	
	protected SRServiceInterface interfaceObject = null;
	
	public IESInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.SERVICE.IES.INTERFACE", router, contextChangeHandler);
		interfaceObject = new SRServiceInterface(interfaceName);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^spoke\\-sdp (.*) create"), new CommandHandler("setSDPBinding", true));
	}
	

	public void setSDPBinding(Matcher matcher) throws Exception{
		
		System.out.println("\nSetting ies binding to " + matcher.group(1));
		String[] sdpInfo = matcher.group(1).split(":");
		System.out.println("SDP = " + sdpInfo[0]);
		System.out.println("VCID = " + sdpInfo[1]);
		if ( this.router.Services.hasSDP(sdpInfo[0])){
			
			SRSDPObject sdp = this.router.Services.getSDP(Integer.parseInt(sdpInfo[0]));
			int tag = -1;
			//int tag = Integer.parseInt(sdpInfo[1]);
			try {
				tag = Integer.parseInt(sdpInfo[1].trim());
			} catch ( Exception err ) {
				System.out.println("Error convertin to int " + err.getMessage());
				System.exit(1);
			}
			//int tag = 3;
			SRInterfaceBinding binding = new SRInterfaceBinding(sdp, tag);
			
			this.interfaceObject.setBinding(binding);
		}
	}
	public void setDescription(Matcher matcher){
		this.interfaceObject.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//this.router.Services.addIES(this.interfaceObject);
			((IESParser)this.parent).addInterface(this.interfaceObject);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
