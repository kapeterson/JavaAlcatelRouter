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

import router.alcatel.router.*;

/**
 * Simple Description
 */
public class Alcatel7x50ParserManager implements ContextChange {
	
	protected ConfigurationSection activeParser = null;
	protected Integer currentContext = -1;
	protected Hashtable<String, ConfigurationSection> contextHash;
	protected SRChassisObject router;

	public Alcatel7x50ParserManager(){
		
		router = new SRChassisObject();
		
		contextHash = new Hashtable<String, ConfigurationSection>();
		contextHash.put("echo \"System Configuration\"", new SystemParser(router,this));
		
		// point to the default parser that does nothing
		ConfigurationSection defaultParser = new DefaultConfigurationSection(router, this);
		
		// Empty parsers that do nothing
		contextHash.put("echo \"System Security Configuration\"", defaultParser);
		contextHash.put("echo \"Card Configuration\"", new CardParser(router, this));
		contextHash.put("echo \"Port Configuration\"", new PortConfigurationParser(router, this));
		contextHash.put("echo \"Redundancy Configuration\"", defaultParser);
		contextHash.put("echo \"LAG Configuration\"", new LagConfigurationParser(router, this));
		contextHash.put("echo \"QoS Policy Configuration\"", new QosConfigurationParser(router,this));
		contextHash.put("echo \"Management Router Configuration\"", defaultParser);
		contextHash.put("echo \"Filter Configuration\"", new FilterConfigurationParser(router, this));
		contextHash.put("echo \"Router (Network Side) Configuration\"", new InterfaceConfigurationParser(router, this));
		contextHash.put("echo \"Static Route Configuration\"", defaultParser);
		contextHash.put("echo \"SGT Mapping Configuration\"", defaultParser);
		contextHash.put("echo \"OSPFv2 Configuration\"",new OSPFConfigurationParser(router, this));
		contextHash.put("echo \"IGMP Configuration\"", new IGMPConfigurationParser(router, this));
		contextHash.put("echo \"PIM Configuration\"", new PIMConfigurationParser(router, this));
		contextHash.put("echo \"MPLS Configuration\"", new MPLSConfigurationParser(router, this));
		contextHash.put("echo \"RSVP Configuration\"", defaultParser);
		contextHash.put("echo \"MPLS LSP Configuration\"", new MPLSConfigurationParser(router, this));
		contextHash.put("echo \"LDP Configuration\"", defaultParser);
		contextHash.put("echo \"Service Configuration\"", new ServiceConfigurationParser(router, this));
		contextHash.put("echo \"Policy Configuration\"", defaultParser);
		contextHash.put("echo \"BGP Configuration\"", defaultParser);
		
	}
	
	public void setActiveParser(ConfigurationSection parser){
		activeParser = parser;
	}
	
	public SRChassisObject getRouter(){
		return this.router;
	}
	
	public void ParseConfig(String filelocation){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filelocation));

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
	
	public void ParseLine(String linetoparse){
		try {
		// Check for context switch
		if ( contextHash.containsKey(linetoparse)) {
			System.out.println("Context switch to contxt " + linetoparse);
			
			activeParser = contextHash.get(linetoparse);
		}  else {
			
			// if the parser is null or if is the default base parser, skip it
			if (activeParser == null || activeParser.getClass().getName() == "parser.ConfigurationSection") {
				
			} else {
			
				activeParser.Parse(linetoparse);
			}
		}
		} catch ( Exception err){
			System.out.println("Error parsing line: " + err.getMessage());
		}
	}
	
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
