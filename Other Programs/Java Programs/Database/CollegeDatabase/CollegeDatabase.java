//TAKE CARE OF THE package NAME, make sure all files when downloading and running are present in a single package
//AND UPDATE THE PACKAGE NAME of all three files, if SAVING IT IN SUB-PACKAGE
/*
 * The program is made for Professors Details as well as Students Details and Marks
 * program have muliple fail safes and all methods and functions related to professors are present in professor Details.java
 * and student details are present in StudentDetails.java
*/ 
//Coder: Phantom-fs


package CollegeDatabase;

import java.util.*;
import java.io.FilterInputStream;       //for I/O as after closing scanner in one method, System.in closes, to prevent that
import java.lang.Character;             //conversion of UpperCase character to LowerCase


public class CollegeDatabase
{
    public static void main (String[] args) throws Exception
    {
        String newline = System.lineSeparator();

        /*
         * scanner for input, but slightly different, this a special method to prevent closing of System.in
         * as on closing scanner, System.in closes, and here we are using and closing scanner in different methods in professor
         *  and student class, thus to prevent that, we are using this special method
        */
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        char mainChoice;        //main menu choice
        int countProf = 0;      //count of professors 
        int countStudent = 0;   //count of students


        //defining the variables here to increase their scope, so that upon returning back to database, they are not reset

        char profChoice;     //choice for professor database
        int k = 0;           //index for inclusion of professor details

        char choice;
        int i = 0;       //index for the inclusion of student details
        int j = 0;       //index for the inclusion of student marks

        CollegeDatabase obj = new CollegeDatabase();

        ProfessorDetails profObj = new ProfessorDetails();    //creating object and initializing it with default type constructor

        StudentDetails studentObj = new StudentDetails();     //creating object and initializing it with default type constructor

        System.out.print(newline + "***************************************************************************" + newline);

        System.out.print(newline+ "              Welcome to the College Database Management System             " + newline);

        System.out.print(newline + "***************************************************************************" + newline);

        Thread.sleep(1500);     //to pause the program for 1.5 seconds

        //COLLEGE DATABASE MANAGEMENT SYSTEM
        do
        {
            System.out.print(newline+ "Please select which database you wish to access" + newline);

            System.out.print(newline+"---------------------------------   MENU   -------------------------------");
            System.out.print(newline+"|       (a) PROFESSOR DATABASE        |       (b) STUDENT DATABASE       |");
            System.out.print(newline+"--------------------------------------------------------------------------");
            System.out.print(newline+"|                                 (x) Exit                               |");
            System.out.print(newline+"--------------------------------------------------------------------------");

            System.out.print(newline+newline+"Enter your choice: ");
            mainChoice = scanner.next().charAt(0);
            mainChoice = Character.toLowerCase(mainChoice);      //converting to lower case, if user enters in upper case

            /*
             *------------------------------------------------------------------------------------------------------------------------------
             *--------------------------------------------------- PROFESSOR DATABASE -------------------------------------------------------
             *------------------------------------------------------------------------------------------------------------------------------
            */
            if (mainChoice == 'a')
            {      
                //Clearing the screen
                obj.clearScreen();

                if(countProf == 0)
                    profObj.arrValueNullProvider();     //initializing array values to null for first time only             

                System.out.print(newline + "*********************************************************************************" + newline);
 
                System.out.print(newline+ "                         Professor Database Management System                     " + newline);

                System.out.print(newline + "*********************************************************************************" + newline);

                Thread.sleep(2000);     //delay of 2 seconds

                //menu, using do while loop as want to print this menu at least once
                do
                {
                    //if professor details are already entered previously, then this menu will not be displayed
                    if(countProf == 0)
                    {
                        System.out.print(newline+"Enter the correct input from menu"+newline);

                        System.out.print(newline+"----------------------------------   MENU   -----------------------------------");
                        System.out.print(newline+"|       (a) Add Professor Details      |       (b) View Professor Details      |");
                        System.out.print(newline+"-------------------------------------------------------------------------------");
                        System.out.print(newline+"|                                  (x) Exit                                    |");
                        System.out.print(newline+"-------------------------------------------------------------------------------");
        
                        System.out.print(newline+newline+"Enter your choice : ");
                        profChoice = scanner.next().charAt(0);         //taking char at first index, i.e. first character of the input string
                        profChoice = Character.toLowerCase(profChoice);       //converting to lower case

                        profObj.line();

                        if (profChoice == 'b')
                        {
                            System.out.println(newline+"No professor details found, Please add professor details"+newline);
                            profObj.line();
                        }

                        else if (profChoice == 'a')
                        {
                            profObj.addProfessorDetails(k);  
                            profObj.line();

                            k++;           //increment index for the inclusion of professor details

                            profObj.line();
                        }

                        else if (profChoice != 'x' && profChoice != 'a' && profChoice != 'b')
                        {
                            System.out.println(newline+"Invalid input, Please enter a valid input"+newline);
                            profObj.line();
                        }

                        countProf++;      //incrementing countProf, so that it does not print the menu again
                    }

                    else
                        profChoice = 'a';          //if professor details are already added previously, then directly go to add professor details

                    Thread.sleep(1500);     //to pause the program for 1.5 seconds

                    /*
                     * the secondary master do-while loop will run continuously.
                     * the following table (if command) will only be entered, if the user has entered a single professor detail, until then
                     * the secondary master do-while loop (professor database loop) will run continuously or exit if the user enters 'x'
                    */
                    if (profChoice == 'a')
                    {
                        //internal do-while loop, run atleast once, until exit is entered  
                        do
                        {
                            obj.clearScreen();

                            System.out.print(newline+"Enter the correct input from menu"+newline);

                            System.out.print(newline+"-----------------------------------   MENU   ------------------------------------");
                            System.out.print(newline+"|       (a) Add Professor Details       |       (b) View Professor Detail        |");
                            System.out.print(newline+"---------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (c) Update Professor Details    |       (d) Delete Professor Details     |");
                            System.out.print(newline+"---------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (e) View All Professors         |       (f) View All Professor's Details |");
                            System.out.print(newline+"---------------------------------------------------------------------------------");
                            System.out.print(newline+"|                                  (x) Exit                                      |");
                            System.out.print(newline+"---------------------------------------------------------------------------------");
                    

                            System.out.print(newline+newline+"Enter your choice : ");
                            profChoice = scanner.next().charAt(0);
                            profChoice = Character.toLowerCase(profChoice);

                            //Clearing the screen
                            obj.clearScreen();

                            switch(profChoice)    //all methods are invoked based on the menu driven options
                            {  
                                case 'a':
                                profObj.addProfessorDetails(k);
                                ++k;
                                break;

                            case 'b':
                                obj.clearScreen();

                                //view a professor based on the professor id
                                profObj.viewProfessorDetails();
                                Thread.sleep(3000);
                                break;

                            case 'c':
                                obj.clearScreen();

                                //update a professor based on the professor id
                                profObj.updateProfessorDetails();
                                Thread.sleep(2000);
                                break;

                            case 'd':
                                obj.clearScreen();

                                profObj.deleteProfessorDetails(); 

                                //boolean method to check whether deletion is successful or not                           
                                if (profObj.taskDeleteDetailsANS() == true)
                                {
                                    --k;
                                }
                                else
                                    continue;
                                Thread.sleep(2000);
                                break;

                            case 'e':
                                obj.clearScreen();

                                //display all professors but their names only
                                profObj.displayAllProfessorWithoutIndex();
                                Thread.sleep(3000);
                                break;

                            case 'f':
                                obj.clearScreen();

                                //display all professors and all their details
                                profObj.displayAllProfessorDetails();
                                Thread.sleep(3500);
                                break;

                            case 'x':
                                break;

                            default:
                                System.out.println(newline+"Invalid input, Please enter a valid input"+newline);
                                break;                
                            }

                            profObj.line();

                            Thread.sleep(3000);     //to pause the program for 3 seconds
                        }
                        while (profChoice != 'x');     //if choice is x, then exit the program

                    }
                }
                while (profChoice != 'x');

            } //end of Professor Database System

            /*
             *------------------------------------------------------------------------------------------------------------------------------
             *---------------------------------------------------- STUDENT DATABASE --------------------------------------------------------
             *------------------------------------------------------------------------------------------------------------------------------
            */
            else if (mainChoice == 'b')
            {
                //Clearing the screen
                obj.clearScreen();

                if(countStudent == 0)
                    studentObj.arrValueNullProvider();                       //initializing name and marks array values to null for first time only

                System.out.print(newline + "*********************************************************************************" + newline);
 
                System.out.print(newline+ "                          Student Database Management System                      " + newline);

                System.out.print(newline + "*********************************************************************************" + newline);

                Thread.sleep(2000);     //delay of 2 seconds

                //menu, using do while loop as want to print this menu at least once
                do
                {
                    //if professor details are already entered previously, then this menu will not be displayed
                    if(countStudent == 0)
                    {
                        System.out.print(newline+"Enter the correct input from menu"+newline);

                        System.out.print(newline+"----------------------------------   MENU   -----------------------------------");
                        System.out.print(newline+"|       (a) Add Student Details        |       (b) View Student Details        |");
                        System.out.print(newline+"-------------------------------------------------------------------------------");
                        System.out.print(newline+"|                                  (x) Exit                                    |");
                        System.out.print(newline+"-------------------------------------------------------------------------------");
        
                        System.out.print(newline+newline+"Enter your choice : ");
                        choice = scanner.next().charAt(0);      //taking char at first index, i.e. first character of the input string
                        choice = Character.toLowerCase(choice);       //converting to lower case

                        studentObj.line();

                        if (choice == 'b')
                        {
                            System.out.println(newline+"No student details found, Please add student details"+newline);
                            studentObj.line();
                        }   

                        else if (choice == 'a')
                        {   
                            studentObj.addStudentDetails(i);  
                            studentObj.line();

                            System.out.print(newline+"Would you like to add " +studentObj.StudentName(i)+ "'s marks? (y/n) : ");
                            Character yORn = scanner.next().charAt(0);
                            yORn = Character.toLowerCase(yORn);

                            if (yORn == 'y')
                            {
                                studentObj.line();
                                studentObj.addStudentMarks(j); 
                                ++j;       //increment index for the inclusion of student marks            
                            }

                            ++i;           //increment index for the inclusion of student details

                            studentObj.line();
                        }

                        else if (choice != 'x' && choice != 'a' && choice != 'b')
                        {
                            System.out.println(newline+"Invalid input, Please enter a valid input"+newline);
                            studentObj.line();
                        }

                        countStudent++;        //incrementing countStudent, so that it does not print the menu again
                    }

                    else
                        choice = 'a';   

                    Thread.sleep(1500);     //to pause the program for 1.5 seconds

                    /*
                     * the secondary master do-while loop will run continuously.
                     * the following table (if command) will only be entered into if the user has entered a single student detail
                     * until then the master do-while loop will run continuously or exit if the user enters 'x'
                    */
                    if (choice == 'a')
                    {
                        do
                        {
                            obj.clearScreen();

                            System.out.print(newline+"Enter the correct input from menu"+newline);

                            System.out.print(newline+"----------------------------------   MENU   -----------------------------------");
                            System.out.print(newline+"|       (a) Add Student Details        |       (b) View Students Detail        |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (c) Update Student Details     |       (d) Delete Student Details      |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (e) Add Student Marks          |       (f) View Students Marks         |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (g) Update Student Marks       |       (h) Delete Student Marks        |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (i) View All Students          |       (j) View All Student Details    |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                            System.out.print(newline+"|       (k) View All Students Marks    |       (l) All Student Details (D&M)   |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                            System.out.print(newline+"|                                  (x) Exit                                    |");
                            System.out.print(newline+"-------------------------------------------------------------------------------");
                    

                            System.out.print(newline+newline+"Enter your choice : ");
                            choice = scanner.next().charAt(0);
                            choice = Character.toLowerCase(choice);

                            switch(choice)    //all methods are invoked based on the menu driven options
                            {  
                                case 'a':
                                studentObj.addStudentDetails(i);
                                ++i;
                                break;

                            case 'b':
                                obj.clearScreen();

                                //view a student based on student index
                                studentObj.viewStudentDetails();
                                Thread.sleep(3000);
                                break;

                            case 'c':
                                obj.clearScreen();

                                //update a student based on student index
                                studentObj.updateStudentDetails();
                                Thread.sleep(2000);
                                break;

                            case 'd':
                                obj.clearScreen();

                                //delete a student based on student index
                                studentObj.deleteStudentDetails();

                                //decrement index if a student has been deleted based on output of boolean method                      
                                if (studentObj.taskDeleteDetailsANS() == true)
                                {
                                    --i;
                                }
                                else
                                    continue;

                                Thread.sleep(2000);
                                break;

                            case 'e':
                                obj.clearScreen();

                                //add marks of student based on student index from menu
                                studentObj.addStudentMarksOption();
                                Thread.sleep(2000);
                                break;

                            case 'f':
                                obj.clearScreen();

                                //view marks based on student index
                                studentObj.viewStudentMarks();
                                Thread.sleep(3000);
                                break;

                            case 'g':
                                obj.clearScreen();

                                //update marks based on student index
                                studentObj.updateStudentMarks();
                                Thread.sleep(2000);
                                break;

                            case 'h':
                                obj.clearScreen();

                                //delete marks based on student index
                                studentObj.deleteStudentMarks();
                                Thread.sleep(2000);
                                break;

                            case 'i':
                                //display all students name only
                                studentObj.displayAllStudentWithoutIndex();
                                Thread.sleep(3000);
                                break;

                            case 'j':
                                obj.clearScreen();

                                //display all students details
                                studentObj.displayAllStudentDetails();
                                Thread.sleep(3500);
                                break;

                            case 'k':
                                obj.clearScreen();

                                //display all students marks
                                studentObj.displayAllStudentMarks();
                                Thread.sleep(5500);
                                break;

                            case 'l':
                                obj.clearScreen();

                                //display all students and all their details and marks
                                studentObj.displayAllStudentAllDetails();
                                Thread.sleep(5500);
                                break;

                            case 'x':
                                break;

                            default:
                                System.out.println(newline+"Invalid input, Please enter a valid input"+newline);
                                break;                
                            }

                            studentObj.line();

                            Thread.sleep(2000);     //to pause the program for 3 seconds
                        }
                        while (choice != 'x');     //if choice is x, then exit the program

                    }
                }
                while (choice != 'x');

            }  //end of Student Database System 

            else if (mainChoice != 'a' && mainChoice != 'b' && mainChoice != 'x')
            {
                System.out.print(newline+"Invalid input, Please enter a valid input"+newline);
                obj.line();
            }

            else if (mainChoice == 'x')
            {
                //clearing the screen
                obj.clearScreen();

                obj.line();
                System.out.print(newline+"------------------------------------- Exiting -----------------------------------"+newline);
                obj.line();
            }

            else
            {
                obj.line();
                System.out.print(newline+"Invalid input, Please enter a valid input"+newline);
                obj.line();
            }

            //if a database has been assessed, then print two lines
            if (mainChoice == 'a' || mainChoice == 'b')
            {
                obj.line();
                obj.clearScreen();
            }

            Thread.sleep(1500);     //to pause the program for 2 seconds
        }  
        while (mainChoice != 'x');    //exit upon x input in master do-while loop         

        System.out.print(newline+newline+"Thank you for using the application...");

        System.out.print(newline+newline);

        Thread.sleep(1500);     //to pause the program for 1.5 seconds

        scanner.close();
    }//end of main method

    //abstract method to print line, result in better looking code and reusable code, defined in extraMethods
    public void line ()
    {
        String newline = System.lineSeparator();
        System.out.print(newline+"*********************************************************************************"+newline);
    }

    //method to clear or flush the screen
    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //to lowercase conversion method
    public static char toLowerCase(char ch)
    {
        if (ch >= 'A' && ch <= 'Z')
        {
            ch = (char)(ch + 'a' - 'A');
        }
        return ch;
    }
}
