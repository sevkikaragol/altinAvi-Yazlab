    
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
    
   
File f = new File("hamleler.txt"); 
 
    if(!f.exists()){ 
        System.out.println("Dosya silinemedi.");
    }else{
        f.delete();
        System.out.println("Dosya silindi.");
    }


// Yorum satırı bloğu arasında kalan kısım 'stackoverflow'dan alınmıştır. 
   //------------------------------------------
    PrintStream out = new PrintStream(new FileOutputStream("hamleler.txt", true));
    System.setOut(out);
   //------------------------------------------    
        
    
        
        
        //dosyaya yazdırma işlemi için yazılan kod 'gelecegiyazanlar.turkcell.com.tr' adresinden alınmıştır.
        
        
        
        
        
        
        int [][] mevcut_konumlar = new int[4][2];
        int[][] digerHedefler= new int[3][3];
        int[][] sonrakiHedef= new int[1][3];
        varsayilanAyarlar ekran = new varsayilanAyarlar();
        ekran.setVisible(true);
        
         
        
        
        
        
        while (true) {
            System.out.print("");
            if(ekran.kontrol==false){
                System.out.println("");
                break;
            }
            
        }
        
        mevcut_konumlar[0][0]=0;
        mevcut_konumlar[0][1]=0;
                
        mevcut_konumlar[1][0]=0;
        mevcut_konumlar[1][1]=ekran.sutunSayisi-1;
                
        mevcut_konumlar[2][0]=ekran.satirSayisi-1;
        mevcut_konumlar[2][1]=ekran.sutunSayisi-1;
                
        mevcut_konumlar[3][0]=ekran.satirSayisi-1;
        mevcut_konumlar[3][1]=0;
     
         OyunAlani oyunAlani = new OyunAlani(ekran.satirSayisi,ekran.sutunSayisi,ekran.altinYuzdesi,ekran.gizliAltinYuzdesi);
    
         OyuncuA a=new OyuncuA(oyunAlani.alan,mevcut_konumlar,ekran.aHedefBelirlemeMaliyeti,ekran.aHamleMaliyeti,ekran.baslangicAltini,ekran.hamleSayisi);
         OyuncuB b=new OyuncuB(oyunAlani.alan,mevcut_konumlar,ekran.bHedefBelirlemeMaliyeti,ekran.bHamleMaliyeti,ekran.baslangicAltini,ekran.hamleSayisi);
         OyuncuC c=new OyuncuC(oyunAlani.alan,mevcut_konumlar,ekran.cHedefBelirlemeMaliyeti,ekran.cHamleMaliyeti,ekran.baslangicAltini,ekran.hamleSayisi,ekran.altinYuzdesi,ekran.gizliAltinYuzdesi);
         OyuncuD d=new OyuncuD(oyunAlani.alan,mevcut_konumlar,ekran.dHedefBelirlemeMaliyeti,ekran.dHamleMaliyeti,ekran.baslangicAltini,ekran.hamleSayisi);
         
         
         
         
         
         
         
         int i=1;
         while(true){
             System.out.println(i+". TUR");
             System.out.println("---------------------------------------------------------");
             
           if(a.getKasadakiAltin()<0 && b.getKasadakiAltin()<0 && c.getKasadakiAltin()<0 && d.getKasadakiAltin()<0)
               break;
           
           else{
               
               
           if(a.getKasadakiAltin()>0){ 
                a.hamleYap(mevcut_konumlar);
           
                if(a.hedefKoordinat!=null){
                    if(a.alan[a.getHedefKoordinatx()][a.getHedefKoordinaty()].isAltinKontrol()){
                        digerHedefler[0][0] = a.getHedefKoordinatx();
                        digerHedefler[0][1] = a.getHedefKoordinaty();
                        digerHedefler[0][2] = a.getUzaklik();
                    }
                    else{
                        sonrakiHedef = a.aHedefBelirle();
                        a.setHarcananAltin(a.getHedefBelirleme()*-1);
                        a.setKasadakiAltin(a.getHedefBelirleme());
                
                        if(sonrakiHedef != null){
                            digerHedefler[0][0] = sonrakiHedef[0][0];
                            digerHedefler[0][1] = sonrakiHedef[0][1];
                            digerHedefler[0][2] = sonrakiHedef[0][2];
                        }
                        else{
                            digerHedefler[0][0] = 0; 
                            digerHedefler[0][1] = 0;
                        }
                    }
                }
                else{         //a hedef olarak kendi koordinatını dönüp 'hedefKoordinat' değeri null dönüyorsa d'ye altınsız bir nokta gönderir.
                   digerHedefler[0][0] = 0; 
                   digerHedefler[0][1] = 0;
                }
                mevcut_konumlar[0][0] = a.mevcut_konum_x();
                mevcut_konumlar[0][1] = a.mevcut_konum_y();
           }
           else
           {
               System.out.println("Kasadaki altın bitti.");
               digerHedefler[0][0] = 0; 
               digerHedefler[0][1] = 0;
               a.setHedefKoordinat();  //hedefKoordinat değerini null yapar.
           }
               
           
           
           
           
           
           
           
  
           if(b.getKasadakiAltin()>0){ 
                b.hamleYap(mevcut_konumlar);
           
                if(b.hedefKoordinat!=null){
                    if(b.alan[b.getHedefKoordinatx()][b.getHedefKoordinaty()].isAltinKontrol()){
                        digerHedefler[1][0] = b.getHedefKoordinatx();
                        digerHedefler[1][1] = b.getHedefKoordinaty();
                        digerHedefler[1][2] = b.getUzaklik();
                    }
                    else{
                        sonrakiHedef = b.bHedefBelirle(); 
                        b.setHarcananAltin(b.getHedefBelirleme()*-1);
                        b.setKasadakiAltin(b.getHedefBelirleme());
                        
                        if(sonrakiHedef != null){
                            digerHedefler[1][0] = sonrakiHedef[0][0];
                            digerHedefler[1][1] = sonrakiHedef[0][1];
                            digerHedefler[1][2] = sonrakiHedef[0][2];
                        }
                        else{
                            digerHedefler[1][0] = 0; 
                            digerHedefler[1][1] = 0;
                        }
                    }
                }
                else{         //b hedef olarak kendi koordinatını dönüp 'hedefKoordinat' değeri null dönüyorsa d'ye altınsız bir nokta gönderir.
                   digerHedefler[1][0] = 0; 
                   digerHedefler[1][1] = 0;
                }
                mevcut_konumlar[1][0] = b.mevcut_konum_x();
                mevcut_konumlar[1][1] = b.mevcut_konum_y();
           }
           else{
               System.out.println("Kasadaki altın bitti.");
               digerHedefler[1][0] = 0; 
               digerHedefler[1][1] = 0;       
               b.setHedefKoordinat();  //hedefKoordinat değerini null yapar.
           }

           
           
           
           
           
           
           
           
           
   
           if(c.getKasadakiAltin()>0){ 
                c.hamleYap(mevcut_konumlar);
           
                if(c.hedefKoordinat!=null){
                    if(c.alan[c.getHedefKoordinatx()][c.getHedefKoordinaty()].isAltinKontrol()){
                        digerHedefler[2][0] = c.getHedefKoordinatx();
                        digerHedefler[2][1] = c.getHedefKoordinaty();
                        digerHedefler[2][2] = c.getUzaklik();
                    }
                    else{
                        sonrakiHedef = c.dHedef();
                        
                
                        if(sonrakiHedef != null){
                            digerHedefler[2][0] = sonrakiHedef[0][0];
                            digerHedefler[2][1] = sonrakiHedef[0][1];
                            digerHedefler[2][2] = sonrakiHedef[0][2];
                        }
                        else{
                            digerHedefler[2][0] = 0; 
                            digerHedefler[2][1] = 0;
                        }
                    }
                }
                else{         //c hedef olarak kendi koordinatını dönüp 'hedefKoordinat' değeri null dönüyorsa d'ye altınsız bir nokta gönderir.
                   digerHedefler[2][0] = 0; 
                   digerHedefler[2][1] = 0;
                }
                mevcut_konumlar[2][0] = c.mevcut_konum_x();
                mevcut_konumlar[2][1] = c.mevcut_konum_y();
           }
           else{
               System.out.println("Kasadaki altın bitti.");
               digerHedefler[2][0] = 0; 
               digerHedefler[2][1] = 0;
               c.setHedefKoordinat();  //hedefKoordinat değerini null yapar.
           }
               
               
           
           
           
           
           
     
           if(d.getKasadakiAltin()>0){ 
           d.hamleYap(digerHedefler, mevcut_konumlar);
         
           mevcut_konumlar[3][0] = d.mevcut_konum_x();
           mevcut_konumlar[3][1] = d.mevcut_konum_y();
           }
           else{
               System.out.println("Kasadaki altın bitti.");
               d.setHedefKoordinat();
           }
               
           
           
           
           
           
           
           
           
           
         }     //büyük else sonu
        
           if(a.hedefKoordinat==null && b.hedefKoordinat==null && c.hedefKoordinat==null && d.hedefKoordinat==null) break; 
           // while tekrar dönmeden önce tüm oyuncuların hedeflerini kontrol et
           
           i++;
       }
        
      

        
        System.out.println("---------------------------------------------------------"); 
        System.out.println("Oyun Sonlanmıştır!");
        JOptionPane message = new JOptionPane();
        message.showMessageDialog(null,"Oyun Sonlanmıştır!"); 
        int input = message.OK_OPTION;
        if(input == message.OK_OPTION)
        {
            oyunAlani.frame.setVisible(false);
        } 
        
        puanTablosu ekran2 = new puanTablosu(a,b,c,d);
        ekran2.setVisible(true);
     
        
       
       
       
       
    }
        
        
        
}
