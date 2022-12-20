//Basic Reading and printing of CSV File consiting data related to food, fav color etc
//NOTE : Take care of the file and package name and make sure the csv file location is set
//Coder : Phantom-fs

package FoodCSV;

//This is an external library, use steps in README.md file to know how to add it
import org.apache.commons.csv.*;
import java.io.*;

public class FoodCSVReading
{
    public void readCSV()
    {
        try
        {
            FileReader fileReader = new FileReader("B:\\3- Java Programs\\IdeaProjects\\CSVData\\CSV Files\\foods.csv");
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            Styling.lineTitle("Food Data:");

            for (CSVRecord csvRecord : csvParser)
            {
                //Styling.color("Record No - " + csvRecord.getRecordNumber() + newline, "red");
                Styling.color("Name - " + csvRecord.get("Name"), "red");
                Styling.color("Favorite Food - " + csvRecord.get("Favorite Food"), "purple");
                Styling.color("Favorite Color - " + csvRecord.get("Favorite Color"), "blue");
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
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public static void main (String[] args)
    {
        FoodCSVReading foodCSVReading = new FoodCSVReading();
        foodCSVReading.readCSV();
    }
}
