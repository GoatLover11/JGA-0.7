package utils;

//gráfszinezés genetikai algorithm

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Population p = new Population(10, 100);
        int i = 0;

        p.rPops();
        p.calcFit();

        while (true){
            try {
                System.out.println("Gen: " + i);
                Thread.sleep(200);
                p.mutation();
                p.calcFit();
                i++;
            }catch (Exception e){
                System.err.println(e);
            }

        }


    }
}
