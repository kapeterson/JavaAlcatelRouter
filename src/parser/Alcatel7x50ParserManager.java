package parser;

import java.util.Hashtable;

import parser.alu.config.sr7x50.system.*;
import parser.alu.config.sr7x50.card.*;

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
		contextHash.put("echo \"Port Configuration\"", defaultParser);
	}
	
	public void setActiveParser(ConfigurationSection parser){
		activeParser = parser;
	}
	
	public SRChassisObject getRouter(){
		return this.router;
	}
	
	public void ParseLine(String linetoparse){
		
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