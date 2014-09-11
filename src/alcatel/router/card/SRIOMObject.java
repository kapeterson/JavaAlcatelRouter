package alcatel.router.card;


public class SRIOMObject extends SRCardObject{
    protected SRMDAObject[] mdas;
    
	public SRIOMObject(int cardNumber, String ctype){
		super(cardNumber, ctype);
		mdas = new SRMDAObject[2];
		mdas[0] = null;
		mdas[1] = null;
		
	}

	public void setMDA(int complex, SRMDAObject mda){
		mdas[complex-1] = mda;
	}
	
	public SRMDAObject getMDA(int complex){
		return mdas[complex-1];
	}
	
	public boolean isIOMObject(){
		return true;
	}
}
