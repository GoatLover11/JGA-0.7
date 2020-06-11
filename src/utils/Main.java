package utils;

//gráfszinezés genetikai algorithm

import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        int n = 15, db = 100;
        Population p = new Population(n, db);
        int i = 0;

        p.rPops();
        p.calcFit();
        Log.createLog();
        Log.log("Max\tAvg\tMin\tSzulok db\tGraf pontok: " + n + "\tEgyedszam: " + db);

        while (i < 10000){
            try {
                Thread.sleep(1);
                System.out.println("Gen: " + i);
                p.mutation();
                p.calcFit();
                i++;
                System.out.println("---------End---------");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println(e);
                break;
            }

        }


    }
}
