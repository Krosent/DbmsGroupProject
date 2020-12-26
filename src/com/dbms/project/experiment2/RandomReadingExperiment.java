package com.dbms.project.experiment2;

import com.dbms.project.experiment1.LengthExperiment;
import com.dbms.project.experiment1.LengthReadStreamInterface;
import com.dbms.project.experiment1.LengthReadStreamMethodFour;
import com.dbms.project.experiment1.LengthReadStreamMethodOne;
import com.dbms.project.streamInterfaces.ReadStreamInterface;
import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class RandomReadingExperiment {
    ReadStreamInterface readStream;
    LengthReadStreamInterface lengthreadStream;
    int sum = 0;
    String pathImdb = "src/com/dbms/project/data/";

    public RandomReadingExperiment(ReadStreamInterface readStream, LengthReadStreamInterface lengthCalculatorStream) {
        this.readStream = readStream;
        this.lengthreadStream = lengthCalculatorStream;
    }

    // f is the name of one of the csv files in the IMDB dataset, and j is a positive integer.
    public void randjump(String f, int j) throws IOException {
        // length of a file
        int lenghtOfFile = lengthreadStream.calculateSum(f);
        System.out.println("lenght of file: "+ lenghtOfFile );
        int sum = 0;

        readStream.open(pathImdb + f);

        // Iterate j times
        for(int i=0; i<j;i++) {
            // Compute random number p
            int minimum = 0;
            int maximum = lenghtOfFile;

            Random rn = new Random();
            int range = maximum - minimum + 1;

            // p value finally computed here
            int p =  rn.nextInt(range) + minimum;

            System.out.println("BEFORE SEEK");
            // Seek to position p.
            readStream.seek(p);
            System.out.println("AFTER SEEK" + p);

            // Read the line
            int l = readStream.readLn().length();
            System.out.println("length" + l);

            sum += l;
        }

        System.out.println("Iteration sum is: " + sum);
    }
}
