
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

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

    public Vertex(double minMesafe) {
        this.minMesafe = minMesafe;

    }

}

public class Dijkstra {

    //public static ArrayList<ArrayList<ArrayList<String>>> minSehirler = new ArrayList<>();
    public static ArrayList<ArrayList<ArrayList<Vertex>>> minSehirler2 = new ArrayList<>();
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

    public static void guzergahEkle(Vertex sehir, int index) { //gidilecek şehirler listesindeki tüm şehirler için bu tekrarlanır. şehir değişkeni sıradaki şehri tutar, index ise o şehrin,
        //gidilecek şehirler listesindeki indexini tutar. Ör: Kocaeli 0,Bursa 1,Eskişehir 2 ....
        for (int i = 0; i < sehirler.size(); i++) {
            //ArrayList<String> cumle = new ArrayList<>();
            ArrayList<Vertex> cumle2 = new ArrayList<>();
            Vertex guzergah = sehirler.get(i); // guzergah bir şehirden kocaeliye gidene kadar uğranılan şehirleri tutuyor.

            //cumle.add(String.valueOf(guzergah.minMesafe));
            cumle2.add(new Vertex(guzergah.minMesafe));
            while (guzergah != sehirler.get(sehir.plaka - 1)) {
                //System.out.println(guzergah.dugumIsmi);
                // cumle.add(guzergah.dugumIsmi);
                cumle2.add(guzergah);
                guzergah = guzergah.parent;
            }
            //cumle.add(sehir.dugumIsmi);
            cumle2.add(sehir);
            //minSehirler.add(new ArrayList<ArrayList<String>>());
            minSehirler2.add(new ArrayList<ArrayList<Vertex>>());//alt satırda çekebilmek için önce bellekten yer ayırmamız gerekiyor.
            // minSehirler.get(index).add(cumle);
            minSehirler2.get(index).add(cumle2);

        }

    }

