package main;

import gameboard.GameBoard;
import gameboard.GameWindow;

/**
 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
 *
 */
public class Driver {

	/**
	 * Main method to create and run the program
	 * @param args
	 */
	public static void main(String[] args){
		GameBoard board = new GameBoard();
		new GameWindow(board);
	}
}