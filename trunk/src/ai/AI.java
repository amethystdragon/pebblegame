package ai;

import gameboard.GameBoard;

public abstract class AI {

	protected GameBoard game;
	
	
	public int choose(GameBoard board){
		this.game = board;
		return 0;
	}
}
