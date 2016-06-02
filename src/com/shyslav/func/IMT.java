package com.shyslav.func;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 29.05.2016.
 */
public class IMT {
    private int k;
    private ArrayList<String> j;
    private ArrayList<String> formula;
    private int l;
    private int sum;

    public IMT(int k, ArrayList<String> j, ArrayList<String> formula, int l, int sum) {
        this.k = k;
        this.j = new ArrayList<>(j);
        this.formula = new ArrayList<>(formula);
        this.l = l;
        this.sum = sum;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getJ() {
        return String.join("\n",j);
    }

    public ArrayList<String> getJList() {
        return j;
    }

    public void setJ(ArrayList<String> j) {
        this.j = j;
    }

    public String getFormula() {
        return String.join("\n",formula);
    }
    public ArrayList<String> getFormulaList()
    {
        return formula;
    }

    public void setFormula(ArrayList<String> formula) {
        this.formula = formula;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public static int GetFormulaValue(String imt)
    {
        String [] result = imt.split("=");
        int res = Integer.parseInt(result[result.length-1].trim());
        return res;
    }
}
