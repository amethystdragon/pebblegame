package ai.reinforcement;


/**
 * Node used for each part of the reinforcement AI's path
 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
 *
 */
public class ReinforcementNode {

	/**
	 * Nodes weight
	 */
	private int weight;
	
	/**
	 * Parent node. Previous node in the path taken
	 */
	private ReinforcementNode parent;
	
	/**
	 * Nodes value
	 */
	private int value;
	
	/**
	 * Creates a new node
	 * @param value - nodes value
	 * @param parent - nodes parent in the path taken
	 */
	public ReinforcementNode(int value, ReinforcementNode parent){
		this.value = value;
		this.parent = parent;
		weight = 0;
	}
	
	/**
	 * Gets the nodes weight
	 * @return - weight
	 */
	public int getWeight(){
		return weight;
	}
	
	/**
	 * Gets the nodes parent
	 * @return - parent
	 */
	public ReinforcementNode getParent(){
		return parent;
	}
	
	/**
	 * Adds weight to the node
	 * @param amount - amount of weight to add (usually 1 or -1)
	 */
	public void addWeight(int amount){
		weight += amount;
	}
	
	/**
	 * Gets the nodes value
	 * @return - value
	 */
	public int getValue(){
		return value;
	}
}
