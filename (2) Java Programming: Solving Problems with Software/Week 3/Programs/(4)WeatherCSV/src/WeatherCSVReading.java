import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.io.File;
import java.io.FileReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.Objects;

public class WeatherCSVReading
{
    static String USERPath = null;        //Path for the folder

    static String nl = System.getProperty("line.separator");
    static String line = "------------------------------------------------------------------------------";

    //Verifying the folder to be a directory of CSV Files i.e. weather data
    public static boolean isDirectoryOfCSVFiles(@NotNull File folder, String exampleYear)
    {
        //verifying if the folder is a directory
        if (folder.isDirectory())
        {
            String path = folder+"\\"+exampleYear+"\\";

            //File array
            File[] listOfFiles = new File(path).listFiles();

            //if the files in an example year folder are not null
            if (listOfFiles != null)
            {
                //checking a file in the example year folder
                File files1 = listOfFiles[0];

                //if it's a file
                if (files1.isFile())
                {
                    //if it's a CSV file
                    if (files1.getName().endsWith(".csv"))
                    {
                        //thus, the folder is a directory of years, and each year is a directory of CSV files
                        USERPath = folder+"\\";

                        //returning true
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //file for year
    public static String @Nullable [] fileNames(String year)
    {
        try
        {
            int i = 0;

            //taking the path of the year, from the USERPath
            String path = USERPath + year + "\\";

            //File array
            String[] fileNames = new File(path).list();

            //if the fileNames array is not null
            if (fileNames != null)
            {
                //getting each fileName in the fileNames array
                for (String fileName : fileNames)
                {
                    //concatenating the path and the fileName
                    fileName = path + fileName;

                    //adding the fileName to the fileNames array
                    fileNames[i++] = fileName;
                }
                return fileNames;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //file for month
    public static String @Nullable [] fileNamesM(String year, String UserMonth)
    {
        try
        {
            //to convert Month in JAN,... format to 01,... format
            String month = monthToNum(UserMonth);

            String[] fileNames31 = new String[31];
            String[] fileNames30 = new String[30];
            String[] fileNames29 = new String[29];
            String[] fileNames28 = new String[28];

            int i = 0;

            //taking the path of the folder, from the USERPath and concatenating the year
            String path = USERPath + year + "\\";

            //File array, all files from the directory
            String[] fileNamesWeatherPart = new File(path).list();

            if (fileNamesWeatherPart != null)
            {
                //converting the year to integer
                int parseInt = Integer.parseInt(year.trim().replace(" ", ""));

                for (String fileName : fileNamesWeatherPart)
                {
                    //getting the month or 01, 02,.... from the fileName
                    String monthPart = fileName.substring(13, 15).trim().replace(" ", "");

                    //if the month in the FILENAME is equal to the user month
                    if (monthPart.equals(month))
                    {
                        //adding the filename to the array of that month
                        switch (month)
                        {
                            case "01", "03", "05", "07", "08", "10", "12" ->
                            {
                                fileName = path + fileName;
                                fileNames31[i++] = fileName;
                            }
                            case "04", "06", "09", "11" ->
                            {
                                fileName = path + fileName;
                                fileNames30[i++] = fileName;
                            }
                            case "02" ->
                            {
                                fileName = path + fileName;

                                if (parseInt % 4 == 0)
                                    fileNames29[i++] = fileName;
                                else
                                    fileNames28[i++] = fileName;
                            }
                        }
                    }
                }

                //returning the array based on the user month
                switch (month)
                {
                    case "01", "03", "05", "07", "08", "10", "12" ->
                    {
                        removeNull(fileNames31);
                        return fileNames31;
                    }
                    case "04", "06", "09", "11" ->
                    {
                        fileNames30 = removeNull(fileNames30);
                        return fileNames30;
                    }
                    case "02" ->
                    {
                        if (parseInt % 4 == 0)
                        {
                            fileNames29 = removeNull(fileNames29);
                            return fileNames29;
                        }
                        else
                        {
                            fileNames28 = removeNull(fileNames28);
                            return fileNames28;
                        }
                    }
                }
            }
            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //file for a date
    public static @NotNull String fileName(String year, String date)
    {
        try
        {
            //path for the weather data
            return USERPath + year + "\\weather-" + date + ".csv";
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //get year from Date
    public String getYear(String date)
    {
        //date btw 2012-01-01 to 2015-12-31
        if (date != null && date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-' && (date.startsWith("2012")
                || date.startsWith("2013") || date.startsWith("2014") || date.startsWith("2015"))
                && Integer.parseInt(date.substring(5, 7)) <= 12 && Integer.parseInt(date.substring(5, 7)) >= 1 && Integer.parseInt(date.substring(8)) <= 31)
        {
            //as date is in yyyy-mm-dd format
            return date.substring(0, 4);
        }
        else
            return null;
    }

    //month to number
    public static String monthToNum(@NotNull String month)
    {
        return switch (month) {
            case "January" -> "01";
            case "February" -> "02";
            case "March" -> "03";
            case "April" -> "04";
            case "May" -> "05";
            case "June" -> "06";
            case "July" -> "07";
            case "August" -> "08";
            case "September" -> "09";
            case "October" -> "10";
            case "November" -> "11";
            case "December" -> "12";
            default -> null;
        };
    }

    //CSV file reading
    public static @Nullable CSVParser readCSV(String path)
    {
        try
        {
            //location of the CSV file
            FileReader fileReader = new FileReader(path);

            //inline variable, returning the parser to whichever method called it
            return new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //Files Max temperature reading
    public CSVRecord maxTemp(String @NotNull [] fileNames)
    {
        double maxTemp = 0;
        double temp;

        //to store the record of the data with max temperature, so that we can use it in GUI file to display its date etc.
        CSVRecord maxTempRecord = null;

        //looping through all the files
        for (String fileName : fileNames)
        {
            //looping through all the records of temperature in the file
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                //converting temperature to double
                temp = Double.parseDouble(Objects.requireNonNull(record.get("TemperatureF")));

                //if the temperature is greater than the max temperature
                if (temp != -9999 && temp > maxTemp)
                {
                    maxTemp = temp;
                    maxTempRecord = record;
                }
            }
        }

        //returning the record with max temperature
        return maxTempRecord;
    }

    //Files Min temperature reading
    public CSVRecord minTemp(String @NotNull [] fileNames)
    {
        double minTemp = 9999;
        double temp;

        CSVRecord minTempRecord = null;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                temp = Double.parseDouble(Objects.requireNonNull(record.get("TemperatureF")));

                if (temp != -9999 && temp < minTemp)
                {
                    minTemp = temp;
                    minTempRecord = record;
                }
            }
        }
        return minTempRecord;
    }

    //Files Max humidity reading
    public CSVRecord maxHumidity(String @NotNull [] fileNames)
    {
        double maxHumidity = 1;
        double humidity;

        CSVRecord maxHumidityRecord = null;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                humidity = Double.parseDouble(Objects.requireNonNull(record.get("Humidity").trim().replace("N/A", "-9999")));

                if (humidity != -9999 && humidity > maxHumidity)
                {
                    maxHumidity = humidity;
                    maxHumidityRecord = record;
                }
            }
        }
        return maxHumidityRecord;
    }

    //Files Min humidity reading
    public CSVRecord minHumidity(String @NotNull [] fileNames)
    {
        int minHumidity = 9999;
        int humidity;

        CSVRecord minHumidityRecord = null;

        for (String fileName : fileNames) {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                humidity = Integer.parseInt(Objects.requireNonNull(record.get("Humidity").trim().replace("N/A", "-9999")));

                if (humidity != -9999 && humidity < minHumidity)
                {
                    minHumidity = humidity;
                    minHumidityRecord = record;
                }
            }
        }
        return minHumidityRecord;
    }

    //avg dew point in files
    public double avgDewPoint(String @NotNull [] fileNames)
    {
        double dewPoint = 0;
        int count = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                dewPoint += Double.parseDouble(Objects.requireNonNull(record.get("Dew PointF")));
                count++;
            }
        }

        return Math.round((dewPoint / count) * 100.0) / 100.0;
    }

    //avg sea level pressure in files
    public double avgSeaLevelPressure(String @NotNull [] fileNames)
    {
        double seaLevelPressure = 0;
        int count = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                seaLevelPressure += Double.parseDouble(Objects.requireNonNull(record.get("Sea Level PressureIn")));
                count++;
            }
        }

        return Math.round((seaLevelPressure / count) * 100.0) / 100.0;
    }

    //wind direction in files
    public String windDirection(String @NotNull [] fileNames)
    {
        int calm = 0;
        int variable = 0;

        int north = 0;
        int south = 0;
        int east = 0;
        int west = 0;

        int NE = 0;
        int NW = 0;
        int SE = 0;
        int SW = 0;

        int SSE = 0;
        int SSW = 0;
        int ESE = 0;
        int ENE = 0;
        int WSW = 0;
        int WNW = 0;
        int NNW = 0;
        int NNE = 0;

        int other = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                String wind = Objects.requireNonNull(record.get("Wind Direction"));

                switch (wind)
                {
                    case "Calm" -> calm++;
                    case "Variable" -> variable++;
                    case "North" -> north++;
                    case "South" -> south++;
                    case "East" -> east++;
                    case "West" -> west++;
                    case "NE" -> NE++;
                    case "NW" -> NW++;
                    case "SE" -> SE++;
                    case "SW" -> SW++;
                    case "SSE" -> SSE++;
                    case "SSW" -> SSW++;
                    case "ESE" -> ESE++;
                    case "ENE" -> ENE++;
                    case "WSW" -> WSW++;
                    case "WNW" -> WNW++;
                    case "NNW" -> NNW++;
                    case "NNE" -> NNE++;
                    default -> other++;
                }
            }
        }

        //most common wind direction
        int[] windDirections = {calm, variable, north, south, east, west, NE, NW, SE, SW, SSE, SSW, ESE, ENE, WSW, WNW, NNW, NNE, other};
        int max = 0;
        int index = 0;

        for (int i = 0; i < windDirections.length; i++)
        {
            if (windDirections[i] > max)
            {
                max = windDirections[i];
                index = i;
            }
        }

        String wind;

        switch (index)
        {
            case 0 -> wind = "Calm";
            case 1 -> wind = "Variable";
            case 2 -> wind = "North";
            case 3 -> wind = "South";
            case 4 -> wind = "East";
            case 5 -> wind = "West";
            case 6 -> wind = "NE";
            case 7 -> wind = "NW";
            case 8 -> wind = "SE";
            case 9 -> wind = "SW";
            case 10 -> wind = "SSE";
            case 11 -> wind = "SSW";
            case 12 -> wind = "ESE";
            case 13 -> wind = "ENE";
            case 14 -> wind = "WSW";
            case 15 -> wind = "WNW";
            case 16 -> wind = "NNW";
            case 17 -> wind = "NNE";
            default -> wind = "Other";
        }

        return wind;
    }

    //most common conditions in files
    public String mostCommonConditions(String @NotNull [] fileNames)
    {
        int clear = 0;
        int partlyCloudy = 0;
        int scatteredCloudy = 0;
        int mostlyCloudy = 0;
        int overcast = 0;
        int cloudy = 0;
        int fog = 0;
        int rain = 0;
        int snow = 0;
        int sleet = 0;
        int thunderstorm = 0;
        int tornado = 0;
        int other = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                String conditions = Objects.requireNonNull(record.get("Conditions"));

                switch (conditions) {
                    case "Clear" -> clear++;
                    case "Partly Cloudy" -> partlyCloudy++;
                    case "Scattered Clouds" -> scatteredCloudy++;
                    case "Mostly Cloudy" -> mostlyCloudy++;
                    case "Overcast" -> overcast++;
                    case "Cloudy" -> cloudy++;
                    case "Fog" -> fog++;
                    case "Rain" -> rain++;
                    case "Snow" -> snow++;
                    case "Sleet" -> sleet++;
                    case "Thunderstorm" -> thunderstorm++;
                    case "Tornado" -> tornado++;
                    default -> other++;
                }
            }
        }

