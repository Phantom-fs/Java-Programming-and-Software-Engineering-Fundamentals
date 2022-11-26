//TAKE CARE OF THE package NAME, make sure all files when downloading and running are present in a single package
//AND UPDATE THE PACKAGE NAME of all three files, if SAVING IT IN SUB-PACKAGE
/*
 * The program is made for Professors Details as well as Students Details and Marks
 * program have muliple fail safes and all methods and functions related to professors are present in professor Details.java
*/ 
//Coder: Phantom-fs

package CollegeDatabase;

import java.io.FilterInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Character;

/*
 * Class ProfessorDetails, containing all methods, data of Professors, and their details
 * using extends here and making ProfessorDetails a child class of CollegeDatabase i.e. main class
 * CollegeDatabase is the main class aka parent class and ProfessorDetails is the child class
*/
public class ProfessorDetails extends CollegeDatabase 
{
    private String name;
    private String department;
    private String designation;
    private String email;
    private String phone;
    private int salary;
    private int studentsUnderProfessor;
    private int classesUnderProfessor;

    private int choice;

    public boolean updateDetailsANS;

    public ProfessorDetails[] arrDetails = new ProfessorDetails[1000];     //Array of objects for Professor details, with max entries of 1000 professors

    static Scanner scanner;

    //default constructor
    public ProfessorDetails() {}

    //constructor for Professor details, will be used internally in this class
    public ProfessorDetails(String name, String department, String designation, String email, String phone, int salary, int studentsUnderProfessor, int classesUnderProfessor)
    {
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.studentsUnderProfessor = studentsUnderProfessor;
        this.classesUnderProfessor = classesUnderProfessor;
    }

    public String ProfessorName(int i)
    {
        return arrDetails[i].name;
    }

    public void addProfessorDetails (int i)
    {
        //clear the screen before displaying the options and menu
        super.clearScreen();

        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        System.out.print(newline+"Enter the Professor details: "+newline+newline);

        System.out.print("Enter the name of the Professor: ");
        String name = scanner.nextLine();
        System.out.print("Enter the department of the Professor: ");
        String department = scanner.nextLine();
        System.out.print("Enter the designation of the Professor: ");
        String designation = scanner.nextLine();
        System.out.print("Enter the email of the Professor: ");
        String email = scanner.nextLine();
        System.out.print("Enter the phone number of the Professor: ");
        String phone = scanner.nextLine();

        int count = 1;
        //String input in integer; exception handling
        //salary input mismatch exception
        int salary;
        System.out.print("Enter the salary of the Professor: ");
        while(true)
        {
            try
            {
                salary = scanner.nextInt();
                if(salary < 0)
                {
                    System.out.print(newline+"Salary cannot be negative. Please enter a valid salary : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }

            }
            catch (InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                }                
                System.out.print("Invalid input. Please enter a valid salary : ");
                scanner.next();
            }
        }

        count = 1;
        //number of students under professor input mismatch exception
        int studentsUnderProfessor;
        System.out.print("Enter the number of students under the Professor: ");
        while(true)
        {
            try
            {
                studentsUnderProfessor = scanner.nextInt();
                if(studentsUnderProfessor < 0)
                {
                    System.out.print(newline+"Number of students cannot be negative. Please enter a valid number of students : ");
                    continue;
                }
                else
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch (InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                }
                System.out.print("Invalid input. Please enter a valid number of students : ");
                scanner.next();
            }
        }

        count = 1;
        //number of classes under professor input mismatch exception
        int classesUnderProfessor;
        System.out.print("Enter the number of classes under the Professor: ");
        while(true)
        {
            try
            {
                classesUnderProfessor = scanner.nextInt();
                if(classesUnderProfessor < 0)
                {
                    System.out.print(newline+"Number of classes cannot be negative. Please enter a valid number of classes : ");
                    continue;
                }
                {
                    if (count > 1)
                        System.out.print(newline);
                    break;
                }
            }
            catch (InputMismatchException e)
            {
                //when the wrong input is entered first time, print a new line, to distinguish from previous outputs
                if(count == 1)
                {
                    System.out.print(newline);
                    count++;
                }
                System.out.print("Invalid input. Please enter a valid number of classes : ");
                scanner.next();
            }
        }
    
        System.out.println(newline+"#Professor details added successfully#"+newline);
    
        //adding the input taken from user to the array of objects, using constructor
        arrDetails[i] = new ProfessorDetails(name, department, designation, email, phone, salary, studentsUnderProfessor, classesUnderProfessor);

        scanner.close();
    }

    public void displayDetails (int i)
    {
        String newline = System.lineSeparator();

        System.out.println(newline+"Professor details: "+newline+newline);
    
        //displaying the details of the Professor, if the index is not null, i.e. as deletion of a detail make that index's value null
        if (arrDetails[i] != null)
        {
            System.out.println("Name: "+arrDetails[i].name);
            System.out.println("Department: "+arrDetails[i].department);
            System.out.println("Designation: "+arrDetails[i].designation);
            System.out.println("Email: "+arrDetails[i].email);
            System.out.println("Phone: "+arrDetails[i].phone);
            System.out.println("Salary: "+arrDetails[i].salary);
            System.out.println("Number of students under the Professor: "+arrDetails[i].studentsUnderProfessor);
            System.out.println("Number of classes under the Professor: "+arrDetails[i].classesUnderProfessor);
        }
    }

    //displaying all Professor names, i.e. the names of all Professors whose details have been added
    public void displayAllProfessor()
    {
        String newline = System.lineSeparator();

        line();

        for (int i = 0; i < arrDetails.length; i++)
        {
            if (arrDetails[i] != null)              //if index's entry is not null, print the name of the professor in tabular form
            {
                System.out.print("("+i+") "+arrDetails[i].name+newline);

                if (i%10 == 0)
                    System.out.print(newline);      //after every 10 names, print a new lines
            }
        }

        line();

    }

    //displaying all Professor names, i.e. the names of all Professors whose details have been added
    public void displayAllProfessorWithoutIndex ()
    {
        String newline = System.lineSeparator();
    
        line();
    
        for (int i=0; i<arrDetails.length; i++)
        {
            if (arrDetails[i] != null)              //if index's entry is not null, print the name of the students in tabular form
            {
                System.out.print(arrDetails[i].name+newline);

                if (i%10 == 0)
                    System.out.print(newline);      //after every 10 names, print a new line
            }
        }
    
        line();
    }

    //displaying all professors, then providing them an option to select a professor to display details with fail safe
    public void viewProfessorDetails()
    {
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        displayAllProfessor();

        System.out.print("Enter the no. of Professor to view details : ");
        int choice3 = scanner.nextInt();


        String newline = System.lineSeparator();

        line();

        //displaying the details of the professor, if the index is not null, i.e. as deletion of a detail make that index's value null
        if (arrDetails[choice3] != null)
        {
            displayDetails(choice3);
        }

        else
            System.out.print(newline+"Professor with this index don't exist"+newline);

        scanner.close();
    }

    //display all professor's all details
    public void displayAllProfessorDetails ()
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

    //updating the details of the professors
    public void updateProfessorDetails ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(new FilterInputStream(System.in){public void close(){}}){public void close(){}});

