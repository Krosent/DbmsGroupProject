package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodOneImpl;

import java.io.IOException;

public class LengthReadStreamExperimentOne implements LengthReadStreamInterface{
    public int executeExperiment(String fileName)throws IOException {
        LengthExperiment sequentialExperiment =
                new LengthExperiment(new ReadStreamMethodOneImpl());
        return sequentialExperiment.calculateSum(fileName);
    }

}
