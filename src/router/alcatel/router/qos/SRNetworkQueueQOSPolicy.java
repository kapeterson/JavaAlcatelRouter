package router.alcatel.router.qos;

import router.alcatel.router.*;


public class SRNetworkQueueQOSPolicy extends AlcatelObject {
	
	protected String policyName = "";
	
	public SRNetworkQueueQOSPolicy(){
		super(AlcatelObjectType.NETWORKQUEUEQOSPOLICY);
		this.policyName = "";
	}
	
	public String getPolicyName(){
		return this.policyName;
		
	}
	
	public void setPolicyName(String policyname){
		this.policyName = policyname;
		this.setName(policyname);
	}
}
