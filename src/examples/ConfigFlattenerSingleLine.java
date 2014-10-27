package examples;

import java.io.IOException;

import router.alcatel.flattener.ConfigFlattener;

public class ConfigFlattenerSingleLine {

	public static void main(String[] args) throws IOException{
		
		if (args.length < 1){
			System.out.println("Error you must supply path to configuration file");
			return;
		}		
		
		String configfile = args[0];
		ConfigFlattener flattener = new ConfigFlattener();
		
		flattener.populateConfig(configfile);
		flattener.returnToRootNode();
		
		String cmd = "";
		while ( (cmd = flattener.getNextCommand()) != null){
			System.out.println(cmd);

		}


	}
	
}
