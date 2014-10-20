package router.alcatel.router.igmp;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.ip.IPv4Address;


/**
 * Models an IGMP Static join
 * @author Kris Peterson
 *
 */
public class SRSSMTranslation extends AlcatelObject {

	/** Start multicast group **/
	protected IPv4Address startAddress;
	
	/** End multicast address **/
	protected IPv4Address endAddress;
	
	/** Source address for the SSM translation **/
	protected IPv4Address sourceAddress;

	
	public SRSSMTranslation(String startAddress, String endAddress){
		super(AlcatelObjectType.IGMPSSM);
		//this.setName(interfacename);
		this.startAddress = new IPv4Address(startAddress, "32");
		this.endAddress = new IPv4Address(endAddress, "32");
	}
	
	
	/** Sets the source IP address for the SSM **/
	public void setSource(String sourceAddress){
		this.sourceAddress = new IPv4Address(sourceAddress, "32");
	}
	
	
	/** Returns the start IP address of the ssm translation represented as a string **/
	public String getStart(){
		return this.startAddress.getHostAddress();
	}
	
	
	/** Retuns the end ip address of the ssm translation represented as a string **/
	public String getEnd(){
		return this.endAddress.getHostAddress();
	}
	
	
	/** Returns the source of the SSM translation **/
	public String getSource(){
		return this.sourceAddress.getHostAddress();
	}
	
}
