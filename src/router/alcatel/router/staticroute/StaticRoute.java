package router.alcatel.router.staticroute;

/**
 * Static route interface that all static routes must implement
 * @author KRis Peterson
 *
 */
public interface StaticRoute {

	public boolean isStaticRoute();
	public String getNetwork();
	public String getMask();
	public String getNextHop();
	public boolean isIPv4Route();
	public boolean isIPv6Route();
	public String getRouteCommand();
	public boolean isNextHopRoute();
	public boolean isIndirectRoute();
	public boolean isBlackHoleRoute();
}
