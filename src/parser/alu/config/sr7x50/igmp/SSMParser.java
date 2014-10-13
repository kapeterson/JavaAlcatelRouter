package parser.alu.config.sr7x50.igmp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.igmp.SRSSMTranslation;

public class SSMParser extends ConfigurationSection{
	
	protected SRSSMTranslation ssm = null;
	public SSMParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.IGMP.SSM", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^source ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)"), new CommandHandler("setSource", true));
		this.commandHash.put(Pattern.compile("^grp\\-range ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+) ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)"), new CommandHandler("setGroupRange", true));
	}
	
	
	public void setGroupRange(Matcher matcher){
		//System.out.println("Got a group range");
		String start = matcher.group(1);
		String end = matcher.group(2);
		this.ssm = new SRSSMTranslation(start, end);
	}
	public void setSource(Matcher matcher){
		//System.out.println("Got an SSM Source");
		this.ssm.setSource(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		if ( ssm != null) {
			this.getParent().addObject(ssm);
			ssm = null;
		}
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
