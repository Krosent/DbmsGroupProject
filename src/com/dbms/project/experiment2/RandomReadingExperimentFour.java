package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodFourImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentFour;

import java.io.IOException;

//with this class the Random reading experiment can be done on a file using the fourth implementation of the read stream and B given as a parameter
// and calculating the length of the file in randjump using the fourth implementation
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
