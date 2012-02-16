package ai.q;

import java.util.Random;

import gameboard.GameBoard;
import ai.AI;

public class Q extends AI {

	private double gamma = 0.8;
	
	private double[][] matrix;
	
	private final int numStates = 101;
	
	private final int numActions = 5;
	
	private final int MAX = 3;
	
	private Random rand;
	
	
	public Q(){
		matrix = new double[numStates][numActions];
		rand = new Random();
	}
	
	public int choose(GameBoard board){

		
		int state = board.getPebblesLeft();
		QItUp(state, 3);
		QItUp(state, 2);
		QItUp(state, 1);
		
		int tempChoice = (int)matrix[state][4];
		return tempChoice;
		
		
	}
	
	@Override
	public void gameOver(boolean win) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void QItUp(int state, int action){
		int nextState = state - action;
		if(state<=2){
			return;
		}
		
		double returns = matrix[state][action-1];
		
		
		
		double max1 = 0;
		double max2 = 0;
		if(nextState==2){
			max1 = Math.max(matrix[nextState][0], matrix[nextState][1]);
			max2 = max1;
		}else if(nextState==1){
			max2 = matrix[nextState][0];
		}else if(nextState==0){
		}else{
			max1 = Math.max(matrix[nextState][2], matrix[nextState][1]);
			max2 = Math.max(matrix[nextState][0], max1);
		}
		
		
		
		returns += gamma * max2;
		matrix[state][action-1] = returns;
		
		max1 = 0;
		max2 = 0;
		
		max1 = Math.max(matrix[state][2], matrix[state][1]);
		max2 = Math.max(matrix[state][0], max1);
		
		if(matrix[state][2]==max2 && matrix[state][1]<max2 && matrix[state][0]<max2){
			matrix[state][4] = 3.0;
		}else if(matrix[state][1]==max2 && matrix[state][2]<max2 && matrix[state][0]<max2){
			matrix[state][4] = 2.0;
		}else if(matrix[state][0]==max2 && matrix[state][2]<max2 && matrix[state][1]<max2){
			matrix[state][4] = 1.0;
		}else if(matrix[state][1]==max2 && matrix[state][2]==max2 && matrix[state][0]<max2){
			int ch = rand.nextInt(2);
			if(ch==1){
				matrix[state][4] = 2.0;
			}else{
				matrix[state][4] = 3.0;
			}
		}else if(matrix[state][0]==max2 && matrix[state][2]==max2 && matrix[state][1]<max2){
			int ch = rand.nextInt(2);
			if(ch==1){
				matrix[state][4] = 1.0;
			}else{
				matrix[state][4] = 3.0;
			}
		}else if(matrix[state][1]==max2 && matrix[state][0]==max2 && matrix[state][2]<max2){
			int ch = rand.nextInt(2);
			if(ch==1){
				matrix[state][4] = 2.0;
			}else{
				matrix[state][4] = 1.0;
			}
		}else if(matrix[state][1]==max2 && matrix[state][2]==max2 && matrix[state][0]==max2){
			int ch = rand.nextInt(3);
			if(ch==1){
				matrix[state][4] = 2.0;
			}else if(ch==2){
				matrix[state][4] = 3.0;
			}else{
				matrix[state][4] = 1.0;
			}
		}
		
		
		matrix[state][MAX] = max2;
	}

}
