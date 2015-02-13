package parser.alu.config.sr7x50.qos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import parser.alu.config.sr7x50.service.SAPEgressParser;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.*;
public class QosConfigurationParser extends ConfigurationSection {
	
	public QosConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.QOS", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^sap\\-ingress ([0-9]+) create"), new CommandHandler("setSapIngressContext", true));
		this.commandHash.put(Pattern.compile("^sap\\-egress ([0-9]+) create"), new CommandHandler("setSapEgressContext", true));
		this.commandHash.put(Pattern.compile("^network ([0-9]+) create"), new CommandHandler("setNetworkQosContext", true));
		this.commandHash.put(Pattern.compile("^network\\-queue \"([0-9]+)\" create"), new CommandHandler("setNetworkQueueQosContext", true));

	}
	
	public void setNetworkQueueQosContext(Matcher matcher){
		//System.out.println("Network-queue " + matcher.group(1));
		SRNetworkQueueQOSPolicy policy = new SRNetworkQueueQOSPolicy();
		policy.setPolicyName(matcher.group(1));
		this.router.QOS.addNetworkQueueQOSPolicy(policy);
	}
	
	public void setSapEgressContext(Matcher matcher){
		//System.out.println("going to sap egress with depth = " + this.getLastCommandDepth());
		SapEgressParser parser = new SapEgressParser(router, this.getContextNotifier(), Integer.parseInt(matcher.group(1)), this.getLastCommandDepth());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void setNetworkQosContext(Matcher matcher){
		//System.out.println("Network QOS " + matcher.group(1));
		SRNetworkQOSPolicy policy = new SRNetworkQOSPolicy(Integer.parseInt(matcher.group(1)));
		this.router.QOS.addNetworkQOSPolicy(policy);
			
	}
	
	public void setSapIngressContext(Matcher matcher){
		//System.out.println("Sap ingress " + matcher.group(1));
		
		/*
		SAPIngressQOSPolicy policy = new SAPIngressQOSPolicy();
		policy.setPolicyNumber(Integer.parseInt(matcher.group(1)));
		this.router.QOS.addSAPIngressQOSPolicy(policy);
		*/
		SapIngressParser parser = new SapIngressParser(router, this.getContextNotifier(), Integer.parseInt(matcher.group(1)), this.getLastCommandDepth());
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
	}
	
	public void addObject(AlcatelObject obj){
		//System.out.println("Added objecdt of type " + obj.getObjectType());
		if ( obj.isSAPEgressQOSPolicy()){
			this.router.QOS.addSAPEgressQOSPolicy((SAPEgressQOSPolicy)obj);
		} else if ( obj.isSAPIngressQOSPolicy()) {
			this.router.QOS.addSAPIngressQOSPolicy((SAPIngressQOSPolicy)obj);
		}
	}
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		//System.out.println("Exited qos");
		super.defaultExitHandler(matcher);
	}
}
