package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodThreeImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentThree;

import java.io.IOException;

public class RandomReadingExperimentThree implements RandomReadStreamInterface{
    int B;
    public RandomReadingExperimentThree(int B) {
        this.B = B;}
    public void executeExperiment(String fileName, int j) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodThreeImpl(B), new LengthReadStreamExperimentThree(B));
        randomReadingExperiment.randjump(fileName, j);
    }
}
