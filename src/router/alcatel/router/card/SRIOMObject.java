package router.alcatel.router.card;

import router.alcatel.router.AlcatelObjectType;

/**
 * Models SR 7x50 IOM 
 * @author Kris Peterson
 *
 */
public class SRIOMObject extends SRCardObject{
    protected SRMDAObject[] mdas;
    
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
    }
    
    /**
     * Constructor using card number and type
     * @param cardNumber  Integer slot number relative 1
     * @param ctype	Card type
     */
	public SRIOMObject(int cardNumber, String ctype){
		super(AlcatelObjectType.IOM, cardNumber, ctype);
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
}
