package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodThreeImpl;

import java.io.IOException;

//with this class the lengthexperiment can be done on a file using the third implementation of the read stream with
//B given as a parameter
public class LengthReadStreamExperimentThree implements LengthReadStreamInterface{
    int B;
    public LengthReadStreamExperimentThree(int B) {
        this.B = B;}
    public int executeExperiment(String fileName)throws IOException {
        LengthExperiment sequentialExperiment =
                new LengthExperiment(new ReadStreamMethodThreeImpl(B));
        return sequentialExperiment.calculateSum(fileName);
    }
}
