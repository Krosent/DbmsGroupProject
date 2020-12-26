package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;
import com.dbms.project.streamInterfaces.ReadStreamInterface;

public class LengthReadStreamMethodTwo implements LengthReadStreamInterface{
    ReadStreamInterface readStreamInstance = new ReadStreamMethodTwoImpl();
    LengthExperiment length1 = new LengthExperiment(readStreamInstance);

    public int calculateSum(String f) {
        int sum = 0;
            sum = length1.calculateSum(f);

        return sum;
    }
}
