package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentTwo;

import java.io.IOException;

public class RandomReadingExperimentTwo implements RandomReadStreamInterface{
    public void executeExperiment(String fileName, int j) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodTwoImpl(), new LengthReadStreamExperimentTwo());
        randomReadingExperiment.randjump(fileName, j);
    }
}
