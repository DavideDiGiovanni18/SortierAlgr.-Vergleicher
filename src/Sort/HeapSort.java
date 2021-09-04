package Sort;

/*Klasse: HeapSort.java
 *
 *@author: Damian Grassi
 *@date: 24.01.2021
 *@version: 1.0
 */

/*
 *Diese Klasse Sortiert Arrays mit einem Heap-Sort
 *Der Heapsort sortirt Arrays mithilfe von Binary-Trees, dabei werden immer wieder durch die Funktion Heapify,
 * neue Max Heaps (Binary-Trees welche absteigend "Sortiert" sind) von welchen die Root nun ans Ende genommen wird
 * dieser Vorgang wird mit dem nun verkürzten Tree wiederholt. gebildet werden die MaxHeaps durch einen Algorithmus welcher eine
 * Grösste "Node" zuerst annimt danach mit den Children vergleicht, sind diese Grösser als die Root wird getauscht und der Vorgang
 * Wird Rekursiv wiederholt.
 *
 *
 * Dieses Beispiel wurde inspiriert durch: https://www.geeksforgeeks.org/heap-sort/
 *
 */

import java.util.Arrays;

public class HeapSort implements InterfaceSort {

    private int schlaufendurchlaeufe; //Zählt die Anzahl schleifendurchläufe
    private int vergleiche; // Zählt die Anzahl Vergleiche
    private int zeit; // die vergangene zeit

    private int counter = 0;
    private String[] sortArray = new String[100];


    public void sort(int array[]) {  //die "Main" Methode welche die anderen Methoden aufruft und verwendet
        schlaufendurchlaeufe = 0;   //zur wiederverwendung werden die messwerte auf null gesetzt
        vergleiche = 0;
        long startZeit = System.nanoTime(); //misst die zeit beim start

        int laenge = array.length; //einfachheitshalber wird hier die array Länge in einen Int gespeichert

        int startIndx = (laenge / 2) - 1;
        ; //dieser index ist immer dier letzte welcher noch kinder hat

        for (int i = startIndx; i >= 0; i--) {         // hier wird der este tree gebaut
            schlaufendurchlaeufe++;
            heapify(array, laenge, i);
        }

        for (int i = laenge - 1; i >= 0; i--) {          //hier werden die roots an die enden gebracht und der nun kleinere tree wieder heapified
            int swap = array[0];
            array[0] = array[i];
            array[i] = swap;
            schlaufendurchlaeufe++;
            heapify(array, i, 0);
        }

        long endZeit = System.nanoTime(); //Misst die Zeit beim ende
        int total = (int) (endZeit - startZeit);
        this.zeit = total;
        this.sortArray[counter++] = Arrays.toString(array);

    }

    public void heapify(int array[], int laenge, int i) {
        int largest = i;      //hier wird die momentane root als der grösste wert angenommen
        int leftChild = 2 * i + 1;    // das linke kind ist in einem binary tree immer 2*i + 1
        int rightChild = 2 * i + 2;    // das rechte kind ist in einem binary tree immer 2*i + 2

        if (leftChild < array.length) {
            vergleiche++;
            if (array[leftChild] > array[largest] && leftChild < laenge) { //falls der wert des linken kindes grösser ist als
                vergleiche++;
                largest = leftChild;                                       //der wert des "grössten" und das linke kind nicht die letzte stelle ist wird
                // das linke kind nun als grösste stelle bislang angesehen
            } else {
                vergleiche++;
            }
        } else {
            vergleiche++;
        }

        if (rightChild < array.length) {
            vergleiche++;
            if (array[rightChild] > array[largest] && rightChild < laenge) { //dassselbe für das rechte child
                vergleiche++;
                largest = rightChild;
            } else {
                vergleiche++;
            }
        } else {
            vergleiche++;
        }
        if (largest != i) {         // falls der grösste einer der kinder war müssen die beiden werte die plätze tauschen
            vergleiche++;           // zudem wird die methode nocheinmal rekursiv aufgerufen, nun allerdings mit der neuen "Grössten zahl"
            int temp = array[i];    // dies solange bis der grösste auch wirklich grösser als die kinder ist

            array[i] = array[largest];
            array[largest] = temp;

            heapify(array, laenge, largest);
        } else {
            vergleiche++;
        }
    }

    /**
     * Methode um die werte im array zuzuweisen und dann zurückzugeben
     *
     * @return die Messwerte in einem Array
     */
    public int[] getMessArray() {
        Runtime r = Runtime.getRuntime();
        int[] values = new int[4];
        values[0] = this.schlaufendurchlaeufe;
        values[1] = this.vergleiche;
        values[2] = this.zeit;
        values[3] = (int) (r.totalMemory() - r.freeMemory());
        return values;
    }

    /**
     * counter für das Array
     *
     * @return anzahl sortierte listen im array
     */
    @Override
    public int getCounter() {
        return counter;
    }

    /**
     * getter für das sortierte Array
     *
     * @return sortierte Array
     */
    @Override
    public String[] getSortedArray() {
        return sortArray;
    }

    /**
     * Methode um das Objekt mit Text zu überschreiben
     *
     * @return name des objekts
     */
    @Override
    public String toString() {
        return "6.Heapsort";
    }

    /**
     * Methode um counter auf null zusetzen
     */
    public void setUP() {
        this.counter = 0;
    }
}





