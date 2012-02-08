package gameboard;

import java.util.Random;

public class GameBoard {

	private int pebblesLeft;
	
	public GameBoard(){
		Random r = new Random();
		int starting = r.nextInt(50) + 50;
		this.pebblesLeft = starting;
	}
	
	public GameBoard(int starting){
		if(starting > 50) this.pebblesLeft = starting;
	}

	public int getPebblesLeft() {
		return pebblesLeft;
	}
	
	public void takeAway(int pickedUp) throws GameOver{
		pebblesLeft-=pickedUp;
		if(pebblesLeft < 1) throw new GameOver();
	}

	public void newGame() { 
			
	}
}
