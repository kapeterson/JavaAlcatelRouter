package router.alcatel.flattener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Queue;


public class ConfigFlattener {

	protected String fileName = "";
	protected Node rootNode = null;
	protected PrintWriter writer = null;
	
	protected Node activeNode = null;
	protected ArrayList<String> configList = null;
	
	public ConfigFlattener(){
		
	}
	
	public ConfigFlattener(String fname){
		this.fileName = fname;
	}
	

	public void flattenConfig(String filename) throws IOException{
		this.fileName = filename;
		
		int linecount = 0;
		int lastDepth = -1;
		
		Node rootNode = new Node(null, "root", -1);
		rootNode.setRoot();
		Node currentNode = rootNode;
		this.rootNode = rootNode;
		String lastline = "";
		String thisline = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			boolean keepgoing = true;
			while ( (( line = br.readLine()) != null) && keepgoing){
				
				thisline = line.trim();
				
				if ( line.trim().contains("parameters")){
					System.out.println("OK");
				}
				linecount++;
				
				if ( line.trim().length() == 0 || line.trim().charAt(0) == '#'  ){
					continue;
				}
					
				if ( line.trim().matches("^echo.*\".*\"")){
					if ( line.contains("QoS Policy")){
						//System.exit(1);
						//keepgoing = false;
					}
					continue;
				}
				
				int depth = line.length() - line.replaceAll("^\\s+",  "").length();
				
				//System.out.println("\n" + depth + "  " + line.trim());

				if ( line.trim().equals("exit")){
					
		
					if ( depth < lastDepth ) {
						if ( !currentNode.isRoot()){
							//System.out.println("** Received exit DEpth is less setting root to current node's parent with data = " + currentNode.getParent().getData().trim() + " **");
							
							currentNode = currentNode.getParent();
						} else {
							//System.out.println("** DEpth is less setting root to current node's parent **");

							//System.out.println("Skipping root");
						}
						
					} else if ( depth == lastDepth ){
						
						//System.out.println("** Received exit DEpth is the same so not doing anything but possibly popping child**");

						if (!currentNode.getData().trim().contains("create") && !currentNode.getData().trim().contains("interface ") && !currentNode.getData().trim().contains("rmon") ){
							
							
							//for ( Node childNode : currentNode.getParent().getChildren()){
								//System.out.println("Child " + childNode.getData().trim());
							//}
							Node ret = currentNode.getParent().popChild();
							//System.out.println("Popped child " + ret.getData().trim());
							//ret.setVisited();
						}
						
						this.checkForHacks(currentNode);

						
					}
				} else if ( depth == lastDepth ) {
		
					//System.out.println("***Depth is the same as the last line setting parent to current node's parent ->" + currentNode.getParent().getData().trim() + "<-  ***");
					Node newNode = new Node(currentNode.getParent(), line.trim(), depth);
					currentNode = newNode;
					currentNode.getParent().addChild(newNode);

					
				} else if ( depth > lastDepth){
					
					//System.out.println("***** Depth is greater - setting current node to the new node and using parent value  **" + currentNode.getData());
					
					try {
						Node newNode = new Node(currentNode, line.trim(), depth);
						currentNode.addChild(newNode);
				
						currentNode = newNode;
					} catch ( Exception err){
						System.out.println("# Error on line " + linecount + " " + err.getMessage());
						System.out.println("\n\n#" + line + "\n\n");
						System.exit(1);
					}
					
					
				} else {
					
					//System.out.println("**Depth is less.  Setting current node to the current node's parent with value = " + currentNode.getParent().getData().trim() + " **");
					String tdata = currentNode.getData();
					currentNode = currentNode.getParent();
					
				}
				
				lastline = line;
				lastDepth = depth;
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("We done");
		
	}
	
	public void flattenConfig(String configFile, String outputFileName) throws IOException{
		this.writer = new PrintWriter(outputFileName, "UTF-8");
		this.writeLine("#  Flattening config for " + configFile);
		//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		this.writeLine("# " + Calendar.getInstance().getTime().toString() );
		this.flattenConfig(configFile);
		writer.close();
		
	}
	
	public void checkForHacks(Node node){
		
		String cmdString = node.getData().trim();
		if ( node.getData().trim().equals("rmon") && node.getChildCount() == 0){
			
			Node parent = node.getParent();
			
			parent.popChild();
			
			if ( parent.getChildCount() == 0){
				parent.getParent().popChild();
			}
			
			return;
		}
		
		if ( cmdString.matches("^policy [0-9]+ create$") && node.getChildCount() == 0 && node.getParent().getData().trim().equals("cpu-protection")){
			node.setData(node.getData().trim() + " no description");
			return;
		}
		
		if ( cmdString.matches("^log [0-9]+ create$") && node.getParent().getData().trim().equals("filter") && node.getChildCount() == 0){
			node.setData(node.getData().trim() + " no description");
			return;
			
		}
		
		
		// sap ingress/egress hack
		
		if ( cmdString.matches("^queue [0-9]+ customer [0-9]+ create") || cmdString.matches("queue [0-9]+.*create$") ){
			String parentString = node.getParent().getData().trim();
			if ( parentString.matches("sap\\-(ingress|egress) [0-9]+.*") ) {
				node.setData(node.getData().trim() + " rate max");
				return;
			}
		}
		
		if ( cmdString.matches("^interface\\-parameters")){
			
			if ( node.getChildCount() == 0)
				node.getParent().popChild();
			
			return;
		}
		
		if ( cmdString.matches("^interface .* create$")){
			
			String parentString = node.getParent().getData().trim();
			
			if ( parentString.matches("ies [0-9]+ customer [0-9]+ create")){
				node.setData(node.getData().trim() + " no shutdown");
			}
		}
		
		
		if ( cmdString.contains("split-horizon-group \"") && node.getParent().getData().contains("vpls")){
			node.setData(node.getData().trim() + " no description");
			return;
		}
		
		if ( cmdString.matches("^sap .* create$") ) {
			node.setData(node.getData().trim() + " no description");
			return;
		}
		
		if ( cmdString.matches("^interface \".*\"$")){
			
			String parentString = node.getParent().getData().trim();
			if ( parentString.trim().equals("interface-parameters")){
				node.setData(node.getData().trim() + " no shutdown");

			}
			
		}
	}
	
	public void writeLine(String line){
		
		if ( this.writer != null) {
			writer.println(line);
			writer.flush();
		}
		else
			System.out.println(line);
	}
	
	public void traverseTree(){
		System.out.println("Going to traverse starting at " + rootNode.getData().trim());
		
		boolean keepon = true;
		
		this.activeNode = this.rootNode;
		
		this.configList = new ArrayList<String>();

		
		while ( keepon){

			if ( this.activeNode.getChildCount() == 0) {
				
				if ( this.activeNode.getData().trim().equals("exit all")){
					//System.out.println("exit all");
					String ret = configList.remove(configList.size()-1);
					this.activeNode = this.activeNode.getParent();
					continue;
				}
				
				
				if ( !this.activeNode.getData().trim().equals("configure")) {
					
					String cmd = "";
					
					for ( String newcmd : configList){
						cmd += " " + newcmd.trim();
					}
					
					//System.out.println(cmd);
					this.writeLine(cmd);
				}
				
				String  ret = configList.remove(configList.size()-1);
				//System.out.println("\tPopped config " + ret);
				this.activeNode = this.activeNode.getParent();
				
			} else {
				
				Node nextNode = getUnvisitedNode(this.activeNode);
				
				if ( nextNode == null){
					
					if ( this.activeNode.isRoot()){
						System.out.println("No more unvisited for root");
						System.exit(1);
					}
					
					this.activeNode = this.activeNode.getParent();
					String val = configList.remove(configList.size()-1);
					//System.out.println("\tPopped config element after getting null next node " + val);
				} else {
					configList.add(nextNode.getData().trim());
					this.activeNode = nextNode;
				}
			}
			

		}
	}
	
	
	public void returnToRootNode(){
		this.activeNode = this.rootNode;
		this.configList = new ArrayList<String>();
	}
	
	
	public String getNextCommand(){
		
		
		while ( this.activeNode.getChildCount() > 0 ) {
		
	
			
			Node nextNode = getUnvisitedNode(this.activeNode);
			
			if ( nextNode == null){
				
				if ( this.activeNode.isRoot()){
					//System.out.println("No more unvisited for root");
					//System.exit(1);
					return null;
				}
				
				this.activeNode = this.activeNode.getParent();
				String val = configList.remove(configList.size()-1);
				//System.out.println("\tPopped config element after getting null next node " + val);
			} else {
				configList.add(nextNode.getData().trim());
				this.activeNode = nextNode;
			}
		}
		
			// at this point, the node will have 0 children
			if ( this.activeNode.getData().trim().equals("exit all")){
				//System.out.println("exit all");
				String ret = configList.remove(configList.size()-1);
				this.activeNode = this.activeNode.getParent();
				return "exit all";
			}
			
			
			if ( !this.activeNode.getData().trim().equals("configure")) {
				
				String cmd = "";
				
				for ( String newcmd : configList){
					cmd += " " + newcmd.trim();
				}
				
				String  ret = configList.remove(configList.size()-1);
				this.activeNode = this.activeNode.getParent();
				
				return cmd;
			}
			

	
		
		return "";
	}
	
	public Node getUnvisitedNode(Node node){
		
		Node nextNode = null;
		
		for ( Node tnode : node.getChildren()){
			
			if ( tnode.wasVisited() == false){
				nextNode = tnode;
				tnode.setVisited();
				return nextNode;
			}
		}
		
		return nextNode;
	}
}
