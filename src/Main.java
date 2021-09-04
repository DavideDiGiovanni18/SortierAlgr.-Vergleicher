import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import Sort.*;

/**
 * Die Hauptklasse  verwaltet wichtige Ein und Ausgaben für verschiedene Sortieralgorithmen in separaten klassen.
 * Die Resultate werden hier miteinander verglichen und ausgegeben. (Man kann auch die sortArrays ausgeben).
 * src: readfiles und concatmethod von https://youtu.be/k9pf8KKPcwI / http://javatricks.de/tricks/zwei-arrays-verbinden
 * Der rest wurde von mir alleine gelöst.
 *
 * @author Davide Di Giovanni
 * @since 20.01.2020
 */
public class Main {
    private static int[] values;
    private static InterfaceSort b = new BubbleSort();
    private static InterfaceSort q = new Quicksort();
    private static InterfaceSort s = new ShakerSort();//instanzierung durch schnittstelle SortAlgorithmen
    private static InterfaceSort i = new InsertionSort();
    private static InterfaceSort sw = new SwapSort();
    private static InterfaceSort h = new HeapSort();

    private static final int anzahlSortAlgo = 3;//um die Messwerte zu speichern (3 array = 3 messwerte[])
    private static int totalTime;
    private static int gesamt;//Variablen für die Messwerte
    private static int anzahlDurch;
    private static int totalSpeicher;

    private static int[] array10 = new int[100];
    private static int[] array100 = new int[200];//hier werden die ausgelesenen werte der arrays geispeichert
    private static int[] array1000 = new int[1200];

    private static int[] sieger = new int[10];//Mithilfe dessen wird der schnellste Algorythmus ausgeben
    private static int counter1 = 0;

    private static int[] temporar = new int[10];//für den speicher, der kleinere davor wird mit dem nacham subtrahiert,
    //die differenz ist dann der speicher
    private static int count;//um es dann auszugeben

    /**
     * Main methode um das Programm auszuführen
     */
    public static void main(String[] args) {
        try {
            welcoming();

            char a = ' ';
            while (a != 'x') {
                abfragen();
                setUp();
                Scanner sc = new Scanner(System.in);
                int zahl = sc.nextInt();//Was ausgeführt werden soll, welcher vergleich

                initalizeFiles();
                initalizeBubble(array10, array100, array1000);//bubble
                saveMessWerte();
                print(zahl);

                initalizeFiles();
                initializeQuick(array10, array100, array1000);//quick
                saveMessWerte();
                print(zahl);

                initalizeFiles();
                initalizeShakeSort(array10, array100, array1000);//shake
                saveMessWerte();
                print(zahl);

                initalizeFiles();
                intitializeInsertion(array10, array100, array1000);//insertion
                saveMessWerte();
                print(zahl);

                initalizeFiles();
                initializeSwap(array10, array100, array1000);//swap
                saveMessWerte();
                print(zahl);

                initalizeFiles();
                initalizeHeap(array10, array100, array1000);//heap
                saveMessWerte();
                print(zahl);

                System.out.println("h: weitere Ausgeben");//menu ausgabe um zu interagieren mit dem Benutzer
                System.out.println("x: beenden");
                System.out.println("-------------------------");
                sc.nextLine();
                a = sc.nextLine().charAt(0);
                if (a != 'h' && a != 'x') {//Falls etwas komisches eingeben wird, wird es sofort beendet
                    throw new Exception();//falls etwas anderes eingeben wird, wird eine Exeption geworfen
                }
            }
            etwasundStopp(800, "Wird ausgewertet");
            evaluation();

            a = ' ';//wird wieder auf leer gesetzt
            Scanner o = new Scanner(System.in);
            while (a != 'x') {
                System.out.println("SortArray von Welchem Algorithmus ausgeben: zahl(siehe Oben)");
                System.out.print("Eingabe-->");
                printAllSortedArray(o.nextInt());
                System.out.println("-------------------------");
                System.out.println("h: weitere Ausgeben");
                System.out.println("x: beenden");
                System.out.println("-------------------------");
                o.nextLine();
                a = o.nextLine().charAt(0);
                if (a != 'h' && a != 'x') {
                    throw new Exception();//falls etwas anderes eingeben wird, wird eine Exeption geworfen
                }
            }
        } catch (Exception e) {
            System.out.println("Das Programm wurde aufgrund einer Falschen Eingabe Beendet");
            //falls ein Buchtsabe eingeben wird etc.
        }

    }

