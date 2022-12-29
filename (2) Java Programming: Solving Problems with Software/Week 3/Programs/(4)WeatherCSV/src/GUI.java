/*
 * CODER : Phantom-fs
 */
//Farhan Sheth
//take care of the location where the data is saved

import javax.swing.*;
import java.awt.*;

import org.apache.commons.csv.*;
import org.jetbrains.annotations.NotNull;

public class GUI
{
    WeatherCSVReading weather = new WeatherCSVReading();

    //File chooser for folder location
    static JFileChooser chooser;

    //Text Pane for printing the output of the methods
    JTextPane textPane = new JTextPane();

    //Scroll Pane for the text pane
    JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JFrame frame = new JFrame("Weather Data - 2012, 2013, 2014, 2015");

    static String yearSTR = "";
    static String monthSTR = "";
    static String combSTR = "";

    static String yearEXAMPLE = "2012";       //variable to check for the files in the folder (CHOOSER), change it according to the need

    static int folderChoosingCount = 0;       //variable to check if the folder is chosen or not and to display the message accordingly
    static int folderReChoosingCount = 0;     //variable to check if the folder is re-chosen or not and to display the message accordingly


    //-------------------------  RADIO BUTTONS DECLARATION  -------------------------//
    //creating objects of JRadioButton class for functions on weather data
    JRadioButton buttonD = new JRadioButton("OVERALL DETAILS");
    JRadioButton buttonMD = new JRadioButton("ALL DETAILS");
    JRadioButton buttonTD = new JRadioButton("DETAILS AT TIME");

    JRadioButton button1 = new JRadioButton("Highest Temperature");
    JRadioButton button2 = new JRadioButton("Lowest Temperature");
    JRadioButton button3 = new JRadioButton("Average Temperature");

    JRadioButton button4 = new JRadioButton("Highest Humidity");
    JRadioButton button5 = new JRadioButton("Lowest Humidity");
    JRadioButton button6 = new JRadioButton("Average Humidity");

    JRadioButton button7 = new JRadioButton("Average Dew Point");
    JRadioButton button8 = new JRadioButton("Average Sea Level Pressure");
    JRadioButton button9 = new JRadioButton("Common Conditions");

    JRadioButton button10 = new JRadioButton("Wind Direction");
    JRadioButton button11 = new JRadioButton("Average Wind Speed");
    JRadioButton button12 = new JRadioButton("Common Wind Dir Degree");


    //-------------------------  CONSTRUCTOR  -------------------------//
    public GUI()
    {
        //-------------------------  BASIC ELEMENTS LAYOUT  -------------------------//
        //FRAME settings
        frame.setSize(710, 415);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LABEL or the "TITLE" in the frame
        JLabel label = new JLabel("Weather Data");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.BLUE);
        label.setBounds(255, 5, 400, 80);
        frame.add(label);

        //LABEL for the "MENU" for the user on the frame
        JLabel info = new JLabel("Choose an option from the list below:");
        info.setFont(new Font("Arial", Font.BOLD, 15));
        info.setForeground(Color.BLACK);
        info.setBounds(215, 40, 400, 80);
        frame.add(info);

        //"EXIT" BUTTON
        JButton exit = new JButton("X");
        exit.setFont(new Font("Arial", Font.BOLD, 15));
        exit.setForeground(Color.RED);
        exit.setBackground(Color.BLACK);
        exit.setBounds(650, 2, 44, 30);
        frame.add(exit);

        //"EXIT" BUTTON ACTION
        exit.addActionListener(e -> System.exit(0));

        //"RESTART" BUTTON
        JButton restart = new JButton("Restart");
        restart.setFont(new Font("Arial", Font.BOLD, 15));
        restart.setForeground(Color.YELLOW);
        restart.setBackground(Color.BLACK);
        restart.setBounds(548, 2, 100, 30);
        frame.add(restart);

        //"RESTART" BUTTON ACTION
        restart.addActionListener(e ->
        {
            frame.dispose();       //disposes the current frame
            new GUI();             //creates a new frame
        });

