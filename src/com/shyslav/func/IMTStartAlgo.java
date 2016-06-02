package com.shyslav.func;

import java.util.ArrayList;

public class IMTStartAlgo {
    //Обьем посвки в k-м периоре
    private int[] x;
    //Сумарный спрос на k-м преоде
    int[] d;
    //Остаток запаса на в k-1 периоде или начале в k-го
    int[] y;
    //Затраты на исполнение
    int[] c;
    //Затраты на доставку
    int[] A;
    //Затраты на хранение
    int[] h;
    //количество периодов
    int n = 6;

    int l = 1;

    int[] f;

    ArrayList<Way> way = new ArrayList<>();
    ArrayList<IMT> imt = new ArrayList<>();

    public IMTStartAlgo(int[] d, int[] a, int[] h, int n) {
        x = new int[n];
        y = new int[n];
        c = new int[n];
        f = new int[n + 1];
        f[f.length - 1] = 0;
        this.d = d;
        this.A = a;
        this.h = h;
        this.n = n;
    }


    //    public IMTStartAlgo(int n) {
//        this.n = n;
////        d = new int [n];
//        x = new int[n];
//        y = new int[n];
//        c = new int[n];
//        A = new int[n];
//        h = new int[n];
//        f = new int[n + 1];
//        //**************************
//        d = new int[]{15, 8, 20, 11, 8, 15};
//        for (int i = 0; i < n; i++) {
//            A[i] = 50;
//            h[i] = 5;
//            f[i] = 0;
//        }
//        f[f.length - 1] = 0;
//    }

//    public Main(int n) {
//        this.n = n;
////        d = new int [n];
//        x = new int[n];
//        y = new int[n];
//        c = new int[n];
//        A = new int[n];
//        h = new int[n];
//        f = new int[n + 1];
//        //**************************
//        d = new int[]{30, 30, 30, 30, 30, 30, 30};
//        A = new int [] {200,200,200,200,200,200,200};
//        h = new int [] {5,3,5,5,4,1,2};
//        f[f.length - 1] = 0;
//    }

//    public Main(int n) {
//        this.n = n;
////        d = new int [n];
//        x = new int[n];
//        y = new int[n];
//        c = new int[n];
//        A = new int[n];
//        h = new int[n];
//        f = new int[n + 1];
//        //**************************
//        d = new int[]{25, 10, 60, 40, 25, 10, 45};
//        A = new int [] {100,100,250,250,250,150,150};
//        h = new int [] {2,2,2,2,2,2,2};
//        f[f.length - 1] = 0;
//    }

//    public Main(int n) {
//        this.n = n;
//        x = new int[n];
//        y = new int[n];
//        c = new int[n];
//        A = new int[n];
//        h = new int[n];
//        f = new int[n + 1];
//        //**************************
//        d = new int[]{10, 100, 60, 30, 115, 70};
//        A = new int [] {150,150,200,150,250,150};
//        h = new int [] {2,3,1,1,1,1};
//        f[f.length - 1] = 0;
//    }

    public ArrayList<IMT> run() {
        System.out.println("Start algo");
        start();
        return imt;
    }

    private void start() {
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                f[i + 1] = minValue(i + 1, l);
            } else {
                f[i + 1] = minValue(i + 1, l);
            }
        }
        getWay();
        System.out.println();
        for (int i = 0 ; i<x.length;i++)
        {
            System.out.print(" " +x[i]);
        }
    }

    private int minValue(int k, int lValue) {
        //Лист который хранит ключ значение, ключ <j,sum> для поиска минимального значения
        ArrayList<KeyValue> keys = new ArrayList<>();
        int tmp = 0;
        //индекс начала для f
        int tr = lValue - 1;
        //лист для формул который потом джоинится с \n
        ArrayList<String> form = new ArrayList<>();
        //лист для шагов который потом джоинится с \n
        ArrayList<String> step = new ArrayList<>();
        for (int x = lValue - 1; x < k; x++) {
            //посчитать сумму h*d
            int sumStor = sumStore(x, k);
            //посчитать полную сумму
            tmp = f[tr] + A[x] + sumStor;
            //добавить формулу
            form.add(generateFormula(tr,x,k)+" = "+f[tr]+"+"+A[x]+"+"+sumStor + " = " +tmp);
            //добавить шаг
            step.add(String.valueOf(x+1));
            System.out.println(tmp);
            keys.add(new KeyValue(x + 1, tmp));
            //увеличить шаг по f на 1
            tr++;
        }
        //Минимальное число из массива ключ-значение
        int minimum = minimum(keys);
        //добавить в лист для таблицы
        imt.add(new IMT(k,step,form,l,minimum));
        return minimum;
    }

    private String generateFormula(int indF ,int step,int k)
    {
        String formula = "f["+indF+"] + A["+step+"] ";
        int sum = 0;
        int newI = 0;
        int newJ = 0;
        for (int i = step; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                newI = i + 1;
                newJ = j + 1;
                formula += "+ h[" + newI + "] * d[" + newJ + "]";
                sum += (h[i] * d[j]);
            }
        }
        if(sum==0)
        {
            return formula+" + 0";
        }
        return formula;
    }
    private int sumStore(int step, int k) {
        int sum = 0;
        for (int i = step; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                sum += h[i] * d[j];
            }
        }
        return sum;
    }

    private int minimum(ArrayList<KeyValue> list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() < min) {
                min = list.get(i).getValue();
                l = list.get(i).getKey();
            }
        }
        System.out.println(l);
        way.add(new Way(min,l,l-1));
        return min;
    }

    private void getWay()
    {
        ArrayList<Integer> getAway = new ArrayList<>();
        System.out.println("*********************************");
        for (int i = way.size()-1; i >= 0; )
        {
            if(way.get(i).getIndF()==i)
            {
                getAway.add(way.get(i).getIndA());
                System.out.print("*"+way.get(i).getIndA());
                i--;
            }else
            {
                getAway.add(way.get(i).getIndA());
                System.out.print("*"+way.get(i).getIndA());
                i = way.get(i).getIndF()-1;
            }
        }
        generateAnswer(getAway);
    }
    private void generateAnswer(ArrayList<Integer> getAway)
    {
        System.out.println("\n*********************************");
        int sum = 0;
        int org = 0;
        boolean twoSumm = false;
        int amountSum = 0;
        for (int i = 1 ; i <= n ; i++)
        {
            if(search(getAway,i+1))
            {
                sum += d[i-1];
                setX(sum,org);
                if(twoSumm)
                {
                    org+=amountSum;
                    amountSum = 0;
                    twoSumm = false;
                }
                org++;
                sum=0;
            }else
            {
                twoSumm = true;
                amountSum++;
                sum+=d[i-1];
            }
        }
        setX(sum,org);
    }
    private boolean search(ArrayList<Integer> getAway, int find)
    {

        for (int i = 0 ; i < getAway.size() ; i++)
        {
            if(getAway.get(i)== find)
            {
                return true;
            }
        }

        return false;
    }

    private void setX(int value ,int p) {
        x[p] = value;
    }

    public int[] getX() {
        return x;
    }
}
