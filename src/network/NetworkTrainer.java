package network;

import persistance.FilePersistance;
import persistance.Persistance;
import tools.ArrayTools;
import trainset.MnistTrainSetTools;
import trainset.TrainSet;

public class NetworkTrainer {

    private Network network;
    private String dataSet;
    private int imageStartIndex;
    private int imageEndIndex;
    private int trainingEpochs;
    private int trainingLoops;
    private int trainingBatchSize;

    public void train() {
        TrainSet set = MnistTrainSetTools.createTrainSet(imageStartIndex, imageEndIndex);
        trainData(network, set, trainingEpochs, trainingLoops, trainingBatchSize);
    }

    private void trainData(Network net, TrainSet set, int epochs, int loops, int batch_size) {
        for (int e=0 ; e<epochs ; e++) {
            net.train(set, loops, batch_size);
        }
    }

    public NetworkTrainer persistNetworkValues() {
        Persistance persistance = new FilePersistance();
        persistance.writeSourceToFile("/output/" + dataSet + "/weights.csv", ArrayTools.toString(network.weights));
        persistance.writeSourceToFile("/output/" + dataSet + "/biases.csv", ArrayTools.toString(network.bias));
        return this;
    }

    public NetworkTrainer addNetwork(Network value){
        network = value;
        return this;
    }

    public NetworkTrainer addDataSet(String value) {
        dataSet = value;
        return this;
    }

    public NetworkTrainer addImageStartIndex(int value) {
        imageStartIndex = value;
        return this;
    }

    public NetworkTrainer addImageEndIndex(int value) {
        imageEndIndex = value;
        return this;
    }

    public NetworkTrainer addTrainingEpochs(int value) {
        trainingEpochs = value;
        return this;
    }

    public NetworkTrainer addTrainingLoops(int value) {
        trainingLoops = value;
        return this;
    }

    public NetworkTrainer addTrainingBatchSize(int value) {
        trainingBatchSize = value;
        return this;
    }

}