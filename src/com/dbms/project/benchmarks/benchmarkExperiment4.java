package com.dbms.project.benchmarks;

import com.dbms.project.MultiWayMerge.Extsort;
import com.dbms.project.alternative.ReadStreamMethodTwoImpl;
import com.dbms.project.alternative.WriteStreamMethodThreeImpl;
import com.dbms.project.experiment3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class benchmarkExperiment4 {
    //results are stored to keep the compiler from optimising the code obtaining them away
    public static List<Integer> use_results = new ArrayList<Integer>(10000);

    //Identifiers for different experimental setups
    public enum Strategy{
        VER_18MB_2ndK_1and5mbBuffer_D2,
        VER_18MB_2ndK_1and5mbBuffer_D4,
        VER_18MB_2ndK_3mbBuffer_D2,
        VER_18MB_2ndK_3mbBuffer_D4,

        VER_2and5MB_2ndK_10mbBuffer_D2,
        VER_2and5MB_2ndK_15mbBuffer_D4,
        VER_2and5MB_2ndK_20mbBuffer_D2,
        VER_2and5MB_2ndK_20mbBuffer_D4,

        VER_2and5MB_5thK_10mbBuffer_D2,
        VER_2and5MB_5thK_15mbBuffer_D4,
        VER_2and5MB_5thK_20mbBuffer_D2,
        VER_2and5MB_5thK_20mbBuffer_D4
    }

        //Actual instantiations of the different strategies compared
    static final List<Function<Integer,Integer>> strategies =
                Arrays.asList(
                        /* // 18 mb file size, 1.5 mb buffer, second column, d is 2
                        version1("/company_name.csv", 2, 1572864, 2),

                        // 18 mb file size, 1.5 mb buffer, second column, d is 2
                        version1("/company_name.csv", 2, 1572864, 4),

                        // 18 mb file size, 3 mb buffer, second column, d is 2
                        version1("/company_name.csv", 2, 3145728, 2),

                        // 18 mb file size, 3 mb buffer, second column, d is 2
                        version1("/company_name.csv", 2, 3145728, 4),
                         */

                        /// SORTED VERSION of compa ny name test
                        // 18 mb file size, 1.5 mb buffer, second column, d is 2
                        version1("/company_name_sorted.csv", 2, 1572864, 2),

                        // 18 mb file size, 1.5 mb buffer, second column, d is 2
                        version1("/company_name_sorted.csv", 2, 1572864, 4),

                        // 18 mb file size, 3 mb buffer, second column, d is 2
                        version1("/company_name_sorted.csv", 2, 3145728, 2),

                        // 18 mb file size, 3 mb buffer, second column, d is 2
                        version1("/company_name_sorted.csv", 2, 3145728, 4)

                        /////////

                        /*
                        // 2.4 mb file size, 0.41 mb buffer, second column, d is 2
                        version1("/complete_cast.csv", 2, 429916, 2),

                        // 2.4 mb file size, 0.41 mb buffer, second column, d is 2
                        version1("/complete_cast.csv", 2, 429916, 4),

                        // 2.4 mb file size, 0.82 mb buffer, second column, d is 2
                        version1("/complete_cast.csv", 2, 859832, 2),

                        // 2.4 mb file size, 0.82 mb buffer, second column, d is 2
                        version1("/complete_cast.csv", 2, 859832, 4),

                        ///

                        // 2.4 mb file size, 0.41 mb buffer, fourth column, d is 2
                        version1("/complete_cast.csv", 4, 429916, 2),

                        // 2.4 mb file size, 0.41 mb buffer, fourth column, d is 2
                        version1("/complete_cast.csv", 4, 429916, 4),

                        // 2.4 mb file size, 0.82 mb buffer, fourth column, d is 2
                        version1("/complete_cast.csv", 4, 859832, 2),

                        // 2.4 mb file size, 0.82 mb buffer, fourth column, d is 2
                        version1("/complete_cast.csv", 4, 859832, 4)
                         */
                        );

        /*
         * main method, to be used to perform run time measurements
         *
         * command-line args:
         * args[0]: # repetitions to perform
         */
        public static void main(String[] args) {

            int n_repetitions = 20;
            System.out.println("Repeating each measurement "+n_repetitions+" times.");

            File res_file = new File("runtimes4_1.csv");
            System.out.println("Measuring runtimes for files ");
            for(int j = 0; j < strategies.size(); j++){
                System.out.print(Strategy.values()[j]+": ");
                System.out.flush();
                //measure runtimes
                List<Long> rts = benchmark(0,strategies.get(j),n_repetitions);
                //write to file
                write2file(res_file, Strategy.values()[j],rts);
                System.out.println("v");
            }
        }


        //writes runtimes for a given strategy to file
        static private void write2file(File f, Strategy s, List<Long> runtimes){
            PrintWriter csv_writer;
            try {
                csv_writer = new PrintWriter(new FileOutputStream(f,true));
                String line = ""+s;
                for(Long rt : runtimes){
                    line += ","+rt;
                }
                csv_writer.println(line);
                csv_writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * Measures the runtime(s) (in nanoseconds) for a given strategy on a given data preset for a given number of repetitions.
         *
         * @param strategy: The filtering strategy, i.e. a function mapping to length
         * @param nrep: The number of times the experiment should be repeated consecutively
         * @return A list of nrep runtimes (in nanoseconds), where the i'th element is the runtime of the i'th repetition
         */
        static public List<Long> benchmark(int B,Function<Integer,Integer> strategy, int nrep){
            List<Long> runtimes = new ArrayList<Long>(nrep);
            for(int i = 0; i < nrep; i++){
                System.gc(); //a heuristic to avoid Garbage Collection (GC) to take place in timed portion of the code
                long before = System.nanoTime(); //time is measured in ns
                use_results.add(strategy.apply(B)); //store results to avoid code getting optimised away
                runtimes.add(System.nanoTime()-before);
            }
            return runtimes;
        }

        /**
         * @return The experiment for version 1
         */

        static Function<Integer,Integer> version1(String file, int k, int M, int d){
            return (Integer i) -> {
                Extsort sort = new Extsort(file, k, M, d, new ReadStreamMethodTwoImpl(),
                        new WriteStreamMethodThreeImpl(12000));

                try {
                    sort.doSort();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            };
        }
}
