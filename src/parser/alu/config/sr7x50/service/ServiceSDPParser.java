package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSDPEgress;
import router.alcatel.router.service.SRSDPIngress;
import router.alcatel.router.service.SRServiceSDPObject;

public class ServiceSDPParser extends ConfigurationSection {
	
	 
	protected SRServiceSDPObject sdp = null;
	
	public ServiceSDPParser(SRChassisObject router, ContextChange contextChangeHandler, String sdpName, AlcatelObjectType sdpType, AlcatelObject parentSDP){
		super("CONFIG.SERVICE.IES.INTERFACE.SDP", router, contextChangeHandler);
		
		if ( sdpType != AlcatelObjectType.MESHSDPOBJECT && sdpType != AlcatelObjectType.SPOKESDPOBJECT){
			System.out.println("Error invalid sdp type in ServiceSDP Parser");
			System.exit(1);
		}
		
		this.sdp = new SRServiceSDPObject(sdpType, sdpName, parentSDP);
		this.commandHash.put(Pattern.compile("^ingress$"), new CommandHandler("setIngressMode", true));
		this.commandHash.put(Pattern.compile("^egress$"), new CommandHandler("setEgressMode", true));
		// = new SRSAPObject(sapName);
		//this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
	}
	
	public void setIngressMode(Matcher matcher){
		SDPIngressParser parser = new SDPIngressParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void setEgressMode(Matcher matcher){
		SDPEgressParser parser = new SDPEgressParser(this.router, this.getContextNotifier());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	
	public void addObject(AlcatelObject obj){
		
		if (obj.isSDPEgress()){
			this.sdp.EGRESS = (SRSDPEgress)obj;
		} else if ( obj.isSDPIngress()){
			this.sdp.INGRESS = (SRSDPIngress)obj;
		} else { 
			System.out.println("Error could not assign obj " + obj.getObjectType() + " to service sdp");
			System.exit(1);
		}
	}
	
	
	@Override
	public void exitSection(Matcher matcher) {
		// TODO Auto-generated method stub
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			
			this.getParent().addObject(this.sdp);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	


	
}
