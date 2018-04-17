package mnist;

public class MnistConstants {
    public static String DATA_SET = "mnist";

    public static int TRAINING_IMAGES_START = 0;
    public static int TRAINING_IMAGES_END = 4999;

    public static int TEST_IMAGES_START = 5000;
    public static int TEST_IMAGES_END = 9999;

    /*
     * IDX is an index persistance extension commonly used in Windows to speed up the search
     * in a db
     * */
    public static String IMAGE_FILE_PATH = "/res/mnist/trainImage.idx3-ubyte";
    public static String IMAGE_LABEL_PATH = "/res/mnist/trainLabel.idx1-ubyte";

    public static String IMAGE_FILE_ACCESS_MODE = "rw";
}
