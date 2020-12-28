package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentTwo;

import java.io.IOException;

//with this class the Random reading experiment can be done on a file using the second implementation of the read stream and
// calculating the length of the file in randjump using the second implementation
public class RandomReadingExperimentTwo implements RandomReadStreamInterface{
    public void executeExperiment(String fileName, int j) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodTwoImpl(), new LengthReadStreamExperimentTwo());
        randomReadingExperiment.randjump(fileName, j);
    }
}
