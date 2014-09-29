package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSDPIngress;

public class SDPIngressParser extends ConfigurationSection {

	protected SRSDPIngress sdpingress = new SRSDPIngress();
	
	public SDPIngressParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SERVICE.SERVICESDP.INGRESS", router, contextChangeHandler);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.sdpingress);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
