package router.alcatel.flattener;

import java.util.ArrayList;
import java.util.Stack;

import sun.misc.Queue;

public class Node {

	private Node parent = null;
	private String data = "";
	private ArrayList<Node> childList = new ArrayList<Node>();
	private boolean visited = false;
	private int depth = -1;
	private Stack childStack = new Stack<Node>();
	private Queue childQueue = new Queue<Node>();
	
	private boolean isRoot = false;
	
	public Node(Node parent, String data, int depth){
		this.parent = parent;
		this.data = data;
		this.depth = depth;
		
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public void setVisited(){
		this.visited = true;
	}
	
	public boolean wasVisited(){
		return this.visited;
	}
	

	
	public ArrayList<Node> getChildren(){
		return this.childList;
	}
	
	public String getData(){
		return this.data;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public int getChildCount(){
		return this.childList.size();
	}
	
	
	public void addChild(Node node){
		this.childList.add(node);
	}
	
	
	public Node popChild(){
		
		return this.childList.remove(childList.size()-1);
		
	}
	
	public void setRoot(){
		this.isRoot = true;
	}
	
	public boolean isRoot(){
		return this.isRoot;
	}
	
}
