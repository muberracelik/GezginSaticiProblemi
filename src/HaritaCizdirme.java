
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;


public class HaritaCizdirme extends JFrame{
    
    public HaritaCizdirme() throws HeadlessException {
      //boş constructor
    }
    
    public HaritaCizdirme(ArrayList<Vertex> a) throws HeadlessException, IOException {
        
        Ekran ekran =new Ekran(a);    
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(1193,570);
        this.add(ekran);
        
    }
    
   

    

   
    
}