        //most common conditions
        int[] conditions = {clear, partlyCloudy, scatteredCloudy, mostlyCloudy, overcast, cloudy, fog, rain, snow, sleet, thunderstorm, tornado, other};
        int max = 0;
        int index = 0;

        for (int i = 0; i < conditions.length; i++)
        {
            if (conditions[i] > max)
            {
                max = conditions[i];
                index = i;
            }
        }

        String condition;

        switch (index) {
            case 0 -> condition = "Clear";
            case 1 -> condition = "Partly Cloudy";
            case 2 -> condition = "Scattered Clouds";
            case 3 -> condition = "Mostly Cloudy";
            case 4 -> condition = "Overcast";
            case 5 -> condition = "Cloudy";
            case 6 -> condition = "Fog";
            case 7 -> condition = "Rain";
            case 8 -> condition = "Snow";
            case 9 -> condition = "Sleet";
            case 10 -> condition = "Thunderstorm";
            case 11 -> condition = "Tornado";
            default -> condition = "Other";
        }

        return condition;
    }

    //avg WindDirDegrees in files
    public double avgWindDirDegrees(String @NotNull [] fileNames)
    {
        double windDirDegrees = 0;
        int count = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                windDirDegrees += Double.parseDouble(Objects.requireNonNull(record.get("WindDirDegrees")));
                count++;
            }
        }

        return Math.round((windDirDegrees / count) * 100.0) / 100.0;
    }

    //Files avg temperature reading
    public double avgTemp(String @NotNull [] fileNames)
    {
        double avgTemp = 0;
        double temp;
        int count = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                temp = Double.parseDouble(Objects.requireNonNull(record.get("TemperatureF")));

                if (temp != -9999)
                {
                    avgTemp += temp;
                    count++;
                }
            }
        }
        return Math.round((avgTemp / count) * 100.0) / 100.0;
    }

    //Files avg humidity reading
    public double avgHumidity(String @NotNull [] fileNames)
    {
        double avgHumidity = 0;
        double humidity;
        int count = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                humidity = Double.parseDouble(Objects.requireNonNull(record.get("Humidity").trim().replace("N/A", "-9999")));

                if (humidity != -9999)
                {
                    avgHumidity += humidity;
                    count++;
                }
            }
        }
        return Math.round((avgHumidity / count) * 100.0) / 100.0;
    }

    //Files avg WindSpeed MPH
    public String avgWindSpeed(String @NotNull [] fileNames)
    {
        double windSpeed = 0;
        double windSpeedCALM = 0;
        double count = 0;

        for (String fileName : fileNames)
        {
            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                String wind = Objects.requireNonNull(record.get("Wind SpeedMPH"));

                if (wind.equals("Calm"))
                {
                    windSpeedCALM++;
                }
                else if (!wind.equals("N/A"))
                {
                    windSpeed += Double.parseDouble(wind);
                    count++;
                }
            }
        }

        if (windSpeedCALM > count)
        {
            return "Calm";
        }
        else
        {
            double avg = Math.round((windSpeed / count) * 100.0) / 100.0;
            return String.valueOf(avg);
        }
    }

    //Max temp in a file
    public CSVRecord maxTempInFile(String fileName)
    {
        double maxTemp = 0;
        double temp;

        CSVRecord maxTempRecord = null;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            temp = Double.parseDouble(Objects.requireNonNull(record.get("TemperatureF")));

            if (temp != -9999 && temp > maxTemp)
            {
                maxTemp = temp;
                maxTempRecord = record;
            }
        }
        return maxTempRecord;
    }

    //Min temp in a file
    public CSVRecord minTempInFile(String fileName)
    {
        double minTemp = 9999;
        double temp;

        CSVRecord minTempRecord = null;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            temp = Double.parseDouble(Objects.requireNonNull(record.get("TemperatureF")));

            if (temp != -9999 && temp < minTemp)
            {
                minTemp = temp;
                minTempRecord = record;
            }
        }
        return minTempRecord;
    }

    //Max humidity in a file
    public CSVRecord maxHumidityInFile(String fileName)
    {
        double maxHumidity = 0;
        double humidity;

        CSVRecord maxHumidityRecord = null;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            humidity = Double.parseDouble(Objects.requireNonNull(record.get("Humidity").trim().replace("N/A", "-9999")));

            if (humidity != -9999 && humidity > maxHumidity)
            {
                maxHumidity = humidity;
                maxHumidityRecord = record;
            }
        }
        return maxHumidityRecord;
    }

    //Min humidity in a file
    public CSVRecord minHumidityInFile(String fileName)
    {
        double minHumidity = 9999;
        double humidity;

        CSVRecord minHumidityRecord = null;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            humidity = Double.parseDouble(Objects.requireNonNull(record.get("Humidity").trim().replace("N/A", "-9999")));

            if (humidity != -9999 && humidity < minHumidity)
            {
                minHumidity = humidity;
                minHumidityRecord = record;
            }
        }
        return minHumidityRecord;
    }

    //Average temp in a file
    public double avgTemperatureInFile(String fileName)
    {
        double avgTemp = 0;
        double temp;
        int count = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            temp = Double.parseDouble(record.get("TemperatureF"));

            if (temp != -9999)
            {
                avgTemp += temp;
                count++;
            }
        }
        return Math.round((avgTemp / count) * 100.0) / 100.0;
    }

    //Average humidity in a file
    public double avgHumidityInFile(String fileName)
    {
        double avgHumidity = 0;
        double humidity;
        int count = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            humidity = Double.parseDouble(Objects.requireNonNull(record.get("Humidity").trim().replace("N/A", "9999")));

            if (humidity != -9999)
            {
                avgHumidity += humidity;
                count++;
            }
        }
        return Math.round((avgHumidity / count) * 100.0) / 100.0;
    }

    //avg dew point in file
    public double avgDewPointInFile(String fileName)
    {
        double dewPoint = 0;
        double count = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            dewPoint += Double.parseDouble(Objects.requireNonNull(record.get("Dew PointF")));
            count++;
        }
        return Math.round((dewPoint / count) * 100.0) / 100.0;
    }

    //avg sea level pressure in file
    public double avgSeaLevelPressureInFile(String fileName)
    {
        double seaLevelPressure = 0;
        double count = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            seaLevelPressure += Double.parseDouble(Objects.requireNonNull(record.get("Sea Level PressureIn")));
            count++;
        }
        return Math.round((seaLevelPressure / count) * 100.0) / 100.0;
    }

    //wind direction in file
    public String windDirectionInFile(String fileName)
    {
        int calm = 0;
        int variable = 0;

        int north = 0;
        int south = 0;
        int east = 0;
        int west = 0;

        int NE = 0;
        int NW = 0;
        int SE = 0;
        int SW = 0;

        int SSE = 0;
        int SSW = 0;
        int ESE = 0;
        int ENE = 0;
        int WSW = 0;
        int WNW = 0;
        int NNW = 0;
        int NNE = 0;

        int other = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            String windDirection = record.get("Wind Direction");

            switch (windDirection)
            {
                case "Calm" -> calm++;
                case "Variable" -> variable++;
                case "North" -> north++;
                case "South" -> south++;
                case "East" -> east++;
                case "West" -> west++;
                case "NE" -> NE++;
                case "NW" -> NW++;
                case "SE" -> SE++;
                case "SW" -> SW++;
                case "SSE" -> SSE++;
                case "SSW" -> SSW++;
                case "ESE" -> ESE++;
                case "ENE" -> ENE++;
                case "WSW" -> WSW++;
                case "WNW" -> WNW++;
                case "NNW" -> NNW++;
                case "NNE" -> NNE++;
                default -> other++;
            }
        }

        int[] windDirectionCount = {calm, variable, north, south, east, west, NE, NW, SE, SW, SSE, SSW, ESE, ENE, WSW, WNW, NNW, NNE, other};
        int max = 0;
        int index = 0;

        for (int i = 0; i < windDirectionCount.length; i++)
        {
            if (windDirectionCount[i] > max)
            {
                max = windDirectionCount[i];
                index = i;
            }
        }

        String windDirection;

        switch (index)
        {
            case 0 -> windDirection = "Calm";
            case 1 -> windDirection = "Variable";
            case 2 -> windDirection = "North";
            case 3 -> windDirection = "South";
            case 4 -> windDirection = "East";
            case 5 -> windDirection = "West";
            case 6 -> windDirection = "NE";
            case 7 -> windDirection = "NW";
            case 8 -> windDirection = "SE";
            case 9 -> windDirection = "SW";
            case 10 -> windDirection = "SSE";
            case 11 -> windDirection = "SSW";
            case 12 -> windDirection = "ESE";
            case 13 -> windDirection = "ENE";
            case 14 -> windDirection = "WSW";
            case 15 -> windDirection = "WNW";
            case 16 -> windDirection = "NNW";
            case 17 -> windDirection = "NNE";
            default -> windDirection = "Other";
        }

        return windDirection;
    }

    //most common conditions in file
    public String mostCommonConditionsInFile(String fileName)
    {
        int clear = 0;
        int partlyCloudy = 0;
        int scatteredCloudy = 0;
        int mostlyCloudy = 0;
        int overcast = 0;
        int cloudy = 0;
        int fog = 0;
        int rain = 0;
        int snow = 0;
        int sleet = 0;
        int thunderstorm = 0;
        int tornado = 0;
        int other = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            switch (record.get("Conditions"))
            {
                case "Clear" -> clear++;
                case "Partly Cloudy" -> partlyCloudy++;
                case "Scattered Clouds" -> scatteredCloudy++;
                case "Mostly Cloudy" -> mostlyCloudy++;
                case "Overcast" -> overcast++;
                case "Cloudy" -> cloudy++;
                case "Fog" -> fog++;
                case "Rain" -> rain++;
                case "Snow" -> snow++;
                case "Sleet" -> sleet++;
                case "Thunderstorm" -> thunderstorm++;
                case "Tornado" -> tornado++;
                default -> other++;
            }
        }

        //most common conditions
        int[] conditions = {clear, partlyCloudy, scatteredCloudy, mostlyCloudy, overcast, cloudy, fog, rain, snow, sleet, thunderstorm, tornado, other};
        int max = 0;
        int index = 0;

        for (int i = 0; i < conditions.length; i++)
        {
            if (conditions[i] > max)
            {
                max = conditions[i];
                index = i;
            }
        }

        String mostCommonConditions;

        switch (index) {
            case 0 -> mostCommonConditions = "Clear";
            case 1 -> mostCommonConditions = "Partly Cloudy";
            case 2 -> mostCommonConditions = "Scattered Clouds";
            case 3 -> mostCommonConditions = "Mostly Cloudy";
            case 4 -> mostCommonConditions = "Overcast";
            case 5 -> mostCommonConditions = "Cloudy";
            case 6 -> mostCommonConditions = "Fog";
            case 7 -> mostCommonConditions = "Rain";
            case 8 -> mostCommonConditions = "Snow";
            case 9 -> mostCommonConditions = "Sleet";
            case 10 -> mostCommonConditions = "Thunderstorm";
            case 11 -> mostCommonConditions = "Tornado";
            default -> mostCommonConditions = "Other";
        }

        return mostCommonConditions;
    }

    //avg windDirDegrees in file
    public double avgWindDirDegreesInFile(String fileName)
    {
        double windDirDegrees = 0;
        double count = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            windDirDegrees += Double.parseDouble(Objects.requireNonNull(record.get("WindDirDegrees")));
            count++;
        }
        return Math.round((windDirDegrees / count) * 100.0) / 100.0;
    }

    //avg wind speed in file
    public String avgWindSpeedInFile(String fileName)
    {
        double windSpeed = 0;
        double windSpeedCALM = 0;
        double count = 0;

        for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
        {
            if (!record.get("Wind SpeedMPH").equals("Calm") && !record.get("Wind SpeedMPH").equals("N/A"))
            {
                windSpeed += Double.parseDouble(Objects.requireNonNull(record.get("Wind SpeedMPH")));
                count++;
            }
            else if (record.get("Wind SpeedMPH").equals("Calm") && !record.get("Wind SpeedMPH").equals("N/A"))
            {
                windSpeedCALM++;
            }
        }

        if (windSpeedCALM > count)
        {
            return "Calm";
        }
        else
        {
            double avg = Math.round((windSpeed / count) * 100.0) / 100.0;
            return String.valueOf(avg);
        }
    }

    //==================================================================================================================
    //All details based on date
    public String[] allDetailsBasedOnDate(String fileName)
    {
        try
        {
            //huge size array as all the records in the file are saved
            String[] details = new String[600];
            int count = 0;

            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                for (int i = 0; i < record.size(); i++)
                {
                    //getting the record according to the record number
                    details[count] = record.get(i);
                    count++;
                }
            }
            return details;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //all details based on either month or year i.e. multiple dates (files)
    public void allDetailsBasedOnFiles(String[] fileNames, JTextPane textPane)
    {
        try
        {
            //getting each file name from the array
            for (String fileName : fileNames)
            {
                //getting all the data of each individual file using single file method 'allDetailsBasedOnDate'
                String[] data = allDetailsBasedOnDate(fileName);

                //printing all the data of each individual file
                for (int i = 0; i < data.length; i++)
                {
                    if (data[i] != null)
                    {
                        i = printAllDetails(data, textPane, i);
                    }
                }
            }
        }
        catch (Exception ignored) {}
    }

    //ALL DETAILS printing
    public int printAllDetails(String @NotNull [] data, @NotNull JTextPane textPane, int i)
    {
        //for printing the date and formatting the text
        StyledDocument doc = textPane.getStyledDocument();

        try
        {
            doc.insertString(doc.getLength(), nl + "Time: " + data[i++] + nl + nl, Styling.headBoldStyle());

            doc.insertString(doc.getLength(), "Temperature: " + data[i++] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Dew Point: " + data[i++] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Humidity: " + data[i++] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Sea Level Pressure: " + data[i++] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Visibility MPH: " + data[i++] + nl + nl, Styling.Style1());

            doc.insertString(doc.getLength(), "Wind Direction: " + data[i++] + nl, Styling.Style2());
            doc.insertString(doc.getLength(), "Wind Speed MPH: " + data[i++] + nl, Styling.Style2());
            doc.insertString(doc.getLength(), "Gust Speed MPH: " + data[i++] + nl, Styling.Style2());
            doc.insertString(doc.getLength(), "Precipitation: " + data[i++] + nl + nl, Styling.Style2());

            doc.insertString(doc.getLength(), "Events: " + data[i++] + nl, Styling.Style3());
            doc.insertString(doc.getLength(), "Conditions: " + data[i++] + nl, Styling.Style3());
            doc.insertString(doc.getLength(), "Wind Direction Degree: " + data[i++] + nl + nl, Styling.Style3());

            doc.insertString(doc.getLength(), nl + "Date UTC: " + data[i] + nl, Styling.headSubStyle());

            doc.insertString(doc.getLength(), line + nl, Styling.headBoldStyle());
        }
        catch (BadLocationException ignored) {}

        return i;
    }

    //==================================================================================================================
    //avg details based on date
    public String[] avgDetailsBasedOnDate(String fileName, String date)
    {
        try
        {
            String[] details = new String[13];

            details[0] = date;

            String maxTemp = maxTempInFile(fileName).get("TemperatureF") + " at " + convertUTCtoTime(maxTempInFile(fileName).get("DateUTC"), maxTempInFile(fileName));
            details[1] = maxTemp;

            String minTemp = minTempInFile(fileName).get("TemperatureF") + " at " + convertUTCtoTime(minTempInFile(fileName).get("DateUTC"), minTempInFile(fileName));
            details[2] = minTemp;

            details[3] = String.valueOf(avgTemperatureInFile(fileName));

            String maxHumidity = maxHumidityInFile(fileName).get("Humidity") + " at " + convertUTCtoTime(maxHumidityInFile(fileName).get("DateUTC"), maxHumidityInFile(fileName));
            details[4] = maxHumidity;

            String minHumidity = minHumidityInFile(fileName).get("Humidity") + " at " + convertUTCtoTime(minHumidityInFile(fileName).get("DateUTC"), minHumidityInFile(fileName));
            details[5] = minHumidity;

            details[6] = String.valueOf(avgHumidityInFile(fileName));

            details[7] = String.valueOf(avgDewPointInFile(fileName));
            details[8] = String.valueOf(avgSeaLevelPressureInFile(fileName));
            details[9] = String.valueOf(windDirectionInFile(fileName));
            details[10] = String.valueOf(mostCommonConditionsInFile(fileName));
            details[11] = avgWindSpeedInFile(fileName);
            details[12] = String.valueOf(avgWindDirDegreesInFile(fileName));

            return details;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //avg details in a year/month
    public String[] avgDetailsBasedOnFiles(String[] fileNames, String detail)
    {
        try
        {
            String[] avgDetails = new String[13];

            CSVRecord maxTemp = maxTemp(fileNames);
            CSVRecord minTemp = minTemp(fileNames);
            CSVRecord maxHumidity = maxHumidity(fileNames);
            CSVRecord minHumidity = minHumidity(fileNames);

            avgDetails[0] = detail;

            avgDetails[1] = maxTemp.get("TemperatureF") + " on " + convertUTCtoEST_EDT(maxTemp.get("DateUTC"), maxTemp);
            avgDetails[2] = minTemp.get("TemperatureF") + " on " + convertUTCtoEST_EDT(minTemp.get("DateUTC"), minTemp);
            avgDetails[3] = String.valueOf(avgTemp(fileNames));

            avgDetails[4] = maxHumidity.get("Humidity") + " on " + convertUTCtoEST_EDT(maxHumidity.get("DateUTC"), maxHumidity);
            avgDetails[5] = minHumidity.get("Humidity") + " on " + convertUTCtoEST_EDT(minHumidity.get("DateUTC"), minHumidity);
            avgDetails[6] = String.valueOf(avgHumidity(fileNames));

            avgDetails[7] = String.valueOf(avgDewPoint(fileNames));
            avgDetails[8] = String.valueOf(avgSeaLevelPressure(fileNames));
            avgDetails[9] = windDirection(fileNames);

            avgDetails[10] = mostCommonConditions(fileNames);
            avgDetails[11] = avgWindSpeed(fileNames);
            avgDetails[12] = String.valueOf(avgWindDirDegrees(fileNames));

            return avgDetails;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //OVERALL DETAILS printing
    public void printAvgDetails(String @NotNull [] data, @NotNull JTextPane textPane)
    {
        StyledDocument doc = textPane.getStyledDocument();

        try
        {
            doc.insertString((doc.getLength()), nl + "Info : " + data[0] + nl + nl, Styling.headBoldStyle());

            doc.insertString((doc.getLength()), "Max Temperature: " + data[1] + nl, Styling.Style1());
            doc.insertString((doc.getLength()), "Min Temperature: " + data[2] + nl, Styling.Style1());
            doc.insertString((doc.getLength()), "Avg Temperature: " + data[3] + nl + nl, Styling.Style1());

            doc.insertString((doc.getLength()), "Max Humidity: " + data[4] + nl, Styling.Style2());
            doc.insertString((doc.getLength()), "Min Humidity: " + data[5] + nl, Styling.Style2());
            doc.insertString((doc.getLength()), "Avg Humidity: " + data[6] + nl + nl, Styling.Style2());

            doc.insertString((doc.getLength()), "Avg Dew Point: " + data[7] + nl, Styling.Style3());
            doc.insertString((doc.getLength()), "Avg Sea Level Pressure: " + data[8] + nl, Styling.Style3());
            doc.insertString((doc.getLength()), "Most Common condition : " + data[10] + nl + nl, Styling.Style3());

            doc.insertString((doc.getLength()), "Wind Direction: " + data[9] + nl, Styling.Style4());
            doc.insertString((doc.getLength()), "Avg Wind Speed: " + data[11] + nl, Styling.Style4());
            doc.insertString((doc.getLength()), "Avg Wind Direction Degree: " + data[12] + nl + nl, Styling.Style4());
        }

        catch (BadLocationException ignored) {}
    }

    //==================================================================================================================
    //details based on time
    public String[] detailsBasedOnTime(String fileName, String date, String time)
    {
        try
        {
            String[] details = new String[14];

            for (CSVRecord record : Objects.requireNonNull(readCSV(fileName)))
            {
                //converting the UTC date to the date in file i.e., EST or EDT format
                String recordDate = convertUTCtoEST_EDT(record.get("DateUTC"), record);

                //concatenating the date and time to compare with the date and time given by the user
                String UserDate = date + " " + time;

                if (Objects.equals(recordDate, UserDate))
                {
                    details[0] = time + " on " + date;
                    details[1] = record.get("DateUTC");
                    details[2] = record.get("TemperatureF");
                    details[3] = record.get("Dew PointF");
                    details[4] = record.get("Humidity");
                    details[5] = record.get("Sea Level PressureIn");
                    details[6] = record.get("VisibilityMPH");
                    details[7] = record.get("Wind Direction");
                    details[8] = record.get("Wind SpeedMPH");
                    details[9] = record.get("Gust SpeedMPH");
                    details[10] = record.get("PrecipitationIn");
                    details[11] = record.get("Events");
                    details[12] = record.get("Conditions");
                    details[13] = record.get("WindDirDegrees");
                }
            }
            return details;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    //DETAILS AT TIME printing
    public void printDetailsBasedOnTime(String @NotNull [] data, @NotNull JTextPane textPane)
    {
        StyledDocument doc = textPane.getStyledDocument();

        try
        {
            doc.insertString(doc.getLength(), "Details at:  " + data[0] + nl, Styling.headBoldStyle());
            doc.insertString(doc.getLength(), "Date UTC:  " + data[1] + nl + nl, Styling.headSubStyle());

            doc.insertString(doc.getLength(), "Temperature: " + data[2] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Dew Point: " + data[3] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Humidity: " + data[4] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Sea Level Pressure: " + data[5] + nl, Styling.Style1());
            doc.insertString(doc.getLength(), "Visibility MPH: " + data[6] + nl + nl, Styling.Style1());

            doc.insertString(doc.getLength(), "Wind Direction: " + data[7] + nl, Styling.Style2());
            doc.insertString(doc.getLength(), "Wind Speed MPH: " + data[8] + nl, Styling.Style2());
            doc.insertString(doc.getLength(), "Gust Speed MPH: " + data[9] + nl, Styling.Style2());
            doc.insertString(doc.getLength(), "Precipitation: " + data[10] + nl + nl, Styling.Style2());

            doc.insertString(doc.getLength(), "Events: " + data[11] + nl, Styling.Style3());
            doc.insertString(doc.getLength(), "Conditions: " + data[12] + nl, Styling.Style3());
            doc.insertString(doc.getLength(), "Wind Direction Degree: " + data[13] + nl, Styling.Style3());
        }
        catch (BadLocationException ignored) {}
    }

    //UTC date format 2012-12-21 00:00:00 to EST format
    public String convertUTCtoEST_EDT(String date, @NotNull CSVRecord record)
    {
        String dateEST_EDT = null;
        try
        {
            //UTC date format
            Date dateUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            Calendar cal = Calendar.getInstance();

            //setting the time to UTC format
            cal.setTime(dateUTC);

            //if the time format is in EST format
            if (csvExceptionEST(record) != null)
            {
                //adding 5 hours to the UTC time
                cal.add(Calendar.HOUR, -5);
            }

            //if the time format is in EDT format
            else if (csvExceptionEDT(record) != null)
            {
                //adding 4 hours to the UTC time
                cal.add(Calendar.HOUR, -4);
            }

            //converting the date to EST or EDT format
            dateEST_EDT = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(cal.getTime());
        }
        catch (ParseException ignored) {}

        return dateEST_EDT;
    }

    public String convertUTCtoTime(String date, CSVRecord record)
    {
        //converting the date to EST or EDT format
        String dateConv = convertUTCtoEST_EDT(date, record);

        //extracting the time from the date
        return dateConv.substring(11, 19);
    }

    public String csvExceptionEST(CSVRecord record)
    {
        try
        {
            //if the record is given in EST format
            if (record.get("TimeEST") != null)
            {
                return "EST";
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }

    public String csvExceptionEDT(CSVRecord record)
    {
        try
        {
            //if the record is given in an EDT format
            if (record.get("TimeEDT") != null)
            {
                return "EDT";
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }

    private static String @NotNull [] removeNull(String @NotNull [] fileNames)
    {
        int i = 0;

        //removing the null values from the array i.e., from the file array
        for (String fileName : fileNames)
        {
            if (fileName == null)
            {
                break;
            }
            i++;
        }

        //creating a new array with the size of the number of files
        String[] fileNamesOUT = new String[i];

        //copying the values from the files array to the new array
        System.arraycopy(fileNames, 0, fileNamesOUT, 0, i);
        return fileNamesOUT;
    }
}
