package com.dbms.project.experiment2;

import com.dbms.project.old.ReadStreamMethodOneImpl;

import java.io.IOException;

public class RandomReadingExperimentOne {

    public void executeExperiment(String fileName) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodOneImpl(), new com.dbms.project.alternative.ReadStreamMethodOneImpl());
        randomReadingExperiment.randjump(fileName, 3);
    }


}
