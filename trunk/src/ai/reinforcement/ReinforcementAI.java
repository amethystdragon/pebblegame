package ai.reinforcement;

import gameboard.GameBoard;
import ai.AI;

/**
 * @author amethyr
 *
 */
public class ReinforcementAI extends AI{

	/**
	 * Array of all weights
	 */
	int[] weights;
	/**
	 * currentNode
	 */
	ReinforcementNode currentNode;
	
	/**
	 * Creates a new reinforcement AI
	 */
	public ReinforcementAI(){
		super();
		weights = new int[101];
	}
	
	/**
	 * Chooses the highest rated node's state to give back to the user based on the next avalible moves
	 */
	public int choose(GameBoard board){
		super.choose(board);
		currentNode = new ReinforcementNode(board.getPebblesLeft(), currentNode);
		
		int choice = 1;
		//Checks if removing 2 or 3 pebbles is better
		if(currentNode.getValue()-3 >= 1)
			choice = (weights[currentNode.getValue()-2] >= weights[currentNode.getValue()-3]) ? 2 : 3;
		//Chesks if removing only one pebble is better
		if(currentNode.getValue()-1 >= 1)
			choice = (weights[currentNode.getValue()-choice] >= weights[currentNode.getValue()-1]) ? choice : 1;
		
		return choice;
	}
	
	/**
	 * Adds weights to the nodes listed in the path.
	 * Outputs to console the current list of weights.
	 */
	public void gameOver(boolean win){
		boolean even = false;
		while(currentNode != null){
			if(even){
				if(win) weights[currentNode.getValue()]++;
				else weights[currentNode.getValue()]--;
			}else{
				if(!win) weights[currentNode.getValue()]++;
				else weights[currentNode.getValue()]--;
			}
			even = (even) ? false : true;
			currentNode = currentNode.getParent();
		}
		System.out.println("----------------------------------------------------");
		for(int i = 0; i<weights.length-3; i+=4){
			System.out.println(i+": "+weights[i]+"\t"+(i+1)+": "+weights[i+1]+"\t"+(i+2)+": "+weights[i+2]+"\t"+(i+3)+": "+weights[i+3]);
		}
	}
}
