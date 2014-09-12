package router.alcatel.router.card;


public class SRIOMObject extends SRCardObject{
    protected SRMDAObject[] mdas;
    
    public SRIOMObject(){
    	super();
    }
    
    public SRIOMObject(int cardNumber){
    	super(cardNumber);
    }
	public SRIOMObject(int cardNumber, String ctype){
		super(cardNumber, ctype);
		mdas = new SRMDAObject[2];
		mdas[0] = null;
		mdas[1] = null;
		
	}

	public void setMDA(int complex, SRMDAObject mda){
		mdas[complex-1] = mda;
	}
	
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
	
	public boolean isIOMObject(){
		return true;
	}
}
