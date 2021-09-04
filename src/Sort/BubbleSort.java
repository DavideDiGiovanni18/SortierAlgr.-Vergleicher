package Sort;

import java.util.Arrays;

/**
 * Diese Klasse beinhaltet den Bubblesortalgorithmus. Die klasse kann das sortierte Arrays zurückgeben.
 *
 * @author Davide Di Giovanni
 * @since 20.01.2021
 */

public class BubbleSort implements InterfaceSort {
    private int gesamtDurchlauf;
    private int anzahlVerglich;
    private int zeitIngesamt;
    private String[] arraySort = new String[100];//Array für das sortierte Array
    private int counter = 0;


    /**
     * Diese Methode sortiert zahlen ahnhand des Buublesort
     *
     * @param array das zu sortierende
     */
    public void sort(int[] array) {
        if (array.length < 2) {//wenn es nur 1 lang ist, ist es schon sortiert bzw unötig
            System.out.println("Fehler");
        } else {
            gesamtDurchlauf = 0;
            anzahlVerglich = 0;
            zeitIngesamt = 0;
            long start = System.nanoTime();//nanotime da es genauer ist, start: zeit
            for (int i = 0; i < array.length - 1; i++) {
                this.gesamtDurchlauf++;
                for (int j = 1; j < array.length; j++) {
                    if (array[j] < array[j - 1]) {
                        int temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                    }
                    this.anzahlVerglich++;
                    this.gesamtDurchlauf++;
                }
            }
            long end = System.nanoTime();//Ende: Zeit
            this.zeitIngesamt = (int) (end - start);//dann wird endzeit minus anfangszeit berechnet=gesamt
            this.arraySort[counter++] = Arrays.toString(array);//int Sortarray wird in einem Platz als Text gepeichert
        }
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
        Runtime a = Runtime.getRuntime();//Um den Speicher zu berechnen
        int[] array = new int[4];//es muss genau 4 sein da sonst bei der saveMessWerte fehler ergeben könnte (bzw 0 werte)
        array[0] = gesamtDurchlauf;
        array[1] = anzahlVerglich;
        array[2] = zeitIngesamt;
        array[3] = (int) (a.totalMemory() - a.freeMemory());//Verwendeter Speicher
        return array;
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
     * überschreibt das Objekt mit einem Text
     *
     * @return name des algorithmus
     */
    @Override
    public String toString() {
        return "1.Bubblesort";
    }

    /**
     * Methode um counter auf null zusetzen
     */
    public void setUP() {
        this.counter = 0;
    }

}
