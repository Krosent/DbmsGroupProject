package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodOneImpl;
import com.dbms.project.streamInterfaces.ReadStreamInterface;

public class LengthReadStreamMethodOne implements LengthReadStreamInterface{
    ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
    LengthExperiment length1 = new LengthExperiment(readStreamInstance);

    public int calculateSum(String f) {
        int sum = 0;

            sum = length1.calculateSum(f);
        return sum;
    }
}
