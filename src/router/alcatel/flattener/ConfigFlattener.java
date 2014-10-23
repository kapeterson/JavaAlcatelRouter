package router.alcatel.flattener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;


public class ConfigFlattener {

	protected String fileName = "";
	public ConfigFlattener(){
		
	}
	
	public void flattenConfig(String filename) throws IOException{
		this.fileName = filename;
		
		int linecount = 0;
		int lastDepth = -1;
		
		Node rootNode = new Node(null, "root", -1);
		rootNode.setRoot();
		Node currentNode = rootNode;
		
		String lastline = "";
		String thisline = "";
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			boolean keepgoing = true;
			while ( (( line = br.readLine()) != null) && keepgoing){
				
				thisline = line.trim();
				
				if ( line.trim().equals("console")){
					System.out.println("we got us a console");
				}
				linecount++;
				
				if ( line.trim().length() == 0 || line.trim().charAt(0) == '#'  ){
					continue;
				}
					
				if ( line.trim().matches("^echo.*\".*\"")){
					if ( line.contains("QoS Policy")){
						//System.exit(1);
						keepgoing = false;
					}
					continue;
				}
				
				int depth = line.length() - line.replaceAll("^\\s+",  "").length();
				
				System.out.println("\n" + depth + "  " + line.trim());

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

						//if (!currentNode.getData().trim().contains("create") && !currentNode.getData().trim().contains("interface") && !currentNode.getData().trim().contains("rmon") ){
							
							
							//for ( Node childNode : currentNode.getParent().getChildren()){
								//System.out.println("Child " + childNode.getData().trim());
							//}
							//Node ret = currentNode.getParent().popChild();
							//System.out.println("Popped child " + ret.getData().trim());
							//ret.setVisited();
						//}
					}
					
				} else if ( depth == lastDepth ) {
		
					//System.out.println("***Depth is the same as the last line setting parent to current node's parent ->" + currentNode.getParent().getData().trim() + "<-  ***");
					Node newNode = new Node(currentNode.getParent(), line.trim(), depth);
					currentNode = newNode;
					currentNode.getParent().addChild(newNode);
					
					if ( newNode.getData().trim().equals("console")){
						System.out.println("Added console");
					}
					
				} else if ( depth > lastDepth){
					
					//System.out.println("***** Depth is greater - setting current node to the new node and using parent value  **" + currentNode.getData());
					
					try {
						Node newNode = new Node(currentNode, line.trim(), depth);
						currentNode.addChild(newNode);
				
						currentNode = newNode;
					} catch ( Exception err){
						System.out.println("Error on line " + linecount + " " + err.getMessage());
						System.out.println("\n\n" + line + "\n\n");
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
		
		traverseTree(rootNode);
	}
	
	public void traverseTree(Node rootNode){
		System.out.println("Going to traverse starting at " + rootNode.getData().trim());
		
		boolean keepon = true;
		
		Node currentNode = rootNode;
		
		ArrayList<String> configList = new ArrayList<String>();
		//configList.add(currentNode.getData().trim());
		while ( keepon){
			//System.out.print("Looking at node " + currentNode.getData().trim() + "\n");
			
			//System.out.println(currentNode.getData().trim());
		
			if ( currentNode.getChildCount() == 0) {
				
				if ( currentNode.getData().trim().equals("exit all")){
					//System.out.println("exit all");
					String ret = configList.remove(configList.size()-1);
					currentNode = currentNode.getParent();
					continue;
				}
				
				
				if ( !currentNode.getData().trim().equals("configure")) {
					
					String cmd = "";
					
					for ( String newcmd : configList){
						cmd += " " + newcmd.trim();
					}
					
					System.out.println(cmd);
				}
				
				String  ret = configList.remove(configList.size()-1);
				//System.out.println("\tPopped config " + ret);
				currentNode = currentNode.getParent();
				
			} else {
				
				Node nextNode = getUnvisitedNode(currentNode);
				
				if ( nextNode == null){
					
					if ( currentNode.isRoot()){
						System.out.println("No more unvisited for root");
						System.exit(1);
					}
					
					currentNode = currentNode.getParent();
					String val = configList.remove(configList.size()-1);
					//System.out.println("\tPopped config element after getting null next node " + val);
				} else {
					configList.add(nextNode.getData().trim());
					currentNode = nextNode;
				}
			}
			

		}
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
