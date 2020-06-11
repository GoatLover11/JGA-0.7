package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population {

    private static ArrayList <Gpoint> Graf = new ArrayList<>();
    private static ArrayList<Color> Colors;
    private ArrayList <Pop> Pops;
    private ArrayList <Pop> OldPops;
    private int N, DB;
    private double AvgFit;

    public Population(int n, int db){
        OldPops = new ArrayList<>();
        Colors = new ArrayList();
        Pops = new ArrayList<>();
        this.N = n;
        this.DB = db;

        Colors.add(Color.BLACK);
        Colors.add(Color.RED);
        Colors.add(Color.GREEN);
        Colors.add(Color.YELLOW);
        Colors.add(Color.BLUE);
        Colors.add(Color.MAGENTA);
        Colors.add(Color.CYAN);

        //System.out.println(Pops.getClass());

        //graf generálás
        for (int i = 0; i < n; i++) {
            Graf.add(new Gpoint());
        }

        //graf öszekötés
        for (int i = 0; i < n; i++) {

            //randpm pontok öszekötése az i elemel kölcsönös kapcsolat kialakitás
            int r = (int) (Math.random()*(n +1) -1);
            if (r >= 0){
                Graf.get(i).Points.add(r);

                //hurkot egynek vegye
                if (r != i){
                    Graf.get(r).Points.add(i);
                }
                //System.out.println("r: " + r);
            }
        }

        pringGraf();

    }

    public void mutation(){

        if (AvgFit < 0.1){
            AvgFit = 0.1;
        }

        OldPops.clear();

        //legjobb fele kiválasztása arányosan
        for (int i = 0; i < Pops.size(); i++) {
            //System.out.println("Fit: " + Pops.get(i).FitC + " Egyed esélye: " + Pops.get(i).FitC / (N * AvgFit));
            if (Math.random() < Pops.get(i).FitC / (N * AvgFit)){
                OldPops.add(Pops.get(i));
            }
        }
        System.out.println("Szülök db: " + OldPops.size());

        //szülök számának random egyedekel való növelés
        int r = (int) (Math.random()*5) +1;    //1-5 random egyed
        //r db egyed
        for (int j = 0; j < r; j++) {

            Pop p = new Pop();

            //N db gen
            for (int i = 0; i < N; i++) {
                p.Nums.add((int) (Math.random() * Colors.size()));
            }
            OldPops.add(p);
        }

        Pops.clear();
        Pops.add(OldPops.get(0));       //legfitebb elem hozzáadás

        //megmaradnépeség random mutálása
        for (Pop p : OldPops) {
            for (int i = 0; i < p.Nums.size(); i++) {
                if (Math.random() > 1 / p.Nums.size()){
                    p.Nums.set(i, (int) (Math.random() * Colors.size()));
                }
            }
        }

        //népeség helyreálitás kicserélésel
        while (Pops.size() != DB){
            Pop p = new Pop();
            int p1 = (int) (Math.random() * OldPops.size());
            int p2 = (int) (Math.random() * OldPops.size());

            for (int i = 0; i < N; i++) {
                if (Math.random() > 0.5){
                    p.Nums.add(OldPops.get(p1).Nums.get(i));
                }else {
                    p.Nums.add(OldPops.get(p2).Nums.get(i));
                }
            }

            Pops.add(p);
        }


    }

    private void sortFit(){
        Collections.sort(Pops, new Comparator<Pop>() {
            @Override
            public int compare(Pop o1, Pop o2) {
                return Integer.valueOf(o2.FitC).compareTo(o1.FitC);
            }
        });
        AvgFit = 0.0;
        for (Pop p : Pops) {
            AvgFit += p.FitC;
        }
        AvgFit = AvgFit/DB;

        System.out.println();
        System.out.println("Max fitc: " + Pops.get(0).FitC + " avgFit: " + AvgFit);
        pringGraf(Pops.get(0).Nums);
        System.out.println();
        System.out.println("Min: " + Pops.get(Pops.size() -1).FitC);
        pringGraf(Pops.get(Pops.size() -1).Nums);
        System.out.println();

        Log.log(Pops.get(0).FitC + "\t" + AvgFit + "\t"+ Pops.get(Pops.size() -1).FitC + "\t" + OldPops.size());
    }

    public void calcFit(){
        //populácion végigmegy
        for (Pop p : Pops) {
            p.Fit = 0;
            //egyes egyed szinsoredjén megy végig
            for (int i = 0; i < p.Nums.size(); i++) {
                //a gráf elemein megy végig hogy ösze e van kötve olyan szinüvel
                for (int j = 0; j < Graf.get(i).Points.size(); j++) {
                    if (p.Nums.get(i) != p.Nums.get(Graf.get(i).Points.get(j))){
                        p.Fit += 2;
                    }
                }
            }
            //System.out.println();
            p.calc();
            //pringGraf(p.Nums);
        }
        sortFit();

    }

    //ranndom szinekkel feltöltés
    public void rPops(){
        for (int i = 0; i < DB; i++) {
            Pops.add(new Pop());
        }

        for (Pop p : Pops) {
            for (int i = 0; i < N; i++) {
                p.Nums.add((int) (Math.random() * Colors.size()));
            }
        }
    }

    //graf kiiratas
    public void pringGraf(){
        System.out.println();
        for (int i = 0; i < Graf.size(); i++) {

            System.out.print(Graf.get(i).C);
            System.out.print( i + ": ");
            System.out.print(Color.reset);

            for (int j = 0; j < Graf.get(i).Points.size(); j++) {
                System.out.print(Graf.get(j).C);
                System.out.print(Graf.get(i).Points.get(j) + ", ");
                System.out.print(Color.reset);
            }
            System.out.println();
        }

    }


    //szinnel kiirás
    public void pringGraf(ArrayList<Integer> num){
        for (int i = 0; i < Graf.size(); i++) {

            System.out.print(Colors.get(num.get(i)));
            System.out.print( i + ": ");
            System.out.print(Color.reset);

            for (int j = 0; j < Graf.get(i).Points.size(); j++) {

                System.out.print(Colors.get(num.get(Graf.get(i).Points.get(j))));
                //System.out.print(Graf.get(i).Points.get(j) + " {" + num.get(i) + ", " + num.get(j) + "}, ");
                System.out.print(Graf.get(i).Points.get(j) + ", ");
                System.out.print(Color.reset);

                if (num.get(i) != (num.get(Graf.get(i).Points.get(j)))){
                    //System.out.print("+1 " );
                }
            }
            System.out.println();
        }

        for (int i = 0; i < num.size(); i++) {
        }

    }
}
