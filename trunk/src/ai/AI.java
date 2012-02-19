package ai;

import gameboard.GameBoard;

import java.util.ArrayList;

/**
 * Creates an abstract that all AI's can extend.
 * Provides basic functionallity that all AIs should have.
 * 
 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
 *
 */
public abstract class AI {

	/**
	 * Path taken by the players 
	 */
	protected ArrayList<Integer> path;
	/**
	 * Current game board state
	 */
	protected GameBoard currentBoard;
	
	
	
	/**
	 * Initializes the path for the extending AI to use
	 */
	public AI(){
		path = new ArrayList<Integer>(100);
	}
	
	/**
	 * Updates the current board
	 * Adds the node to the path.
	 * @param board - current board
	 * @return - 0 - basic functionallity. Each extending class should implement this its own way.
	 */
	public int choose(GameBoard board){
		path.add(board.getPebblesLeft());
		this.currentBoard = board;
		return 0;
	}
	
	/**
	 * What happens in a game over situation
	 * @param win - if the AI won
	 */
	public abstract void gameOver(boolean win);

}