    /**
     * Diese Methode wird benotigt um die Zahlen aus den Files zu lesen
     *
     * @param file hier wird der File-path übergeben
     * @return das int array der zahlen von dem Filw
     */
    public static int[] readFiles(String file) {
        try {
            File f = new File(file);//neues File erstellt
            Scanner scan = new Scanner(f);
            int counter = 0;
            while (scan.hasNextInt()) {//solange es eine zahl hat
                counter++;              //so viel zahlen hat es dann am schluss(ingesamt)
                scan.nextInt();
            }
            int[] array = new int[counter];//neues Array der tasächlichen Länge wird erstellt
            Scanner b = new Scanner(f);
            for (int i = 0; i < array.length; i++) {
                array[i] = b.nextInt();//hier werden die zahlen in da array gespeichert
            }
            return array;
        } catch (Exception e) {//wenn es keine Files finden kann wird exeption ausgegebn
            System.out.println("Path Fehler: Keine Files mit int-werten gefunden ");
            System.exit(0);
            return null;
        }
    }

    /**
     * Diese Methode dient dazu die werte zu sortieren(Bubblesort)
     * und die messwerte in ein array zu packen um sie zusammen zu zahlen
     *
     * @param array  array von file1
     * @param array1 array von file2
     * @param array2 arry von file3
     */
    public static void initalizeBubble(int[] array, int[] array1, int[] array2) {
        values = new int[400];//neu erstellt
        b.sort(array);
        values = b.getMessArray();//messarray wird gepeichert
        b.sort(array1);
        concatArrays(b.getMessArray());//weiters messwert[] wird mit dem ersten messarry verbunden, gespeichert
        b.sort(array2);
        concatArrays(b.getMessArray());//Jetzt sind alle drei messarray in einem Array berbunden: value
        System.out.println(b);
    }

    /**
     * Diese Methode dient dazu die werte zu sortieren(quicksort)
     * und die messwerte in ein array zu packen um sie zusammen zu zählen.
     *
     * @param array  array von file1
     * @param array1 array von file2
     * @param array2 array von file3
     */
    public static void initializeQuick(int[] array, int[] array1, int[] array2) {
        q.sort(array);
        values = q.getMessArray();//da die alten werte von bubblesort drin sind werden diese überschrieben
        q.sort(array1);
        concatArrays(q.getMessArray());
        q.sort(array2);
        concatArrays(q.getMessArray());
        System.out.println(q);
    }

    /**
     * Diese Methode dient dazu werte mit dem ShakeSort Algorithmus zu sortieren
     * und die messwerte in ein array zu packen um sie zusammen zu zählen.
     *
     * @param array  array von file1
     * @param array1 array von file2
     * @param array2 array von file3
     */
    public static void initalizeShakeSort(int[] array, int[] array1, int[] array2) {
        s.sort(array);
        values = s.getMessArray();
        s.sort(array1);
        concatArrays(s.getMessArray());
        s.sort(array2);
        concatArrays(s.getMessArray());
        System.out.println(s);
    }

    /**
     * Diese Methode dient dazu werte mit dem Insertionsort Algorithmus zu sortieren
     * und die messwerte in ein array zu packen um sie zusammen zu zählen.
     *
     * @param array  array von file1
     * @param array1 array von file2
     * @param array2 array von file3
     */
    public static void intitializeInsertion(int[] array, int[] array1, int[] array2) {
        i.sort(array);
        values = i.getMessArray();
        i.sort(array1);
        concatArrays(i.getMessArray());
        i.sort(array2);
        concatArrays(i.getMessArray());
        System.out.println(i);
    }

    /**
     * Diese Methode dient dazu werte mit dem Swapsort Algorithmus zu sortieren
     * und die messwerte in ein array zu packen um sie zusammen zu zählen.
     *
     * @param array  array von file1
     * @param array1 array von file2
     * @param array2 array von file3
     */
    public static void initializeSwap(int[] array, int[] array1, int[] array2) {
        sw.sort(array);
        values = sw.getMessArray();
        sw.sort(array1);
        concatArrays(sw.getMessArray());
        sw.sort(array2);
        concatArrays(sw.getMessArray());
        System.out.println(sw);
    }

    /**
     * Diese Methode dient dazu werte mit dem Heapsort Algorithmus zu sortieren
     * und die messwerte in ein array zu packen um sie zusammen zu zählen.
     *
     * @param array  array von file1
     * @param array1 array von file2
     * @param array2 array von file3
     */
    public static void initalizeHeap(int[] array, int[] array1, int[] array2) {
        h.sort(array);
        values = h.getMessArray();
        h.sort(array1);
        concatArrays(h.getMessArray());
        h.sort(array2);
        concatArrays(h.getMessArray());
        System.out.println(h);
    }

