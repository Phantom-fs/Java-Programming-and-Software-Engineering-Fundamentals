/*
 * Program to read DNA from file, find gene in it, read gene from file, write gene to a file (IO)
 * Program to find the all the gene in DNA strand with muliple end codon support 
*/
//Program 5 in Week 2
//Coder: Phantom-fs

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.util.*;

public class FindingGeneAndIO 
{
    public int findStopCodon (String dna, int startIndex, String stopCodon)
    {
        //stop codon here is a string i.e. with value "TAA", "TAG" or "TGA"

        int currIndex = dna.indexOf(stopCodon, startIndex);

        while(currIndex != -1)
        {
            if((currIndex - startIndex) % 3 == 0)
                return currIndex;

            else
                currIndex = dna.indexOf(stopCodon, currIndex+1);
        }

        //if no stop codon found then return -1
        return -1;
    }
    
    public int findSingleGene (String dna, int CurrPos)
    {
        //new ATG is found at CurrPos, provided by the calling method
        int startIndex = CurrPos;

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;

        //if any of the stop codon is not -1, and exists in the dna strand
        if (taaIndex != -1 || tagIndex != -1 || tgaIndex != -1)
        {
            //check whether it exist, and if it does then it must be smaller than the other stop codon

            if(taaIndex != -1 && (taaIndex < tagIndex || tagIndex == -1) && (taaIndex < tgaIndex || tgaIndex == -1))
                minIndex = taaIndex;

            else if(tagIndex != -1 && (tagIndex < taaIndex || taaIndex == -1) && (tagIndex < tgaIndex || tgaIndex == -1))
                minIndex = tagIndex;

            else if(tgaIndex != -1 && (tgaIndex < taaIndex || taaIndex == -1) && (tgaIndex < tagIndex || tagIndex == -1))
                minIndex = tgaIndex;

            //return the index of stop codon found with its inclusion, so that we don't have to add 3 in substring method
            return minIndex + 3;
        }

        else
            return -1;
    }

    public static String[] MultipleGeneFinder (String dna)
    {
        //object creation as calling non-static method from static method
        FindingGeneAndIO obj = new FindingGeneAndIO();

        //array to store all the genes found
        String[] geneArray = new String[50];
        int i = 0;

        //finding the first ATG
        int currStartIndex = dna.indexOf("ATG");

        //if no ATG found then return null, i.e., empty array
        if(currStartIndex == -1)
            return (geneArray = null);

        int currEndIndex = 0;
        int count = 0;
        
        //infinite loop for finding all possible genes
        while (true)
        {
            //the currEndIndex is the index of the stop codon to be found
            //and start finding it after the currStartIndex + 3 i.e., after ATG
            currEndIndex = obj.findSingleGene (dna, currStartIndex+3);

            if (currEndIndex != -1)
            {
                //if stop codon found then store the gene in the array, and increment the index
                String gene = dna.substring(currStartIndex, currEndIndex);      //here currEndIndex + 3 is not done, as its already done in findSingleGene method
                geneArray[i] = gene;
                i++;
                count++;      //to know that atleast one gene is found

                //now find the next ATG after the currEndIndex
                currStartIndex = dna.indexOf("ATG", currEndIndex);

                //if no new ATG found, then break the loop
                if(currStartIndex == -1)
                    break;
            }

            else
                break;
        }

        if (count == 0)
            geneArray = null;      //if no gene is found then return null array

        return geneArray;
    }

    public int createGeneFile (String fileName, String[] geneArray)
    {
        try
        {
            String path = "B:\\3- Java Programs\\Programs\\src\\DNA\\GeneFindingAndIO\\" + fileName;
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

            for (int i = 0; i < geneArray.length; i++)
            {
                if (geneArray[i] != null)
                    bw.write(geneArray[i] + '\n');

                else
                    break;
            }
            
            bw.close();
            return 1;
        }  
        catch (IOException e)
        {
            return 0;
        }
    }

    public String[] readGeneFile (String fileName)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String[] geneArray = new String[100];
            String gene = "";
            int i = 0;

            while ((gene = br.readLine()) != null)
            {
                geneArray[i] = gene;
                i++;
            }

