package parser.alu.config.sr7x50.filter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.SRIPFilterEntry;

public class IPEntryParser extends ConfigurationSection {

	protected SRIPFilterEntry entry = null;
	
	protected List<String> validProtocols = null;

	public IPEntryParser(SRChassisObject router, ContextChange contextChangeHandler, Integer entryNumber){
		super("CONFIG.FILTER.IP.ENTRY", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^src\\-ip (.*)\\/([0-9]+)"), new CommandHandler("setSrcIP", true));
		this.commandHash.put(Pattern.compile("^dst\\-ip (.*)\\/([0-9]+)"), new CommandHandler("setDstIP", true));
		this.commandHash.put(Pattern.compile("^match protocol (.*)"), new CommandHandler("setProtocol", true));
		
		this.commandHash.put(Pattern.compile("^src\\-port eq ([0-9]+)"), new CommandHandler("setSourcePortSingle", true));
		this.commandHash.put(Pattern.compile("^src\\-port range ([0-9]+) ([0-9]+)"), new CommandHandler("setSourcePortRange", true));
		
		this.commandHash.put(Pattern.compile("^dst\\-port eq ([0-9]+)"), new CommandHandler("setDestinationPortSingle", true));
		this.commandHash.put(Pattern.compile("^dst\\-port range ([0-9]+) ([0-9]+)"), new CommandHandler("setDestinationPortRange", true));

		
		if ( this.validProtocols == null ){
			this.validProtocols = Arrays.asList("none", "tcp", "udp", "igmp", "icmp", "rsvp", "gre", "ipv6", "ospf-igp", "vrrp");
			//this.validProtocols.add("50");

		}
		this.entry = new SRIPFilterEntry(entryNumber);
	}
	
	public void setDestinationPortSingle(Matcher matcher){
		
		int port = Integer.parseInt(matcher.group(1));
		this.entry.setDstPorts(port, port);
	}	
	
	
	public void setDestinationPortRange(Matcher matcher){
		int startport = Integer.parseInt(matcher.group(1));
		int endport = Integer.parseInt(matcher.group(2));

		this.entry.setDstPorts(startport, endport);
	}
	
	public void setSourcePortSingle(Matcher matcher){
		
		int port = Integer.parseInt(matcher.group(1));
		this.entry.setSrcPorts(port, port);
	}
	
	public void setSourcePortRange(Matcher matcher){
		int startport = Integer.parseInt(matcher.group(1));
		int endport = Integer.parseInt(matcher.group(2));

		this.entry.setSrcPorts(startport, endport);
	}
	
	
	public void setProtocol(Matcher matcher){

		//
		

		if ( matcher.group(1).equals("*"))
			return;
		
		/*
		for ( SRFilterProtocol prot : SRFilterProtocol.values() ) {
			if ( prot.name().equals(matcher.group(1)) )
				validProtocol = true;
		}
		*/
		String protocolValue = matcher.group(1).trim();
		/*
		if ( !this.validProtocols.contains(protocolValue)){
		//if ( !validProtocol){
			System.out.println("ERROR: Invalid protocoll value in filter protocl match " + matcher.group(1));
			System.out.println("CFG: " + this.getCurrentLine());
			System.exit(1);
		}*/
		
		//SRFilterProtocol protocolValue = SRFilterProtocol.valueOf(matcher.group(1));
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
		
		
		if ( this.getSectionDepth() <= this.getLastCommandDepth() && this.getLastCommandDepth() <= (this.getSectionDepth() + 3)) {

		
			this.getParent().addObject(this.entry);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
