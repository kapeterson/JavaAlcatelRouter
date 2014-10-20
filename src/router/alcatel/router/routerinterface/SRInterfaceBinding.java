package router.alcatel.router.routerinterface;

import router.alcatel.router.*;


/**
 * Models an interface binding.  Used to store information about a binding object that is bound to 
 * an interface or lag
 * @author kp109p
 *
 */
public class SRInterfaceBinding {
	
	/** Reference to the object that is bound **/
	protected AlcatelObject binding = null;
	
	/** Tag of the binding object **/
	protected Integer bindingTag = -1;
	
	public SRInterfaceBinding(AlcatelObject bindingObject){
		this.binding = bindingObject;
	}
	
	public SRInterfaceBinding(AlcatelObject bindingObject, int tag){
		this.binding = bindingObject;
		this.bindingTag = tag;
		
	}
	
	
	/** Returns the binding object as type AlcatelObject.  It cab be cast to the correct object type **/
	public AlcatelObject getBindingObject(){
		return this.binding;
	}
	
	/** is the binding tagged? **/
	public boolean isTagged(){
		return (this.bindingTag > -1);
	}
	
	
	/** Get the tag on the bound object **/
	public Integer getTag(){
		return this.bindingTag;
	}
	
	
	/** get the type of object that is bound **/
	public AlcatelObjectType getObjectType(){
		
		if ( this.binding == null)
			return null;
		else
			return this.binding.getObjectType();
	}
	
}
