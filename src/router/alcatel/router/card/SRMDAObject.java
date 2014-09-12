package router.alcatel.router.card;

import router.alcatel.router.AlcatelHardwareObject;
import router.alcatel.router.AlcatelObjectType;
/**
 * Models an Alcatel MDA 
 * @author Kris Peterson
 *
 */
public class SRMDAObject extends AlcatelHardwareObject{

	/** MDA complex.  Wil be relative 1 */
	protected int mdaComplex = -1;
	
	/** MDA type **/
	protected String mdaType = "";
	
	/**
	 * Default constructor with no parameters
	 */
	public SRMDAObject(){
		super(AlcatelObjectType.MDA);
		mdaType = "";
		
	}
	
	/**
	 * Constructor with MDA complex only
	 * @param complex integer value for the MDA complex relative 1.  (i.e. first mda starts at 1)
	 */
	public SRMDAObject(int complex){
		super(AlcatelObjectType.MDA);
		this.mdaComplex = complex;
		this.mdaType = "";
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
		return this.mdaType;
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
}
