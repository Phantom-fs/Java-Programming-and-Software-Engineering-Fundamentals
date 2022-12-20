//Basic Reading and printing of CSV File consiting data related to fruits etc
//NOTE : Take care of the file and package name and make sure the csv file location is set
//Coder : Phantom-fs

package FruitCSV;


//This is an external library, use steps in CSV README.md file to know how to add it
import org.apache.commons.csv.*;
import java.io.*;

public class FruitCSVReading
{
    static String newline = System.getProperty("line.separator");
    public void readCSV ()
    {
        try
        {
            //change location to the directory where .csv file is saved
            FileReader fileReader = new FileReader("B:\\3- Java Programs\\IdeaProjects\\CSVData\\CSV Files\\SimpleFruitData.csv");
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            Styling.lineTitle("Fruit Data:");

            for (CSVRecord csvRecord : csvParser)
            {
                Styling.color("Record No - " + csvRecord.getRecordNumber() + newline, "red");
                Styling.color("Fruit - " + csvRecord.get(0), "yellow");
                Styling.color("Price - " + csvRecord.get(2), "purple");
                Styling.color("Quantity Available - " + csvRecord.get(1), "blue");
                Styling.color("Rating - " + csvRecord.get(3), "cyan");
                Styling.color(newline + "Comments - " + csvRecord.get(4), "green");
                Styling.line();
            }

            csvParser.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args)
    {
        FruitCSVReading csvBasicReading = new FruitCSVReading();
        csvBasicReading.readCSV();
    }
}