    /**
     * Diese Methode dient dazu zwei array zusammenzufügen zu einem
     *
     * @param value das array das zusamengefuegt werden soll
     */
    public static void concatArrays(int[] value) {
        int[] a = Arrays.copyOf(values, values.length + value.length);//speichert eine kopie des arrays und
        // vergrössert es um die neue anzahl
        System.arraycopy(value, 0, a, values.length, value.length);
        //value das objekt was im ziel: array a kopiert werden soll
        //0 ist die start position vom value array,values.length ist ab dort wo die werte hinzugefuegt werden,
        // value.length länge der werte die kopiert werden müssen
        values = a;
    }

    /**
     * Diese Methode speichert die Messwerte aus dem array in die jewiligen richtigen attribute
     */
    public static void saveMessWerte() {
        int counter3 = 1;//muss bei 1 sein, da das erste sich nicht mit sich selber subtrahieren kann
        count = 0;//für das subtrahieren vom vorherigen speichern
        anzahlDurch = 0;//damit das Attribut nicht die werte des vorherigen Algorithmus hat
        totalTime = 0;//werden sie gecleared bzw. auf 0 gesetzt
        totalSpeicher = 0;
        gesamt = 0;
        int counter = 0;
        int anzahlDuchlauf = 0;//damit der durchlauf nach 3 mal beendet wird
        while (anzahlDuchlauf != anzahlSortAlgo) {//man weiss das man 3 mal messwerte[]bekommen(3 Arrays eingelesen)
            gesamt += values[counter++];// hat deshalb 3 durchläufe bei jedem 4. muss der wert sein
            // der im gleichen Attribut kommt(0=gesamt, 4=gesamt), es ist ja ein Array aus 12 plätzen
            anzahlDurch += values[counter++];
            totalTime += values[counter++];
            totalSpeicher = values[counter++];//speicher bleibt ja gleich
            anzahlDuchlauf++;
        }
        temporar[counter3++] = totalSpeicher;//den vorherigen speichern minus zu rechnen, differenz
        if(counter1<6) {//anfangswerte der Zeit gespiechert, 6= Anzahl sortieralgorithmen
            sieger[counter1++] = totalTime;//für den vergleich später wer der schnellste Algorithmus
        }
        // ist in nanotime
    }

    /**
     * Diese Methode gibt alle Messwerte zu den jeweiligen Sortieralgorithmen aus
     */
    public static void print(int zahl) {
        System.out.println("-------------------------");
        if (zahl == 1 || zahl == 5) {
            System.out.println("Gesamt Duchlaufe: " + gesamt);
        }
        if (zahl == 2 || zahl == 5) {
            System.out.println("Total Vergleiche: " + anzahlDurch);
        }
        if (zahl == 3 || zahl == 5) {
            System.out.println("Nanosekunden: "+ totalTime);
            System.out.println("in Milisekunden: " + (totalTime / 1000000));//umwandlung zu Milisek
        }
        if (zahl == 4 || zahl == 5) {
            System.out.println("Speicher: " + ((totalSpeicher - temporar[count++]) + " Byte"));
        }
        if(zahl<1||zahl>5) {//Fehlermeldung bei falscher eingabe
            System.out.println("Fehler: Zahl zu hoch oder zu klein!");
        }

        System.out.println("-------------------------");
    }

    /**
     * Diese Methode bergüsst den Benutzer
     */
    public static void welcoming() {
        System.out.println("Wilkommen zu unserem SortierAlgorithmus-Messer/vergleicher");
        System.out.println("-----------------------------------------");
        etwasundStopp(400, "Starting");
        System.out.println("-----------------------------------------");
        System.out.println("Beschreibung:");
        System.out.println("Es gibt verschiedene Sortieralgorithmen die ");
        System.out.println("unterschiedlich Schnell sind. Um dies zu Beweisen haben ");
        System.out.println("wir 10,100,1000 zahlen eingelesen und ausgewertet.");
        System.out.println("-----------------------------------------");

    }

    /**
     * Diese Methode ist gibt eine coole Loading animation aus mit punkten
     *
     * @param milisek wie lange es verzögert ist bis die punkte ausgabe komnt
     * @param ausgabe hier wird der text Loading übergeben
     */
    public static void etwasundStopp(int milisek, String ausgabe) {
        System.out.print(ausgabe);
        int counter = 5;
        for (int i = 0; i < counter; i++) {
            try {
                Thread.sleep(milisek);
            } catch (InterruptedException ex) {
            }
            System.out.print(".");
        }
        System.out.println();
        try {
            Thread.sleep(milisek);
        } catch (Exception e) {

        }
    }

