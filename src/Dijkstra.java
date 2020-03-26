
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public static ArrayList<Vertex> sehirler = new ArrayList<>();

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
        komsularDosyasi.close();
        Vertex aktifSehir = sehirler.get(40);
        sehirler.get(40).minMesafe = 0;
        for (int i = 0; i < sehirler.get(40).komsular.size(); i++) {//kocaelinin komslarının mesafelerini ve parentlerini güncelledik
            sehirler.get(40).komsular.get(i).hedefVertex.minMesafe = sehirler.get(40).komsular.get(i).mesafe;
            sehirler.get(40).komsular.get(i).hedefVertex.parent = aktifSehir;
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

        System.out.println(aktifSehir.minMesafe + " " + (aktifSehirIndex + 1));
        System.out.println(aktifSehir.dugumIsmi);

        for (int i = 0; i < (sehirler.size() - 1); i++) {
            aktifSehir.kullanildiMi = true;
            for (int j = 0; j < sehirler.get(aktifSehirIndex).komsular.size(); j++) {//kocaelinin komslarının mesafelerini ve parentlerini güncelledik
                if (sehirler.get(aktifSehirIndex).komsular.get(j).hedefVertex.minMesafe > (sehirler.get(aktifSehirIndex).komsular.get(j).mesafe + aktifSehir.minMesafe)) {

                    sehirler.get(aktifSehirIndex).komsular.get(j).hedefVertex.minMesafe = (sehirler.get(aktifSehirIndex).komsular.get(j).mesafe + aktifSehir.minMesafe);
                    sehirler.get(aktifSehirIndex).komsular.get(j).hedefVertex.parent = aktifSehir;
                }
            }

            tmp = Double.POSITIVE_INFINITY;

            for (int k = 0; k < sehirler.size(); k++) {// sıradaki şehri belirler, şehrin kullanılıp kullanılmadığına da bakar.
                if (sehirler.get(k).minMesafe < tmp && sehirler.get(k).kullanildiMi != true && k != (aktifSehir.plaka - 1)) {
                    tmp = sehirler.get(k).minMesafe;    //minimum mesafeyi veriyor(kontrol amaçlı)
                    aktifSehirIndex = k;   //şehrin indeksini tutar                
                }
            }
            aktifSehir = sehirler.get(aktifSehirIndex);
        }
        System.out.println(sehirler.get(25).parent.parent.parent.dugumIsmi);
    }
    
}
