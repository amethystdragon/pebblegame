package ai.neuralnet;

import java.util.ArrayList;

public class NeuronLayer {

	private int numNeurons;
	
	private ArrayList<Neurons> neuronList;
	
	public NeuronLayer(int numNeurons, int numInputsPerNeuron){
		this.numNeurons=numNeurons;
	}
	
	public int getNumNeurons(){
		return numNeurons;
	}
	
	public ArrayList<Neurons> getNeuronList(){
		return neuronList;
	}
}
