
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Edge { // edge => kenar

    public final Vertex hedefVertex;
    public final double mesafe;

    public Edge(Vertex hedefVertex, double mesafe) {
        this.hedefVertex = hedefVertex;
        this.mesafe = mesafe;
    }
}

class Vertex { // vertex => düğüm

    public String dugumIsmi;
    public int plaka;
    public ArrayList<Edge> komsular = new ArrayList<>();
    public double minMesafe = Double.POSITIVE_INFINITY;
    public Vertex parent;
    public boolean kullanildiMi = false;

    public Vertex(String sehirİsmi, int sehirPlaka) {
        dugumIsmi = sehirİsmi;
        plaka = sehirPlaka;
    }

}

public class Dijkstra {

    public static ArrayList<ArrayList<ArrayList<String>>> minSehirler = new ArrayList<>();
    public static ArrayList<Vertex> sehirler = new ArrayList<>();
    public static ArrayList<Vertex> gidilecekSehirler = new ArrayList<>();

    public static void sehirDijkstra(Vertex sehir) {
        for (int i = 0; i < sehirler.size(); i++) {
            sehirler.get(i).minMesafe = Double.POSITIVE_INFINITY;
            sehirler.get(i).kullanildiMi = false;
        }
        Vertex aktifSehir = sehirler.get(sehir.plaka - 1);
        sehirler.get(sehir.plaka - 1).minMesafe = 0;
        for (int i = 0; i < sehirler.get(sehir.plaka - 1).komsular.size(); i++) {//kocaelinin komslarının mesafelerini ve parentlerini güncelledik
            sehirler.get(sehir.plaka - 1).komsular.get(i).hedefVertex.minMesafe = sehirler.get(sehir.plaka - 1).komsular.get(i).mesafe;
            sehirler.get(sehir.plaka - 1).komsular.get(i).hedefVertex.parent = aktifSehir;
        }
        //  System.out.println(sehirler.get(40).komsular.get(0).hedefVertex.plaka+"   "+sehirler.get(40).komsular.get(0).hedefVertex.minMesafe);
        int aktifSehirIndex = 26;//bunu 26 yapmamızın sebebi: bir şehrin maks 13 tane komşusu vardır.

        double tmp = Double.POSITIVE_INFINITY;
        for (int i = 0; i < sehirler.size(); i++) {// sıradaki şehri belirler, şehrin kullanılıp kullanılmadığına da bakar.
            if (sehirler.get(i).minMesafe < tmp && sehirler.get(i).kullanildiMi != true && i != (aktifSehir.plaka - 1)) {
                tmp = sehirler.get(i).minMesafe;    //minimum mesafeyi veriyor(kontrol amaçlı)
                aktifSehirIndex = i;   //şehrin indeksini tutar                
            }
        }
        aktifSehir = sehirler.get(aktifSehirIndex);

        // System.out.println(aktifSehir.minMesafe + " " + (aktifSehirIndex + 1));
        //System.out.println(aktifSehir.dugumIsmi);
        for (int i = 0; i < (sehirler.size() - 1); i++) {
            aktifSehir.kullanildiMi = true;
            for (int j = 0; j < sehirler.get(aktifSehirIndex).komsular.size(); j++) {//kocaelinin komslarının mesafelerini geldiği yerdeki mesafelerle toplayarak ve parentlerini güncelledik.
                if ((sehirler.get(aktifSehirIndex).komsular.get(j).mesafe + aktifSehir.minMesafe) < sehirler.get(aktifSehirIndex).komsular.get(j).hedefVertex.minMesafe) {

                    sehirler.get(aktifSehirIndex).komsular.get(j).hedefVertex.minMesafe = (sehirler.get(aktifSehirIndex).komsular.get(j).mesafe + aktifSehir.minMesafe);
                    sehirler.get(aktifSehirIndex).komsular.get(j).hedefVertex.parent = aktifSehir;
                }
            }

            tmp = Double.POSITIVE_INFINITY;
            // tmp baktığım aktifSehir'in en küçük mesafesini bulana kadar güncellenir. aktifSehir index'i yeni aktifSehir icin güncellenir.
            for (int k = 0; k < sehirler.size(); k++) {// sıradaki şehri belirler, şehrin kullanılıp kullanılmadığına ve kullanılan şehrin dışındakilere bakar. 
                if (sehirler.get(k).minMesafe < tmp && sehirler.get(k).kullanildiMi != true && k != (aktifSehir.plaka - 1)) {
                    tmp = sehirler.get(k).minMesafe;    //minimum mesafeyi veriyor(kontrol amaçlı)
                    aktifSehirIndex = k;   //şehrin indeksini tutar                
                }
            }
            aktifSehir = sehirler.get(aktifSehirIndex);
        }

    }

    public static void guzergahEkle(Vertex sehir, int index) {
        for (int i = 0; i < sehirler.size(); i++) {
            ArrayList<String> cumle = new ArrayList<>();
            Vertex guzergah = sehirler.get(i); // guzergah bir şehirden kocaeliye gidene kadar uğranılan şehirleri tutuyor.

            cumle.add(String.valueOf(guzergah.minMesafe));
            while (guzergah != sehirler.get(sehir.plaka - 1)) {

                cumle.add(guzergah.dugumIsmi);
                guzergah = guzergah.parent;
            }
            cumle.add(sehir.dugumIsmi);
            minSehirler.add(new ArrayList<ArrayList<String>>());
            minSehirler.get(index).add(cumle);
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileReader komsularDosyasi = new FileReader("komsuluk.txt");
        Scanner scanner = new Scanner(komsularDosyasi);

        while (scanner.hasNextLine()) {
            String satir = scanner.nextLine();
            String[] bolum = satir.split(",");
            String[] ikili = bolum[0].split("-");
            Integer plaka = Integer.parseInt(ikili[0]);//plakayı integera çevirmemiz lazım
            sehirler.add(new Vertex(ikili[1], plaka));

        }
        komsularDosyasi.close();

        komsularDosyasi = new FileReader("komsuluk.txt");
        scanner = new Scanner(komsularDosyasi);
        int sehirCursor = 0;
        while (scanner.hasNextLine()) {
            String satir = scanner.nextLine();
            String[] bolum = satir.split(",");

            for (int i = 1; i < bolum.length; i++) {//tüm komşulukları dolaşması için
                String cursorBolum = bolum[i].trim();
                String[] ikili = cursorBolum.split("-");
                int plaka = Integer.parseInt(ikili[0]);
                double mesafe = Double.parseDouble(ikili[1]);
                sehirler.get(sehirCursor).komsular.add(new Edge(sehirler.get(plaka - 1), mesafe));

            }
            sehirCursor++;
        }
        komsularDosyasi.close(); // dosyadan çekip graphı tamamladık.
        
        //gidilecek şehirleri ekledik şimdilik,  not: gittiğimiz şehirleri listeden bir bir sileceğiz
        gidilecekSehirler.add(sehirler.get(40));//kocaeli
        gidilecekSehirler.add(sehirler.get(15));//bursa
        gidilecekSehirler.add(sehirler.get(25));//esk
        gidilecekSehirler.add(sehirler.get(5));//ankara

        for (int i = 0; i < gidilecekSehirler.size(); i++) {
            sehirDijkstra(sehirler.get(gidilecekSehirler.get(i).plaka-1)); // Djikstra bulma fonk. çağırıldı. 
            guzergahEkle(gidilecekSehirler.get(i), i);
        }

        System.out.println(minSehirler.get(2).get(40).get(0));

    }//psvm
}//djikstra
