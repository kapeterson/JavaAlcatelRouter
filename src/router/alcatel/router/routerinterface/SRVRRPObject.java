package router.alcatel.router.routerinterface;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRVRRPObject extends AlcatelObject {
	
	protected String backupAddress = "";
	protected boolean preempt = true;
	protected int priority = 100;
	protected boolean pingReply = false;
	protected boolean sshReply = false;
	protected boolean tracerouteReply = false;
	
	public SRVRRPObject(int instanceID){
		super(AlcatelObjectType.VRRPOBJECT);
	}
	
	public void setBackup(String backup){
		
	}
	
	public String getBackup(){
		return this.backupAddress;
	}
	
	
	public void setPreempt(boolean value){
		this.preempt = value;
	}
	
	public boolean isPreempt(){
		return this.preempt;
	}
	
	public boolean getPreempt(){
		return this.preempt;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public void setPrintReply(boolean value){
		this.pingReply = value;
	}
	
	public boolean getPingReply(){
		return this.pingReply;
	}
	
	public void setSSHReply(boolean value){
		this.sshReply = value;
	}
	
	public boolean getSSHReply(){
		return this.sshReply;
	}

	public void setTracerouteReply(boolean value){
		this.tracerouteReply = value;
	}
	
	public boolean getTracerouteReply(){
		return this.tracerouteReply;
	}
}
	