        displayAllProfessor();
        
        System.out.print(newline+newline+"Enter your choice : ");
        choice = scanner.nextInt();

        if (arrDetails[choice] != null)
        {
            //display the details of student whose index has been provided
            displayDetails(choice);

            System.out.print(newline+"Which detail you want to update?"+newline);
            System.out.print(newline+"------------------------------------------------------------------------------------------------------------");
            System.out.print(newline+"|   1. Name   |   2. Department   |  3. Designation   |   4. Email   |   5. Phone Number   |   6. Salary   |");
            System.out.print(newline+"------------------------------------------------------------------------------------------------------------");
            System.out.print(newline+"|   7. Number of students under the Professor   |   8. Number of classes under the Professor   |  9. Exit  |");
            System.out.print(newline+"------------------------------------------------------------------------------------------------------------");

            //choose what to update
            System.out.print(newline+newline+"Enter your choice : ");
            int option = scanner.nextInt();
 
            switch (option)
            {
                case 1:
                    System.out.print(newline+"Enter updated Professor name : ");
                    String name = scanner.next();
                    arrDetails[choice].name = name;
                    System.out.print(newline+"#Professor Details Updated#");
                    break;

                case 2:
                    System.out.print(newline+"Enter updated Professor Department : ");
                    String department = scanner.next();
                    arrDetails[choice].department = department;
                    System.out.print(newline+"#Professor Details Updated#");
                    break;

                case 3:
                    System.out.print(newline+"Enter updated Professor Designation : ");
                    String designation = scanner.next();
                    arrDetails[choice].designation = designation;
                    System.out.print(newline+"#Professor Details Updated#");
                    break;

                case 4:
                    System.out.print(newline+"Enter updated Professor Email : ");
                    String email = scanner.next();
                    arrDetails[choice].email = email;
                    System.out.print(newline+"#Professor Details Updated#");
                    break;

                case 5:
                    System.out.print(newline+"Enter updated Professor Phone Number : ");
                    String phone = scanner.next();
                    arrDetails[choice].phone = phone;
                    System.out.print(newline+"#Professor Details Updated#");
                    break;

                case 6:
                    int salary;
                    System.out.print(newline+"Enter updated Professor Salary : ");
                    while(true)
                    {
                        try
                        {
                            salary = scanner.nextInt();
                            arrDetails[choice].salary = salary;
                            System.out.print(newline+"#Professor Details Updated#");
                            break;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Enter a valid number : ");
                            scanner.next();
                        }
                    }
                    break;

                case 7:
                    int studentsUnderProfessor;
                    System.out.print(newline+"Enter updated number of students under the Professor : ");
                    while(true)
                    {
                        try
                        {
                            studentsUnderProfessor = scanner.nextInt();
                            arrDetails[choice].studentsUnderProfessor = studentsUnderProfessor;
                            System.out.print(newline+"#Professor Details Updated#");
                            break;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Enter a valid number : ");
                            scanner.next();
                        }
                    }
                    break;

                case 8:
                    int classesUnderProfessor;
                    System.out.print(newline+"Enter updated number of classes under the Professor : ");
                    while(true)
                    {
                        try
                        {
                            classesUnderProfessor = scanner.nextInt();
                            arrDetails[choice].classesUnderProfessor = classesUnderProfessor;
                            System.out.print(newline+"#Professor Details Updated#");
                            break;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.print(newline+"Enter a valid number : ");
                            scanner.next();
                        }
                    }
                    break;

                case 9:
                    System.out.print(newline+"#Update cancelled#"+newline);
                    break;

                default:
                    System.out.print(newline+"#Invalid choice#"+newline);
                    break;
            }

            line();

            if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6 || option == 7 || option == 8)
            {
                //further update the details of the professor
                updateFurtherDetails();
            }

