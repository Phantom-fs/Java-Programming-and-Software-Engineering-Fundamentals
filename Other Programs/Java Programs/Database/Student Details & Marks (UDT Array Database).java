//CHANGE THE JAVA FILE NAME TO StudentsDetails.java AS MAIN CLASS NAME IS StudentsDetails
/*
 * The program is made for Student Details and Marks
 * program have muliple fail safes and all methods and functions are present in the second class named StudentDetail
*/ 
//Coder: Phantom-fs


import java.util.*;
import java.io.FilterInputStream;       //for I/O as after closing scanner in one method, System.in closes, to prevent that
import java.lang.Character;             //conversion of UpperCase character to LowerCase

public class StudentsDetails
{
    public static void main (String[] args) throws Exception
    {
        String newline = System.lineSeparator();

        /*
         * scanner for input, but slightly different, this a special method to prevent closing of System.in
         * as on closing scanner, System.in closes, and here we are using and closing scanner in different methods in StudentDetails class
         * so to prevent that, we are using this special method
        */
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        char choice;
        int i = 0;       //index for the inclusion of student details
        int j = 0;       //index for the inclusion of student marks

        StudentDetail defaultObj = new StudentDetail();      //creating object and initializing it with default type constructor

        defaultObj.arrValueNullProvider();                   //initializing the name and marks array with null values

        System.out.print(newline + "***************************************************************************" + newline);

        System.out.print(newline+"Enter the correct input from menu"+newline);

        //menu, using do while loop as want to print this menu at least once
        do
        {
            System.out.print(newline+"----------------------------------   MENU   -----------------------------------");
            System.out.print(newline+"|       (a) Add Student Details        |       (b) View Student Details        |");
            System.out.print(newline+"------------------------------------------------------------------------------");
            System.out.print(newline+"|                                  (x) Exit                                    |");
            System.out.print(newline+"-------------------------------------------------------------------------------");
        
            System.out.print(newline+newline+"Enter your choice : ");
            choice = scanner.next().charAt(0);      //taking char at first index, i.e. first character of the input string
            choice = Character.toLowerCase(choice);       //converting to lower case

            defaultObj.line();

            if (choice == 'b')
            {
                System.out.println(newline+"No student details found, Please add student details"+newline);
                defaultObj.line();
            }

            else if (choice == 'a')
            {
                defaultObj.addStudentDetails(i);  
                defaultObj.line();

                System.out.print(newline+"Would you like to add " +defaultObj.StudentName(i)+ "'s marks? (y/n) : ");
                Character yORn = scanner.next().charAt(0);
                yORn = Character.toLowerCase(yORn);

                if (yORn == 'y')
                {
                    defaultObj.line();
                    defaultObj.addStudentMarks(j); 
                    ++j;       //increment index for the inclusion of student marks            
                }

                ++i;           //increment index for the inclusion of student details

                defaultObj.line();
            }

            else if (choice != 'x' && choice != 'a' && choice != 'b')
            {
                System.out.println(newline+"Invalid input, Please enter a valid input"+newline);
                defaultObj.line();
            }

            Thread.sleep(1500);     //to pause the program for 1.5 seconds

            /*
             * the master do-while loop will run continuously.
             * the following table (if command) will only be entered into if the user has entered a single student detail
             * until then the master do-while loop will run continuously or exit if the user enters 'x'
            */
            if (choice == 'a')
            {
                do
                {
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
                            defaultObj.addStudentDetails(i);
                            ++i;
                            break;

                        case 'b':
                            defaultObj.viewStudentDetails();
                            Thread.sleep(3000);
                            break;

                        case 'c':
                            defaultObj.updateStudentDetails();
                            Thread.sleep(2000);
                            break;

                        case 'd':
                            defaultObj.deleteStudentDetails();                            
                            if (defaultObj.taskDeleteDetailsANS() == true)
                            {
                                --i;
                            }
                            else
                                continue;
                            Thread.sleep(2000);
                            break;

                        case 'e':
                            defaultObj.addStudentMarksOption();
                            Thread.sleep(2000);
                            break;

                        case 'f':
                            defaultObj.viewStudentMarks();
                            Thread.sleep(3000);
                            break;

                        case 'g':
                            defaultObj.updateStudentMarks();
                            Thread.sleep(2000);
                            break;

                        case 'h':
                            defaultObj.deleteStudentMarks();
                            Thread.sleep(2000);
                            break;

                        case 'i':
                            defaultObj.displayAllStudentWithoutIndex();
                            Thread.sleep(3000);
                            break;

                        case 'j':
                            defaultObj.displayAllStudentDetails();
                            Thread.sleep(3500);
                            break;

                        case 'k':
                            defaultObj.displayAllStudentMarks();
                            Thread.sleep(5500);
                            break;

                        case 'l':
                            defaultObj.displayAllStudentAllDetails();
                            Thread.sleep(5500);
                            break;

                        case 'x':
                            break;

                        default:
                            System.out.println(newline+"Invalid input, Please enter a valid input"+newline);
                            break;                
                    }

                    defaultObj.line();

                    Thread.sleep(3000);     //to pause the program for 3 seconds
            }
            while (choice != 'x');     //if choice is x, then exit the program

            }
        }
        while (choice != 'x');       

