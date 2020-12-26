package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;
import com.dbms.project.experiment1.LengthReadStreamMethodTwo;

import java.io.IOException;

public class RandomReadingExperimentTwo {
    public void executeExperiment(String fileName) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodTwoImpl(), new LengthReadStreamMethodTwo());
        randomReadingExperiment.randjump(fileName, 5);
    }
}
