package ai.astar;

import java.util.ArrayList;

import ai.neuralnet.Node;

public class AStarNodes implements Comparable {

	
	private AStarNodes parent;
	
	private ArrayList<AStarNodes> childs;
	
	private int pathCost;
	
	private int costToGoal;
	 
	private int totalCost;
	
	private int learningCost;
	
	private int value;
	
	
	public AStarNodes(int value, AStarNodes parent, int pathCost, int costToGoal){
		this.parent = parent;
		this.pathCost = pathCost;
		this.costToGoal = costToGoal;
		this.learningCost = 0;
	}
	
	public AStarNodes getParent(){
		return parent;
	}
	
	public ArrayList<AStarNodes> getChilds(){
		return childs;
	}
	
	public int getPathCost(){
		return pathCost;
	}
	
	public int getCostToGoal(){
		return costToGoal;
	}
	
	public int getTotalCost(){
		return totalCost;
	}
	
	public int getLearningCost(){
		return learningCost;
	}
	
	public void setPathCost(int cost){
		this.pathCost = cost;
	}
	
	public void setCostToGoal(int cost){
		this.costToGoal = cost;
	}
	
	public void updateTotalCost(){
		this.totalCost = costToGoal + pathCost;
	}
	
	public void setParent(AStarNodes newParent){
		this.parent = newParent;
	}
	
	public void setChilds(ArrayList<AStarNodes> newChilds){
		this.childs = newChilds;
	}
	
	public void adjustLearningCost(int cost){
		this.learningCost += cost;
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
