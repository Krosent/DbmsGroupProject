package com.dbms.project.experiment1;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.IOException;


public class LengthExperiment{
    ReadStreamInterface version;
    int sum = 0;
    String pathImdb = "src/com/dbms/project/data/";
    public LengthExperiment(ReadStreamInterface v) {
        this.version = v;
    }

    //the total length of this file is returned. Concretely, the length is calculated by reading the file once completely,
    //reading it line by line with the ‘readln’ operation corresponding to the chosen implementation 'version'.
    public int calculateSum(String f)throws IOException{
        String file = pathImdb + f;
        version.open(file);

        while (!(version.endOfStream())) {
            String line = version.readLn();
            sum += line.length();
        }
        //outputting sum as written, otherwise the compiler/interpreter may actually infer that you are reading the file without actually doing anything with it, and subsequently optimize your program by not reading the file at all.
        System.out.println("total length of " + f +": "+ sum);
        return sum;
    };
}
