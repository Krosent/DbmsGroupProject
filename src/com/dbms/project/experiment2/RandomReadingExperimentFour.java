package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodFourImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentFour;

import java.io.IOException;

public class RandomReadingExperimentFour implements RandomReadStreamInterface{
    int B;
    public RandomReadingExperimentFour(int B) {
        this.B = B;}
    public void executeExperiment(String fileName, int j) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodFourImpl(B), new LengthReadStreamExperimentFour(B));
        randomReadingExperiment.randjump(fileName,j);
    }
}
