package parser.alu.config.sr7x50.system;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SystemParser extends ConfigurationSection  {
	
	protected ContextChange cChange;
	
	public SystemParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.SYSTEM", router, contextChangeHandler);
		
		this.commandHash.put(Pattern.compile("name \"(.*)\""), new CommandHandler("setHostName", false));
		this.commandHash.put(Pattern.compile("chassis\\-mode ([a-z])"), new CommandHandler("setChassisMode", false));
		this.commandHash.put(Pattern.compile("location (.*)"), new CommandHandler("setLocation", false));
		this.commandHash.put(Pattern.compile("^snmp$"), new CommandHandler("setSNMP", true));
		this.commandHash.put(Pattern.compile("^system$"), new CommandHandler("setSystemDepth",false));
		this.commandHash.put(Pattern.compile("^persistence$"), new CommandHandler("changeToPersistanceContext",false));

	}

	public void setHostName(Matcher matcher){
		//System.out.println("SetHostName invoked");
		router.System.setHostName(matcher.group(1));
		
	}
	
	public void setChassisMode(Matcher matcher){
		//System.out.println("Set Chassis mode ");
		router.System.setChassiMode(matcher.group(1));
	}
	
	public void setLocation(Matcher matcher){
		//System.out.println("Set Chassis mode ");
		router.System.setLocation(matcher.group(1));
	}
	
	public void setSNMP(Matcher matcher){
		//System.out.println("Calls setSNMP depth is " + this.getLastCommandDepth());
		SystemSNMPParser snmpParser = new SystemSNMPParser(router, this.contextChange);
		snmpParser.setSectionDepth(this.getLastCommandDepth());
		snmpParser.setParent(this);
		this.contextChange.contextChangeCallback(this, snmpParser);
		
	}
	
	public void changeToPersistanceContext(Matcher matcher){
		//System.out.println("Calls to change to persistence depth is " + this.getLastCommandDepth());
		SystemPersistenceParser persistenceParser = new SystemPersistenceParser(router, this.contextChange);
		persistenceParser.setSectionDepth(this.getLastCommandDepth());
		persistenceParser.setParent(this);
		this.contextChange.contextChangeCallback(this, persistenceParser);
	}
	
	public void setSystemDepth(Matcher matcher){
		//System.out.println("Ssetting System's self depth to   " + this.getLastCommandDepth());
		this.setSectionDepth(4);
	}
	public void exitSection(Matcher matcher){
		//System.out.println("System config exit");
		contextChange.contextChangeCallback(this,null);
	}

}
