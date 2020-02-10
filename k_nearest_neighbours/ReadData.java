package k_nearest_neighbours;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class ReadData
{
    private String path;
    public ReadData()
    {
        path = "C:/Users/Mike/Downloads/breast-cancer-wisconsin.data";
    }

    public ReadData(String path)
    {
        this.path = path;
    }

    public double[][] GetData()
    {
        double[][] data = new double [0][0];
        try {
            int rowCount;
            File myObj = new File(path);
            Scanner reader = new Scanner(myObj);
            for (rowCount = 0; reader.hasNextLine(); rowCount++) reader.nextLine();
            reader.close();
            Scanner myReader = new Scanner(myObj);
            data = new double[rowCount][10];
            for (int i = 0; myReader.hasNextLine(); i++) {
                String row = myReader.nextLine();
                if(!row.contains("?"))
                {
                    String[] splitData = row.split(",");
                    for(int j = 0; j < splitData.length; j++)
                    {
                        if(j != 0 )
                        {
                            data[i][j-1] = Double.parseDouble(splitData[j]);
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}
