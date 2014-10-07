package router.alcatel.router;


/**
 * A Class containing commonly used regular expressions
 * @author kp109p
 *
 */
public final class RouterRegex {

	/** Matches a port and vlan tag is provided **/
	public final static String PortRegex = "([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?";
	
	public final static String lagSapRegex = "lag\\-([0-9]+)(:)?([0-9]{1,10})?";
	
	/** Match lag ports **/
	public final static String lagPortPattern = "([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?";
	
	/** Matches a spoke sdp **/
	public final static String serviceSDPPattern = "(spoke)\\-sdp ([0-9]+):([0-9]+) (.*)";
	
	/** Matches the sdp and vcid of a service sdp **/
	public final static String sdpInfoPattern = "([0-9]+)\\:([0-9]+)";

	
}
