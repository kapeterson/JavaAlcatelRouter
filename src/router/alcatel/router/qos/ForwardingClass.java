package router.alcatel.router.qos;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/** Models a forwarding class **/
public class ForwardingClass extends AlcatelObject {
	
	int queue = 0;
	
	public ForwardingClass(String fcname){
		super(AlcatelObjectType.FORWARDINGCLASS);
		this.setName(fcname);

	}
	
	/** Set the Queue number for the forwarding class **/
	public void setQueue(int q){
		this.queue = q;
	}
	
	/** Get the queue for the forwarding class **/
	public int getQueue(){
		return this.queue;
		
	}
}
