package com.dbms.project.experiment2;

import com.dbms.project.streamInterfaces.ReadStreamInterface;
import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class RandomReadingExperiment {
    ReadStreamInterface readStream;
    ReadStreamInterface _readStream;
    int sum = 0;
    String pathImdb = "src/com/dbms/project/data/";

    public RandomReadingExperiment(ReadStreamInterface readStream, ReadStreamInterface lengthCalculatorStream) {
        this.readStream = readStream;
        this._readStream = lengthCalculatorStream;
    }

    // f is the name of one of the csv files in the IMDB dataset, and j is a positive integer.
    public void randjump(String f, int j) throws IOException {
        // length of a file
        //int lenghtOfFile = length(f);
        int lenghtOfFile = 5;
        int sum = 0;

        readStream.open(pathImdb + f);

        // Iterate j times
        for(int i=0; i<j;j++) {
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
            System.out.println("AFTER SEEK");

            // Read the line
            int l = readStream.readLn().length();

            sum += l;
        }

        System.out.println("Iteration sum is: " + sum);
    }

    public int length(String f) throws IOException {
        int _sum = 0;
        String file = pathImdb + f;
        try {
            _readStream.open(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            while (!(_readStream.endOfStream())) {
                    String line = _readStream.readLn();
                    System.out.println("Line length: " + line.length());
                    _sum = _sum + line.length();
            }
        }
        return _sum;
    };
}
