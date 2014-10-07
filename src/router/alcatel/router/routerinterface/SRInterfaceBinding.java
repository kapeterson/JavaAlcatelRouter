package router.alcatel.router.routerinterface;

import router.alcatel.router.*;

public class SRInterfaceBinding {
	
	protected AlcatelObject binding = null;
	protected Integer bindingTag = -1;
	
	public SRInterfaceBinding(AlcatelObject bindingObject){
		this.binding = bindingObject;
	}
	
	public SRInterfaceBinding(AlcatelObject bindingObject, int tag){
		this.binding = bindingObject;
		this.bindingTag = tag;
		
	}
	
	public AlcatelObject getBindingObject(){
		return this.binding;
	}
	
	public boolean isTagged(){
		return (this.bindingTag > -1);
	}
	
	public Integer getTag(){
		return this.bindingTag;
	}
	
	public AlcatelObjectType getObjectType(){
		
		if ( this.binding == null)
			return null;
		else
			return this.binding.getObjectType();
	}
	
}
