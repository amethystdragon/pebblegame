package ai.heuristics;

import gameboard.GameBoard;

public abstract class Heuristics {
	

	public Heuristics(){
		
	}
	
	public int run(GameBoard board, String heuristic){
		return 0;
		
	}
	
		
	public abstract int choose(GameBoard board);
}
