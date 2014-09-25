package router.alcatel.router;

public final class RouterRegex {

	public final static String PortRegex = "([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?";
	public final static String lagPortPattern = "([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,2})(:)?([0-9]{1,10})?";
	public final static String serviceSDPPattern = "(spoke)\\-sdp ([0-9]+):([0-9]+) (.*)";
	public final static String sdpInfoPattern = "([0-9]+)\\:([0-9]+)";

	
}
