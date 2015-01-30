package router.alcatel.router.qos;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class ForwardingClass extends AlcatelObject {
	
	int queue = 0;
	
	public ForwardingClass(String fcname){
		super(AlcatelObjectType.FORWARDINGCLASS);
		this.setName(fcname);

	}
	public void setQueue(int q){
		this.queue = q;
	}
	
	public int getQueue(){
		return this.queue;
		
	}
}
