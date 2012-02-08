package gameboard;

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
}
