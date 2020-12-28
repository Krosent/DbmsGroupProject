package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodThreeImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentThree;

import java.io.IOException;

//with this class the Random reading experiment can be done on a file using the third implementation of the read stream and B given as a parameter
// and calculating the length of the file in randjump using the third implementation
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
