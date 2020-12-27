package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;

import java.io.IOException;

public class LengthReadStreamExperimentTwo implements LengthReadStreamInterface{
    public int executeExperiment(String fileName)throws IOException {
        LengthExperiment sequentialExperiment =
                new LengthExperiment(new ReadStreamMethodTwoImpl());
        return sequentialExperiment.calculateSum(fileName);
    }
}
