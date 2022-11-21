/*
Program to find the simple Gene strand from the pre-given input and using while loop to find TAA if not muliple of 3
*/
//Input is pre-provided, by defining DNA in String in the program itself
//Program 2 in Week 2
//rename the file to FindingSingleGeneAndWhileLoop
//Coder: Phantom-fs

package DNA;
import java.lang.String;

public class FindingSingleGeneAndWhileLoop 
{
    public String findSimpleGene (String dna)
    {
        int startIndex = dna.indexOf("ATG");

        if (startIndex != -1)
        {
            int currIndex = dna.indexOf("TAA", startIndex+3);

            while(currIndex != -1)
            {
                if((currIndex - startIndex) % 3 == 0)
                    return (dna.substring(startIndex, currIndex+3));

                else
                    currIndex = dna.indexOf("TAA", currIndex+1);
            }
        }

        return "No Gene found in the DNA strand";
    }
    
    public static void main(String[] args) 
    {
        String newline = System.lineSeparator();

        String dna_strand = "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACC";
        
        FindingSingleGeneAndWhileLoop obj = new FindingSingleGeneAndWhileLoop();

        System.out.print(newline+newline+"****************************************************************************************"+newline);

        System.out.print(newline+"DNA Strand 1 : " + dna_strand);
        System.out.println(newline+"Gene : " + obj.findSimpleGene(dna_strand));

        System.out.print(newline+"****************************************************************************************"+newline+newline);
    }
}
