package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodFourImpl;

import java.io.IOException;

//with this class the lengthexperiment can be done on a file using the fourth implementation of the read stream with
//B given as a parameter
public class LengthReadStreamExperimentFour implements LengthReadStreamInterface{
    int B;
    public LengthReadStreamExperimentFour(int B) {
        this.B = B;}
    public int executeExperiment(String fileName)throws IOException {
        LengthExperiment sequentialExperiment =
                new LengthExperiment(new ReadStreamMethodFourImpl(B));
        return sequentialExperiment.calculateSum(fileName);
    }
}
