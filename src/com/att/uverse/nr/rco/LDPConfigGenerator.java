package com.att.uverse.nr.rco;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;

// CICRILCI0BW040406101LVH23
// 
public class LDPConfigGenerator {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 1){
			System.out.println("Error you must supply path to configuration file");
			return;
		}		
		
		String cfile = args[0];
		System.out.println("Going to parse " + cfile);;
		

		Alcatel7x50ParserManager pman = new Alcatel7x50ParserManager();

		try {
			pman.ParseConfig(cfile);
			SRChassisObject router = pman.getRouter();
			System.out.println("Router is BBRouter = " + router.isBB());
			System.out.println("Router is ODRRouter = " + router.isODR());
			System.out.println("Router is CRRouter = " + router.isCR());

			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
