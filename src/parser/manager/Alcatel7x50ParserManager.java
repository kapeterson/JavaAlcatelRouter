package parser.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;

import parser.ConfigurationSection;
import parser.ContextChange;
import parser.DefaultConfigurationSection;
import parser.alu.config.sr7x50.system.*;
import parser.alu.config.sr7x50.card.*;
import parser.alu.config.sr7x50.port.*;
import parser.alu.config.sr7x50.lag.*;
import parser.alu.config.sr7x50.qos.*;
import parser.alu.config.sr7x50.filter.*;
import parser.alu.config.sr7x50.routerinterace.*;
import parser.alu.config.sr7x50.ospf.*;
import parser.alu.config.sr7x50.service.*;
import parser.alu.config.sr7x50.pim.*;
import parser.alu.config.sr7x50.igmp.*;
import parser.alu.config.sr7x50.mpls.*;
import parser.alu.config.sr7x50.ldp.*;
import parser.alu.config.sr7x50.rsvp.*;
import parser.alu.config.sr7x50.policy.*;
import parser.alu.config.sr7x50.bgp.*;
import parser.alu.config.sr7x50.staticroute.*;
import parser.alu.config.sr7x50.impm.*;

import router.alcatel.router.*;

/**
 * Parses 7x50 Configuration
 * @author Kris Peterson
 *
 */
public class Alcatel7x50ParserManager implements ContextChange {
	
	protected ConfigurationSection activeParser = null;
	protected Integer currentContext = -1;
	protected Hashtable<String, ConfigurationSection> contextHash;
	protected SRChassisObject router;
	protected String filename = "";
	
	/**
	 * Parser Manager Constructor
	 */
	public Alcatel7x50ParserManager(){
		
		router = new SRChassisObject();
		
		contextHash = new Hashtable<String, ConfigurationSection>();
		contextHash.put("echo \"System Configuration\"", new SystemParser(router,this));
		
		// point to the default parser that does nothing
		ConfigurationSection defaultParser = new DefaultConfigurationSection(router, this);
		
		// Empty parsers that do nothing
		contextHash.put("echo \"System Security Configuration\"", defaultParser);
		contextHash.put("echo \"Filter Log Configuration\"", defaultParser);
		
		contextHash.put("echo \"Multicast Path Management Policy Configuration\"", new IMPMConfigurationParser(router, this));
		contextHash.put("echo \"Card Configuration\"", new CardParser(router, this));
		contextHash.put("echo \"Port Configuration\"", new PortConfigurationParser(router, this));
		contextHash.put("echo \"Redundancy Configuration\"", defaultParser);
		contextHash.put("echo \"LAG Configuration\"", new LagConfigurationParser(router, this));
		contextHash.put("echo \"QoS Policy Configuration\"", new QosConfigurationParser(router,this));
		contextHash.put("echo \"Management Router Configuration\"", defaultParser);
		contextHash.put("echo \"Filter Configuration\"", new FilterConfigurationParser(router, this));
		contextHash.put("echo \"Router (Network Side) Configuration\"", new InterfaceConfigurationParser(router, this));
		contextHash.put("echo \"Static Route Configuration\"", new StaticRouteConfigurationParser(router, this));
		contextHash.put("echo \"SGT Mapping Configuration\"", defaultParser);
		contextHash.put("echo \"OSPFv2 Configuration\"",new OSPFConfigurationParser(router, this));
		contextHash.put("echo \"IGMP Configuration\"", new IGMPConfigurationParser(router, this));
		contextHash.put("echo \"PIM Configuration\"", new PIMConfigurationParser(router, this));
		contextHash.put("echo \"MPLS Configuration\"", new MPLSConfigurationParser(router, this));
		contextHash.put("echo \"RSVP Configuration\"", new RSVPConfigurationParser(router, this));
		contextHash.put("echo \"MPLS LSP Configuration\"", new MPLSConfigurationParser(router, this));
		contextHash.put("echo \"LDP Configuration\"",  new LDPConfigurationParser(router, this));
		contextHash.put("echo \"Service Configuration\"", new ServiceConfigurationParser(router, this));
		contextHash.put("echo \"Policy Configuration\"", new PolicyConfigurationParser(router, this));
		contextHash.put("echo \"BGP Configuration\"", new BGPConfigurationParser(router, this));
		
	}
	
	public void setActiveParser(ConfigurationSection parser){
		activeParser = parser;
	}
	
	
	/**
	 * Returs the SRChassisObject router that was parsed
	 * @return
	 */
	public SRChassisObject getRouter(){
		return this.router;
	}
	
	
	/**
	 * Parse the file at the provided location
	 * @param filelocation - Full path to the file
	 */
	public void ParseConfig(String filelocation){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filelocation));
			this.filename = filelocation;
			String line;
			while ( br.ready()) {
				line = br.readLine();
				this.ParseLine(line);

				
			}
			br.close();

		} catch ( Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
	}
	
	/**
	 * Parses an individual configuration line
	 * @param linetoparse
	 */
	public void ParseLine(String linetoparse){
		try {
		// Check for context switch
		if ( contextHash.containsKey(linetoparse)) {
			//System.out.println("Context switch to contxt " + linetoparse);
			
			activeParser = contextHash.get(linetoparse);
		}  else {
			
			// if the parser is null or if is the default base parser, skip it
			if (activeParser == null || activeParser.getClass().getName() == "parser.ConfigurationSection") {
				
			} else {
			
				activeParser.Parse(linetoparse, this.filename);
			}
		}
		} catch ( Exception err){
			System.out.println("Error parsing line: " + err.getMessage());
		}
	}
	
	
	/**
	 * Callback used to move between configuration context and depths
	 */
	public void contextChangeCallback(ConfigurationSection oldSection, ConfigurationSection newSection){
		
		/*
		if ( newSection == null) {
			System.out.println("Handing back to null section");
		} else {
			System.out.println("Callback for ContextChange from " + oldSection.getName() + " (depth = " + oldSection.getSectionDepth() + ") to " + newSection.getName() + " depth = " + newSection.getSectionDepth());
		}
		*/
		activeParser = newSection;
	}
	
	public void contextDepthChange(int depth){
		
	}
}
