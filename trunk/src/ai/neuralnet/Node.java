package ai.neuralnet;

public class Node {

	private int weight;
	
	private Node parent;
	
	public Node(Node parent){
		weight = 0;
		this.parent = parent;
	}

	public int getWeight(){
		return weight;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public void addWeight(int amount){
		weight += amount;
	}
	
}
