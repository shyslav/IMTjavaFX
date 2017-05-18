package com.shyslav.func;

import java.util.ArrayList;

public class IMTAlgoStandart {
    //Обьем посвки в k-м периоре
    private int[] x;
    //Сумарный спрос на k-м преоде
    int[] d;
    //Затраты на доставку
    int[] A;
    //Затраты на хранение
    int[] h;

    //количество периодов
    int n;
    //Условно оптимальное решение
    int l = 1;
    int[] f;

    ArrayList<Way> way = new ArrayList<>();
    ArrayList<IMT> imt = new ArrayList<>();

    public IMTAlgoStandart(int[] d, int[] a, int[] h, int n) {
        x = new int[n];
        f = new int[n + 1];
        f[f.length - 1] = 0;
        this.d = d;
        this.A = a;
        this.h = h;
        this.n = n;
    }

    public ArrayList<IMT> run() {
        System.out.println("----Обычный алгоритм----");
        start();
        return imt;
    }

    /**
     * Функция запуска алгоритм
     */
    private void start() {
        //следать n планирований
        for (int i = 0; i < n; i++) {
                f[i + 1] = minValue(i + 1, l);
        }
        //построить правильный путь
        getWay();
        //вывести ответ
        for (int i = 0; i < x.length; i++) {
            System.out.print(" " + x[i]);
        }
        System.out.println();
    }

    /**
     * Функция которая возвращает минимальное значение на шаге
     *
     * @param k      - шаг
     * @param lValue - индекс начала для f
     * @return минимальное значение (условно оптимальный оптимум f
     */
    private int minValue(int k, int lValue) {
        //Лист который хранит ключ значение, ключ <j,sum> для поиска минимального значения
        ArrayList<KeyValue> keys = new ArrayList<>();
        int tmp = 0;
        //индекс начала для f
        int tr = lValue - 1;
        //лист для формул который потом джоинится с \n - стоимость решения
        ArrayList<String> form = new ArrayList<>();
        //лист для шагов который потом джоинится с \n - j елементы
        ArrayList<String> step = new ArrayList<>();
        for (int x = lValue - 1; x < k; x++) {
            //посчитать сумму h*d
            int sumStor = sumStore(x, k);
            //посчитать полную сумму
            tmp = f[tr] + A[x] + sumStor;
            //добавить формулу
            form.add(generateFormula(tr, x, k) + " = " + f[tr] + "+" + A[x] + "+" + sumStor + " = " + tmp);
            //добавить шаг
            step.add(String.valueOf(x + 1));
            keys.add(new KeyValue(x + 1, tmp));
            //увеличить шаг по f на 1
            tr++;
        }
        //Минимальное число из массива ключ-значение
        int minimum = minimum(keys);
        //добавить в лист для таблицы
        imt.add(new IMT(k, step, form, l, minimum));
        return minimum;
    }

    /**
     * Функция генерации формулы для вывода
     *
     * @param indF индекс f
     * @param step число с которого начинаем генерацию (j)
     * @param k    текущий шаг
     * @return сгенерированную формулу для стандартного алгоритма
     */
    private String generateFormula(int indF, int step, int k) {
        String formula = "f[" + indF + "] + A[" + step + "] ";
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
        if (sum == 0) {
            return formula + " + 0";
        }
        return formula;
    }

    /**
     * Функция для подсчета суммы h * d
     * @param step - последний период поставки j
     * @param k - шаг на котором мы находимся
     * @return - сумму h*d
     */
    private int sumStore(int step, int k) {
        int sum = 0;
        for (int i = step; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                sum += h[i] * d[j];
            }
        }
        return sum;
    }

    /**
     * Функция возвращающая минимум из всех формул на шаге
     *
     * @param list лист который содержит шаг и значение (l;f(x))
     * @return минимальное значение
     */
    private int minimum(ArrayList<KeyValue> list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            //если текущий минимум меньше приведущего на данном шаге
            if (list.get(i).getValue() < min) {
                //изменить текущий минимум
                min = list.get(i).getValue();
                //изменить условный опимум l
                l = list.get(i).getKey();
            }
        }
        //добавить в лист пути минимальное значение ф, идекс А, индекс Ф
        way.add(new Way(min, l, l - 1));
        return min;
    }

    /**
     * Функция которая формирует правильный путь по f
     */
    private void getWay() {
        //лист который содержит индексы правильного пути
        ArrayList<Integer> getAway = new ArrayList<>();
        for (int i = way.size() - 1; i >= 0; ) {
            //если элемент пути равен текущему i, значит мы переходим на вершину выше
            if (way.get(i).getIndF() == i) {
                //добавить в путь вершину с индексом А
                getAway.add(way.get(i).getIndA());
                //следующая посещаемая вершина
                i--;
            }
            //иначе мы переходим на вершину при индексе F
            else {
                //добавить в путь вершину с индексом А
                getAway.add(way.get(i).getIndA());
                //следующая посещаемая вершина
                i = way.get(i).getIndF() - 1;
            }
        }
        generateAnswer(getAway);
    }

    /**
     * Функция формулирования Объема поставок x
     *
     * @param getAway массив элементов пути
     */
    private void generateAnswer(ArrayList<Integer> getAway) {
        //сумма элементов d
        int sum = 0;
        //индекс объемов поставок x
        int org = 0;
        //переменная которая показывает, что в сумме не одна d
        boolean twoSumm = false;
        //количество пропущенных элементов d
        int amountSum = 0;
        for (int i = 1; i <= n; i++) {
            //если есть такая вершина в листе
            if (search(getAway, i + 1)) {
                //считаем сумму текущего на данном шаге d (спроса)
                sum += d[i - 1];
                //записываем x
                setX(sum, org);
                //если до этого вершина не вошла в правильный путь
                if (twoSumm) {
                    //увеличить индекс х
                    org += amountSum;
                    //обнулить количество элементов входящих в сумму
                    amountSum = 0;
                    //окончить формирование ответа х
                    twoSumm = false;
                }
                //увеличить индекс х
                org++;
                //обнулить сумму
                sum = 0;
            } else {
                //Включить текущую вершину к сумме спроса приведущей
                twoSumm = true;
                //Увеличить индекс к-ва входящих элементов
                amountSum++;
                //Подсчитать сумму
                sum += d[i - 1];
            }
        }
        //Записать последнюю сумму в х
        setX(sum, org);
    }

    /**
     * Функция для поиска элемента в пути
     *
     * @param getAway - лист элементов вершин к которым нам стоит заглянуть
     * @param find    - число которое нужно найти
     * @return
     */
    private boolean search(ArrayList<Integer> getAway, int find) {
        for (int i = 0; i < getAway.size(); i++) {
            if (getAway.get(i) == find) {
                return true;
            }
        }
        return false;
    }

    /**
     * Функция записи последующего элемента в x
     * @param value - значение
     * @param p - индекс
     */
    private void setX(int value, int p) {
        x[p] = value;
    }

    /**
     * Функция получения обьема поставок
     * @return
     */
    public int[] getX() {
        return x;
    }

    public int[] getD() {
        return d;
    }

    public int[] getA() {
        return A;
    }

    public int[] getH() {
        return h;
    }

    public int getN() {
        return n;
    }

    public int getL() {
        return l;
    }

    public int[] getF() {
        return f;
    }
}
