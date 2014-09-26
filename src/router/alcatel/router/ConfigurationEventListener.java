package router.alcatel.router;

public interface ConfigurationEventListener {

	public void notifyAssociationCreated(AlcatelObject associationParent, AlcatelObject associationChild );
	
	public void notifyBindingAdded(AlcatelObject bindingParent, AlcatelObject bindingChild);
	
}
