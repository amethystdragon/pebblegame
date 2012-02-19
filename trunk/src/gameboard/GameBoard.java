package gameboard;


/**
 * Provides an implementation of the stack of pebbles for the AI and the GUI to use
 * 
 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
 *
 */
public class GameBoard {

	/**
	 * Number of pebbles left on the board
	 */
	private int pebblesLeft;
	
	/**
	 * Used to specify which plauers turn it is currentlly.
	 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
	 *
	 */
	public enum players{player1, player2}
	
	/**
	 * Whose turn is it? Ask this variable.
	 */
	private players currentPlayer;
	
	/**
	 * Max number of starting pebbles on the board. Default: 100
	 */
	private int max = 100;
	
	/**
	 * Constructor creates a new game
	 */
	public GameBoard(){
		newGame();
	}
	
	/**
	 * Constructor creates a new game with a non standard starting number of pebbles
	 * @param starting - the number of pebbles to start with.
	 */
	public GameBoard(int starting){
		max = starting;
		newGame();
	}

	/**
	 * Gets the number of pebbles left on the 
	 * @return - pebblesLeft on the field
	 */
	public int getPebblesLeft() {
		return pebblesLeft;
	}
	
	/**
	 * Takes away a number of pebbles
	 * @param pickedUp - number of pebbles that a player picks up. Should be 1, 2, or 3 only
	 * @throws GameOver - if the number of pebbles drops below 1
	 */
	public void takeAway(int pickedUp) throws GameOver{
		pebblesLeft-=pickedUp;
		if(pebblesLeft < 1) throw new GameOver(currentPlayer);
	}

	/**
	 * Creates/Resets the game board for a new game
	 */
	public void newGame() { 
		this.pebblesLeft = max;
		currentPlayer = players.player1;
	}
	
	/**
	 * Toggles a switch of the current players
	 * @return - current player
	 */
	public players switchPlayers(){
		currentPlayer = (currentPlayer == players.player1) ? players.player2 : players.player1;
		return currentPlayer;
	}
	
	/**
	 * returns the current player
	 * @return - the currentPlayer
	 */
	public players getCurrentPlayer(){
		return currentPlayer;
	}
	
	
}