        //button for "RESELECT THE FOLDER"
        JButton reselect = new JButton("Reselect the folder");
        reselect.setFont(new Font("Arial", Font.BOLD, 10));
        reselect.setForeground(Color.BLUE);
        reselect.setBackground(Color.WHITE);
        reselect.setOpaque(true);
        reselect.setBounds(5, 2, 130, 20);
        frame.add(reselect);

        //-------------------------  OPTION BUTTONS  -------------------------//
        //Date or Year or month Buttons
        JButton date = new JButton("Date");
        JButton month = new JButton("Month");
        JButton year = new JButton("Year");

        //LAYOUT
        date.setBounds(180, 310, 100, 40);  date.setOpaque(true);  date.setBackground(Color.WHITE);
        month.setBounds(295, 310, 100, 40); month.setOpaque(true); month.setBackground(Color.WHITE);
        year.setBounds(410, 310, 100, 40); year.setOpaque(true);  year.setBackground(Color.WHITE);

        frame.add(date);
        frame.add(month);
        frame.add(year);

        //-------------------------  RADIO BUTTONS LAYOUT  -------------------------//
        //setting bounds of Radio buttons
        buttonD.setBounds(40, 100, 150, 40);
        buttonMD.setBounds(270, 100, 150, 40);
        buttonTD.setBounds(500, 100, 150, 40);

        button1.setBounds(40, 150, 200, 40);
        button2.setBounds(270, 150, 200, 40);
        button3.setBounds(500, 150, 200, 40);
        button4.setBounds(40, 180, 200, 40);
        button5.setBounds(270, 180, 200, 40);
        button6.setBounds(500, 180, 200, 40);
        button7.setBounds(40, 210, 200, 40);
        button8.setBounds(270, 210, 200, 40);
        button9.setBounds(500, 210, 200, 40);
        button10.setBounds(40, 240, 200, 40);
        button11.setBounds(270, 240, 200, 40);
        button12.setBounds(500, 240, 200, 40);

