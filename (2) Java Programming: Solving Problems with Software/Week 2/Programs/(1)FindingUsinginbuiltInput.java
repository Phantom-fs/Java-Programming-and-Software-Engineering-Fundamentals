/*
Program to find the Gene strand from the pre-given input
*/
//Input is pre-provided, by defining DNA in String in the program itself
//Program 1 in Week 2
//Coder: Phantom-fs

public class FindingUsinginbuiltInput 
{
    public String findSimpleGene (String dna)
    {
        int startIndex = dna.indexOf("ATG");

        //if no start codon found i.e. ATG is not present in the string
        if(startIndex == -1)
        {
            return "No Gene Found";
        }


        int stopIndex = dna.indexOf("TAA", startIndex+3);

        //if no stop codon found i.e. TAA is not present in the string
        if(stopIndex == -1)
        {
            return "No Gene Found";
        }

        //must be in the multiple of 3 as 3 codons are there in a gene
        if ((stopIndex - startIndex) % 3 == 0)
        {
            return dna.substring(startIndex, stopIndex+3);
        }

        else
        {
            return "No Gene Found";
        }
    }

    public static void main(String[] args) 
    {
        String newline = System.lineSeparator();

        String dna_strand1 = "ATGCGATACGCTTGATAATGAGAA";
        String dna_strand2 = "ATGCGATACGTGAAA";
        String dna_strand3 = "AACCGAATGTCAATCGAATAAGCAACC";
        
        FindingUsinginbuiltInput obj = new FindingUsinginbuiltInput();

        System.out.print(newline+newline+"****************************************************************************************"+newline);

        System.out.print(newline+"DNA Strand 1 : " + dna_strand1);
        System.out.println(newline+"Gene : " + obj.findSimpleGene(dna_strand1));

        System.out.print(newline+"DNA Strand 2 : " + dna_strand2);
        System.out.println(newline+"Gene : " + obj.findSimpleGene(dna_strand2));

        System.out.print(newline+"DNA Strand 3 : " + dna_strand3);
        System.out.println(newline+"Gene : " + obj.findSimpleGene(dna_strand3));

        System.out.print(newline+"****************************************************************************************"+newline+newline);
    }    
}
