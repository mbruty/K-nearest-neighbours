package k_nearest_neighbours;

public class Main {

    public static void main(String[] args)
    {
        ReadData r = new ReadData();
        double[][] data = r.GetData();
        String[] features = {"", "", "Benign", "", "Malignant"};
        Knn k;
        int iterations = 100;
        for(int i = 0; i < iterations; i++)
            k = new Knn(data, features, 0.3f);
    }
}
