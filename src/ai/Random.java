package ai;

import gameboard.GameBoard;

public class Random extends AI{

	private java.util.Random r = new java.util.Random();
	
	@Override
	public int choose(GameBoard board) {
		super.choose(board);
		return r.nextInt(3)+1;
	}
}
