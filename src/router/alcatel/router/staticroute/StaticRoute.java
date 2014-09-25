package router.alcatel.router.staticroute;

public interface StaticRoute {

	public boolean isStaticRoute();
	public String getNetwork();
	public String getMask();
	public String getNextHop();
	public boolean isIPv4Route();
	public boolean isIPv6Route();
	public String getRouteCommand();
}