            else if (option == 9)
            {
                line();
                System.out.print(newline+"#Updating has been cancelled by user#"+newline);
                line();
            }

            else
            {
                line();
                System.out.print(newline+"#Invalid Choice, Please try again!"+newline);
                line();
                //recursion, recalling the method
                updateProfessorDetails();
                line();
            }
        }

        else
        {
            System.out.println(newline+"#Professor with current index don't exist, please enter a valid Professor number#"+newline);
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

            //recursive call, update Professor Details again. Will be called until user enters 'n'
            updateProfessorDetails();
        }

        else 
        {
            System.out.print(newline+"Would you like to view updated Details of Professor? (y/n)");
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

    //delete the details of a professor
    public void deleteProfessorDetails ()
    {
        String newline = System.lineSeparator();
        Scanner scanner = new Scanner(new FilterInputStream(System.in){public void close(){}});

        displayAllProfessor();

        System.out.print(newline+newline+"Enter your choice : ");
        choice = scanner.nextInt();

        if (arrDetails[choice] != null)
        {
            System.out.print(newline+arrDetails[choice].name+" details : "+newline);

            displayDetails(choice);

            System.out.print(newline+newline+"Are you sure you want to delete the present Professor's details? (y/n) : ");
            char option = scanner.next().charAt(0);
            option = Character.toLowerCase(option);

            if (option == 'y')
            {
                //delete the details of the Professor, and provide the detail array with null value
                arrDetails[choice] = null;

                //move all the elements of the array to the left, to fill the gap
                allDetailsMoveUponDeletion(choice);

                this.updateDetailsANS = true;

                System.out.print(newline+"#Professor Details Deleted#"+newline);
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
            System.out.println(newline+"#Professor with current index don't exist, please enter a valid Professor number#"+newline);
        }
    }

    //shift the elements of the array to the left, to fill the gap
    private void allDetailsMoveUponDeletion (int i)
    {
        for (int j = i; j < countProfessor()-i; j++)
        {
            arrDetails[j] = arrDetails[j+1];
        }
    }

    //giving all the values of array as null, so that upon wrong entry in any field, the program doesn't crash
    //both arrays are given null value using for loop, will use this in main method
    public void arrValueNullProvider ()
    {
        for (int i = 0; i < 1000; i++)
        {
            arrDetails[i] = null;
        }
    }

    //the boolean value of updateDetailsANS is updated in deleteProfessorDetails method, and is used here
    //this method will tell the value to user, whether the details of Professor has been updated or not
    public boolean taskDeleteDetailsANS()
    {
        return updateDetailsANS;
    }

    //count the number of Professors, and return the count
    private int countProfessor() 
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
