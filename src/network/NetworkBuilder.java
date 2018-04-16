package network;

import persistance.FilePersistance;
import persistance.Persistance;
import tools.ArrayTools;

import java.util.ArrayList;
import java.util.List;

import static network.NetworkConstants.*;

public class NetworkBuilder {

    private List<Integer> layers;
    private double[][][] weights;
    private double[][] bias;
    private boolean requiresTraining;

    private int NO_OF_LAYERS;

    public Network build() {
        Network network = new Network(getLayers());
        network.weights = weights;
        network.bias = bias;
        network.requiresTraining = requiresTraining;
        return network;
    }

    public int[] getLayers(){
        int[] arr = new int[layers.size()];
        for (int i=0 ; i<layers.size() ; i++) {
            arr[i] = layers.get(i);
        }
        return arr;
    }

    public NetworkBuilder newNetwork() {
        layers = new ArrayList<Integer>();
        return this;
    }

    public NetworkBuilder addInputLayer(int neurons) {
        layers.add(neurons);
        return this;
    }

    public NetworkBuilder addHiddenLayer(int neurons) {
        layers.add(neurons);
        return this;
    }

    public NetworkBuilder addOutputLayer(int neurons) {
        layers.add(neurons);
        NO_OF_LAYERS = layers.size();
        return this;
    }

    public NetworkBuilder withDataSet(String dataSet) {
        Persistance persistance = new FilePersistance();
        boolean existingWeights = persistance.checkForExistingSource("/output/" + dataSet + WEIGHTS_FILE_NAME);
        boolean existingBiases = persistance.checkForExistingSource("/output/" + dataSet + BIASES_FILE_NAME);

        if(existingBiases && existingWeights) {
            System.out.println("Loading existing weights and biases");
            double[][][] weights = ArrayTools.to3DArray(persistance.readSourceFromFile("/output/" + dataSet + WEIGHTS_FILE_NAME), getLayers());
            double[][] biases = ArrayTools.to2DArray(persistance.readSourceFromFile("/output/" + dataSet + BIASES_FILE_NAME), getLayers());

            withWeights(weights);
            withBiases(biases);
            requiresTraining = false;
        } else {
            System.out.println("Creating a new network");
            withRandomBiases();
            withRandomWeights();
            requiresTraining = true;
        }
        return this;
    }

    public NetworkBuilder withRandomWeights() {
        weights = new double[NO_OF_LAYERS][][];
        for (int i = 1; i< NO_OF_LAYERS; i++) {
            weights[i] = NetworkTools.createRandomArray(
                    layers.get(i),
                    layers.get(i-1),
                    WEIGHTS_LOWER_BOUND,
                    WEIGHTS_UPPER_BOUND
            );
        }
        requiresTraining = true;
        return this;
    }

    public NetworkBuilder withRandomBiases() {
        bias = new double[NO_OF_LAYERS][];
        for (int i = 0; i< NO_OF_LAYERS; i++) {
            bias[i] = NetworkTools.createRandomArray(
                    layers.get(i),
                    BIAS_LOWER_BOUND,
                    BIAS_UPPER_BOUND
            );
        }
        requiresTraining = true;
        return this;
    }

    public NetworkBuilder withWeights(double[][][] weights) {
        this.weights = weights;
        return this;
    }

    public NetworkBuilder withBiases(double[][] bias) {
        this.bias = bias;
        return this;
    }

}
