package main;

import gameboard.GameBoard;
import gameboard.GameWindow;

public class Driver {

	public static void main(String[] args){
		GameBoard board = new GameBoard();
		new GameWindow(board);
	}
}