        //adding Radio buttons to the frame
        frame.add(buttonD);
        frame.add(buttonMD);
        frame.add(buttonTD);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button10);
        frame.add(button11);
        frame.add(button12);

        //creating a button group for radio buttons, thus only one radio button can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(buttonD);
        group.add(buttonMD);
        group.add(buttonTD);
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        group.add(button5);
        group.add(button6);
        group.add(button7);
        group.add(button8);
        group.add(button9);
        group.add(button10);
        group.add(button11);
        group.add(button12);

        //PANEL  for the RADIO BUTTONS BACKGROUND
        JPanel panel = new JPanel();
        panel.setBounds(30, 100, 640, 185);
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        //-------------------------  RADIO BUTTONS LAYOUT  -------------------------//
        buttonD.setOpaque(true); buttonD.setForeground(Color.WHITE); buttonD.setBackground(Color.DARK_GRAY);
        buttonMD.setOpaque(true); buttonMD.setForeground(Color.WHITE); buttonMD.setBackground(Color.DARK_GRAY);
        buttonTD.setOpaque(true); buttonTD.setForeground(Color.WHITE); buttonTD.setBackground(Color.DARK_GRAY);
        button1.setOpaque(true); button1.setForeground(Color.WHITE); button1.setBackground(Color.DARK_GRAY);
        button2.setOpaque(true); button2.setForeground(Color.WHITE); button2.setBackground(Color.DARK_GRAY);
        button3.setOpaque(false); button3.setForeground(Color.WHITE); button3.setBackground(Color.DARK_GRAY);
        button4.setOpaque(true); button4.setForeground(Color.WHITE); button4.setBackground(Color.DARK_GRAY);
        button5.setOpaque(true); button5.setForeground(Color.WHITE); button5.setBackground(Color.DARK_GRAY);
        button6.setOpaque(false); button6.setForeground(Color.WHITE); button6.setBackground(Color.DARK_GRAY);
        button7.setOpaque(true); button7.setForeground(Color.WHITE); button7.setBackground(Color.DARK_GRAY);
        button8.setOpaque(true); button8.setForeground(Color.WHITE); button8.setBackground(Color.DARK_GRAY);
        button9.setOpaque(false); button9.setForeground(Color.WHITE); button9.setBackground(Color.DARK_GRAY);
        button10.setOpaque(true); button10.setForeground(Color.WHITE); button10.setBackground(Color.DARK_GRAY);
        button11.setOpaque(true); button11.setForeground(Color.WHITE); button11.setBackground(Color.DARK_GRAY);
        button12.setOpaque(false); button12.setForeground(Color.WHITE); button12.setBackground(Color.DARK_GRAY);

        //Setting the PROPERTIES of the text pane for the output
        textPaneProperties(textPane);

        //-------------------- FOLDER CHOOSING --------------------
        //PANEL for the folder chooser
        JOptionPane panelFolder = new JOptionPane();
        panelFolder.setBounds(0, 0, 0, 0);
        folderChoosing(panelFolder);

        //"RESELECT THE FOLDER" BUTTON ACTION LISTENER
        reselect.addActionListener(e -> folderReChoosing(panelFolder));

        //DATE BUTTON (OPTION 1)
        date.addActionListener(e ->
        {
            //OVERALL DETAILS or avg details --------------------
            if(buttonD.isSelected())
            {
                //repetitive part of cde in a method form for code reusability
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    String[] data = weather.avgDetailsBasedOnDate(filePath, dateE);

                    if(data[0] == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        //resetting the text pane, i.e. clearing the text pane
                        textPane.setText("");

                        //printing the details onn text pane
                        weather.printAvgDetails(data, textPane);

                        //setting up scroll pane (scroll pane is used to scroll the text pane)
                        scrollPane.setPreferredSize(new Dimension(400, 400));

                        //adding text pane to scroll pane and adding that to JOPTIONPANE, thus like printing on new window
                        JOptionPane.showMessageDialog(frame, scrollPane, "Weather Data", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }

            //similar steps with different methods
            //ALL DETAILS ------------
            else if(buttonMD.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String filePath = tasks[2];

                    String[] data = weather.allDetailsBasedOnDate(filePath);

                    if(data[0] == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        textPane.setText("");

                        for (int i = 0; i < data.length; i++)
                        {
                            if (data [i] != null)
                            {
                                i = weather.printAllDetails(data, textPane, i);
                            }
                        }

                        scrollPane.setPreferredSize(new Dimension(400, 400));
                        JOptionPane.showMessageDialog(frame, scrollPane, "Weather Data", JOptionPane.PLAIN_MESSAGE);
                    }
                    scrollPane.getVerticalScrollBar().setValue(0);
                }
            }

            //DETAILS BASED ON TIME --------------
            else if(buttonTD.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    //Options for time selection
                    String[] timeOption = {"12:51 AM", "1:51 AM", "2:51 AM", "3:51 AM", "4:51 AM", "5:51 AM", "6:51 AM", "7:51 AM", "8:51 AM", "9:51 AM", "10:51 AM",
                            "11:51 AM", "12:51 PM", "1:51 PM", "2:51 PM", "3:51 PM", "4:51 PM", "5:51 PM", "6:51 PM", "7:51 PM", "8:51 PM", "9:51 PM", "10:51 PM", "11:51 PM"};

                    //adding the options to the input dialog, and a selected option is stored in timme string
                    //thus making a time selection DIALOG BOX
                    String time = (String) JOptionPane.showInputDialog(frame, "Select Time", "Time", JOptionPane.QUESTION_MESSAGE, null, timeOption, timeOption[0]);

                    if (time != null)
                    {
                        String dateE = tasks[0];
                        String filePath = tasks[2];

                        String[] data = weather.detailsBasedOnTime(filePath, dateE, time);

                        if(data[0] == null)
                        {
                            JOptionPane.showMessageDialog(frame, "Data not available for this time", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        else
                        {
                            textPane.setText("");
                            weather.printDetailsBasedOnTime(data, textPane);
                            scrollPane.setPreferredSize(new Dimension(400, 400));
                            JOptionPane.showMessageDialog(frame, scrollPane, "Weather Data", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            }

            //MAX TEMP
            else if (button1.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String filePath = tasks[2];

                    //receiving the CSV Record, thus can easily get other related data from it
                    CSVRecord record = weather.maxTempInFile(filePath);

                    if(record == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String maxTempTime = weather.convertUTCtoTime(record.get("DateUTC"), record);
                        String maxTempUTCDate = record.get("DateUTC");
                        String maxTemp = record.get("TemperatureF");

                        JOptionPane.showMessageDialog(frame, "Highest Temperature was " + maxTemp + " F at " + maxTempTime + " (" + maxTempUTCDate + " UTC)", "Highest Temperature", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //LEAST TEMP
            else if (button2.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String filePath = tasks[2];

                    CSVRecord record = weather.minTempInFile(filePath);

                    if(record == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String minTempTime = weather.convertUTCtoTime(record.get("DateUTC"), record);
                        String minTempUTCDate = record.get("DateUTC");
                        String minTemp = record.get("TemperatureF");

                        JOptionPane.showMessageDialog(frame, "Minimum Temperature was " + minTemp + " F at " + minTempTime + " (" + minTempUTCDate + " UTC)", "Coldest Temperature", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //AVG TEMP
            else if (button3.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    double avgTemp = weather.avgTemperatureInFile(filePath);

                    if(avgTemp == -1)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " average Temperature was " + avgTemp + " F", "Average Temperature", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //MAX HUMIDITY
            else if (button4.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String filePath = tasks[2];

                    CSVRecord record = weather.maxHumidityInFile(filePath);

                    if(record == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String maxHumidityTime = weather.convertUTCtoTime(record.get("DateUTC"), record);
                        String maxHumidityUTCDate = record.get("DateUTC");
                        String maxHumidity = record.get("Humidity");

                        JOptionPane.showMessageDialog(frame, "Highest Humidity was " + maxHumidity + " at " + maxHumidityTime + " (" + maxHumidityUTCDate + " UTC)", "Highest Humidity", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //LEAST HUMIDITY
            else if (button5.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String filePath = tasks[2];

                    CSVRecord record = weather.minHumidityInFile(filePath);

                    if(record == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String minHumidityTime = weather.convertUTCtoTime(record.get("DateUTC"), record);
                        String minHumidityUTCDate = record.get("DateUTC");
                        String minHumidity = record.get("Humidity");

                        JOptionPane.showMessageDialog(frame, "Lowest Humidity was " + minHumidity + " at " + minHumidityTime + " (" + minHumidityUTCDate + " UTC)", "Lowest Humidity", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //AVG HUMIDITY
            else if (button6.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    double avgHumidity = weather.avgHumidityInFile(filePath);

                    if(avgHumidity == -1)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " average Humidity was " + avgHumidity, "Average Humidity", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //AVG DEW POINT
            else if (button7.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    double avgDewPoint = weather.avgDewPointInFile(filePath);

                    if(avgDewPoint == -1)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " average Dew Point was " + avgDewPoint + " F", "Average Dew Point", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //AVG SEA LEVEL PRESSURE
            else if (button8.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    double avgSeaLevelPressure = weather.avgSeaLevelPressureInFile(filePath);

                    if(avgSeaLevelPressure == -1)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " average Sea Level Pressure was " + avgSeaLevelPressure + " in", "Average Sea Level Pressure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //Common Conditions
            else if (button9.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    String commonConditions = weather.mostCommonConditionsInFile(filePath);

                    if(commonConditions == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " most common conditions was " + commonConditions, "Most Common Conditions", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //Wind Direction
            else if (button10.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    String windDirection = weather.windDirectionInFile(filePath);

                    if(windDirection == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "The most common wind direction on " +dateE+ " was " + windDirection, "Most Common Wind Direction", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //AVG WIND SPEED
            else if (button11.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    String avgWindSpeed = weather.avgWindSpeedInFile(filePath);

                    if(avgWindSpeed == null)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " average wind speed was " + avgWindSpeed, "Average Wind Speed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            //AVG WIND DIR DEG
            else if (button12.isSelected())
            {
                String [] tasks = repetitiveDateTask();

                if (tasks != null && tasks.length != 0)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    double avgWindDirDeg = weather.avgWindDirDegreesInFile(filePath);

                    if(avgWindDirDeg == -1)
                    {
                        JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "On " +dateE+ " the most common wind direction was " + avgWindDirDeg, "Average Wind Direction", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        //MONTH BUTTON (OPTION 2)
        month.addActionListener(e -> monYearTasks("month", textPane));

        //YEAR BUTTON (OPTION 3)
        year.addActionListener(e -> monYearTasks("year", textPane));
    }


    //METHODS for MONTH and YEAR BUTTONS as both work on the same functionality but with different number of FILES
    //MONTH max 31 files, YEAR max 366 files
    public void monYearTasks(String mon_year, JTextPane textPane)
    {
        //OVERALL or avg Details
        if(buttonD.isSelected())
        {
            //FILENAMES for MONTH and YEAR, similar to the FILENAME for DATE
            //repetitive tasks for MONTH and YEAR
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                //details for MONTH and YEAR
                String[] data = weather.avgDetailsBasedOnFiles(fileNames, combSTR);

                //clearing the textPane
                textPane.setText("");

                //printing the details in the textPane
                weather.printAvgDetails(data, textPane);

                //adding the pane to scroll
                scrollPane.setPreferredSize(new Dimension(400, 400));

                //adding the scroll to the DIALOG BOX
                JOptionPane.showMessageDialog(frame, scrollPane, "Weather Data", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        //similar functionality for other methods
        //ALL DETAILS
        else if(buttonMD.isSelected())
        {
            JOptionPane.showMessageDialog(frame, "As a huge amount of data is processed, it may take a while to display", "Alert", JOptionPane.WARNING_MESSAGE);

            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                textPane.setText("");
                weather.allDetailsBasedOnFiles(fileNames, textPane);
                scrollPane.setPreferredSize(new Dimension(400, 400));
                JOptionPane.showMessageDialog(frame, scrollPane, "Weather Data", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Details at time
        else if(buttonTD.isSelected())
        {
            String [] tasks = repetitiveDateTask();

            if (tasks != null && tasks.length != 0)
            {
                String[] timeOption = {"12:51 AM", "1:51 AM", "2:51 AM", "3:51 AM", "4:51 AM", "5:51 AM", "6:51 AM", "7:51 AM", "8:51 AM", "9:51 AM", "10:51 AM",
                        "11:51 AM", "12:51 PM", "1:51 PM", "2:51 PM", "3:51 PM", "4:51 PM", "5:51 PM", "6:51 PM", "7:51 PM", "8:51 PM", "9:51 PM", "10:51 PM", "11:51 PM"};

                String time = (String) JOptionPane.showInputDialog(frame, "Select Time", "Time", JOptionPane.QUESTION_MESSAGE, null, timeOption, timeOption[0]);

                if (time != null)
                {
                    String dateE = tasks[0];
                    String filePath = tasks[2];

                    String[] data = weather.detailsBasedOnTime(filePath, dateE, time);

                    if(data[0] == null)
                    {
                        JOptionPane.showMessageDialog(frame, "Data not available for this time", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    else
                    {
                        textPane.setText("");
                        weather.printDetailsBasedOnTime(data, textPane);
                        scrollPane.setPreferredSize(new Dimension(400, 400));
                        JOptionPane.showMessageDialog(frame, scrollPane, "Weather Data", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        }

        //MAX TEMP
        else if (button1.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                CSVRecord record = weather.maxTemp(fileNames);

                if(record == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String maxTempDate = weather.convertUTCtoEST_EDT(record.get("DateUTC"), record);
                    String maxTempUTCDate = record.get("DateUTC");
                    String maxTemp = record.get("TemperatureF");

                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Maximum temperature was " + maxTemp + " F on " + maxTempDate +" (" + maxTempUTCDate + " UTC)", "Maximum Temperature", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //MIN TEMP
        else if (button2.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                CSVRecord record = weather.minTemp(fileNames);

                if(record == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String minTempDate = weather.convertUTCtoEST_EDT(record.get("DateUTC"), record);
                    String minTempUTCDate = record.get("DateUTC");
                    String minTemp = record.get("TemperatureF");

                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Minimum temperature was " + minTemp + " F on " + minTempDate + " (" + minTempUTCDate + " UTC)", "Minimum Temperature", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //AVG TEMP
        else if (button3.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                double avgTemp = weather.avgTemp(fileNames);

                if(avgTemp == 0 || avgTemp == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Average temperature was " + avgTemp + " F", "Average Temperature", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //MAX HUMIDITY
        else if (button4.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                CSVRecord record = weather.maxHumidity(fileNames);

                if(record == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String maxHumidityDate = weather.convertUTCtoEST_EDT(record.get("DateUTC"), record);
                    String maxHumidityUTCDate = record.get("DateUTC");
                    String maxHumidity = record.get("Humidity");

                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Maximum humidity was " + maxHumidity + " % on " + maxHumidityDate + " (" + maxHumidityUTCDate + " UTC)", "Maximum Humidity", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //MIN HUMIDITY
        else if (button5.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                CSVRecord record = weather.minHumidity(fileNames);

                if(record == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String minHumidityDate = weather.convertUTCtoEST_EDT(record.get("DateUTC"), record);
                    String minHumidityUTCDate = record.get("DateUTC");
                    String minHumidity = record.get("Humidity");

                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Minimum humidity was " + minHumidity + " % on " + minHumidityDate + " (" + minHumidityUTCDate + " UTC)", "Minimum Humidity", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //AVG HUMIDITY
        else if (button6.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                double avgHumidity = weather.avgHumidity(fileNames);

                if(avgHumidity == 0 || avgHumidity == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Average humidity was " + avgHumidity + " %", "Average Humidity", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //AVG DEW POINT
        else if (button7.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                double avgDewPoint = weather.avgDewPoint(fileNames);

                if(avgDewPoint == 0 || avgDewPoint == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Average dew point was " + avgDewPoint + " F", "Average Dew Point", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //AVG SEA LEVEL PRESSURE
        else if (button8.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                double avgSeaLevelPressure = weather.avgSeaLevelPressure(fileNames);

                if(avgSeaLevelPressure == 0 || avgSeaLevelPressure == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " Average sea level pressure was " + avgSeaLevelPressure + " inHg", "Average Sea Level Pressure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //Common Conditions
        else if (button9.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                String commonConditions = weather.mostCommonConditions(fileNames);

                if(commonConditions == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " most common conditions were " + commonConditions, "Common Conditions", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //Wind Direction
        else if (button10.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                String windDirection = weather.windDirection(fileNames);

                if(windDirection == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " most common wind direction was " + windDirection, "Wind Direction", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //AVG WIND SPEED
        else if (button11.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                String avgWindSpeed = weather.avgWindSpeed(fileNames);

                if(avgWindSpeed == null)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " average wind speed was " + avgWindSpeed, "Average Wind Speed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        //AVG WIND DIR DEG
        else if (button12.isSelected())
        {
            String [] fileNames = repetitiveMonYearTask(mon_year);

            if (fileNames != null && fileNames.length != 0)
            {
                double avgWindDirDeg = weather.avgWindDirDegrees(fileNames);

                if(avgWindDirDeg == 0 || avgWindDirDeg == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Data not available", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "In " +combSTR+ " average wind direction was " + avgWindDirDeg + " degrees", "Average Wind Direction", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    //This method is used to get the file names for the selected month and year
    public String[] repetitiveMonYearTask (@NotNull String mon_year)
    {
        //OPTION menu for year available
        String[] yearOption = {"2012", "2013", "2014", "2015"};

        //OPTION menu for month available
        String[] monthOption = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        //allowing users to select a year, by proving a list of options, and the selected date is stored in year string
        String year = (String) JOptionPane.showInputDialog(frame, "Enter a year", "Year", JOptionPane.QUESTION_MESSAGE, null, yearOption, yearOption[0]);

        if(mon_year.equals("year") && year != null)
        {
            //storing info in static variable
            yearSTR = year;
            combSTR = yearSTR;

            //getting file names for the selected year
            return WeatherCSVReading.fileNames(year);
        }

        else if (mon_year.equals("month"))
        {
            //allowing users to select a month, by proving a list of options, and the selected date is stored in month string
            String month = (String) JOptionPane.showInputDialog(frame, "Enter a month", "Month", JOptionPane.QUESTION_MESSAGE, null, monthOption, monthOption[0]);

            if (month != null && year != null)
            {
                //storing info in static variable
                yearSTR = year;
                monthSTR = month;
                combSTR = month + " " + year;

                //getting file names for the selected month and year
                return WeatherCSVReading.fileNamesM(yearSTR, monthSTR);
            }
        }
        return null;
    }

    //This method is used to get the file names for the selected date
    public String[] repetitiveDateTask()
    {
        //INPUT dialog for date
        String dateE = JOptionPane.showInputDialog("Enter Date in (YYYY-MM-DD) format");

        //get th year from the date, and verify if it's within the range
        String yearE = weather.getYear(dateE);

        if(dateE != null && yearE == null)
        {
            JOptionPane.showMessageDialog(frame, "UNAVAILABLE", "Alert", JOptionPane.ERROR_MESSAGE);
        }

        else if (dateE != null)
        {
            //returning a string of array of date, year and filename
            return new String[]{dateE, yearE, WeatherCSVReading.fileName(yearE, dateE)};
        }

        return null;
    }

    //METHOD to apply properties to the text pane
    public static void textPaneProperties (@NotNull JTextPane textPane)
    {
        textPane.setEditable(false);
        textPane.setBackground(Color.DARK_GRAY);
        textPane.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    //METHOD to get the folder, where all the files are stored
    public static void folderChoosing (JOptionPane frame)
    {
        //displaying the message only once
        if(folderChoosingCount++ == 0)
            folderChoosingMessage(frame);

        //creating a file chooser, to get the folder
        chooser = new JFileChooser();

        //setting the current directory to the user's home directory
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose the DIRECTORY, containing FOLDERS like 2012, 2013,.....");

        //allowing only directories to be selected, no single file
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //disabling the "All files" option
        chooser.setAcceptAllFileFilterUsed(false);

        //isDirectoryOfCSVFiles check whether the folder selected is the ones containing the files, i.e. required
        if (!(chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION && WeatherCSVReading.isDirectoryOfCSVFiles(chooser.getSelectedFile(), yearEXAMPLE)))
        {
            //if re choosing to select the folder, the folder selected was wrong, then recalling the method i.e. recursion
            if(folderChoosingReOption() == JOptionPane.YES_OPTION)
            {
                folderChoosing(frame);
            }

            //if the user doesn't want to select the folder again, then exiting the program
            else
            {
                System.exit(0);
            }
        }
    }

    //OPTIONS for choosing the folder
    public static int folderChoosingReOption ()
    {
        String[] options = new String[] {"Yes", "Exit"};
        return JOptionPane.showOptionDialog(null, "NOT FOUND, Would you like to reselect the Directory?", "Alert",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    //METHOD for button to reselect the folder
    public static void folderReChoosing (JOptionPane frame)
    {
        if(folderReChoosingCount++ < 1)
            folderChoosingMessage(frame);

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose the DIRECTORY");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //disabling the "All files" option
        chooser.setAcceptAllFileFilterUsed(false);

        //OPTIONS
        String[] options = new String[] {"Yes", "Back", "Exit"};

        if(!(chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION && WeatherCSVReading.isDirectoryOfCSVFiles(chooser.getSelectedFile(), yearEXAMPLE)))
        {
            int reOption = JOptionPane.showOptionDialog(null, "NOT FOUND, Would you like to reselect the Directory?", "Alert",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            //if re selecting the folder
            if (reOption == 0)
            {
                folderChoosing(frame);
            }

            //if an exit option is select
            else if (reOption == 2)
            {
                System.exit(0);
            }

            //if BACK, then automatically return, and the same folder stays selected
        }
    }

    //message for the user to select the folder
    public static void folderChoosingMessage (JOptionPane frame)
    {
        JOptionPane.showMessageDialog(frame, "Please select the DIRECTORY containing the YEARS, eg. 2012, 2013,... with each folder containing the .CSV FILES"
                , "Folder Selection", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args)
    {
        //creating an object of the class
        //Constructor calling
        new GUI();
    }
}
