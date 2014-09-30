package parser.alu.config.sr7x50.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.SRFilterProtocol;
import router.alcatel.router.filter.SRIPFilterEntry;

public class IPEntryParser extends ConfigurationSection {

	protected SRIPFilterEntry entry = null;
	
	public IPEntryParser(SRChassisObject router, ContextChange contextChangeHandler, Integer entryNumber){
		super("CONFIG.FILTER.IP.ENTRY", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^src\\-ip (.*)\\/([0-9]+)"), new CommandHandler("setSrcIP", true));
		this.commandHash.put(Pattern.compile("^dst\\-ip (.*)\\/([0-9]+)"), new CommandHandler("setDstIP", true));
		this.commandHash.put(Pattern.compile("^match protocol (.*)"), new CommandHandler("setProtocol", true));

		this.entry = new SRIPFilterEntry(entryNumber);
	}
	
	
	public void setProtocol(Matcher matcher){

		//
		
		boolean validProtocol = false;

		if ( matcher.group(1).equals("*"))
			return;
		
		for ( SRFilterProtocol prot : SRFilterProtocol.values() ) {
			if ( prot.name().equals(matcher.group(1)) )
				validProtocol = true;
		}
	
		if ( !validProtocol){
			System.out.println("ERROR: Invalid protocol value in filter protocl match " + matcher.group(1));
			System.out.println("CFG: " + this.getCurrentLine());
			System.exit(1);
		}
		
		SRFilterProtocol protocolValue = SRFilterProtocol.valueOf(matcher.group(1));
		this.entry.setProtocol(protocolValue);
	}
	
	public void setDstIP(Matcher matcher){
		this.entry.setDestAddress(matcher.group(1), matcher.group(2));
	}
	
	public void setSrcIP(Matcher matcher){
		this.entry.setSourceAddress(matcher.group(1), matcher.group(2));
	}
	
	public void setDescription(Matcher matcher){
		this.entry.setDescription(matcher.group(1));
	}

	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		Integer lastdpth = this.getSectionDepth();
		
		if ( this.getSectionDepth() <= this.getLastCommandDepth() && this.getLastCommandDepth() <= (this.getSectionDepth() + 3)) {

		
			this.getParent().addObject(this.entry);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
