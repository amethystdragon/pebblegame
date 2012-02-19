package gameboard;

import gameboard.GameBoard.players;

/**
 * Custom exception to look for.
 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
 *
 */
public class GameOver extends Exception {
	
	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -6163959949734880209L;

	/**
	 * New game over with custom string
	 * @param msg - custom game over message
	 */
	public GameOver(String msg) {
		super(msg);
	}

	/**
	 * Defalut game over no information given
	 */
	public GameOver() {
		super();
	}

	/**
	 * New game over specifying which player lost with the 
	 * @param currentPlayer
	 */
	public GameOver(players currentPlayer) {
		super((currentPlayer == players.player1) ? "Player 1 Lost" : "Player 2 Lost");
	}
}
