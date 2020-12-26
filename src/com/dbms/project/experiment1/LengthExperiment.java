package com.dbms.project.experiment1;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;


public class LengthExperiment{
    ReadStreamInterface version;
    int sum = 0;
    String pathImdb = "src/com/dbms/project/data/";
    public LengthExperiment(ReadStreamInterface v) {
        this.version = v;
    }

    public int calculateSum(String f){
        String file = pathImdb + f;
        try {
            version.open(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                while (!(version.endOfStream())) {
                    String line = version.readLn();
                    sum += line.length();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("sum of" + f + sum);
        return sum;
    };
}
