

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Ekran extends JPanel{
    public ArrayList<Koordinatlar> koordinatlar = new ArrayList<>(); // Dosyadan çekilen koordinatları tutar.
    ImageIcon resim = new ImageIcon("src/images/harita.png");    
    ArrayList<Vertex> rota= new ArrayList<>(); // Kullanıcının arayüzde seçtiği, çizdirilmek istenen rota. Constructor içinde atama yapılmıştır.    
   
     @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));               
        g.drawImage(resim.getImage(), 5, 5, 1193, 531, null);
        
        for(int i=1;i<rota.size()-1;i++){
            g.setColor(Color.WHITE);
            g.drawLine(koordinatlar.get(rota.get(i).plaka-1).x, koordinatlar.get(rota.get(i).plaka-1).y, koordinatlar.get(rota.get(i+1).plaka-1).x, koordinatlar.get(rota.get(i+1).plaka-1).y);
            if(Arayüz.d.gidilecekSehirler.contains(rota.get(i))){
                g.setColor(Color.red);
                 g.drawOval(koordinatlar.get(rota.get(i).plaka-1).x-5, koordinatlar.get(rota.get(i).plaka-1).y-5, 10, 10);
            }
            else{
            g.setColor(Color.BLACK);
                 g.drawOval(koordinatlar.get(rota.get(i).plaka-1).x-5, koordinatlar.get(rota.get(i).plaka-1).y-2, 8, 8);
            }
        }
    }
    
    public Ekran(ArrayList<Vertex> kullanıcınınSectigiRota) throws IOException {           
        koordinatOkuma();
        rota=kullanıcınınSectigiRota;  // Kullanıcının arayüzde seçtiği, çizdirilmek istenen rota.     
    }

    public void koordinatOkuma() throws FileNotFoundException, IOException {// Dosyadan şehirlerin harita üzerindeki koordinatlarını okur
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