        System.out.print(newline+"********************   Thank you for using the application   ********************"+newline);

        System.out.print(newline+"*********************************************************************************"+newline+newline);

        Thread.sleep(1500);     //to pause the program for 1.5 seconds

        scanner.close();
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


//Class StudentDetail, containing all methods, data of student details and marks
/*
 * using extends here and making StudentDetail a child class of StudentDetails i.e. main class
 * though no use here as in single file, but we can make another class or java file of StudentDetail(the child class)
 *  and import it in the main class i.e. StudentDetails
*/
class StudentDetail extends StudentsDetails
{
    //private for internal use only, i.e. not accessible outside the class

    private String name;
    private int age;
    private static String branch = "CSE";       //static variable, common for all students
    private static String college = "MUJ";      //static variable, common for all students
    private String dob;
    private String registrationNo;

    private float cse;
    private float dsa;
    private float oop;
    private float dbms;
    private float csa; 

    private float totalMarks;
    private float percentage;

    private int choice;

    public boolean updateDetailsANS;

    public StudentDetail[] arrDetails = new StudentDetail[1000];     //Array of objects for student details, with max entries of 1000
    public StudentDetail[] arrMarks = new StudentDetail[1000];       //Array of objects for student marks, with max entries of 1000

    static Scanner scanner;

    //default constructor
    public StudentDetail () {}

    //constructor for student details, has been used internally in class
    public StudentDetail (String name, int age, String dob, String registrationNo)
    {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.registrationNo = registrationNo;
    }

    //constructor fro marks, has been used internally in class
    public StudentDetail (float cse, float dsa, float oop, float dbms, float csa)
    {
        this.cse = cse;
        this.dsa = dsa;
        this.oop = oop;
        this.dbms = dbms;
        this.csa = csa;

        this.totalMarks = this.cse + this.dsa + this.oop + this.dbms + this.csa;
        this.percentage = (this.totalMarks/500)*100;
    }

    //method to return student's name, based on index provided
    public String StudentName(int i)
    {
        return arrDetails[i].name;
    }

    public void addStudentDetails (int i)
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        System.out.print(newline+"Enter the student details : "+newline+newline);
        System.out.print("Enter the student name : ");
        String name = scanner.nextLine();
        System.out.print("Enter the student Registration Number : ");
        String registrationNo = scanner.nextLine();
        System.out.print("Enter the student age : ");
        int age = scanner.nextInt();
        System.out.print("Enter the student Date of Birth : ");
        String dob = scanner.next();

        System.out.println(newline+"#Student details added successfully#"+newline);

        //adding the input taken from user to the array of objects, using constructor
        arrDetails[i] = new StudentDetail (name, age, dob, registrationNo);

        scanner.close();
    }

    public void addStudentMarks (int i)
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        this.cse = 0;
        this.dsa = 0;
        this.oop = 0;
        this.dbms = 0;
        this.csa = 0;

        this.totalMarks = 0;
        this.percentage = 0;

        System.out.print(newline+"Enter the student marks : "+newline+newline);
        System.out.print("Enter the student CSE marks : ");
        float cse = scanner.nextFloat();
        System.out.print("Enter the student DSA marks : ");
        float dsa = scanner.nextFloat();
        System.out.print("Enter the student OOP marks : ");
        float oop = scanner.nextFloat();
        System.out.print("Enter the student DBMS marks : ");
        float dbms = scanner.nextFloat();
        System.out.print("Enter the student CSA marks : ");
        float csa = scanner.nextFloat();

