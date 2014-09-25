package parser.alu.config.sr7x50.staticroute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.staticroute.*;

public class StaticRouteConfigurationParser extends ConfigurationSection  {
	
	
	protected Pattern tagPattern = Pattern.compile("tag ([0-9]+)");
	protected Pattern metricPattern = Pattern.compile("metric ([0-9]+)");
	public StaticRouteConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.STATICROUTE", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^static\\-route ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)\\/([0-9]+) next\\-hop ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+) .*"), new CommandHandler("addStaticRoute", true));
	}
	
	
	public void addStaticRoute(Matcher matcher){

		//System.out.println("Found static route " + matcher.group(1));
		SRIPv4StaticRoute myRoute = new SRIPv4StaticRoute(matcher.group(1), matcher.group(2), matcher.group(3));
		//System.out.println("\n\nMatched on string " + matcher.group());
		Matcher tmatch = tagPattern.matcher(matcher.group());
		
		if ( tmatch.find()){
			myRoute.setTag(Integer.parseInt(tmatch.group(1)));
		}
		
		Matcher mMatch = metricPattern.matcher(matcher.group());
		
		if ( mMatch.find()){
			myRoute.setMetric(Integer.parseInt(mMatch.group(1)));
		}
		
		this.router.StaticRoute.addIPv4StaticRoute(myRoute);
		
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
