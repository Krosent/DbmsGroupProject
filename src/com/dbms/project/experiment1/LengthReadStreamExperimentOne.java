package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodOneImpl;

import java.io.IOException;

//with this class the lengthexperiment can be done on a file using the first implementation of the read stream
public class LengthReadStreamExperimentOne implements LengthReadStreamInterface{
    public int executeExperiment(String fileName)throws IOException {
        LengthExperiment sequentialExperiment =
                new LengthExperiment(new ReadStreamMethodOneImpl());
        return sequentialExperiment.calculateSum(fileName);
    }

}
