//MIT License
//
//Copyright (c) [2020] [Michael Bruty]

package k_nearest_neighbours;

public class Main {

    public static void main(String[] args)
    {
        ReadData r = new ReadData();
        double[][] data = r.GetData();
        String[] features = {"", "", "Benign", "", "Malignant"};
        Knn k = new Knn(data, features, 0.3f, 10000);
    }
}
