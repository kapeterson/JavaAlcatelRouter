package parser;
import java.util.regex.Matcher;

import router.alcatel.router.SRChassisObject;


/**
 * Default configuration section that is used to handle unimplemented configuration contexts or modes
 */
public class DefaultConfigurationSection extends ConfigurationSection {
	
	/**
	 * Initializer
	 * @param routerObject Router object that will be populated with paring.  Does not apply since this is a dummy class
	 * @param contextChangeHandler Notifier interface to the ParserManager.  Not used since this is a dummy class.
	 */
	public DefaultConfigurationSection(SRChassisObject routerObject, ContextChange contextChangeHandler){
		super("DEFAULTCONFIG", null, contextChangeHandler);
	}
	
	public void exitSection(Matcher matcher){
		//super.contextChange.contextChangeCallback(null,  null);
		//System.out.println("Exit default section");
	}
	

}
