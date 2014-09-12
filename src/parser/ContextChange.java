package parser;

/**
 * Notifier Interface for context/config mode changes.  This is used to notify the ParserManager when context changes from one to another
 * for instance, when the router changes from RouterInterface context to the RouterInterface ingress context.
 * Also used to notify when leaving or entering a new context.
 */
public interface ContextChange {
	
	/**
	 * Notify ParserManager of a context change
	 */
	public void contextChangeCallback(ConfigurationSection oldsection, ConfigurationSection newsection);
	
	/**
	 * Notify ParserManager of a depth change but not necessarily context.  This will not change
	 * context if a class for the context is not implemented
	 */
	public void contextDepthChange(int depth);
}
