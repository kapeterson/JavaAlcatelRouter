package parser;

/**
 * Models parameters for a router command.  The CommandHandler will contain the string
 * method name that should be called when the command reached.  The method name will
 * be used to invoke a method when needed (i.e. a partciular command is reached.
 * <p>
 * 
 * @param functionName the name of the function that is called when command is reached in configuration
 * @param inserasedepth boolean value telling whether or not the command insreases the depth of the configuration context
 * 		meaning a sub context
 * 
 * @author pete
 *
 */
public class CommandHandler {

	/**
	 * STores the method name to invoke when the command is encountered
	 */
	protected String methodName;
	protected Boolean increaseDepth;

	/**
	 * Initializer for CommandHandler
	 * @param functionName	Name of the function to invoke when when the command is encountered
	 * @param increasedepth	Boolean for whteher or not the command increases the depth of the configuration
	 */
	public CommandHandler(String functionName, Boolean increasedepth){
		methodName = functionName;
		increaseDepth = increasedepth;
	}
	
	/**
	 * Returns the method name that will be called when the command is reached
	 */
	public String getMethod(){
		return this.methodName;
	}
	
	/**
	 * 
	 * @return	Returns boolean true = increases depth. false = doesn't increase depth
	 */
	public Boolean insereasesDepth(){
		return this.increaseDepth;
	}
	
	
}
