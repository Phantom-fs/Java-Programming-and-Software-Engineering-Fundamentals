/*
Program to find the all the gene in DNA strand ith muliple end codon support 
*/
//Input is pre-provided, by defining DNA in String in the program itself
//Program 4 in Week 2
//rename the file to FindingMultipleGene
//Coder: Phantom-fs

import java.lang.String;

public class FindingMultipleGene 
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
        FindingMultipleGene obj = new FindingMultipleGene();

        //array to store all the genes found
        String[] geneArray = new String[100];
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

                //now find the next ATG after the currEndIndex, thus add 1 to currEndIndex
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
    
    public static void main(String[] args) 
    {
        String newline = System.lineSeparator();
        int count = 0;

        String dna = "ATGGCACGATGACGGGCCATGATCCTAGCCCGCTAACGAGCATGCCGTAGACGGCATAGGCCCGA";

        //string array with its values provided from the static method MultipleGeneFinder, with dna as its parameter
        String[] geneArray = MultipleGeneFinder(dna);

        System.out.print(newline+newline+"****************************************************************************************"+newline);

        System.out.print(newline+"DNA strand : "+newline+dna+newline);

        if(geneArray == null)
            System.out.print(newline+"No gene found in the DNA strand"+newline);

        else
        {
            for (int i = 0; i < geneArray.length; i++)
            {
                if (geneArray[i] != null)
                {   
                    System.out.print(newline+"Gene Strand "+(i+1)+" : "+geneArray[i]);
                    count++;   //number of genes found
                    System.out.print(newline);
                }
            }

            System.out.print(newline+"Total number of genes found : "+count+newline);
        }

        System.out.print(newline+"****************************************************************************************"+newline+newline);
    }
}
