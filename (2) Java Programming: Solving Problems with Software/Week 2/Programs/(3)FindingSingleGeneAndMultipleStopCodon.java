/*
Program to find the simple Gene strand from the pre-given input and having muliple stop codon
*/
//Input is pre-provided, by defining DNA in String in the program itself
//Program 3 in Week 2
//rename the file to FindingSingleGeneAndMultipleStopCodon
//Coder: Phantom-fs

import java.lang.String;

public class FindingSingleGeneAndMultipleStopCodon 
{
    public int findStopCodon (String dna, int startIndex, String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);

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
    
    public String findSimpleGene (String dna)
    {
        int startIndex = dna.indexOf("ATG");

        if (startIndex != -1)
        {
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

                return (dna.substring(startIndex, minIndex+3));
            }

            else
                return "No Gene found in DNA strand";
        }

        return "No Gene found in the DNA strand";
    }

    public static void main(String[] args) 
    {
        String newline = System.lineSeparator();
        String dna[] = new String[15];

        dna[0]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACC";
        dna[1]= "AATGCGAATAAGCCGATAGATGTAACAATCGAATAAGCAACCTAA";
        dna[2]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTAG";
        dna[3]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTGA";
        dna[4]= "AATGCGAATAAGCCGAATGTAACAATCGATAAATAAGCAACCTAA";
        dna[5]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTAG";
        dna[6]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTGA";
        dna[7]= "AATGCGAATAAGCCGAATGTAACAATAGTCGAATAAGCAACCTAA";
        dna[8]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTAG";
        dna[9]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTGA";
        dna[10]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTGATAA";
        dna[11]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTAG";
        dna[12]= "AATGCGAATAAGCCGAATGTGATAACAATCGAATAAGCAACCTGA";
        dna[13]= "AATGCGAATAAGCCGAATGTAGTAACAATCGAATAAGCAACCTAGTAA";
        dna[14]= "AATGCGAATAAGCCGAATGTAACAATCGAATAAGCAACCTAATAG";

        FindingSingleGeneAndMultipleStopCodon obj = new FindingSingleGeneAndMultipleStopCodon();

        System.out.print(newline+newline+"****************************************************************************************"+newline);

        for(int i=0; i<15; i++)
        {
            System.out.print(newline+"DNA strand "+(i+1)+" : "+dna[i]);
            System.out.print(newline+"Gene: "+obj.findSimpleGene(dna[i]));
            System.out.print(newline);
        }

        System.out.print(newline+"****************************************************************************************"+newline+newline);
    }
}
