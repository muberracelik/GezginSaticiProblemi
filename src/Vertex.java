
import java.util.ArrayList;

public class Vertex { // vertex => düğüm  sehirlerin bilgilerini tutar.

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
