package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;

import java.io.IOException;

//with this class the lengthexperiment can be done on a file using the second implementation of the read stream
public class LengthReadStreamExperimentTwo implements LengthReadStreamInterface{
    public int executeExperiment(String fileName)throws IOException {
        LengthExperiment sequentialExperiment =
                new LengthExperiment(new ReadStreamMethodTwoImpl());
        return sequentialExperiment.calculateSum(fileName);
    }
}
