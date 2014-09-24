package router.alcatel.router.lag;
import java.util.TreeMap;

public class SRLagConfiguration {
	protected TreeMap<Integer, SRLagObject> lags = null;
	
	public SRLagConfiguration(){
		this.lags = new TreeMap<Integer, SRLagObject>();
		
	}
	
	public void addLag(SRLagObject lag){
		this.lags.put(lag.getLagNumber(), lag);
	}
	
	public SRLagObject getLag(Integer lagnumber){
		return this.lags.get(lagnumber);
	}
	
	public TreeMap<Integer, SRLagObject> getLags(){
		return this.lags;
	}
	
	public boolean hasLag(Integer lagnumber){
		return this.lags.containsKey(lagnumber);
	}
	

}
