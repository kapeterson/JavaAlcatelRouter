package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.ospf.*;

public class OSPFAreaTest01 {
	public static void main(String[] args){
	if (args.length < 1){
		System.out.println("Error you must supply path to configuration file");
		return;
	}		
	
	String cfile = args[0];
	System.out.println("Going to parse " + cfile);
	

	Alcatel7x50ParserManager pman = new Alcatel7x50ParserManager();

	try {
		pman.ParseConfig(cfile);
		SRChassisObject router = pman.getRouter();

		
		for ( String areaname : router.OSPF.getAreas().keySet()){
			SROSPFArea area = router.OSPF.getArea(areaname);
			//System.out.println("Areaname is of type " + areaname.getClass().toString() + " areaname ");
			System.out.format("AREA:  %s  \n",  areaname);
			for ( String intName : area.getInterfaces().keySet() ){
				System.out.format("\tInterface: %-15s \n", intName);
			}
			
		}
		
	} catch ( Exception e) {
		System.out.println("Run test error : " + e.getMessage());
	}
	}
}
