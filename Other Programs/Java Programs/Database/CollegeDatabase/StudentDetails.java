//TAKE CARE OF THE package NAME, make sure all files when downloading and running are present in a single package
//AND UPDATE THE PACKAGE NAME of all three files, if SAVING IT IN SUB-PACKAGE
/*
 * The program is made for Professors Details as well as Students Details and Marks
 * program have muliple fail safes and all methods and functions related to student details are present in StudentDetails.java
*/ 
//Coder: Phantom-fs


package CollegeDatabase;

import java.io.FilterInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Character;

/*
 * Class StudentDetail, containing all methods, data of student details and marks
 * using extends here and making StudentDetail a child class of CollegeDatabase i.e. main class
 * CollegeDatabase is the main class aka parent class and StudentDetail is the child class
*/
public class StudentDetails extends CollegeDatabase
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

    public StudentDetails[] arrDetails = new StudentDetails[1000];     //Array of objects for student details, with max entries of 1000
    public StudentDetails[] arrMarks = new StudentDetails[1000];       //Array of objects for student marks, with max entries of 1000

    static Scanner scanner;

    //default constructor
    public StudentDetails () {}

    //constructor for student details, has been used internally in class
    public StudentDetails (String name, int age, String dob, String registrationNo)
    {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.registrationNo = registrationNo;
    }

    //constructor for marks, has been used internally in class
    public StudentDetails (float cse, float dsa, float oop, float dbms, float csa)
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
        //clear the screen before displaying the options and menu
        super.clearScreen();

        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        System.out.print(newline+"Enter the student details : "+newline+newline);
        
        System.out.print("Enter the student name : ");
        String name = scanner.nextLine();
        System.out.print("Enter the student Registration Number : ");
        String registrationNo = scanner.nextLine();

        int count = 1;
        int age;
        System.out.print("Enter the student age : ");
        while(true)
        {
            try
            {
                age = scanner.nextInt();
                if(age < 0)
                {
                    System.out.print("Enter a valid age : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch(InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                }
                System.out.print("Enter a valid age : ");
                scanner.nextLine();
            }
        }

        System.out.print("Enter the student Date of Birth : ");
        String dob = scanner.next();

        System.out.print(newline+"#Student details added successfully#"+newline);

        //adding the input taken from user to the array of objects, using constructor
        arrDetails[i] = new StudentDetails (name, age, dob, registrationNo);

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
        
        int count = 1;
        float cse;
        System.out.print("Enter the student CSE marks : ");
        while(true)
        {
            try
            {
                cse = scanner.nextFloat();
                if(cse < 0 || cse > 100)
                {
                    //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                    if(count == 1)
                    {
                        System.out.print(newline);
                        count++;
                    } 
                    System.out.print("Enter valid marks (0-100) : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch(InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                } 
                System.out.print("Enter valid marks : ");
                scanner.nextLine();
            }
        }

        count = 1;
        float dsa;
        System.out.print("Enter the student DSA marks : ");
        while(true)
        {
            try
            {
                dsa = scanner.nextFloat();
                if(dsa < 0 || dsa > 100)
                {
                    //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                    if(count == 1)
                    {
                        System.out.print(newline);
                        count++;
                    }
                    System.out.print("Enter valid marks (0-100) : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch(InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                } 
                System.out.print("Enter valid marks : ");
                scanner.nextLine();
            }
        }

        count = 1;
        float oop;
        System.out.print("Enter the student OOP marks : ");
        while(true)
        {
            try
            {
                oop = scanner.nextFloat();
                if(oop < 0 || oop > 100)
                {
                    //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                    if(count == 1)
                    {
                        System.out.print(newline);
                        count++;
                    }
                    System.out.print("Enter valid marks (0-100) : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch(InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                } 
                System.out.print("Enter valid marks : ");
                scanner.nextLine();
            }
        }

        count = 1;
        float dbms;
        System.out.print("Enter the student DBMS marks : ");
        while(true)
        {
            try
            {
                dbms = scanner.nextFloat();
                if(dbms < 0 || dbms > 100)
                {
                    //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                    if(count == 1)
                    {
                        System.out.print(newline);
                        count++;
                    }
                    System.out.print("Enter valid marks (0-100) : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch(InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                } 
                System.out.print("Enter valid marks : ");
                scanner.nextLine();
            }
        }

        count = 1;
        float csa;
        System.out.print("Enter the student CSA marks : ");
        while(true)
        {
            try
            {
                csa = scanner.nextFloat();
                if(csa < 0 || csa > 100)
                {
                    //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                    if(count == 1)
                    {
                        System.out.print(newline);
                        count++;
                    }
                    System.out.print("Enter valid marks (0-100) : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch(InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                } 
                System.out.print("Enter valid marks : ");
                scanner.nextLine();
            }
        }

        System.out.println(newline+"#Student marks added successfully#"+newline);

        //adding the input taken from user to the array of objects, using constructor
        arrMarks[i] = new StudentDetails (cse, dsa, oop, dbms, csa);
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
                System.out.print("("+i+") "+arrDetails[i].name+newline);

                if (i%10 == 0)
                    System.out.print(newline);      //after every 10 names, print a new line
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
                System.out.print(arrDetails[i].name+newline);
    
                if (i%10 == 0)
                    System.out.print(newline);      //after every 10 names, print a new line
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
                    int age;
                    System.out.print(newline+"Enter updated student age : ");
                    while(true)
                    {
                        try
                        {
                            age = scanner.nextInt();
                            arrDetails[choice].age = age;
                            System.out.print(newline+"#Student Details Updated#");
                            break;
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Please enter a valid age : ");
                            scanner.next();
                        }
                    }
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
                    int cse;
                    System.out.print(newline+"Enter new or updated CSE marks : ");
                    while(true)
                    {
                        try
                        {
                            cse = scanner.nextInt();
                            arrMarks[choice].cse = cse;
                            System.out.print(newline+"#Student Marks Updated#");
                            break;
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Please enter a valid marks : ");
                            scanner.next();
                        }
                    }
                    break;

                case 2:
                    int dsa;
                    System.out.print(newline+"Enter new or updated DSA marks : ");
                    while(true)
                    {
                        try
                        {
                            dsa = scanner.nextInt();
                            arrMarks[choice].dsa = dsa;
                            System.out.print(newline+"#Student Marks Updated#");
                            break;
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Please enter a valid marks : ");
                            scanner.next();
                        }
                    }
                    break;

                case 3:
                    int oop;
                    System.out.print(newline+"Enter new or updated OOP marks : ");
                    while(true)
                    {
                        try
                        {
                            oop = scanner.nextInt();
                            arrMarks[choice].oop = oop;
                            System.out.print(newline+"#Student Marks Updated#");
                            break;
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Please enter a valid marks : ");
                            scanner.next();
                        }
                    }
                    break;

                case 4:
                    int dbms;
                    System.out.print(newline+"Enter new or updated DBMS marks : ");
                    while(true)
                    {
                        try
                        {
                            dbms = scanner.nextInt();
                            arrMarks[choice].dbms = dbms;
                            System.out.print(newline+"#Student Marks Updated#");
                            break;
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Please enter a valid marks : ");
                            scanner.next();
                        }
                    }
                    break;

                case 5:
                    int csa;
                    System.out.print(newline+"Enter new or updated CSA marks : ");
                    while(true)
                    {
                        try
                        {
                            csa = scanner.nextInt();
                            arrMarks[choice].csa = csa;
                            System.out.print(newline+"#Student Marks Updated#");
                            break;
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Please enter a valid marks : ");
                            scanner.next();
                        }
                    }
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

    //simple method to print line, result in better looking code and reusable code
    public void line ()
    {
        String newline = System.lineSeparator();
        System.out.println(newline+"---------------------------------------------------------------------------------"+newline);
    }

    //convert the char entered to lowercase
    public static char loLowerCase(char ch)
    {
        if (ch >= 'A' && ch <= 'Z')
        {
            ch = (char)(ch + 'a' - 'A');
        }
        return ch;
    }
}
