package ai.reinforcement;


public class ReinforcementNode {

	private int weight;
	
	private ReinforcementNode parent;
	
	private int value;
	
	public ReinforcementNode(int value, ReinforcementNode parent){
		this.value = value;
		this.parent = parent;
		weight = 0;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public ReinforcementNode getParent(){
		return parent;
	}
	
	public void addWeight(int amount){
		weight += amount;
	}
	
	public int getValue(){
		return value;
	}
}
