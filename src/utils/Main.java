package utils;

//gráfszinezés genetikai algorithm

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Population p = new Population(10, 10000);
        int i = 0;

        p.rPops();
        p.calcFit();

        while (true){
            try {
                Thread.sleep(200);
                System.out.println("Gen: " + i);
                p.mutation();
                p.calcFit();
                i++;
                System.out.println("---------End---------");
                System.out.println();
            }catch (Exception e){
                System.err.println(e);
            }

        }


    }
}
