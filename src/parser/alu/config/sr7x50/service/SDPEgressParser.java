package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.SRFilterType;
import router.alcatel.router.service.SRSDPEgress;

public class SDPEgressParser extends ConfigurationSection {

	protected SRSDPEgress sdpegress = new SRSDPEgress();
	
	public SDPEgressParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SERVICE.SERVICESDP.EGRESS", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^filter (ip|mac) ([0-9]+)$"), new CommandHandler("setFilter", true));

	}
	
	public void setFilter(Matcher matcher){
		

		if ( matcher.group(1).equals("mac")){
			this.sdpegress.setFilterType(SRFilterType.mac);

		} else if ( matcher.group(1).equals("ip")){
			
			this.sdpegress.setFilterType(SRFilterType.ip);

		} else {
			System.out.println("ERROR: Invalid filter type " + matcher.group(1) + " for sap Ingress " + this.sdpegress.getName());
			System.exit(1);
		}
		
		this.sdpegress.setFilterNumber(Integer.parseInt(matcher.group(2)));

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
