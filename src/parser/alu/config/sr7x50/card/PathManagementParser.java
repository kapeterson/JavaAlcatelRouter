package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRMDAMcastPathManagement;
import router.alcatel.router.impm.SRBandwidthPolicy;

public class PathManagementParser extends ConfigurationSection {

	protected SRMDAMcastPathManagement mcast = null;
	
	public PathManagementParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD.IOM.MDA.INGRESS.PATHMGMT",router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^bandwidth\\-policy \"(.*)\""), new CommandHandler("setBandwidthPolicy",false));
		this.mcast = new SRMDAMcastPathManagement();

	}

	public void setBandwidthPolicy(Matcher matcher){
		//System.out.println("Add policy " + matcher.group(1) + " to mda ingress");
		//this.mcast.setName(matcher.group(1));
		
		// get the policy and add it to the mcast
		SRBandwidthPolicy bwPolicy = this.router.IMPM.getPolicy(matcher.group(1));
		this.mcast.setBandwidthPolicy(bwPolicy);
	}
	
	/**
	 * Use customer exit
	 */
	public void exitSection(Matcher matcher){
		 
		if ( this.getSectionDepth() == this.lastDepth) {
			this.getParent().addObject(this.mcast);
			this.getContextNotifier().contextChangeCallback(this, this.parent);
		}	
	}
}
