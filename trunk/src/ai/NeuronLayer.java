package ai;

import java.util.ArrayList;

public class NeuronLayer {

	private int numNeurons;
	
	private ArrayList<Neurons> neuronList;
	
	public NeuronLayer(int numNeurons, int numInputsPerNeuron){
		this.numNeurons=numNeurons;
		
		for(int i = 0; i < numNeurons; i++){
			neuronList.add(new Neurons(numInputsPerNeuron));
		}
	}
	
	public int getNumNeurons(){
		return numNeurons;
	}
	
	public ArrayList<Neurons> getNeuronList(){
		return neuronList;
	}
}
