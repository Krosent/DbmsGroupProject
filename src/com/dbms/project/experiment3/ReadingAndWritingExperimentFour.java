package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodFourImpl;

import java.io.IOException;

//with this class the reading and writing experiment can be done on a couple of files using the fourth implementation of the write stream and
// outputting all the lines of all the files in an output file, the name of which is given as a parameter, together with the B value
public class ReadingAndWritingExperimentFour implements ReadAndWriteStreamInterface{
    int B;
    String nameOutputFile;
    public ReadingAndWritingExperimentFour(int B,String outputFile) {
        this.B = B;
        this.nameOutputFile = outputFile;}

    public void executeExperiment(String... fileNames) throws IOException {
        ReadingAndWritingExperiment experiment =
                new ReadingAndWritingExperiment(new WriteStreamMethodFourImpl(B),nameOutputFile);
        experiment.rrmerge(fileNames);
    }
}
