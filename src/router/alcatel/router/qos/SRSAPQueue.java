package router.alcatel.router.qos;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRSAPQueue extends AlcatelObject{

	public String rate = "max";
	public String cir = "0";
	public String mbs = "default";
	public String cbs = "default";
	public Integer queuenumber = 0;
	
	public SRSAPQueue(Integer queuenumber){
		super(AlcatelObjectType.SRSAPQUEUE);
		this.queuenumber = queuenumber;
	}
	
	public void setRate(String rate){
		this.rate = rate;
	}
	
	public void setCIR(String cir){
		this.cir = cir;
	}
	
	public String getCIR(){
		return this.cir;
	}
	
	public String getRate(){
		return this.rate;
	}
	
	public void setMBS(String mbs){
		this.mbs = mbs;
	}
	
	public void setCBS(String cbs){
		this.cbs = cbs;
	}
	
	public String getMBS(){
		return this.mbs;
	}
	
	public String getCBS(){
		return this.cbs;
	}
	
	public Integer getQueueNumber(){
		return this.queuenumber;
	}
}
