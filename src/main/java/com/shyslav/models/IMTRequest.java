package com.shyslav.models;

/**
 * @author Shyshkin Vladyslav on 20.05.17.
 */
public class IMTRequest {
    private String dishName;
    private int dishID;
    private int N;
    private int[] A;
    private int[] H;
    private int[] D;

    public IMTRequest() {
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int[] getA() {
        return A;
    }

    public void setA(int[] a) {
        A = a;
    }

    public int[] getH() {
        return H;
    }

    public void setH(int[] h) {
        H = h;
    }

    public int[] getD() {
        return D;
    }

    public void setD(int[] d) {
        D = d;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }
}
