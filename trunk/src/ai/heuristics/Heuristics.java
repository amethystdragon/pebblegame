package ai.heuristics;

import gameboard.Gameboard;

public abstract class Heuristics {
	

	public Heuristics(){
		
	}
	
	public int run(Gameboard board, String heuristic){
		return 0;
		
	}
	
		
	public abstract int choose(Gameboard board);
}
