package Sort;
import java.util.Arrays;

/**
 * Klasse: ShakerSort.java
 *
 * @author Nils Rothenubuehler
 * @version 1.0
 * @date 22.01.2021
 */
public class ShakerSort implements InterfaceSort{
    private int total = 0;
    private int timeMS = 0;
    private int compare = 0;
    private String[] sortArray = new String[100];
    private int counter = 0;

    /**
     * ShakerSort Algorithmus sortiert eine Liste von Zahlen
     * Es werden immer 2 Zahlen miteinander verglichen,
     * wenn eine grösser ist, wird dementsprechen getauscht,
     * das geht solange, bis man bis zum Ende kommt und
     * dann wird vom Ende zum Start wieder verglichen, die Liste wird so beim Ende um 1 kleiner
     * wenn es wieder beim Start angekommen ist, wird die Liste beim Start um 1 kleiner
     * Das geht so lange, bis die Liste sortiert ist
     *
     * @param list
     * @return list
     */
    public void sort(int[] list) {
        int start = 0; //Start des Arrays (0)
        int end = list.length - 1; //Letze Zahl des Arrays (länge-1)
        int tmp = 0; //temporäre Zahl, wird zum tauschen gebraucht
        boolean isSorted = false; //boolean, das angibt, ob das Array sortiert ist
        boolean changes = false;
        total = 0; //zählt die Iterationen

        long startTime = System.nanoTime(); //startet die Zeit [in Nanosekunden]

        while (isSorted == false) { //While-Loop bis das Array sortiert ist
            for (int i = start; i < end; i++) { //For Schlaufe von Start bis Ende
                compare++;
                if (list[i] > list[i + 1]) { //Wenn die Zahl links grösser ist als die rechte Zahl [Vergleich: 2 Zahlen]
                    tmp = list[i]; //Tauscht die beiden Zahlen
                    list[i] = list[i + 1];
                    list[i + 1] = tmp;
                    changes = true; //Es gab Änderungen
                }
            }
            if (changes == false) { //Wenn keine Zahlen getauscht wurden, dann ist die Liste sortiert
                isSorted = true;
            }
            changes = false;
            end--; //Verkürzt die Zahlen, die noch überprüft werden müssen

            for (int i = end; i > start; i--) { //For Schlaufe von Ende bis Start
                if (list[i] < list[i - 1]) { //Wenn die Zahl rechts grösser kleiner ist als die linke Zahl [Vergleich: 2 Zahlen]
                    tmp = list[i]; //Tauscht die beiden Zahlen
                    list[i] = list[i - 1];
                    list[i - 1] = tmp;
                    changes = true; //Es gab Änderungen
                }
                compare++; //zählt vergleiche

            }
            if (changes == false) { //Wenn keine Zahlen getauscht wurden, dann ist die Liste sortiert
                isSorted = true;
            }

            changes = false;
            start++; //Verkürzt die Zahlen, die noch überprüft werden müssen
            total++; //zählt Iterationen
        }
        this.sortArray[counter++] = Arrays.toString(list);
        long endTime = System.nanoTime(); //Zeit wird gestoppt
        this.timeMS = (int) (endTime - startTime); //Zeit wird zusammengezählt

    }

    /**
     * gibt das sortierte Array zurück
     * @return sortierte, umgeandelte String array
     */
    public String[] getSortedArray() {
        return sortArray;
    }

    /**
     * speichert die messwerte ung gibt diese jeweils zurück
     * @return messArray
     */
    public int[] getMessArray() {
        Runtime r = Runtime.getRuntime();
        int[] values = new int[4];
        values[0] = this.total;
        values[1] = this.compare;
        values[2] = this.timeMS;
        values[3] = (int) (r.totalMemory() - r.freeMemory());
        return values;
    }

    /**
     * getter für den Counter
     * @return anzahl gepeicherte listen(toString)
     */
    @Override
    public int getCounter() {
        return counter;
    }

    /**
     * methode um das objekt als text auzugeben
     * @return text
     */
    public String toString() {
        return "3.ShakerSort";
    }

    /**
     * Methode um counter auf null zusetzen
     */
    public void setUP(){
        this.counter=0;
    }

}
