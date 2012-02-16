package ai;

import gameboard.GameBoard;

import java.util.ArrayList;

public abstract class AI {

	protected ArrayList<Integer> path;
	protected GameBoard currentBoard;
	
	
	
	public AI(){
		path = new ArrayList<Integer>(100);
	}
	
	public int choose(GameBoard board){
		path.add(board.getPebblesLeft());
		this.currentBoard = board;
		return 0;
	}
	
	public abstract void gameOver(boolean win);

}
