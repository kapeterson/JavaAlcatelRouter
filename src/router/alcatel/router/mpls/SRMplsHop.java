package router.alcatel.router.mpls;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models an MPLS Hop
 * @author Kris Peterson
 *
 */
public class SRMplsHop extends AlcatelObject {

	/** Index number of the hopi **/
	protected Integer hopNumber = -1;
	
	/** IP address of the HOP **/
	protected String hopAddress = "";
	
	public SRMplsHop(Integer hopnumber, String hopAddress){
		super(AlcatelObjectType.MPLSPATHHOP);
		this.hopNumber = hopnumber;
		this.hopAddress = hopAddress;
	}
	
	
	/** Get the Hop address **/
	public String getAddress(){
		return this.hopAddress;
	}

	
	/** get the hop number **/
	public Integer getHopNumber(){
		return this.hopNumber;
	}
}
