package ai.randomai;

import gameboard.GameBoard;

import java.util.Random;

import ai.AI;

public class RandomAI extends AI{

    private Random r = new Random();

    
    public RandomAI(){
    	super();
    }
    
    
	@Override
	public int choose(GameBoard board) {
        super.choose(board);
        return r.nextInt(3)+1;
	}


	@Override
	public void gameOver(boolean win) {
	}
}
