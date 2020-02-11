//This software is licensed under the MIT License
//
//Copyright (c) [2020] [Michael Bruty]
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

package k_nearest_neighbours;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//MIT License
//
//Copyright (c) [2020] [Michael Bruty]
public class Knn
{
    private double[][]data;
    private double[][] testData;
    private double[] predict;
    private double testSize;
    private int k;
    private String[] features;

    public Knn(double[][] inData, String[] inFeatures, double testSize, int iterations)
    {
        if(inData.length == 3)
        {
            System.out.println("K is set to a value less than total voting groups!");
        }
        else
        {
            ArrayList<Long> timeToExecute = new ArrayList<Long>();
            ArrayList<Double> accuracies = new ArrayList<Double>();
            ArrayList<Double> confidences = new ArrayList<Double>();
            //Time how long each iteration takes
            long startTime;
            //Loop how ever many iterations required
            for(int iters = 0; iters <= iterations; iters++)
            {
                System.out.println("Starting iteration " + iters + " / " + iterations);
                startTime = System.currentTimeMillis();
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
                this.k = 12;
                this.testSize = testSize;
                this.features = inFeatures;
                double correct = 0;
                double total = 0;
                for (double[] testDatum : testData) {
                    predict = testDatum;
                    //System.out.println("Predicted: " +features[predict()] + "   ##   Actual: " + features[(int) testDatum[9]]))
                    int[] predicted = predict();
                    confidences.add((double) (predicted[1] / k));
                    if((int)testDatum[9] == predicted[0])
                        correct++;
                    total++;
                }
                long endTime = System.currentTimeMillis();
                timeToExecute.add(endTime - startTime);
                accuracies.add(correct / total);
            }
            //Calculate average time each iteration took to run
            long timeSum = 0;
            for (long time: timeToExecute)
                timeSum += time;
            double confidenceSum = 0;
            for (double confidence : confidences)
                confidenceSum += confidence;
            confidenceSum /= confidences.size();
            double accuraciesSum = 0;
            for (double accuracy : accuracies)
                accuraciesSum += accuracy;
            accuraciesSum /= accuracies.size();
            System.out.printf("%n%n###############-- COMPLETED --###############%n%n");
            System.out.printf("Average time for one iteration to execute: %3.2f s%n", (double)(timeSum / timeToExecute.size()) / 1000 );
            System.out.printf("Total time for %s iterations: %3.2f%n", iterations, (double) timeSum / 1000);
            System.out.printf("Average accuracy: %3.1f%s%n", accuraciesSum * 100, '%');
            System.out.printf("Average confidence: %3.1f%s%n", confidenceSum * 100, "%");
        }
    }

    public int[] predict()
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
        int[] result = mostCommon(votes);
        return result;
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

    private int[] mostCommon(double[] data)
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
        return new int[] {(int)mostCommon, count};
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
