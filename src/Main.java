import network.Network;
import network.NetworkBuilder;
import network.NetworkTools;
import network.NetworkTrainer;
import dataset.DataSet;
import dataset.MnistDataSetBuilder;

import static network.NetworkConstants.*;

public class Main {

    private static final String DATA_SET = "mnist";

    public static void main(String[] args) {
        NetworkBuilder builder = new NetworkBuilder();
        Network network = builder.newNetwork()
                .addInputLayer(INPUT_NEURONS)
                .addHiddenLayer(HIDDEN_LAYER_1_NEURONS)
                .addHiddenLayer(HIDDEN_LAYER_2_NEURONS)
                .addOutputLayer(OUTPUT_NEURONS)
                .withDataSet(DATA_SET)
                .build();

        if (network.requiresTraining) {
            System.out.println("Training start");

            NetworkTrainer trainer = new NetworkTrainer();
            trainer.addNetwork(network)
                    .addDataSetBuilder(new MnistDataSetBuilder())
                    .addImageStartIndex(TRAINING_IMAGES_START)
                    .addImageEndIndex(TRAINING_IMAGES_END)
                    .addTrainingEpochs(TRAINING_EPOCHS)
                    .addTrainingLoops(TRAINING_LOOPS)
                    .addTrainingBatchSize(TRAINING_BATCH_SIZE)
                    .train();

            System.out.println("Training complete");
            trainer.addDataSet(DATA_SET).persistNetworkValues();
        }

        DataSet testSet = new MnistDataSetBuilder().createDataSet(TEST_IMAGES_START, TEST_IMAGES_END);
        testTrainSet(network, testSet, 10);
    }



    public static void testTrainSet(Network net, DataSet set, int printSteps) {
        int correct = 0;

        for (int i=0 ; i<set.size() ; i++) {
            double highest = NetworkTools.indexOfHighestValue(net.calculate((set.getInput(i))));
            double actualHighest = NetworkTools.indexOfHighestValue(set.getOutput(i));

            if(highest == actualHighest) {
                correct++;
            }
            if(i%printSteps == 0) {
                System.out.println(i + ": " + (double)correct / (double) (i + 1));
            }
            System.out.println("Testing finished, RESULT: " + correct + " / " + set.size()+ "  -> " + (double)correct*100 / (double)set.size() +" %");
        }
    }

}
