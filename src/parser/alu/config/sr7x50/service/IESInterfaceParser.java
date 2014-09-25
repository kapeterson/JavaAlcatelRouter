package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.RouterRegex;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.port.SRPortObject;
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
		this.commandHash.put(Pattern.compile("^sap (.*) create"), new CommandHandler("setSAPContext", true));
	}
	
	
	public void setSAPContext(Matcher matcher){

		SAPParser parser = new SAPParser(router, this.contextChange, matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}

	public void setSDPBinding(Matcher matcher) throws Exception{
		ServiceSDPParser parser = new ServiceSDPParser(router, this.contextChange, matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		/*
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
		*/
	}
	
	
	public void addObject(AlcatelObject object){
		
		if ( object.isSAPObject()){

			Pattern sapPattern = Pattern.compile(RouterRegex.PortRegex);
			Matcher m = sapPattern.matcher(object.getName());
			
			if ( m.find()){

				Integer tag = -1;
				if ( m.group(3) != null){
					tag = Integer.parseInt(m.group(3));
				}
				
				SRInterfaceBinding binding = new SRInterfaceBinding(object, tag);
				try {
					this.interfaceObject.setBinding(binding);
				} catch ( Exception err){
					System.out.println("Error trying to set binding of ies to a sap " + object.getName());
					System.exit(1);
				}
				
				// The SAP already adds the association
			}
			
			this.interfaceObject.setServiceBinding(object);
			
		} else if ( object.isServiceSDPObject()) {
			System.out.println("Adding sdp to ies " + object.getName().trim());
			Pattern sdpPattern = Pattern.compile(RouterRegex.sdpInfoPattern);

			Matcher sdpMatch = sdpPattern.matcher(object.getName().trim());
			
			if ( sdpMatch.find()){
				
				Integer tag = -1;
				if ( sdpMatch.group(2) != null){
					tag = Integer.parseInt(sdpMatch.group(2));
				} else {
					System.out.println("Error getting vcid in sdp binding " );
					System.exit(1);
				}
				
				SRInterfaceBinding binding = new SRInterfaceBinding(object, tag);
				
				try {
					this.interfaceObject.setBinding(binding);
				} catch ( Exception err){
					System.out.println("Error trying to set binding of ies to a sdp " + object.getName());
					System.exit(1);
				}				
				
				this.interfaceObject.setServiceBinding(object);

				
			} else {
				System.out.println("Error trying to set binding of ies to a sdp " + object.getName());
				System.exit(1);
			}
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
