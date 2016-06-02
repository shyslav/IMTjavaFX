package com.shyslav.func;

/**
 * Created by Shyshkin Vladyslav on 29.05.2016.
 */
public class Way {
    private int fMin;
    private int indA;
    private int indF;

    public Way(int fMin, int indA, int indF) {
        this.fMin = fMin;
        this.indA = indA;
        this.indF = indF;
    }

    public int getfMin() {
        return fMin;
    }

    public void setfMin(int fMin) {
        this.fMin = fMin;
    }


    public int getIndA() {
        return indA;
    }

    public void setIndA(int indA) {
        this.indA = indA;
    }

    public int getIndF() {
        return indF;
    }

    public void setIndF(int indF) {
        this.indF = indF;
    }
}