        System.out.println(newline+"#Student marks added successfully#"+newline);

        //adding the input taken from user to the array of objects, using constructor
        arrMarks[i] = new StudentDetail (cse, dsa, oop, dbms, csa);
        //marks total and percentage are calculated in constructor and automatically added to the array of Marks

        scanner.close();
    }

    //Print all the student names, and based on user input, that student marks are added
    //uses addStudentMarks and displayAllStudents method
    public void addStudentMarksOption()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        displayAllStudent();

        System.out.print(newline+"Enter the number of student to add marks : ");
        int i = scanner.nextInt();

        //count student method, so that only the student available in the array can be selected
        if (i >= 0 && i <= countStudent())
        {
            //check whether the student exists
            /*
             * This method provides the fail safe for all marks methods
             * as the marks will not be added unless the student exist
             * thus we don't have to write a if statement to check whether the student exist or not
             * i.e. if (arrDetails[] != null)
             */
            if (arrDetails[i] != null)
            {
                addStudentMarks(i);
            }
            else
            {
                System.out.println(newline+"#Student not found, please enter a valid student number#"+newline);
            }
        }

        else
        {
            System.out.println(newline+"#Student with current index don't exist, please enter a valid student number#"+newline);
        }
    }

    //Displaying the detail of a particular student based on index provided
    public void displayDetails (int i)
    {
        String newline = System.lineSeparator();

        System.out.print(newline+"Student Details : "+newline+newline);

        //displaying the details of the student, if the index is not null, i.e. as deletion of a detail make that index's value null
        if (arrDetails[i] != null)
        {
            System.out.println("Name : "+arrDetails[i].name);
            System.out.println("Registration Number : "+arrDetails[i].registrationNo);
            System.out.println("Age : "+arrDetails[i].age);
            System.out.println("Date of Birth : "+arrDetails[i].dob);
            System.out.println("Branch : "+branch);
            System.out.println("College : "+college);
        }
    }

    public void displayMarks (int i)
    {
        String newline = System.lineSeparator();

        //displaying the details of the student, if the index is not null, i.e. as deletion of a detail make that index's value null
        if (arrDetails[i] != null)
        {
            if (arrMarks[i] != null)
            {
                System.out.print(newline+"Student Marks : "+newline+newline);

                System.out.println("CSE : "+arrMarks[i].cse);
                System.out.println("DSA : "+arrMarks[i].dsa);
                System.out.println("OOP : "+arrMarks[i].oop);
                System.out.println("DBMS : "+arrMarks[i].dbms);
                System.out.println("CSA : "+arrMarks[i].csa);
                System.out.println("Total Marks : "+arrMarks[i].totalMarks);
                System.out.println("Percentage : "+arrMarks[i].percentage);
            }
            else
            {
                System.out.println(newline+"#Student marks not found, please add marks for this student#"+newline);
            }
        }

        else
            System.out.print(newline+"No student details found. Please add Student Details for current index"+newline);
    }

    //display all details and marks of the student whose INDEX has been provided
    public void displayAll (int i)
    {
        String newline = System.lineSeparator();
        line();
        displayDetails(i);
        System.out.print(newline);
        displayMarks(i);
        line();
    }

    //displaying all student names, i.e. the names of all students whose details have been added
    public void displayAllStudent ()
    {
        String newline = System.lineSeparator();

        line();

        for (int i=0; i<arrDetails.length; i++)
        {
            if (arrDetails[i] != null)    //if index's entry is not null, print the name of the students in tabular form
            {
                System.out.println("("+i+") "+arrDetails[i].name+'\t');

                if (i%3 == 0)
                    System.out.print(newline);      //after every 3 names, print a new line
            }
        }

        line();
    }

        //displaying all student names, i.e. the names of all students whose details have been added
        public void displayAllStudentWithoutIndex ()
        {
            String newline = System.lineSeparator();
    
            line();
    
            for (int i=0; i<arrDetails.length; i++)
            {
                if (arrDetails[i] != null)    //if index's entry is not null, print the name of the students in tabular form
                {
                    System.out.println(arrDetails[i].name+'\t');
    
                    if (i%4 == 0)
                        System.out.print(newline);      //after every 4 names, print a new line
                }
            }
    
            line();
        }

    //displaying all students, then providing them an option to select a student to display details with fail safe
    public void viewStudentDetails()
    {
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        displayAllStudent();

        System.out.print("Enter the no. of student to view details : ");
        int choice3 = scanner.nextInt();

        String newline = System.lineSeparator();

        line();

        System.out.print(newline+"Student Details : "+newline+newline);

        //displaying the details of the student, if the index is not null, i.e. as deletion of a detail make that index's value null
        if (arrDetails[choice3] != null)
        {
            System.out.println("Name : "+arrDetails[choice3].name);
            System.out.println("Registration Number : "+arrDetails[choice3].registrationNo);
            System.out.println("Age : "+arrDetails[choice3].age);
            System.out.println("Date of Birth : "+arrDetails[choice3].dob);
            System.out.println("Branch : "+branch);
            System.out.println("College : "+college);
        }

        else
            System.out.print(newline+"Student with this index don't exist"+newline);

        scanner.close();
    }

    //displaying all students, then providing them an option to select a student to display marks
    public void viewStudentMarks()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        displayAllStudent();

        System.out.print(newline+"Enter the no. of student to view details : ");
        int choice3 = scanner.nextInt();

        if (arrMarks[choice3] != null)
        {
            displayMarks(choice3);
        }

        else
            System.out.print(newline+"Current index's Student or Marks don't exist. Please add their details or marks"+newline);

        scanner.close();
    }

    //displaying all students details
    public void displayAllStudentDetails ()
    {
        String newline = System.lineSeparator();

        line();

        for (int i=0; i<arrDetails.length; i++)
        {
            if (arrDetails[i] != null)
            {
                line();
                displayDetails(i);
                System.out.print(newline);
                line();
            }
        }

        line();
    }

    //displaying all students marks
    public void displayAllStudentMarks ()
    {
        String newline = System.lineSeparator();

        line();

        for (int i=0; i<arrDetails.length; i++)
        {
            if (arrMarks[i] != null)
            {
                line();
                displayMarks(i);
                System.out.print(newline);
                line();
            }
        }

        line();
    }

    //displaying all student's details and marks
    public void displayAllStudentAllDetails()
    {
        String newline = System.lineSeparator();

        line();

        for (int i=0; i<arrDetails.length; i++)
        {
            if (arrDetails[i] != null)
            {
                line();

                displayDetails(i);
                System.out.print(newline+newline);
                displayMarks(i);

                line();
            }
        }

        line();
    }

    //updating the details of a student
    public void updateStudentDetails ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        displayAllStudent();
        
        System.out.print(newline+newline+"Enter your choice : ");
        choice = scanner.nextInt();

        if (arrDetails[choice] != null)
        {
            //display the details of student whose index has been provided
            displayDetails(choice);

            System.out.print(newline+"Which detail you want to update?"+newline);
            System.out.print(newline+"----------------------------------------------------------------------------------------------------------");
            System.out.print(newline+"|   1. Name   |   2. Registration No.   |  3. Age   |   4. Date of Birth   |   5. Cancel   |   6. Exit   |");
            System.out.print(newline+"----------------------------------------------------------------------------------------------------------");

            //choose what to update
            System.out.print(newline+newline+"Enter your choice : ");
            int option = scanner.nextInt();
 
            switch (option)
            {
                case 1:
                    System.out.print(newline+"Enter updated student name : ");
                    String name = scanner.next();
                    arrDetails[choice].name = name;
                    System.out.print(newline+"#Student Details Updated#");
                    break;

                case 2:
                    System.out.print(newline+"Enter updated student registration number : ");
                    String registrationNo = scanner.next();
                    arrDetails[choice].registrationNo = registrationNo;
                    System.out.print(newline+"#Student Details Updated#");
                    break;

                case 3:
                    System.out.print(newline+"Enter updates student age : ");
                    int age = scanner.nextInt();
                    arrDetails[choice].age = age;
                    System.out.print(newline+"#Student Details Updated#");
                    break;

                case 4:
                    System.out.print(newline+"Enter updated student Date of Birth : ");
                    String dob = scanner.next();
                    arrDetails[choice].dob = dob;
                    System.out.print(newline+"#Student Details Updated#");
                    break;

                case 5:
                    System.out.print(newline+"#Update cancelled#"+newline);
                    break;

                default:
                    System.out.print(newline+"#Invalid choice#"+newline);
                    break;
            }

            line();

            if (option == 1 || option == 2 || option == 3 || option == 4)
            {
                //further update the details of the student
                updateFurtherDetails();
            }

            else if (option == 5)
            {
                line();
                System.out.print(newline+"#Updating has been cancelled by user#"+newline);
                line();
            }

            else
            {
                line();
                System.out.print(newline+"#Due to Invalid choice, Update has been cancelled#"+newline);
                line();
            }
        }

        else
        {
            System.out.println(newline+"#Student with current index don't exist, please enter a valid student number#"+newline);
        }

        scanner.close();
    }

    public void updateFurtherDetails ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        //Option to update once again
        System.out.print(newline+newline+"Do you want to update more details? (y/n) ");
        char option1 = scanner.next().charAt(0);
        option1 = Character.toLowerCase(option1);

        if (option1 == 'y')
        {
            System.out.print(newline);

            //recursive call, update marks again. Will be called until user enters 'n'
            updateStudentDetails();
        }

        else 
        {
            System.out.print(newline+"Would you like to view updated Details of Student? (y/n)");
            char option2 = scanner.next().charAt(0);
            option2 = Character.toLowerCase(option2);

            if (option2 == 'y')
            {
                line();
                displayDetails(choice);
            }

            line();
        }

        scanner.close();
    }

    //menu driven method, to update the marks of a student
    public void updateStudentMarks ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        displayAllStudent();
        
        System.out.print(newline+newline+"Enter your choice : ");
        choice = scanner.nextInt();

        if (arrMarks[choice] != null)
        {       
            displayMarks(choice);

            System.out.print(newline+newline+"Which marks you want to update or add?"+newline);
            System.out.print(newline+"-----------------------------------------------------------------------------------");
            System.out.print(newline+"|   1. CSE   |   2. DSA   |   3. OOP   |   4. DBMS   |   5. CSA   |   6. Cancel   |");
            System.out.print(newline+"-----------------------------------------------------------------------------------");

            System.out.print(newline+"Enter your choice : ");
            int option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    System.out.print(newline+"Enter new or updated CSE marks : ");
                    float cse = scanner.nextFloat();
                    arrMarks[choice].cse = cse;
                    System.out.print(newline+"#Student Marks Added or Updated#");
                    break;

                case 2:
                    System.out.print(newline+"Enter new or updated DSA marks : ");
                    float dsa = scanner.nextFloat();
                    arrMarks[choice].dsa = dsa;
                    System.out.print(newline+"#Student Marks Added or Updated#");
                    break;

                case 3:
                    System.out.print(newline+"Enter new or updated OOP marks : ");
                    float oop = scanner.nextFloat();
                    arrMarks[choice].oop = oop;
                    System.out.print(newline+"#Student Marks Added or Updated#");
                    break;

                case 4:
                    System.out.print(newline+"Enter new or updated DBMS marks : ");
                    float dbms = scanner.nextFloat();
                    arrMarks[choice].dbms = dbms;
                    System.out.print(newline+"#Student Marks Added or Updated#");
                    break;

                case 5:
                    System.out.print(newline+"Enter new or updated CSA marks : ");
                    float csa = scanner.nextFloat();
                    arrMarks[choice].csa = csa;
                    System.out.print(newline+"#Student Marks Updated#");
                    break;

                case 6:
                    System.out.print(newline+"#Update cancelled#"+newline);
                    break;

                default:
                    System.out.print(newline+"#Invalid choice#"+newline);
                    break;
            }

            if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5)
            {
                //further update the Marks of the student
                updateFurtherMarks();
            }

            else if (option == 6)
            {
                line();
                System.out.print(newline+"#Updating has been cancelled by user#"+newline);
                line();
            }

            else
            {
                line();
                System.out.print(newline+"#Due to Invalid choice, Update has been cancelled#"+newline);
                line();
            }
        }

        else
        {
            System.out.println(newline+"#Student with current index don't exist, please enter a valid student number#"+newline);
        }

        scanner.close();
    }

    //option to update marks once again
    public void updateFurtherMarks ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        System.out.print(newline+"Do you want to update more marks? (y/n)"+newline);
        char option1 = scanner.next().charAt(0);
        option1 = Character.toLowerCase(option1);

        if (option1 == 'y')
        {
            System.out.print(newline);
            
            //recursive call, update marks again. Will be called until user enters 'n'
            updateStudentMarks();  
        }

        else 
        {
            System.out.print(newline+"Would you like to view updated Marks of Student? (y/n)");
            char option2 = scanner.next().charAt(0);
            option2 = Character.toLowerCase(option2);

            if (option2 == 'y')
                displayMarks(choice);

            line();
        }

        scanner.close();
    }

    //delete the details of a student
    public void deleteStudentDetails ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        displayAllStudent();

        System.out.print(newline+newline+"Enter your choice : ");
        choice = scanner.nextInt();

        if (arrDetails[choice] != null)
        {
            System.out.print(newline+arrDetails[choice].name+" details : "+newline);

            displayDetails(choice);

            System.out.print(newline+newline+"NOTE : MARKS of Student "+arrDetails[choice].name+" will also be deleted."+newline);
            System.out.print(newline+newline+"Are you sure you want to delete this student details? (y/n) : ");
            char option = scanner.next().charAt(0);
            option = Character.toLowerCase(option);

            if (option == 'y')
            {
                //delete the details of the student, and provide both Student and Marks array with null value
                arrDetails[choice] = null;
                arrMarks[choice] = null;

                //move all the elements of the array to the left, to fill the gap
                allDetailsMoveUponDeletion(choice);

                this.updateDetailsANS = true;

                System.out.print(newline+"#Student Details Deleted#"+newline);
            }

            else
            {
                System.out.print(newline+"#Deletion cancelled#"+newline);

                this.updateDetailsANS = false;
            }

            line();

            scanner.close();
        }

        else
        {
            System.out.println(newline+"#Student with current index don't exist, please enter a valid student number#"+newline);
        }
    }

    //delete student marks, and provide the array with 0 value
    public void deleteStudentMarks ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        displayAllStudent();

        System.out.print(newline+newline+"Enter your choice : ");
        choice = scanner.nextInt();

        if (arrMarks[choice] != null)
        {
            System.out.print(newline+arrDetails[choice].name+"'s marks : "+newline);

            displayMarks(choice);

            System.out.print(newline+newline+"Are you sure you want to delete this student marks? (y/n) : ");
            char option = scanner.next().charAt(0);
            option = Character.toLowerCase(option);

            if (option == 'y')
            {
                arrMarks[choice].cse = 0;
                arrMarks[choice].dsa = 0;
                arrMarks[choice].oop = 0;
                arrMarks[choice].dbms = 0;
                arrMarks[choice].csa = 0;
                arrMarks[choice].totalMarks = 0;
                arrMarks[choice].percentage = 0;

                arrMarks[choice] = null;

                System.out.print(newline+"#Student Marks Deleted#"+newline);
            }   

            else
            {
                    System.out.print(newline+"#Deletion cancelled#"+newline);
            }
        }

        else
        {
            System.out.print(newline+"#Current Student Marks already don't exist#"+newline);
        }

        line();

        scanner.close();
    }

    //shift the elements of the array to the left, to fill the gap
    private void allDetailsMoveUponDeletion (int i)
    {
        for (int j = i; j < countStudent()-i; j++)
        {
            arrDetails[j] = arrDetails[j+1];
            arrMarks[j] = arrMarks[j+1];
        }
    }

    //giving all the values of array as null, so that upon wrong entry in any field, the program doesn't crash
    //both arrays are given null value using for loop, will use this in main method
    public void arrValueNullProvider ()
    {
        for (int i = 0; i < 1000; i++)
        {
            arrDetails[i] = null;
            arrMarks[i] = null;
        }
    }

    //the boolean value of updateDetailsANS is updated in deleteStudentDetails method, and is used here
    //this method will tell the value to user, whether the details of student has been updated or not
    public boolean taskDeleteDetailsANS()
    {
        return updateDetailsANS;
    }

    //count the number of student, and return the count
    private int countStudent() 
    {
        int count = 0;

        for (int i = 0; i < 1000; i++)
        {
            if (arrDetails[i] != null)
            {
                count++;
            }
        }
        return count;
    }

    //convert the char to lowercase
    public static char toLowerCase(char ch)
    {
        if (ch >= 'A' && ch <= 'Z')
        {
            ch = (char)(ch + 'a' - 'A');
        }
        return ch;
    }

    //simple method to print line, result in better looking code and reusable code
    public void line ()
    {
        String newline = System.lineSeparator();
        System.out.println(newline+"---------------------------------------------------------------------------------"+newline);
    }
}
