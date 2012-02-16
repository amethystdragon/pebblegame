package ai.minimax;


public class MinimaxNode {

	private int weight;
	
	private MinimaxNode[] parents;
	
	private MinimaxNode[] children;

	private int value;
	
	public MinimaxNode(int value, MinimaxNode[] parents){
		this.value = value;
		this.parents = parents;
		weight = 0;
	}
	
	public void setChildren(MinimaxNode[] children){
		this.children = children;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public MinimaxNode[] getParent(){
		return parents;
	}
	
	public MinimaxNode[] getChildren(){
		return children;
	}
	
	public void addWeight(int amount){
		weight += amount;
	}
	
	public int getValue(){
		return value;
	}
}
