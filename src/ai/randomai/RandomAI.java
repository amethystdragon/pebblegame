package ai.randomai;

import gameboard.GameBoard;

import java.util.Random;

import ai.AI;

/**
 * Implements an Ai that chooses a random number of pebbles to pick each turn.
 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
 *
 */
public class RandomAI extends AI{

    /**
     * Random number generator
     */
    private Random r = new Random();

    
    /**
     * Creates a new Random AI
     */
    public RandomAI(){
    	super();
    }
    
    
	/**
	 * Chooses a random number of pebbles from 1-3 to pick up
	 */
	@Override
	public int choose(GameBoard board) {
        super.choose(board);
        return r.nextInt(3)+1;
	}


	/* (non-Javadoc)
	 * @see ai.AI#gameOver(boolean)
	 */
	@Override
	public void gameOver(boolean win) {
		//Does nothing
	}
}
