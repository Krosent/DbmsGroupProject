package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodOneImpl;
import com.dbms.project.experiment1.LengthReadStreamExperimentOne;

import java.io.IOException;

//with this class the Random reading experiment can be done on a file using the first implementation of the read stream and
// calculating the length of the file in randjump using the first implementation
public class RandomReadingExperimentOne implements RandomReadStreamInterface{

    public void executeExperiment(String fileName, int j) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodOneImpl(), new LengthReadStreamExperimentOne());
        randomReadingExperiment.randjump(fileName, j);
    }


}
