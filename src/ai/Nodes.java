package ai;

public class Nodes {

	private int weight;
	
	private Nodes parent;
	
	public Nodes(Nodes parent){
		weight = 0;
		this.parent = parent;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public Nodes getParent(){
		return parent;
	}
	
	public void addWeight(int amount){
		weight += amount;
	}
	
}
