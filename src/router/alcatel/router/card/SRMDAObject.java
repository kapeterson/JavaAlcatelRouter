package router.alcatel.router.card;

import router.alcatel.router.AlcatelHardwareObject;
import router.alcatel.router.AlcatelObjectType;
/**
 * Models an Alcatel MDA 
 * @author Kris Peterson
 *
 */
public class SRMDAObject extends AlcatelHardwareObject{

	/** Ingress MDA configuration object **/
	public SRFPIngress INGRESS = null;
	
	/** Accessor to the MDA Network configuration **/
	public SRMDANetwork NETWORK = null;
	
	
	/** Accessor to the MDA Access configuration **/
	public SRMDAAccess ACCESS = null;
	
	/** Admin state of the MDA **/
	protected boolean isShutdown = false;
	
	/** MDA complex.  Wil be relative 1 */
	protected int mdaComplex = -1;
	
	/** MDA type **/
	protected String mdaType = "";
	
	/**
	 * Default constructor with no parameters
	 */
	
	/*
	public SRMDAObject(){
		super(AlcatelObjectType.MDA);
		mdaType = "";
		this.initializeMDA();

	}
	*/
	
	/**
	 * Constructor with MDA complex only
	 * @param complex integer value for the MDA complex relative 1.  (i.e. first mda starts at 1)
	 */
	public SRMDAObject(int complex, SRIOMObject parentObject){
		super(AlcatelObjectType.MDA);
		this.setObjectType(AlcatelObjectType.MDA);
		this.mdaComplex = complex;
		this.mdaType = "";
		this.parent = parentObject;
		this.initializeMDA();
	}
	
	private void initializeMDA(){
		this.NETWORK = new SRMDANetwork(this);
		this.INGRESS = new SRFPIngress(this);
		this.ACCESS = new SRMDAAccess(this);
	}
	/**
	 * Constructor with MDA Complex and MDA Type
	 * @param complex  complex value for the mda.  relative 1 (i.e. starts at 1)
	 * @param mdatype  the mda type
	 */
	public SRMDAObject(int complex, String mdatype){
		super(AlcatelObjectType.MDA);
		this.mdaType = mdatype;
		this.mdaComplex = complex;
	}
	
	/**
	 * Constructor with MDA Type only
	 * @param mdatype  Type of mda
	 */
	public SRMDAObject(String mdatype){
		super(AlcatelObjectType.MDA);
		mdaType = mdatype;
	}
	

	/**
	 * REturns the mda type
	 * @return the string value of the mda type
	 */
	public String getMDAType(){
		if ( !((SRIOMObject)this.getParent()).isIMM())
			return this.mdaType;
		else {
			if (ImmHash.immTypes.containsKey(  ((SRIOMObject)this.parent).getCardType().trim() )) {
				return ImmHash.immTypes.get(((SRIOMObject)this.parent).getCardType().trim());
			} else {
				System.out.println("Error:  No hash type for card type " + this.mdaType);
				System.exit(1);
			}
	
		
		}
		return "ERR";
	}
	
	/**
	 * Sets the mda type
	 * @param mdaType STring value of the mda type.  Not verified
	 */
	public void setMDAType(String mdaType){
		this.mdaType = mdaType;
	}
	
	
	/**
	 * returns the complex of the mda
	 * @return complex value as an integer relative 1 (i.e. the first complex = 1)
	 */
	public int getComplex(){
		return this.mdaComplex;
	}
	
	
	/**
	 * Sets the value of the complex
	 * @param complex position of the complex relative to 1 (i.e. first mda value = 1)
	 */
	public void setComplex(int complex){
		this.mdaComplex = (complex-1);
	}
	
	/**
	 * Is the MDA shut ?
	 * @return boolean
	 */
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	
	/**
	 * is the mda admin up
	 * @return boolean
	 */
	public boolean isAdminUp(){
		return !this.isShutdown();
	}
	/**
	 * Sets the shutdown state of the mda ( shut or no shut )
	 * @param val
	 */
	public void setShudown(boolean val){
		this.isShutdown = val;
	}
	
	
	/** 'no shut' the mda **/
	public void adminUp(){
		this.isShutdown = false;
	}
	
	/**
	 * Shutdown the mda
	 */
	public void adminDown(){
		this.isShutdown = true;
	}


}
