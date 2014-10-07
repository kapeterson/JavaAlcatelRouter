package router.alcatel.router.card;

import router.alcatel.router.AlcatelObjectType;

/**
 * Models SR 7x50 IOM 
 * @author Kris Peterson
 *
 */
public class SRIOMObject extends SRCardObject{
	
	
    protected SRMDAObject[] mdas;
    
    public SRIOMForwardingPath FP = null;
    /**
     * Empty constructor
     */
    public SRIOMObject(){
    	super(AlcatelObjectType.IOM);
    }
    
    /**
     * Constructor with card number only
     * @param cardNumber integer slot position relative 1
     */
    public SRIOMObject(int cardNumber){
    	super(AlcatelObjectType.IOM, cardNumber);
		mdas = new SRMDAObject[2];
		mdas[0] = null;
		mdas[1] = null;
    }
    
    /**
     * Constructor using card number and type
     * @param cardNumber  Integer slot number relative 1
     * @param ctype	Card type
     */
	public SRIOMObject(int cardNumber, String ctype){
		super(AlcatelObjectType.IOM, cardNumber, ctype);
		this.setCardType(ctype);
		
		mdas = new SRMDAObject[2];
		mdas[0] = null;
		mdas[1] = null;
		
	}


	/**
	 * Assigns the mda object to the complex relative 1
	 * 
	 * @param complex Integer value of the complex in which the mda resides relative 1
	 * @param mda  MDAObject to put in position complex
	 */
	public void setMDA(int complex, SRMDAObject mda){
		mdas[complex-1] = mda;
	}
	
	/**
	 * @param complex
	 * @param mda
	 */
	public void addMDA(int complex, SRMDAObject mda){
		setMDA(complex, mda);
	}
	
	/**
	 * Returns the mda in mda complex.  Parameter is relative to 1.
	 * complex 1 = MDA 1
	 * complex 2 = MDA 2
	 * @param complex MDA Complex relative to 1.  
	 * @return the MDAObject in the given complex
	 */
	public SRMDAObject getMDA(int complex){
		return mdas[complex-1];
	}
	
	
	/**
	 * Always returns true
	 */
	public boolean isIOMObject(){
		return true;
	}
	
	
	/**
	 * Is the IOM an IMM
	 * @return
	 */
	public boolean isIMM(){
		return this.cardType.contains("imm");
	}
	

	/**
	 * Is the iom an iomb
	 * @return
	 */
	public boolean isIOMb(){
		return this.cardType.matches("iom(2)?\\-20g.*");
	}
	
	/**
	 * Is the iom an IOM3?
	 * @return
	 */
	public boolean isIOM3(){
		return this.cardType.matches("iom3.*");
	}
	
	/**
	 * sets the card type
	 * @param cardType string value of the card type.  unchecked / unverified.
	 */
	@Override
	public void setCardType(String cardType){
		this.cardType = cardType;
		if ( this.isIOM3() || this.isIMM()){
			this.FP = new SRIOMForwardingPath();
		}  else {
			this.FP = null;
		}
	}
}
