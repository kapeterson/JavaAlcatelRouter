package router.alcatel.router;
import java.util.ArrayList;

/**
 * Interface to be used for association children.  Any object that can be associated with another should
 * implement this interface.  When associations are added, verify that it is an Association Child.  For example,
 * a port an be associated with Many different saps, lags, and interfaces.  This allows you to get a list of
 * all associations for the port.  The port would be the association child.  The SAP, Int or lag would be the
 * association parent.
 * 
 * @author kp109p
 *
 */
public interface AssociationChild {
	
	/**
	 * Adds an association to the associationParent
	 * @param associationParent
	 */
	public void addAssociation(AlcatelObject associationParent);
	
	
	public boolean isAssociationChild();
	
	/**
	 * Get the list of all assocations as an ArrayList of AlcatelObjects
	 * @return ArrayList of AlcatelObjects
	 */
	public ArrayList<AlcatelObject> getAssociations();
}
