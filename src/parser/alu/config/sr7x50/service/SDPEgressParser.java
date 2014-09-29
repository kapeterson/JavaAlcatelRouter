package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;

import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSDPEgress;

public class SDPEgressParser extends ConfigurationSection {

	protected SRSDPEgress sdpegress = new SRSDPEgress();
	
	public SDPEgressParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SERVICE.SERVICESDP.EGRESS", router, contextChangeHandler);

	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.sdpegress);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
