package ai;

import java.util.ArrayList;

public class NeuralNet {

	private int numInputs;

	private int numOutputs;

	private int numHiddenLayers;

	private int neuronsPerHiddenLayer;

	private ArrayList<NeuronLayer> neuronLayers;

	public final double dActivationResponse = 1.0;

	public final double dbias = -1.0;
	
	public final int iNumHidden = 1;

	public final int iNumOutputs = 2;

	public final int iNeuronsPerHiddenLayer = 3;

	public final int iNumInputs = 3;


	/**
	 * 
	 */
	public NeuralNet(){
		numInputs = iNumInputs;
		numOutputs = iNumOutputs;
		numHiddenLayers = iNumHidden;
		neuronsPerHiddenLayer = iNeuronsPerHiddenLayer;
		createNet();
	}

	/**
	 * 
	 */
	public void createNet(){
		if(numHiddenLayers > 0){
			neuronLayers.add(new NeuronLayer(neuronsPerHiddenLayer, numInputs));

			for(int i = 0; i < numHiddenLayers - 1; i++){
				neuronLayers.add(new NeuronLayer(neuronsPerHiddenLayer, neuronsPerHiddenLayer));
			}
			
			neuronLayers.add(new NeuronLayer(numInputs, neuronsPerHiddenLayer));
		}else{
			neuronLayers.add(new NeuronLayer(numOutputs, numInputs));
		}
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Double> getWeights(){
		ArrayList<Double> weights = null;

		for(int i = 0; i < numHiddenLayers + 1; i++){
			for(int j = 0; j < neuronLayers.get(i).getNumNeurons(); j++){
				for(int k = 0; k < neuronLayers.get(i).getNeuronList().get(j).getNumInputs(); k++){
					weights.add(neuronLayers.get(i).getNeuronList().get(j).getWeights(k));
				}
			}
		}

		return weights;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfWeights(){
		int weights = 0;

		for (int i = 0; i < numHiddenLayers + 1; i++){
			for(int j = 0; j < neuronLayers.get(i).getNumNeurons(); j++){
				for(int k = 0; k < neuronLayers.get(i).getNeuronList().get(j).getNumInputs(); k++){
					weights++;
				}
			}
		}

		return weights;
	}

	/**
	 * 
	 * @param weights
	 */
	public void putWeights(ArrayList<Double> weights){
		int cWeight = 0;

		for(int i = 0; i < numHiddenLayers + 1; i++){
			for(int j = 0; j < neuronLayers.get(i).getNumNeurons(); j++){
				for(int k = 0; k < neuronLayers.get(i).getNeuronList().get(j).getNumInputs(); k++){
					neuronLayers.get(i).getNeuronList().get(j).setWeights(k, weights.get(cWeight++));
				}
			}
		}
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 */
	public ArrayList<Double> update(ArrayList<Double> inputs){
		ArrayList<Double> outputs = null;

		int weight = 0;

		if(inputs.size() != numInputs){
			return outputs;
		}

		for(int i = 0; i < numHiddenLayers + 1; i++){
			if(i > 0){
				outputs = inputs;
			}

			outputs.clear();

			weight = 0;

			for(int j = 0; j<neuronLayers.get(i).getNumNeurons(); j++){
				double netInput = 0;
				int tempNumInputs = neuronLayers.get(i).getNeuronList().get(j).getNumInputs();

				for(int k = 0; k < numInputs - 1; k++){
					netInput += neuronLayers.get(i).getNeuronList().get(j).getWeights(k) * inputs.get(weight++);
				}

				netInput += neuronLayers.get(i).getNeuronList().get(j).getWeights(tempNumInputs-1) * dbias;
				outputs.add(Sigmoid(netInput, dActivationResponse));

				weight = 0;
			}
		}

		return outputs;
	}

	/**
	 * 
	 * @param netInput
	 * @param response
	 * @return
	 */
	public double Sigmoid(double netInput, double response){
		return ( 1.0 / (1.0 + Math.exp(-netInput / response)));
	}
}
