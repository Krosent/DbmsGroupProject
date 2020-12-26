package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodOneImpl;
import com.dbms.project.experiment1.LengthReadStreamMethodOne;

import java.io.IOException;

public class RandomReadingExperimentOne {

    public void executeExperiment(String fileName) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodOneImpl(), new LengthReadStreamMethodOne());
        randomReadingExperiment.randjump(fileName, 3);
    }


}
