import dataset.FashionDataSetBuilder;
import dataset.MnistDataSetBuilder;
import fashion.FashionConstants;
import mnist.MnistConstants;
import network.Network;
import network.NetworkBuilder;
import network.NetworkTrainer;

import static network.NetworkConstants.*;

public class Main {

    public static void main(String[] args) {
        //mnsit();
        fashion();
    }

    private static void mnsit() {
        NetworkBuilder builder = new NetworkBuilder();
        Network network = builder.newNetwork()
                .addInputLayer(INPUT_NEURONS)
                .addHiddenLayer(HIDDEN_LAYER_1_NEURONS)
                .addHiddenLayer(HIDDEN_LAYER_2_NEURONS)
                .addOutputLayer(OUTPUT_NEURONS)
                .withDataSet(MnistConstants.DATA_SET)
                .build();

        if (network.requiresTraining) {
            System.out.println("Training start");

            NetworkTrainer trainer = new NetworkTrainer();
            trainer.addNetwork(network)
                    .addDataSetBuilder(new MnistDataSetBuilder())
                    .addImageStartIndex(MnistConstants.TRAINING_IMAGES_START)
                    .addImageEndIndex(MnistConstants.TRAINING_IMAGES_END)
                    .addTrainingEpochs(TRAINING_EPOCHS)
                    .addTrainingLoops(TRAINING_LOOPS)
                    .addTrainingBatchSize(TRAINING_BATCH_SIZE)
                    .train();

            System.out.println("Training complete");
            trainer.addDataSet(MnistConstants.DATA_SET).persistNetworkValues();
        }

        NetworkTrainer tester = new NetworkTrainer();
        tester.addNetwork(network)
                .addDataSetBuilder(new MnistDataSetBuilder())
                .addImageStartIndex(MnistConstants.TEST_IMAGES_START)
                .addImageEndIndex(MnistConstants.TEST_IMAGES_END)
                .test();
    }

    private static void fashion() {

        NetworkBuilder builder = new NetworkBuilder();
        Network network = builder.newNetwork()
                .addInputLayer(INPUT_NEURONS)
                .addHiddenLayer(HIDDEN_LAYER_1_NEURONS)
                .addHiddenLayer(HIDDEN_LAYER_2_NEURONS)
                .addOutputLayer(OUTPUT_NEURONS)
                .withDataSet(FashionConstants.DATA_SET)
                .build();

        if (network.requiresTraining) {
            System.out.println("Training start");

            NetworkTrainer trainer = new NetworkTrainer();
            trainer.addNetwork(network)
                    .addDataSetBuilder(new FashionDataSetBuilder(FashionConstants.TRAIN_IMAGE_PATH, FashionConstants.TRAIN_LABEL_PATH))
                    .addImageStartIndex(FashionConstants.TRAINING_IMAGES_START)
                    .addImageEndIndex(FashionConstants.TRAINING_IMAGES_END)
                    .addTrainingEpochs(FashionConstants.TRAINING_EPOCHS)
                    .addTrainingLoops(FashionConstants.TRAINING_LOOPS)
                    .addTrainingBatchSize(FashionConstants.TRAINING_BATCH_SIZE)
                    .train();

            System.out.println("Training complete");
            trainer.addDataSet(FashionConstants.DATA_SET).persistNetworkValues();
        }

        NetworkTrainer tester = new NetworkTrainer();
        tester.addNetwork(network)
                .addDataSetBuilder(new FashionDataSetBuilder(FashionConstants.TEST_IMAGE_PATH, FashionConstants.TEST_LABEL_PATH))
                .addImageStartIndex(FashionConstants.TEST_IMAGES_START)
                .addImageEndIndex(FashionConstants.TEST_IMAGES_END)
                .test();
    }

}
