package network;

import dataset.DataSet;
import dataset.DataSetBuilder;
import persistance.FilePersistance;
import persistance.Persistance;
import tools.ArrayTools;

public class NetworkTrainer {

    private Network network;
    private String dataSet;
    private DataSetBuilder dataSetBuilder;
    private int imageStartIndex;
    private int imageEndIndex;
    private int trainingEpochs;
    private int trainingLoops;
    private int trainingBatchSize;

    public NetworkTrainer addNetwork(Network value){
        network = value;
        return this;
    }

    public NetworkTrainer addDataSet(String value) {
        dataSet = value;
        return this;
    }

    public NetworkTrainer addDataSetBuilder(DataSetBuilder value) {
        dataSetBuilder = value;
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

    public void train() {
        DataSet set = dataSetBuilder.createDataSet(imageStartIndex, imageEndIndex);
        for (int epoch=0 ; epoch<trainingEpochs ; epoch++) {
            network.train(set, trainingLoops, trainingBatchSize);
        }
    }

    public void test() {
        DataSet set = dataSetBuilder.createDataSet(imageStartIndex, imageEndIndex);

        int correctGuesses = 0;
        for (int test=0 ; test<set.size() ; test++) {
            double networkGuess = NetworkTools.indexOfHighestValue(network.calculate(set.getInput(test)));
            double actualValue  = NetworkTools.indexOfHighestValue(set.getOutput(test));

            if (networkGuess == actualValue) {
                correctGuesses++;
            }
            if (test%10 == 0) {
                System.out.println(test + ": " + (double)correctGuesses / (double) (test + 1));
            }
            System.out.println("Testing finished, RESULT: " + correctGuesses + " / " + set.size()+ "  -> " + (double)correctGuesses*100 / (double)set.size() +" %");
        }
    }

    public NetworkTrainer persistNetworkValues() {
        Persistance persistance = new FilePersistance();
        persistance.writeSourceToFile("/output/" + dataSet + "/weights.csv", ArrayTools.toString(network.weights));
        persistance.writeSourceToFile("/output/" + dataSet + "/biases.csv", ArrayTools.toString(network.bias));
        return this;
    }
}
