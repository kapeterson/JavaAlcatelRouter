package router.alcatel.router.igmp;


import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;


/**
 * Models an IGMP Static join.  Stores group and source address.
 * @author kp109p
 *
 */
public class SRIGMPStaticJoin extends AlcatelObject {

	/** Stores the group address **/
	String groupAddress = null;
	
	/** store the source address **/
	String groupSource = null;
	
	
	/**
	 * Constructor #1
	 * @param groupAddress
	 */
	public SRIGMPStaticJoin(String groupAddress){
		super(AlcatelObjectType.IGMPSTATICJOIN);
		this.groupAddress = groupAddress;

	}
	
	
	/**
	 * Constructor #2
	 * @param groupAddress STring
	 * @param src	String
	 */
	public SRIGMPStaticJoin(String groupAddress, String src){
		super(AlcatelObjectType.IGMPSTATICJOIN);
		this.groupAddress = groupAddress;
		this.groupSource = src;
	}
	
	/**
	 * Return the string value representation of the group address
	 * @return String with the group address
	 */
	public String getGroup(){
		return this.groupAddress;
	}
	
	/**
	 * Return the string value representation of the source address
	 * @return String with the source address
	 */
	public String getSource(){
		return this.groupSource;
	}
	
	
	/**
	 * Sets the source ip address of the static join
	 * @param src
	 */
	public void setSource(String src){
		this.groupSource = src;
	}
	

}
