package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodThreeImpl;

import java.io.IOException;

//with this class the reading and writing experiment can be done on a couple of files using the third implementation of the write stream and
// outputting all the lines of all the files in an output file, the name of which is given as a parameter, together with the B value
public class ReadingAndWritingExperimentThree implements ReadAndWriteStreamInterface{
    int B;
    String nameOutputFile;
    public ReadingAndWritingExperimentThree(int B,String outputFile) {
        this.B = B;
        this.nameOutputFile = outputFile;}

    public void executeExperiment(String... fileNames) throws IOException {
        ReadingAndWritingExperiment experiment =
                new ReadingAndWritingExperiment(new WriteStreamMethodThreeImpl(B),nameOutputFile);
        experiment.rrmerge(fileNames);
    }
}
