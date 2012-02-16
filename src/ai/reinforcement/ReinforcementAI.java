package ai.reinforcement;

import gameboard.GameBoard;
import ai.AI;

public class ReinforcementAI extends AI{

	int[] memory;
	ReinforcementNode currentNode;
	
	public ReinforcementAI(){
		super();
		memory = new int[101];
	}
	
	public int choose(GameBoard board){
		super.choose(board);
		currentNode = new ReinforcementNode(board.getPebblesLeft(), currentNode);
		
		int choice = 1;
		
		if(currentNode.getValue()-3 >= 4)
			choice = (choice <= memory[currentNode.getValue()-3]) ? choice : 3;
		if(currentNode.getValue()-3 >= 3)
			choice = (choice <= memory[currentNode.getValue()-2]) ? choice : 2;
		if(currentNode.getValue()-1 >= 2)
			choice = (choice <= memory[currentNode.getValue()-1]) ? choice : 1;
		
		return choice;
	}
	
	public void gameOver(boolean win){
		boolean even = false;
		while(currentNode != null){
			if(even){
				if(win) memory[currentNode.getValue()]++;
				else memory[currentNode.getValue()]--;
			}else{
				if(!win) memory[currentNode.getValue()]++;
				else memory[currentNode.getValue()]--;
			}
			even = (even) ? false : true;
			currentNode = currentNode.getParent();
		}
		System.out.println("----------------------------------------------------");
		for(int i = 0; i<memory.length-3; i+=4){
			System.out.println(i+": "+memory[i]+"\t"+(i+1)+": "+memory[i+1]+"\t"+(i+2)+": "+memory[i+2]+"\t"+(i+3)+": "+memory[i+3]);
		}
	}
}
