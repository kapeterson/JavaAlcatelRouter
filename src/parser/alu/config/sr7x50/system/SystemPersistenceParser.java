package parser.alu.config.sr7x50.system;

import parser.CommandHandler;
import parser.ContextChange;
import parser.ConfigurationSection;
import router.alcatel.router.SRChassisObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemPersistenceParser extends ConfigurationSection {
	
	public SystemPersistenceParser(SRChassisObject routerObject, ContextChange contextChangeHandler ){
		super("CONFIG.SYSTEM.PERSISTENCE", routerObject, contextChangeHandler);
		this.commandHash.put(Pattern.compile("description \"(.*)\""), new CommandHandler("setDescription", false));
		this.commandHash.put(Pattern.compile("location (.*)"), new CommandHandler("setPersistenceLocation", false));


	}
	
	public void setPersistenceLocation(Matcher matcher){
		System.out.println("\tSet location of the persistence to " + matcher.group(1));
	}
	
	
	public void setDescription(Matcher matcher){
		System.out.println("\tSet description of the persistence to " + matcher.group(1));
	}
	
	public void exitSection(Matcher matcher){
		super.defaultExitHandler(matcher);
	}

}
