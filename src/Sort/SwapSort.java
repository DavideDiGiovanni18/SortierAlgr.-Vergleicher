package Sort;

import java.util.Arrays;

/**
 * @author Francesco Ryu
 * @Version 1.0
 * @date 23.01.2021
 */
public class SwapSort implements InterfaceSort {
    private int anzDurchgang = 0;
    private int vergleiche = 0;
    private int zeit = 0;
    private String[] sortiert = new String[100];
    private int counter = 0;

    /**
     * swapSort
     *
     * @param testArray Der SwapSort geht zur ersten Arraystelle und setzt die Zahl in die Variable "minimumValue".
     *                  Die Position der Zahl wird auch in der Variable "minimumValueIndex" gespeichert.
     *                  Danach geht es zur nächsten Stelle mit der Variable "j".
     *                  Hier wird die Variable "j", also die Zahl an dieser Stelle, mit dem "minimumValue" verglichen.
     *                  Falls dies kleiner ist ersetzt es diese Position und überschreibt dann "minimumValue" und "minimumValueIndex".
     *                  Danach vergleicht es noch das "i" mit der ersetzten "minimumValueIndex".
     *                  das if-Statement wird nur ausgeführt, dalls "i" ungleich "minimumValueIndex" ist.
     *                  Hier wird das Array an der Position "i" in einer Zwischenspeicher - Variable gespeichert("temp").
     *                  Es swapt das "arr[i]" mit dem "arr[minimumValueIndex]".
     *                  Dieses Beispiel wurde inspiriert von: //https://gist.github.com/VorotnikovaAlena/b8fced945bcf923a1875
     */
    public void sort(int[] testArray) {
        long start = System.nanoTime();

        /**
         * Diese for-Schleife setzt erstmal die Zahl und deren Index.
         * Beim nächsten Durchlauf geht es dann eine Stelle weiter.
         */
        for (int i = 0; i < testArray.length; i++) {
            anzDurchgang++;
            int minimumValue = testArray[i];
            int minimumValueIndex = i;

            /**
             * In dieser Schleife geht das Array zur nächsten Position("j") und "ersetzt" dann, falls die neue Zahl
             * kleiner als die erste Zahl(minimumValue) ist, mit der gesetzten Zahl und deren Index. Nun sind die
             * Zahlen, als "minimumValue" und "minimumValueIndex" mit "j" gesetzt. Die Zahl von "j"
             * dementsprechend auch.
             */
            for (int j = i + 1; j < testArray.length; j++) {
                if (testArray[j] < minimumValue) {
                    minimumValue = testArray[j];
                    minimumValueIndex = j;
                }
                vergleiche++;
            }
            /**
             * Dieses if-Statement swapt, falls das "i" nicht mehr der minimumValueIndex enspricht, das Array
             * an der Position "i" mit dem Array an der Position "minimumValue". Dieser Prozess wird durch einen
             * Zwischenspeicher "temp" durchgeführt. Beim neuen Durchlauf des SwapSorts ist jetzt "i" sozusagen die
             * kleinste Zahl.
             */
            if (i != minimumValueIndex) {
                int temp = testArray[i];
                testArray[i] = testArray[minimumValueIndex];
                testArray[minimumValueIndex] = temp;
            }
            vergleiche++;
        }
        long end = System.nanoTime();
        this.zeit = (int) ((int) end - start);
        this.sortiert[counter++] = Arrays.toString(testArray);
    }

    /**
     * getter für den Counter
     *
     * @return anzahl gepeicherte listen(toString)
     */
    public int getCounter() {
        return counter;
    }

    /**
     * getter und zuweiser für die Messwerte
     *
     * @return die messwerte in einem array
     */
    public int[] getMessArray() {
        Runtime r = Runtime.getRuntime();
        int[] values = new int[4];
        values[0] = this.anzDurchgang;
        values[1] = this.vergleiche;
        values[2] = this.zeit;
        values[3] = (int) (r.totalMemory() - r.freeMemory());
        return values;
    }

    /**
     * gibt das Sortierte Array zurück, mit allen werten string
     *
     * @return
     */
    @Override
    public String[] getSortedArray() {
        return sortiert;
    }

    /**
     * Methode um das Objekt zu überschreiben
     *
     * @return name des Sortalgorithmus
     */
    @Override
    public String toString() {
        return "5.Swapsort";
    }

    /**
     * setzt den counter auf 0
     */
    public void setUP() {
        this.counter = 0;
    }
}
