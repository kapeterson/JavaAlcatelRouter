package parser;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.Method;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;

/**
 * Base class for moding a configuration section.  This implements the Section interface
 * The class is used to handle and parse all configuration lines.  There should be a
 * specific extenstion of this class when parsing configuration files.  The state machine
 * should keep trace of the current context and/or mode of the configuration and call
 * the appropriate sub ConfigurationSection sub-class to handle a given configuration command.
 * 
 * @author Kris Peterson
 *
 */
public abstract class ConfigurationSection    {
	
	protected SRChassisObject router;

	/** Depth of last configuration line **/
	protected int lastDepth;
	
	/** Handler for context changes **/
	protected ContextChange contextChange;
	
	/** Name of the current Section.  All subclasses should implement this **/
	protected String sectionName;
	
	/** Parent configuration section.  Used to hand control back when a context is exited **/
	protected ConfigurationSection parent;
	
	/** Hashtable of regex, to commandHandler **/
	protected Hashtable<Pattern, CommandHandler> commandHash;
	
	/** The start depth of the current section **/
	protected int sectionDepth = -1;
	
	
	protected String currentLine = "";
	
	/**
	 * 
	 * @param configSectionName	Custom string for the configuration section.  Each subclass should be unique
	 * @param routerObject	RouterObject that will be populated by the parser
	 * @param contextChangeHandler	Notifier for context changes.
	 */
	public ConfigurationSection(String configSectionName, SRChassisObject routerObject, ContextChange contextChangeHandler){
		
		this.sectionName = configSectionName;
		this.router = routerObject;
		
		commandHash = new Hashtable<Pattern, CommandHandler>();
		lastDepth = -1;
		this.parent = null;
		
		this.contextChange = contextChangeHandler;
		this.commandHash.put(Pattern.compile("^exit$"), new CommandHandler("exitSection",false));
		
	}
	
	
	/**
	 * Parse the configuration line.  The lines should be handed to this method one at a time.
	 * The commandHash will be searched for a match.  If a match is found int eh commandHash, 
	 * the appriate handler from CommandHandler.getMethodName() will be called to handle the 
	 * configuration line
	 * @param line	Single line from the configuration file
	 */
	public void Parse(String line){
		this.currentLine = line;
		//if ( this.getName() == "CONFIG.QOS")
		//	System.out.println(line);
		
		for ( Pattern regex: commandHash.keySet()){

			Matcher matcher = regex.matcher(line.trim());
			
			if ( matcher.matches()){
				
				int depth = line.length() - line.trim().length();
				
				
				// possible new section if the spacing changes
				if ( depth != lastDepth){
					
					lastDepth = depth;
					this.contextChange.contextDepthChange(depth);
				}

				/*
				if ( this.getName() == "CONFIG.FILTER" || this.getName() == "CONFIG.FILTER.IP"){
					
					if ( line.contains("filter"))
						System.out.println(line + " at depth = " + this.getLastCommandDepth());
					
					if ( line.contains("exit") && this.getLastCommandDepth() <  12)
						System.out.println(line + " at " + this.getLastCommandDepth());
				}
				*/
				
				Class<? extends ConfigurationSection> c = this.getClass();
				String mName = "";
				
				try {
					
					CommandHandler handler = commandHash.get(regex);
					mName = handler.getMethod();
					Method methodToCall = c.getDeclaredMethod(mName, new Class[]{ Matcher.class });
					methodToCall.invoke(this,matcher);
					
				} catch ( Exception err ){
					System.out.println("Error invoking method  " + mName + " error = " + err.getMessage());
				}
			}
		}
	}

	public String getCurrentLine(){
		return this.currentLine;
	}
	/**
	 * Set the parent configuration section/mode.
	 * @param section	Section that is the parent for the current context/mode.
	 */
	public void setParent(ConfigurationSection section){
		this.parent = section;
	}
	
	/**
	 * REturns the parent configuration section
	 * @return ConfigurationSection object of the parent.  Null if it is a top level context/mode.
	 */
	public ConfigurationSection getParent(){
		return this.parent;
	}
	
	
	/**
	 * 
	 * @return Returns the depth of the last/current command passed to the Parse command.
	 */
	public int getLastCommandDepth(){
		return this.lastDepth;
	}
	
	/**
	 * 
	 * @return Returns the start depth of the section - the depth of the command that changed the context to the current section.
	 */
	public int getSectionDepth(){
		return this.sectionDepth;
	}
	
	/**
	 * Sets the depth for the section
	 * @param depth Depth of the current section.  Number of spaces
	 */
	public void setSectionDepth(int depth){
		this.sectionDepth = depth;
	}
	
	/**
	 * @return Name of the configuration section
	 */
	public String getName(){
		return this.sectionName;
	}

	/*
	 * (non-Javadoc)
	 * @see parser.Section#exitSection(java.util.regex.Matcher)
	 */
	//public abstract void exitSection(Matcher matcher);
	
	/**
	 * Default handler for exiting a section.  The depth is checked
	 * to make sure the exit command was at the same depth as the entrance
	 * depth to the context/section.  If it is the same, then change then
	 * notify the ParserManager of the change through the contextChangeCallback.
	 * @param matcher The Match statement from the regex that was used to match the command.
	 */
	public void defaultExitHandler(Matcher matcher){
		if ( this.getLastCommandDepth() == this.sectionDepth) {
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}

	public abstract void exitSection(Matcher matcher);
	
	public ContextChange getContextNotifier(){
		return this.contextChange;
	}
	
	public void addObject(AlcatelObject object){
		
	}
}
