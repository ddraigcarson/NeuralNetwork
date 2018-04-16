package tools;

import java.util.List;

public class ArrayTools {

    public static String toString(double[][][] arr) {
        String output = "";

        for (int i=0 ; i<arr.length ; i++) {
            if (arr[i] == null) {
                continue;
            }
            for (int j=0 ; j<arr[i].length ; j++) {
                if (arr[i][j] == null) {
                    continue;
                }
                for (int k=0 ; k<arr[i][j].length ; k++) {
                    output += i + "," + j + "," + k + "," + arr[i][j][k] + ",\n";
                }
            }
        }
        return output;
    }

    public static String toString(double[][] arr) {
        String output = "";
        for (int i=0 ; i<arr.length ;i++) {
            if (arr[i] == null) {
                continue;
            }
            for (int j=0 ; j<arr[i].length ;j++) {
                output += i + "," + j + "," + arr[i][j] +",\n";
            }
        }
        return output;
    }

    public static double[][][] to3DArray(List<String> list, int[] arraySizes) {
        double[][][] arr = null;
        arr = new double[arraySizes.length][][];
        for (int layer=1 ; layer<arraySizes.length ; layer++) {
            arr[layer] = new double[arraySizes[layer]][arraySizes[layer-1]];
        }

        for (String str : list) {
            String[] split = str.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int z = Integer.parseInt(split[2]);
            Double value = Double.parseDouble(split[3]);
            arr[x][y][z] = value;
        }
        return arr;
    }

    public static double[][] to2DArray(List<String> list, int[] arraySizes) {
        double[][] arr = null;
        arr = new double[arraySizes.length][];

        for (int layer=0 ; layer<arraySizes.length ; layer++) {
            arr[layer] = new double[arraySizes[layer]];
        }

        for (String str : list) {
            String[] split = str.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            Double value = Double.parseDouble(split[2]);
            arr[x][y] = value;
        }
        return arr;
    }

}
