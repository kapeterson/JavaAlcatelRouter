package examples;

import java.io.IOException;

import router.alcatel.flattener.ConfigFlattener;

public class ConfigFlattenerTest {

	public static void main(String[] args) throws IOException{
		
		if (args.length < 1){
			System.out.println("Error you must supply path to configuration file");
			return;
		}		
		
		String configfile = args[0];
		ConfigFlattener flattener = new ConfigFlattener();
		flattener.flattenConfig(configfile);
		//flattener.traverseTree();
	}
}
