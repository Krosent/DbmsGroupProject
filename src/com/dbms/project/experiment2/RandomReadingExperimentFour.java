package com.dbms.project.experiment2;

import com.dbms.project.alternative.ReadStreamMethodFourImpl;
import com.dbms.project.alternative.ReadStreamMethodThreeImpl;
import com.dbms.project.experiment1.LengthReadStreamMethodFour;

import java.io.IOException;

public class RandomReadingExperimentFour {
    public void executeExperiment(String fileName,int B) throws IOException {
        RandomReadingExperiment randomReadingExperiment =
                new RandomReadingExperiment(new ReadStreamMethodFourImpl(B), new LengthReadStreamMethodFour(B));
        randomReadingExperiment.randjump(fileName, 5);
    }
}
