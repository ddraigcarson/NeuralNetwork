package fashion;

public class FashionConstants {
    public static String DATA_SET = "fashion2";

    public static int TRAINING_IMAGES_START = 0;
    public static int TRAINING_IMAGES_END = 29999;

    public static int TEST_IMAGES_START = 0;
    public static int TEST_IMAGES_END = 4999;

    public static int TRAINING_BATCH_SIZE = 100;
    public static int TRAINING_EPOCHS = 100;
    public static int TRAINING_LOOPS = 50;

    /*
     * IDX is an index persistance extension commonly used in Windows to speed up the search
     * in a db
     * */
    public static String TRAIN_IMAGE_PATH = "/res/fashion/train-images.idx3-ubyte";
    public static String TRAIN_LABEL_PATH = "/res/fashion/train-labels.idx1-ubyte";
    public static String TEST_IMAGE_PATH = "/res/fashion/t10k-images.idx3-ubyte";
    public static String TEST_LABEL_PATH = "/res/fashion/t10k-labels.idx1-ubyte";

    public static String IMAGE_FILE_ACCESS_MODE = "rw";

}
