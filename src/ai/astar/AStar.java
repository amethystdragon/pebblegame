package ai.astar;

import gameboard.GameBoard;
import ai.AI;

public class AStar extends AI {

//	private int[] memory;
	
//	private AStarNodes currentNode;
	
	
	public AStar(){
		
	}
	
	public int choose(GameBoard board){
		super.choose(board);
	//	currentNode = new AStarNodes(board.getPebblesLeft(), currentNode, );
		return 0;
	}

	@Override
	public void gameOver(boolean win) {
		// TODO Auto-generated method stub
		
	}
}
