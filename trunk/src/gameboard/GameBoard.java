package gameboard;

import java.util.Random;

public class GameBoard {

	private int pebblesLeft;
	
	public enum players{player1, player2}
	
	private players currentPlayer;
	
	public GameBoard(){
		newGame();
	}
	
	public GameBoard(int starting){
		if(starting > 50) this.pebblesLeft = starting;
	}

	public int getPebblesLeft() {
		return pebblesLeft;
	}
	
	public void takeAway(int pickedUp) throws GameOver{
		pebblesLeft-=pickedUp;
		if(pebblesLeft < 1) throw new GameOver(currentPlayer);
	}

	public void newGame() { 
		Random r = new Random();
		int starting = r.nextInt(50) + 50;
		this.pebblesLeft = starting;
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
