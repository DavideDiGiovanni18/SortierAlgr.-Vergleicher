package Sort;

import java.util.Arrays;

/**
 * Lb3 Gruppenprojekt
 * Klasse f�r die Implementation des Insertionsorts
 * gem�ass Angaben, im Auftrag von Herr.Maurizi.
 * <p>
 * Dieser Algorithmus ist stabil und im verglich zu anderen ziemlich simple.
 * Das Array wird in einen sortierten und einen unsortierten
 * Teil unterteilt. Werte aus dem unsortierten Teil werden
 * ausgew�hlt und an der richtigen Position im sortierten Teil platziert.
 * <p>
 * Average Case: O(n^2)
 * Best case: O(n)
 * Worst Case: O(n^2)
 * (code von: https://www.javatpoint.com/insertion-sort-in-java )
 * <p>
 * Klasse: InsertionsSort.java
 *
 * @author Danial vaezi
 * @version 1.0
 * @date 22.01.2021
 */

public class InsertionSort implements InterfaceSort {


    private int timeMS = 0;

    //compare Z�hlt wie oft verglichene Zahlen getauscht wurden.
    private int compare = 0;
    private int iterationCnt = 0;
    private String[] sortArray = new String[100];
    private int counter = 0;

    // Methode gibt die gebrauchte Zeit zur Sortierung des Arrays zur�ck
    public int getTime() {
        return timeMS;
    }

    // Methode gibt die Anzahl Iterationen der while Schleife zur�ck
    public int getTotal() {
        return iterationCnt;
    }

    // Der InsertionSort Sortieralgorithmus wird hier ausgef�rht.

    public void sort(int array[]) {
        iterationCnt = 0;
        compare = 0;
        timeMS = 0;
        long start = System.nanoTime();


        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            iterationCnt++;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                compare++;

            }
            array[j + 1] = key;
            compare++;

        }

        long finish = System.nanoTime();
        this.sortArray[counter++] = Arrays.toString(array);
        this.timeMS = (int) (finish - start);

    }


    /**
     * gibt das sortierte array z�r�ck
     *
     * @return sortArray als String mit den umgewandelten werten
     */
    @Override
    public String[] getSortedArray() {
        return sortArray;
    }

    /**
     * speichert die Messdaten in einem Array und gibt diese dann zur�ck
     *
     * @return die Messwerten als String-array
     */
    @Override
    public int[] getMessArray() {
        Runtime r = Runtime.getRuntime();
        int[] values = new int[4];
        values[0] = iterationCnt;
        values[1] = this.compare;
        values[2] = this.timeMS;
        values[3] = (int) (r.totalMemory() - r.freeMemory());
        return values;
    }

    /**
     * counter f�r die gespeicherten sortArray im array
     *
     * @return anzahl im Array
     */
    @Override
    public int getCounter() {
        return counter;
    }

    /**
     * methode um bei objekt ausgabe den namen anzuzeigen
     *
     * @return text mit den namen vom sort
     */
    @Override
    public String toString() {
        return "4.InsertionSort";
    }

    /**
     * Methode um counter auf null zusetzen
     */
    public void setUP() {
        this.counter = 0;
    }


}