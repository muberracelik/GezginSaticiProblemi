
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Ekran extends JPanel{
    public ArrayList<Koordinatlar> koordinatlar = new ArrayList<>();
    ImageIcon resim = new ImageIcon("src/images/harita.png");    
    ArrayList<Vertex> alternatifRota= new ArrayList<>();
    
   
     @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(resim.getImage(), 5, 5, 1193, 531, null);
        
        for(int i=1;i<alternatifRota.size()-1;i++){
            g.setColor(Color.WHITE);
            g.drawLine(koordinatlar.get(alternatifRota.get(i).plaka-1).x, koordinatlar.get(alternatifRota.get(i).plaka-1).y, koordinatlar.get(alternatifRota.get(i+1).plaka-1).x, koordinatlar.get(alternatifRota.get(i+1).plaka-1).y);
            if(Arayüz.d.gidilecekSehirler.contains(alternatifRota.get(i))){
                g.setColor(Color.red);
                 g.drawOval(koordinatlar.get(alternatifRota.get(i).plaka-1).x-5, koordinatlar.get(alternatifRota.get(i).plaka-1).y-5, 10, 10);
            }
            else{
            g.setColor(Color.BLACK);
                 g.drawOval(koordinatlar.get(alternatifRota.get(i).plaka-1).x-5, koordinatlar.get(alternatifRota.get(i).plaka-1).y-2, 8, 8);
            }
        }
    }
    
    public Ekran(ArrayList<Vertex> a) throws IOException {       
        koordinatOkuma();
        alternatifRota=a;        
    }

    public void koordinatOkuma() throws FileNotFoundException, IOException {
        FileReader koordinatDosyasi = new FileReader("koordinatlar.txt");
        Scanner scanner = new Scanner(koordinatDosyasi);
        while (scanner.hasNextLine()) {
            String satir = scanner.nextLine();
            String[] bolum = satir.split(",");

            koordinatlar.add(new Koordinatlar(Integer.parseInt(bolum[0]), Integer.parseInt(bolum[1])));

        }
        koordinatDosyasi.close();

    }
    
}
