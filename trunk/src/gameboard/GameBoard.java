package gameboard;

import java.util.Random;

public class GameBoard {

	private int pebblesLeft;
	
	public enum players{player1, player2}
	
	private players currentPlayer;
	
	private int max = 100;
	
	public GameBoard(){
		newGame();
	}
	
	public GameBoard(int starting){
		max = starting;
		newGame();
	}

	public int getPebblesLeft() {
		return pebblesLeft;
	}
	
	public void takeAway(int pickedUp) throws GameOver{
		pebblesLeft-=pickedUp;
		if(pebblesLeft < 1) throw new GameOver(currentPlayer);
	}

	public void newGame() { 
		this.pebblesLeft = max;
		currentPlayer = players.player1;
	}
	
	public players switchPlayers(){
		currentPlayer = (currentPlayer == players.player1) ? players.player2 : players.player1;
		return currentPlayer;
	}
	
	public players getCurrentPlayer(){
		return currentPlayer;
	}
	
	
}
