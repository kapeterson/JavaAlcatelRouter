package router.alcatel.router;
import java.util.ArrayList;

public interface AssociationChild {
	
	public void addAssociation(AlcatelObject associationParent);
	public boolean isAssociationChild();
	public ArrayList<AlcatelObject> getAssociations();
}
