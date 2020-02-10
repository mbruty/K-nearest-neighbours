package k_nearest_neighbours;

import java.util.Arrays;
import java.util.Random;

public class Knn
{
    private double[][]data;
    private double[][] testData;
    private double[] predict;
    private double testSize;
    private int k;
    private String[] features;

    public Knn(double[][] inData, String[] inFeatures, double testSize)
    {
        if(inData.length == 3)
        {
            System.out.println("K is set to a value less than total voting groups!");
        }
        else
        {
            data = new double[(int) Math.floor(inData.length * (1 - testSize))][10];
            testData = new double[(int) Math.floor(inData.length * testSize)][10];
            shuffleArray(inData);
            for(int i = 0; i < inData.length - 1; i++)
            {
                if(i < data.length)
                    data[i] = inData[i];
                else
                    testData[i - data.length] = inData[i];
            }
            this.k = 3;
            this.testSize = testSize;
            this.features = inFeatures;
            double correct = 0;
            double total = 0;
            for (double[] testDatum : testData) {
                predict = testDatum;
                //System.out.println("Predicted: " +features[predict()] + "   ##   Actual: " + features[(int) testDatum[9]]))
                int predicted = predict();
                if((int)testDatum[9] == predicted)
                    correct++;
                total++;
            }
            System.out.println(correct / total);
        }
    }

    public int predict()
    {
        double[][] distances = new double[0][2];
        //Each group in training set
        for (int i = 0; i < data.length; i++)
        {
            //Each feature in group
            double sum = 0.0;
            for(int j = 0; j< data[i].length; j++)
            {
                sum+= Math.pow(data[i][j] - predict[j], 2);
            }
            double euclideanDistance = Math.sqrt(sum);
            distances = Arrays.copyOf(distances, distances.length + 1);
            distances[distances.length - 1] = new double[]{euclideanDistance, data[i][9]};
        }
        //Find the lowest euclidean distance
        sort(distances);
        double[] votes = new double[k];
        for(int i = 0; i < k; i++)
        {
            //Getting the group
            votes[i] = distances[i][1];
        }
        //Finding the most common input
        double result = mostCommon(votes);
        return (int) result;
    }

    private double[][] sort(double[][] data)
    {
        boolean sorted = false;
        while(!sorted)
        {
             for(int i = 0; i < data.length - 1; i++)
             {
                 //If number on the left is smaller than the one on the right, swap!
                 if(data[i][0] > data[i+1][0])
                 {
                     double[] temp = data[i];
                     data[i] = data[i+1];
                     data[i+1] = temp;
                 }
             }
             //Presume that data is sorted, if there is an element that isn't sorted, repeat
             sorted = true;
             for(int i = 0; i < data.length - 1; i++)
             {
                 if(data[i][0] > data[i+1][0])
                     sorted = false;
             }
        }
        return data;
    }

    private double mostCommon(double[] data)
    {
        double mostCommon = 0;
        int count = 0;
        for(int i = 0; i < data.length; i++)
        {
            double currentElement = data[i];
            int elementCount = 0;
            for(int j = 0; j < data.length; j++)
            {
                if(data[i] == currentElement)
                {
                    elementCount++;
                }
            }
            if(elementCount > count)
            {
                mostCommon = currentElement;
                count = elementCount;
            }
        }
        return mostCommon;
    }

    public static void shuffleArray(double[][] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(double[][] a, int i, int change) {
        double[] helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }
}
