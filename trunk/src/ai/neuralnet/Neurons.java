package ai.neuralnet;

import java.util.*;


public class Neurons {

	
	private int numInputs;
	
	private ArrayList<Double> weights;
	
	private Random rand;
	
	public Neurons(int numberInputs){
		this.numInputs=numberInputs + 1;
		weights = new ArrayList<Double>(numberInputs);
		rand = new Random();
		
		for(int i = 0; i < numInputs+1; i++){
			weights.add(i, rand.nextDouble());
		}
	}

	public int getNumInputs(){
		return numInputs;
	}
	
	public double getWeights(int index){
		return weights.get(index);
	}
	
	public void setWeights(int index, double weight){
		weights.set(index, weight);
	}
}