    public static int fak(int sayi) {//faktoriyel fonksiyonu
        int fakt = 1;
        for (int i = 1; i <= sayi; i++) {
            fakt *= i;
        }
        return fakt;
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
        gidilecekSehirler.add(sehirler.get(76));//yalova
        gidilecekSehirler.add(sehirler.get(15));//bursa
        gidilecekSehirler.add(sehirler.get(25));//esk
        gidilecekSehirler.add(sehirler.get(5));//ankara
        gidilecekSehirler.add(sehirler.get(19));// denizli
        gidilecekSehirler.add(sehirler.get(59)); // tokat
        gidilecekSehirler.add(sehirler.get(67)); // aksaray        
        gidilecekSehirler.add(sehirler.get(44)); // manisa
        gidilecekSehirler.add(sehirler.get(62));
        gidilecekSehirler.add(sehirler.get(63));

        for (int i = 0; i < gidilecekSehirler.size(); i++) {
            sehirDijkstra(sehirler.get(gidilecekSehirler.get(i).plaka - 1)); // Djikstra bulma fonk. çağırıldı. 
            guzergahEkle(gidilecekSehirler.get(i), i);
        }

        //System.out.println(minSehirler.get(0).get(25).get(0));
        System.out.println(minSehirler2.get(0).get(25).get(1).dugumIsmi);
///////////////////////////////////////////////////////////////////////////////////////////// rotaları hesaplama kısmı
        ArrayList<Integer> a = new ArrayList<>();
        int sayi = gidilecekSehirler.size() - 1;//kocaeli dahil olmadığı için 1 çıkarttık.
        String[] s = new String[fak(sayi)];//tüm rotalaarı s dizisinde tutuyoruz.
        for (int i = 1; i <= sayi; i++) {
            a.add(i);
        }
        for (int i = 0; i < fak(sayi); i++) {//güzergahların sonuna kocaeliyi ekler
            s[i] = "";
            s[i] += 0;
        }

        for (int i = 1; i <= sayi; i++) {
            for (int j = 0; j < fak(sayi - 1); j++) {
                if (i == 10) {
                    s[j + (fak(sayi - 1) * (i - 1))] += ":";
                } else {
                    s[j + (fak(sayi - 1) * (i - 1))] += i;
                }
            }
        }
        int sayi3 = sayi - 1;
        int sayi2 = sayi - 2;
        int m = 999;
        int n = 0;
        ArrayList<Integer> iceren = new ArrayList<>();
        for (int i = 0; i < sayi - 1; i++) {//sütunlar için
            for (int j = n; j < fak(sayi);) {//satırlar için
                for (int k = 0; k < fak(sayi2); k++) {
                    for (int u = 1; u <= sayi; u++) {
                        if (s[j].contains(String.valueOf(u))) {
                            iceren.add(u);
                        }
                    }
                    if (m == 999) {
                        for (int o = 1; o <= sayi; o++) {
                            if (iceren.contains(o) == false) {
                                m = o;
                                iceren.add(o);
                                break;
                            }
                        }
                    }
                    if (m == 10) {
                        s[j] += ":";
                    } else {
                        s[j] += m;
                    }
                    j++;
                    n++;
                    if (n % fak(sayi3) == 0) {
                        iceren.clear();
                    }
                }
                m = 999;
            }
            n = 0;
            sayi2 -= 1;
            sayi3 -= 1;
        }
        for (int i = 0; i < fak(sayi); i++) {//güzergahşların sonuna kocaeliyi ekler.           
            s[i] += 0;
        }/*
        for (int j = 0; j < fak(sayi); j++) {
            if (s[j].contains("0")) {
                System.out.println(s[j]);
            }
        }*/
///////////////////////////////////////////////////////////////////////////////////////////// rotaların mesafeleri toplamı bulma
        double toplam = 0;
        ArrayList<Integer> toplamMesafe = new ArrayList<>();
        for (int i = 0; i < fak(sayi); i++) {

            for (int j = 0; j < sayi + 1; j++) {
                char s1[] = s[i].toCharArray();
                int s2[] = new int[s1.length];
                for (int k = 0; k < s1.length; k++) {
                    s2[k] = s1[k] - 48;
                }

                toplam += minSehirler2.get(s2[j]).get(gidilecekSehirler.get(s2[j + 1]).plaka - 1).get(0).minMesafe;

            }
            toplamMesafe.add((int) toplam);
            //System.out.println(toplam);
            toplam = 0;
        }

        int enk = Integer.MAX_VALUE;
        String mubis = "";
        for (int i = 0; i < (toplamMesafe.size() - 1) / 2; i++) {
            if (enk > toplamMesafe.get(i)) {
                enk = toplamMesafe.get(i);
                mubis = s[i];
            }
        }
        System.out.println(mubis + " " + enk);

        /*for (int i = 0; i < (toplamMesafe.size() - 1)/2; i++) {
            for (int j = 0; j < (toplamMesafe.size() - 1)/2; j++) {
                if (toplamMesafe.get(j) > toplamMesafe.get(j + 1)) {
                    int tmp = toplamMesafe.get(j);
                    toplamMesafe.set(j, toplamMesafe.get(j + 1));
                    toplamMesafe.set(j + 1, tmp);

                    String temp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = temp;

                }
            }

        } 
        for (int i = 0; i < toplamMesafe.size(); i++) {
            System.out.println(toplamMesafe.get(i));
            System.out.println(s[i]);
        }

        //System.out.println(minSehirler.get(0).get(25).get(n);
         */
        ArrayList<Vertex> rotaIskelet = new ArrayList<>();

        for (int i = 0; i < gidilecekSehirler.size(); i++) {
            char dizi1[] = s[0].toCharArray();

            for (int j = minSehirler2.get(dizi1[i] - 48).get(gidilecekSehirler.get(dizi1[i + 1] - 48).plaka - 1).size() - 1; j > 1; j--) {
                rotaIskelet.add(minSehirler2.get(dizi1[i] - 48).get(gidilecekSehirler.get(dizi1[i + 1] - 48).plaka - 1).get(j));
                System.out.println(minSehirler2.get(dizi1[i] - 48).get(gidilecekSehirler.get(dizi1[i + 1] - 48).plaka - 1).get(j).dugumIsmi);

            }
        }
        System.out.println("Kocaeli");

        Vertex aktifSehir;
        char s3[] = s[0].toCharArray();
        int s4[] = new int[s3.length];
        for (int k = 0; k < s3.length; k++) {
            s4[k] = s3[k] - 48;
        }
        int index1 = 0;
        for (int i = 0; i < rotaIskelet.size() - 2; i++) {
            System.out.println("i: " + i);
            aktifSehir = rotaIskelet.get(i);
            if (gidilecekSehirler.contains(rotaIskelet.get(i + 1))) {
                if (index1 < 6) {

                    ++index1;
                }
                continue;
            } else {
                for (int j = 0; j < aktifSehir.komsular.size(); j++) {
                    for (int k = 0; k < aktifSehir.komsular.get(j).hedefVertex.komsular.size(); k++) {

                        if (aktifSehir.komsular.get(j).hedefVertex.komsular.get(k).hedefVertex.equals(rotaIskelet.get(i + 2))) {
                            int yeniToplam = (int) ((minSehirler2.get(s4[index1]).get(aktifSehir.komsular.get(j).hedefVertex.plaka - 1).get(0).minMesafe) + (minSehirler2.get(s4[index1 + 1]).get(aktifSehir.komsular.get(j).hedefVertex.plaka - 1).get(0).minMesafe));
                            //System.out.println((minSehirler2.get(s4[0]).get(gidilecekSehirler.get(s4[1]).plaka - 1).get(0).minMesafe));
                            int çıkarma = (int) (enk - (minSehirler2.get(s4[index1]).get(gidilecekSehirler.get(s4[index1 + 1]).plaka - 1).get(0).minMesafe));
                            System.out.println(çıkarma + yeniToplam);
                            //System.out.println(yeniToplam);
                            //System.out.println(aktifSehir.komsular.get(j).hedefVertex.dugumIsmi);

                        }

                    }

                }
            }

        }

        /*
      
      
       Vertex aktifSehir2;
         while (aktifSehir2 != sehirler.get(sehir.plaka - 1)) {
                //System.out.println(guzergah.dugumIsmi);
                cumle.add(guzergah.dugumIsmi);
                guzergah = guzergah.parent;               
            }*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////rotaları hesaplama kısmı
        /*
        
        JFrame f = new JFrame("Gezgin Kargo Dağıtıcısı");
        f.getContentPane().setBackground(Color.YELLOW);
        ArrayList<JCheckBox> checkBoxTutucu=new ArrayList<>();
        int konumx = 0, konumy = 0;

        for (int i = 0; i < sehirler.size(); i++) {
            JCheckBox checkBox = new JCheckBox(sehirler.get(i).plaka+" - "+sehirler.get(i).dugumIsmi);
            checkBoxTutucu.add(checkBox);
            checkBox.setBounds(konumx, konumy, 150, 50);
            f.add(checkBox);
            konumy+=50;
            
            if((i+1)%9 == 0){
                konumx +=220;
                konumy=0;
            }
        }

        f.setSize(1920, 1080);E
        f.setLayout(null);
        f.setVisible(true);
        
        System.out.println(checkBoxTutucu.get(0).isSelected());
         */
    }//psvm
}//djikstra
