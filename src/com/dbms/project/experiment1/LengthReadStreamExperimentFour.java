package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodFourImpl;

import java.io.IOException;

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
