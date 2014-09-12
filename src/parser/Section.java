package parser;
import java.util.regex.Matcher;

/**
 * Interface for a configuration section or configuration mode
 */
public interface Section {
	
	/**
	 * Handler when exiting a configuration section or mode
	 * 
	 */
	public void exitSection(Matcher matcher);
	
	/**
	 * Returns the name of the configuration section or mode
	 */
	public String getName();
}