            br.close();
            return geneArray;
        }
        catch (Exception e)
        {
            return null;
        }
    } 

    public String readDNAFile (String fileName)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            
            String dna = "";
            String line = "";

            while ((line = br.readLine()) != null)
            {
                dna += line;
            }

            br.close();
            return dna;
        }
        catch (Exception e)
        {
            return null;
        }
    }      
    
    public static void main(String[] args) throws InterruptedException 
    {
        String newline = System.lineSeparator();
        Scanner sc = new Scanner(System.in);
        String filename = null;
        String dnaZ = null;
        int errorCount = 0;
        String[] geneArrayK = null;
        char choice;

        FindingGeneAndIO obj = new FindingGeneAndIO();

        System.out.print(newline+newline+"****************************************************************************************"+newline);

        do
        {
            //clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.print(newline+"Choose an option from the menu : "+newline);

            System.out.print(newline+"1. Read DNA from a file");
            System.out.print(newline+"2. Find all genes in the DNA       (enter the DNA strand from the console)");
            System.out.print(newline+"3. Find all genes in the DNA file  (from option 1, i.e., DNA file)");
            System.out.print(newline+"4. Write all genes to a file       (DNA from option 2 or 3)");
            System.out.print(newline+"5. Read genes from a file");
            System.out.print(newline+"6. Exit...");

            System.out.print(newline+newline+"Enter your choice : ");
            choice = sc.next().charAt(0);

            //clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            switch(choice)
            {
                case '1' : 
                {
                    System.out.print(newline+"NOTE : PLEASE ENTER FILE LOCATION NAME IN .txt FORMAT");
                    System.out.print(newline+"NOTE : Example FORMAT => C:\\Users\\User\\Desktop\\DNA.txt"+newline);

                    System.out.print(newline+"Enter the name of the file : ");
                    filename = sc.next();

                    dnaZ = obj.readDNAFile(filename);

                    if (dnaZ != null)
                        System.out.print(newline+"DNA read from the file is : "+newline+newline+dnaZ);

                    else
                    {
                        System.out.print(newline+"File not found or error in reading file");
                        errorCount++;
                    }

                    break;
                }

                case '2' : 
                {
                    System.out.print(newline+"Enter the DNA : ");
                    String dnaK = sc.next();

                    geneArrayK = MultipleGeneFinder(dnaK);

                    if (geneArrayK != null)
                    {
                        System.out.print(newline+"The genes found are : "+newline+newline);

                        for (int i = 0; i < geneArrayK.length; i++)
                        {
                            if (geneArrayK[i] != null)
                                System.out.print(geneArrayK[i] + newline);

                            else
                                break;
                        }
                        break;
                    }

                    else
                    {
                        System.out.print(newline+"No genes found");
                        break;
                    }
                }

                case '3' :
                {
                    if(filename == null)
                    {
                        System.out.print(newline+"Please read the DNA from a file first, by choosing option 1");
                        break;
                    }

                    else if(errorCount > 0)
                    {
                        System.out.print(newline+"The file read in option 1 has some error, so please read the DNA from a file again, by choosing option 1");
                        break;
                    }

                    else
                    {
                        String[] geneArray = MultipleGeneFinder(dnaZ);

                        System.out.print(newline+"The genes found are : "+newline+newline);

                        for(int i = 0; i < geneArray.length; i++)
                        {
                            if (geneArray[i] != null)
                                System.out.print(geneArray[i] + newline);

                            else
                                break;
                        }
                        break;
                    }                    
                }

                case '4' : 
                {
                    System.out.print(newline+"NOTE : PLEASE ENTER FILE NAME ONLY (with .txt extension)"+newline);
                    System.out.print(newline+"NOTE : Example FORMAT => dna.txt"+newline);

                    System.out.print(newline+"Enter the name of the file (the file to store the genes) : ");
                    String filename4 = sc.next();

                    System.out.print(newline+newline+"Choose an option from the menu : "+newline);
                    sc.nextLine();

                    System.out.print(newline+"1. DNA read from the console (from option 2)");
                    System.out.print(newline+"2. DNA read from the file (from option 1, i.e., DNA file)");

                    System.out.print(newline+newline+"Enter your choice : ");
                    choice = sc.next().charAt(0);

                    switch(choice)
                    {
                        case '1' : 
                        {
                            System.out.print(newline+"Writing the genes found from DNA entered in Console (option 2)"+newline);

                            if (geneArrayK != null)
                            {

                                int rt = obj.createGeneFile (filename4, geneArrayK);

                                if (rt == 1)
                                    System.out.print(newline+"The genes have been written to the file");

                                else
                                    System.out.print(newline+"Error in writing to the file");

                                break;
                            }

                            else
                            {
                                System.out.print(newline+"Please enter the DNA from the console first, by choosing option 2");
                                break;
                            }
                        }

                        case '2' :
                        {
                            if(filename == null)
                            {
                                System.out.print(newline+"Please read the DNA from a file first, by choosing option 1");
                                break;
                            }

                            else if(errorCount > 0)
                            {
                                System.out.print(newline+"The file read in option 1 has some error, so please read the DNA from a file again, by choosing option 1");
                                break;
                            }

                            else
                            {
                                String[] geneArray = MultipleGeneFinder(dnaZ);

                                int rt = obj.createGeneFile (filename4, geneArray);

                                if (rt == 1)
                                    System.out.print(newline+"The genes have been written to the file");

                                else
                                    System.out.print(newline+"Error in writing to the file");

                                break;
                            }                    
                        }

                        default:
                        {
                            System.out.print(newline+"Invalid choice");
                            break;
                        }
                    }

                    break;
                }

                case '5' : 
                {
                    System.out.print(newline+"NOTE : PLEASE ENTER FILE LOCATION NAME IN .txt FORMAT");
                    System.out.print(newline+"NOTE : Example FORMAT => C:\\Users\\User\\Desktop\\DNA.txt"+newline);

                    System.out.print(newline+"Enter the name of the file : ");
                    String filename5 = sc.next();

                    String[] geneArray = obj.readGeneFile(filename5);

                    if (geneArray != null)
                    {
                        System.out.print(newline+"The genes read from the file are : "+newline+newline);

                        for (int i = 0; i < geneArray.length; i++)
                        {
                            if (geneArray[i] != null)
                                System.out.print(geneArray[i] + newline);
                        }
                        break;
                    }

                    else
                    {
                        System.out.print(newline+"File not found or error in reading file");
                        break;
                    }
                }

                case '6' : 
                {
                    System.out.print(newline+"Exiting...");
                    break;
                }

                default:
                {
                    System.out.print(newline);
                    break;
                }
            }

            Thread.sleep(4000);
            System.out.print(newline+newline+newline+newline);
        }
        while (choice != '6');

        System.out.print(newline+"****************************************************************************************"+newline+newline);

        sc.close();
    }
}
