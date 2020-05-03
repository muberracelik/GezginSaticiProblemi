
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ekran extends JPanel {

    public ArrayList<Koordinatlar> koordinatlar = new ArrayList<>(); // Dosyadan çekilen koordinatları tutar.
    ImageIcon resim = new ImageIcon("src/images/harita.png");
    ArrayList<Vertex> rota = new ArrayList<>(); // Kullanıcının arayüzde seçtiği, çizdirilmek istenen rota. Constructor içinde atama yapılmıştır.    
    String rotaText = "";

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.drawImage(resim.getImage(), 5, 5, 1193, 531, null);
        for (int i = 1; i < rota.size() - 1; i++) {
            g.setColor(Color.WHITE);
            g.drawLine(koordinatlar.get(rota.get(i).plaka - 1).x, koordinatlar.get(rota.get(i).plaka - 1).y, koordinatlar.get(rota.get(i + 1).plaka - 1).x, koordinatlar.get(rota.get(i + 1).plaka - 1).y);
            if (Arayüz.d.gidilecekSehirler.contains(rota.get(i))) {
                g.setColor(Color.red);
                g.drawOval(koordinatlar.get(rota.get(i).plaka - 1).x - 5, koordinatlar.get(rota.get(i).plaka - 1).y - 5, 10, 10);
            } else {
                g.setColor(Color.BLACK);
                g.drawOval(koordinatlar.get(rota.get(i).plaka - 1).x - 5, koordinatlar.get(rota.get(i).plaka - 1).y - 2, 8, 8);
            }
        }
    }

    public Ekran(ArrayList<Vertex> kullanıcınınSectigiRota) throws IOException {
        koordinatOkuma();
        rota = kullanıcınınSectigiRota;  // Kullanıcının arayüzde seçtiği, çizdirilmek istenen rota.
        for (int i = 1; i < rota.size() - 1; i++) {
            rotaText += rota.get(i).dugumIsmi + "→";
        }
        rotaText += rota.get(rota.size() - 1).dugumIsmi;
        String rotaText1;
        String rotaText2;
        int boyut = rotaText.length();
        rotaText1 = rotaText.substring(0, boyut / 2);
        rotaText2 = rotaText.substring(boyut / 2, boyut);
        JButton rotayiGoster = new JButton("Rotayı Göster");
        rotayiGoster.setBounds(1193, 531, 1193, 531);
        rotayiGoster.setLocation(1100, 510);
        JPanel sulo = this;
        rotayiGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //JOptionPane.showMessageDialog(null, rotaText1 + "\n" + rotaText2);//rota ekrana sığmayacağı için rotaları ikiye bölüp JOptine da gösterdik.
                JOptionPane.showOptionDialog(new JFrame(),
                rotaText1 + "\n" + rotaText2,
                "Uğranılan Şehirler",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,null,null);
            }
        });
        this.add(rotayiGoster);

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
