package Sort;

import java.util.Arrays;

/**
 * Diese Klasse beinhaltet den Quicksortalgorithmus. Die klasse kann das sortierte Arrays zurückgeben.
 * inspiriert: https://youtu.be/PrxjH8R-kE8
 *
 * @author Davide Di Giovanni
 * @since 20.01.2021
 */
public class Quicksort implements InterfaceSort {
    private int gesamtDurchlauf;
    private int anzahlVerglich;
    private int zeitIngesamt;
    private String[] arraySort = new String[100];
    private int counter = 0;


    /**
     * Methode um die benötigten werte für den Quicksort Algorithmus vorzubereiten
     *
     * @param arry das zu Sortierende Array
     */
    public void sort(int[] arry) {
        Runtime a = Runtime.getRuntime();
        this.gesamtDurchlauf = 0;
        this.anzahlVerglich = 0;
        this.zeitIngesamt = 0;
        long start = System.nanoTime();
        _quicksort(0, arry.length - 1, arry);
        long end = System.nanoTime();
        this.zeitIngesamt = (int) (end - start);
        this.arraySort[counter++] = Arrays.toString(arry);//int Sortarray wird in einem Platz als Text gepeichert
    }


    /**
     * getter für anzahl gepeicherte Text =sortArray
     *
     * @return anzahl sortierte Array bzw. benutzte Plätze im Array
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Diese Methode führt den Rekursiven Algorithmus Quicksort aus
     *
     * @param leftindex  position rechts des Array = default 0
     * @param rightIndex end position des Arrays
     * @param arr        das zu sortierende Array
     */
    private void _quicksort(int leftindex, int rightIndex, int[] arr) {
        if (leftindex >= rightIndex) {
            return;
        }
        int i = leftindex;
        int k = rightIndex - 1;
        int privot = arr[rightIndex];
        while (i < k) {
            while (arr[i] <= privot && i < rightIndex) {
                i++;
                anzahlVerglich++;
                gesamtDurchlauf++;
            }
            while (arr[k] >= privot && k > leftindex) {
                k--;
                anzahlVerglich++;
                gesamtDurchlauf++;
            }
            gesamtDurchlauf++;
            if (i < k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;

            }

        }
        anzahlVerglich++;
        if (arr[i] > privot) {
            int temp = arr[i];
            arr[i] = arr[rightIndex];
            arr[rightIndex] = temp;
        }

        _quicksort(leftindex, i - 1, arr);//rekursiver Aufruf linke seite)
        _quicksort(i + 1, rightIndex, arr);//rekursiver Aufruf(reche seite)
    }

    /**
     * getter für das Sortierte Array
     *
     * @return sorted Array
     */
    @Override
    public String[] getSortedArray() {
        return arraySort;
    }

    /**
     * Speichert alle Messwerte in ein Array, getter für alle Messwerte
     *
     * @return Array von Messwerten
     */
    public int[] getMessArray() {
        Runtime r = Runtime.getRuntime();
        int[] array = new int[4];
        array[0] = gesamtDurchlauf;
        array[1] = anzahlVerglich;
        array[2] = zeitIngesamt;
        array[3] = (int) (r.totalMemory() - r.freeMemory());
        return array;
    }

    /**
     * überschreibt das Objekt mit einem Text
     *
     * @return name des algorithmus
     */
    @Override
    public String toString() {
        return "2.Quicksort";
    }

    /**
     * Methode um counter auf null zusetzen
     */
    public void setUP() {
        this.counter = 0;
    }
}
