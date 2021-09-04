package Sort;

/**
 * Dieses Interface dient als Schnittstelle zwischen den SortAlgorithmen Klassen
 *
 * @author Davide Di Giovanni
 * @since 20.1.2020
 */
public interface InterfaceSort {
    /**
     * Methode um die ausgewerten Messwerte in ein Array zu speichern und sie dann zu �bergeben
     *
     * @return die Messwerte als array
     */
    int[] getMessArray();

    /**
     * Getter f�r das sortierte Array
     *
     * @return das Array mit den Sortierten Zahlen als String
     */
    String[] getSortedArray();

    /**
     * Diese Methode ist dient als Verst�ndnis f�r die Ausgabe im Main. Counter ist die anzahl Pl�tze im
     * Array(da drei arrays zu string umgewandlet)und wird verwendet um eine Schleifen Ausgabe zu machen.
     *
     * @return
     */
    int getCounter();

    /**
     * Methode um den jeweiligen Algorithmus auszuf�hren
     */
    void sort(int[] arry);

    /**
     * Diese Methode setzt die counter zahl immer auf 0, damit das array
     * keine duplikate der nochmals sortierten Arryas hat
     */
    void setUP();
}
