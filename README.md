# K Nearest Neighbours Algorithm
## How does it work / what is it?
The KNN Algorithm is a clustering algorithm used for classification. The algorithm calculates the euclidean distance to the other data-points within the training data. The results are then sorted and k closest points are chosen. The classifications of each k closest points are counted to see how many 'votes' each classification has. The classification that has the highest number of 'votes' is chosen. 

This algorithm is inherently slow as it calculates the distance to every single point within the training data. There are faster means to cluster data - currently working on them too, so check my github and it might be there!
Whilst being inherently slow, with the data-set linked to later, it is fine. Splitting the data in to 70% training 30% test resulted in the whole iteration taking 0.96 seconds.
### Results:
K : 12 | Iterations : 10,000 | 70 - 30 train-test split 
Average time for one iteration to execute: 0.19 s
Total time for 10000 iterations: 1857.39 s
Average accuracy: 97.3%
Average confidence: 100.0%
## The data
This algorithm was tested on the [**Wisconsin Breast Cancer Data Set**](https://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29)
The data-set has been proven to work well with clustering algorithms. This algorithm had a 97% average accuracy over 5,000 iterations.

## Licence

This software is licensed under the MIT License

Copyright (c) [2020] [Michael Bruty]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

### Basically - use it how ever you want, just don't claim it as your own work.
