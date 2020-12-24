package com.dbms.project.experiment2;

import com.dbms.project.old.ReadStreamMethodOneImpl;
import com.dbms.project.old.ReadStreamMethodTwoImpl;

import java.io.IOException;

public class RandomReadingExperimentTwo {
    public void executeExperiment(String fileName) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodTwoImpl(), new ReadStreamMethodTwoImpl());
        randomReadingExperiment.randjump(fileName, 5);
    }
}
