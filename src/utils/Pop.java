package utils;

import java.util.ArrayList;
import java.util.HashSet;

public class Pop {

    public ArrayList<Integer> Nums;
    public  int Fit = 0, FitC = 0;
    private int ColorNum = 1;

    public Pop(){
        Nums = new ArrayList<>();
    }

    public void calc(){
        HashSet<Integer> hs = new HashSet<>();
        for (int i : Nums) {
            hs.add(i);
        }
        ColorNum = hs.size();
        FitC = Fit - ColorNum;
        //System.out.println("Fit: " + Fit + " Colors: " + ColorNum + " FitC: " + FitC);
    }
}