    /**
     * Mit dieser Methode kann man die bestimmten sortierten Arrays aus allen Algorithmus abfragen und Ausgeben
     *
     * @param listNr des Algorithmus das augeben werden soll
     */
    public static void printAllSortedArray(int listNr) {
        System.out.println("------------------------");
        if (listNr == 1) {
            System.out.println(b);
            System.out.println("------------------------");
            for (int i = 0; i < b.getCounter(); i++) {
                System.out.println((i + 1) + ": " + b.getSortedArray()[i]);
            }
        } else if (listNr == 2) {
            System.out.println(q);
            System.out.println("------------------------");
            for (int i = 0; i < q.getCounter(); i++) {
                System.out.println((i + 1) + ": " + q.getSortedArray()[i]);
            }
        } else if (listNr == 3) {
            System.out.println(s);
            System.out.println("------------------------");
            for (int i = 0; i < s.getCounter(); i++) {
                System.out.println((i + 1) + ": " + s.getSortedArray()[i]);
            }
        } else if (listNr == 4) {
            System.out.println(i);
            System.out.println("------------------------");
            for (int j = 0; j < i.getCounter(); j++) {
                System.out.println((j + 1) + ": " + i.getSortedArray()[j]);
            }
        } else if (listNr == 5) {
            System.out.println(sw);
            System.out.println("------------------------");
            for (int j = 0; j < sw.getCounter(); j++) {
                System.out.println((j + 1) + ": " + sw.getSortedArray()[j]);
            }
        } else if (listNr == 6) {
            System.out.println(h);
            for (int j = 0; j < h.getCounter(); j++) {
                System.out.println((j + 1) + ": " + h.getSortedArray()[j]);
            }

        } else {

            System.out.println("Fehler: Zahl nicht vorhanden!");
        }
    }

    /**
     * Diese Methode initalisert die zahlen aus den Dateien in Arrays
     */
    public static void initalizeFiles() {
        array10 = readFiles("C:\\Users\\david\\IdeaProjects\\M411_LB3_1\\files\\10Digits.dat");//path vom File das
        array100 = readFiles("C:\\Users\\david\\IdeaProjects\\M411_LB3_1\\files\\100Digits.dat");//in ein Array[]konvertiert wird
        array1000 = readFiles("C:\\Users\\david\\IdeaProjects\\M411_LB3_1\\files\\1000Digits.dat");
    }

    /**
     * Diese Methode wertet am Schluss aus, welcher Algorithmus am schnellsten war(zeit) und
     * gibt diesen dann aus.
     */
    public static void evaluation() {
        int min = sieger[0];
        int pos = 0;
        for (int i = 0; i < counter1; i++) {//sucht den am wenig schnellsten
            if (sieger[i] < min) {
                min = sieger[i];
                pos = i;//merkt dich die position der algorithmen
            }
        }
        System.out.println("-------------------------");
        System.out.println("Der Schnellste ist:");
        if (pos == 0) {//da bubblesort als erstes initialisert wird muss er inm array an pos 0 sein
            System.out.println(b);
        } else if (pos == 1) {
            System.out.println(q);
        } else if (pos == 2) {
            System.out.println(s);
        } else if (pos == 3) {
            System.out.println(i);
        } else if (pos == 4) {
            System.out.println(sw);
        } else if (pos == 5) {
            System.out.println(h);
        }
        System.out.println("------------------------");
    }

    /**
     * Diese Methode wird benutzt um zu Fragen(Ausgabe) was der Nutzer miteinander Vergleichen will
     */
    public static void abfragen() {
        System.out.println("Was wolllen sie vergleichen?");
        System.out.println("-------------------------");
        System.out.println("1.Schleifendurchlaufe");
        System.out.println("2.Vergleiche");
        System.out.println("3.Zeit");
        System.out.println("4.Speicher");
        System.out.println("5.Alles");
        System.out.println("------------------------");
        System.out.print("Zahl->");
    }

    /**
     * Diese Methode macht das die Arrays wieder auf null gesetzt werden sodass
     * bei mehrere ausführen(siehe Methode eins oben) von vergleichen nicht mehr als 3 mal das
     * sortierte Array gespeichert wird.
     */
    public static void setUp() {
        b.setUP();
        q.setUP();
        s.setUP();
        sw.setUP();
        i.setUP();
        h.setUP();
    }

}
