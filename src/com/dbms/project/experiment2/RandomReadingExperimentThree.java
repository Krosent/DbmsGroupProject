package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodThreeImpl;
import com.dbms.project.experiment1.LengthReadStreamMethodThree;

import java.io.IOException;

public class RandomReadingExperimentThree {
    public void executeExperiment(String fileName,int B) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodThreeImpl(B), new LengthReadStreamMethodThree(B));
        randomReadingExperiment.randjump(fileName, 5);
    }
}
