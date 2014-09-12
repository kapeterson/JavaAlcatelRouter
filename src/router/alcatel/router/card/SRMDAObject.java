package router.alcatel.router.card;

import router.alcatel.router.AlcatelHardwareObject;

public class SRMDAObject extends AlcatelHardwareObject{

	protected int mdaComplex = -1;
	protected String mdaType = "";
	
	public SRMDAObject(){
		mdaType = "";
		
	}
	
	public SRMDAObject(int complex){
		this.mdaComplex = complex;
		this.mdaType = "";
	}
	public SRMDAObject(int complex, String mdatype){
		this.mdaType = mdatype;
		this.mdaComplex = complex;
	}
	public SRMDAObject(String mdatype){
		mdaType = mdatype;
	}
	

	public String getMDAType(){
		return this.mdaType;
	}
	
	public void setMDAType(String mdaType){
		this.mdaType = mdaType;
	}
	
	public int getComplex(){
		return this.mdaComplex;
	}
	
	public void setComplex(int complex){
		this.mdaComplex = (complex-1);
	}
}
