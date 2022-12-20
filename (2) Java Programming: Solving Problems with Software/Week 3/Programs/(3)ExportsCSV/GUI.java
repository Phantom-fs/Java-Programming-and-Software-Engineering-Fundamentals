/*
 * Coder : Phantom-fs
*/

//make sure all files are in same package
package ExportsCSV;

import org.apache.commons.csv.*;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;

public class ExportsCSVReading
{
    String nl = System.lineSeparator();
    static int count = 0;

    //the method to create CSV parser and read the CSV file
    public static @Nullable CSVParser readCSV()
    {
        try
        {
            //location of the CSV file
            FileReader fileReader = new FileReader("B:\\3- Java Programs\\IdeaProjects\\CSVData\\CSV Files\\exports\\exportdata.csv");

            //inline variable, returning the parser to whichever method called it
            return new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
        return null;
    }

    public void printFullCSV ()
    {
        try
        {
            Styling.lineTitle("Countries, their exports and export value : ");

            //the CSVParser object is returned from the readCSV() method, thus not recreating multiple times
            //Objects.requireNonNull() to make sure that the csvParser object is not null
            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                Styling.color("Country - " + csvRecord.get("Country"), "red");
                Styling.color("Exports - " + csvRecord.get("Exports"), "purple");
                Styling.color("Value (dollars) - " + csvRecord.get("Value (dollars)"), "blue");
                Styling.line();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void printCountryAndExports ()
    {
        try
        {
            Styling.lineTitle("Countries and their exports : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                Styling.color("Country - " + csvRecord.get("Country"), "red");
                Styling.color("Exports - " + csvRecord.get("Exports"), "purple");
                Styling.line();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void printCountryAndValue ()
    {
        try
        {
            Styling.lineTitle("Countries and their export value : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                Styling.color("Country - " + csvRecord.get("Country"), "red");
                Styling.color("Value (dollars) - " + csvRecord.get("Value (dollars)"), "blue");
                Styling.line();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void printCountries ()
    {
        try
        {
            Styling.lineTitle("Countries : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                Styling.colorTB(csvRecord.get("Country"), "red");

                //after every 5 countries, a new line is printed
                if (csvRecord.getRecordNumber() % 5 == 0)
                    System.out.println();
            }
            Styling.line();
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void printExportOfInterest (String export)
    {
        count = 0;
        try
        {
            Styling.lineTitle("Countries that export " + export + " : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                if (csvRecord.get("Exports").contains(export))
                {
                    Styling.colorTB(csvRecord.get("Country"), "red");
                    count++;

                    //newline after every 5 countries
                    if (count % 5 == 0)
                        System.out.println();
                }
            }
            Styling.line();
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void exportsByCountry (String country)
    {
        try
        {
            Styling.lineTitle("Exports of " + country + " : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                if (csvRecord.get("Country").equals(country))
                {
                    Styling.color(csvRecord.get("Exports"), "purple");
                    Styling.line();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void exportsValueByCountry (String country)
    {
        try
        {
            Styling.lineTitle("Export Value of " + country + " : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                if (csvRecord.get("Country").equals(country))
                {
                    Styling.color(csvRecord.get("Value (dollars)"), "purple");
                    Styling.line();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void exportsByValue (String value)
    {
        count = 0;
        try
        {
            Styling.lineTitle("Countries whose export value is more than $" + value + " : ");

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                String valueStr = csvRecord.get("Value (dollars)");

                if(valueStr.length() > 0)
                {
                    //removing the commas and $ sign from the value string
                    valueStr = valueStr.replaceAll(",", "").replace("$", "").replace(" ", "");

                    //converting the string to long
                    long valueStrInt = Long.parseLong(valueStr);

                    //converting the string to long
                    long valueUser = Long.parseLong(value);

                    if (valueStrInt > valueUser)
                    {
                        Styling.colorTB(csvRecord.get("Country"), "red");
                        count++;

                        //newline after every 5 countries
                        if (count % 5 == 0)
                            System.out.println();
                    }
                }
            }
            Styling.line();
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error");
        }
    }

    public void printSomeImportantExports ()
    {
        count = 0;
        try
        {
            String[] impExports = new String[21];

            impExports[0] = "crude oil";
            impExports[1] = "petroleum";
            impExports[2] = "gold";
            impExports[3] = "diamonds";
            impExports[4] = "copper";
            impExports[5] = "iron ore";
            impExports[6] = "silver";
            impExports[7] = "tin";
            impExports[8] = "zinc";
            impExports[9] = "lead";
            impExports[10] = "nickel";
            impExports[11] = "aluminum";
            impExports[12] = "uranium";
            impExports[13] = "manganese";
            impExports[14] = "platinum";
            impExports[15] = "cotton";
            impExports[16] = "tea";
            impExports[17] = "coffee";
            impExports[18] = "sugar";
            impExports[19] = "cocoa";
            impExports[20] = "rubber";

            for (String impExport : impExports)
            {
                Styling.lineTitle("Countries that export " + impExport + " : ");

                for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
                {
                    if (csvRecord.get("Exports").contains(impExport))
                    {
                        Styling.colorTB(csvRecord.get("Country"), "purple");
                        count++;

                        //newline after every 5 countries
                        if (count % 5 == 0)
                            System.out.print(nl);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public void someCalculations(int sizeEx)
    {
        try
        {
            //number of countries exporting more than n items
            int count = 0;

            //total value of exports
            long total = 0;

            int count2 = 0;

            //array of countries that satisfy the condition
            String[] countries = new String[250];

            for (CSVRecord csvRecord : Objects.requireNonNull(readCSV()))
            {
                String valueStr = csvRecord.get("Value (dollars)");
                String valueEx = csvRecord.get("Exports");

                if (valueStr.length() > 0 && valueEx.length() > 0)
                {
                    //removing the commas and $ sign from the value string
                    valueStr = valueStr.replaceAll(",", "").replace("$", "").replace(" ", "");

                    //converting the string to long
                    long valueStrInt = Long.parseLong(valueStr);

                    //adding the value to the total
                    total += valueStrInt;


                    //splitting the export string and putting it in an array
                    String[] exports = valueEx.split(",");

                    //checking, if the number of exports is more than the size, as a country export is saved in this string, with each entry being an export item,
                    //thus if the number of exports in the string is more than the size provide, then the country satisfies the condition, i.e. incrementing the count
                    if (exports.length > sizeEx)
                    {
                        countries[count] = csvRecord.get("Country");
                        count++;
                    }
                }
            }

            Styling.lineTitle("Some calculations on complete data: ");

            Styling.color("Number of countries exporting more than " +sizeEx+ " items : " + count +nl, "red");
            Styling.color("Total value of exports : $" + total + nl, "purple");

            //printing the countries exporting more than n items
            Styling.color("Name of Countries exporting more than " + sizeEx + " items : "+nl, "red");

            for (int i = 0; i < 250; i++)
            {
                if(countries[i] != null)
                {
                    Styling.colorTB(countries[i], "purple");
                    count2++;

                    //newline after every 5 countries
                    if (count2 % 5 == 0)
                        System.out.print(nl);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    public static String removeNonDigits(final String str)
    {
        if (str == null || str.isEmpty())
            return str;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9')
                sb.append(c);
        }
        return sb.toString();
    }
}
//linked In : https://www.linkedin.com/in/farhan-sheth/
