package gameboard;

import gameboard.GameBoard.players;

public class GameOver extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163959949734880209L;

	public GameOver(String msg) {
		super(msg);
	}

	public GameOver() {
		super();
	}

	public GameOver(players currentPlayer) {
		super((currentPlayer == players.player1) ? "Player 1 Lost" : "Player 2 Lost");
	}
}
