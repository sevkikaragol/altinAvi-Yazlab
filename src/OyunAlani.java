
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sevkikaragol
 */
public class OyunAlani {
    ArrayList<Integer> sayilar= new ArrayList<>();
    
   
    static int boyutSatir;
    static int boyutSutun;
    static int gizliBoyut;
    static double altinYuzde;
    static double gizliAltinYuzde;
     
    ImageIcon gold5= new ImageIcon("src//gorseller//gold5.png");
    ImageIcon gold10= new ImageIcon("src//gorseller//gold10.png");
    ImageIcon gold15= new ImageIcon("src//gorseller//gold15.png");
    ImageIcon gold20= new ImageIcon("src//gorseller//gold20.png");
    ImageIcon sgold5= new ImageIcon("src//gorseller//sgold5.png");
    ImageIcon sgold10= new ImageIcon("src//gorseller//sgold10.png");
    ImageIcon sgold15= new ImageIcon("src//gorseller//sgold15.png");
    ImageIcon sgold20= new ImageIcon("src//gorseller//sgold20.png");
    ImageIcon Aimg= new ImageIcon("src//gorseller//A.png");
    ImageIcon Bimg= new ImageIcon("src//gorseller//B.png");
    ImageIcon Cimg= new ImageIcon("src//gorseller//C.png");
    ImageIcon Dimg= new ImageIcon("src//gorseller//D.png");
    
    
    Kare[][] alan;
    
    JFrame frame;
    
    
    
    
    
    
    public OyunAlani(int alanx, int alany, int altinYuzdesi, int gizliAltinYuzdesi) {
        
        
        
        this.boyutSatir=alanx;
        this.boyutSutun=alany;
        this.altinYuzde = (double)altinYuzdesi/100.0;
        this.gizliAltinYuzde = (double)gizliAltinYuzdesi/100.0;
        this.gizliBoyut= (int) (Math.ceil(this.boyutSatir*this.boyutSutun*altinYuzde*gizliAltinYuzde));
        
        
         alan = new Kare[boyutSatir][boyutSutun];
     
        
       frame=new JFrame("Altın Toplama");
       frame.setSize(1000,1000);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.isAlwaysOnTop();
       frame.setLayout(new GridLayout(boyutSatir,boyutSutun));
       frame.setLocationRelativeTo(null);
       //frame.setAlwaysOnTop(true);
       
       
       
        for(int satir=0; satir< alan.length; satir++){
            for(int sutun=0;sutun<alan[0].length;sutun++){
                Kare k= new Kare(satir,sutun);
                frame.add(k);
               
                alan[satir][sutun]=k;
                
            }
        }
        
       altinOlustur();
      
       gorselYerlestir();
      
        
       frame.setVisible(true);
    }
   
    public void altinOlustur(){
    int i=0;
    int sayac=0;
   
    sayilar.add(5);
    sayilar.add(10);
    sayilar.add(15);
    sayilar.add(20);
    
    while(i<boyutSatir*boyutSutun*altinYuzde){
        int randSatir= (int) (Math.random()* alan.length);
        int randSutun= (int) (Math.random()* alan[0].length);
        
        while(alan[randSatir][randSutun].isAltinKontrol()||(randSatir==0 &&randSutun==0)
                
                ||(randSatir==0 &&randSutun==boyutSutun-1) || (randSatir==boyutSatir-1 && randSutun==0)
                
                || (randSatir==boyutSatir-1 && randSutun== boyutSutun-1)
               
                ){
         randSatir= (int) (Math.random()* alan.length);
         randSutun= (int) (Math.random()* alan[0].length);
        }
       
       
       
       alan[randSatir][randSutun].setAltinKontrol(true);
     
       
       
       
       
       
       Collections.shuffle(sayilar);
       alan[randSatir][randSutun].setMiktar(sayilar.get(0));
        
       
       i++;
        if(sayac<gizliBoyut){
            alan[randSatir][randSutun].setGizliAltinKontrol(true);
            sayac++;
          
        }
      
           
    }
  
    
    }
   
    public void gorselYerlestir(){
        for(int satir=0; satir< alan.length; satir++){
            for(int sutun=0;sutun<alan[0].length;sutun++){
                if(alan[satir][sutun].isAltinKontrol()==true && alan[satir][sutun].isGizliAltinKontrol()==false){
                    if(alan[satir][sutun].getMiktar()==5){
                        alan[satir][sutun].setIcon(gold5);
                    }
                    else if(alan[satir][sutun].getMiktar()==10){
                         alan[satir][sutun].setIcon(gold10);
                    }
                    else if(alan[satir][sutun].getMiktar()==15){
                         alan[satir][sutun].setIcon(gold15);
                    }
                    else if(alan[satir][sutun].getMiktar()==20){
                         alan[satir][sutun].setIcon(gold20);
                    }
                    
                }
               if(alan[satir][sutun].isGizliAltinKontrol()==true){
                      if(alan[satir][sutun].getMiktar()==5){
                        alan[satir][sutun].setIcon(sgold5);
                    }
                    else if(alan[satir][sutun].getMiktar()==10){
                         alan[satir][sutun].setIcon(sgold10);
                    }
                    else if(alan[satir][sutun].getMiktar()==15){
                         alan[satir][sutun].setIcon(sgold15);
                    }
                    else if(alan[satir][sutun].getMiktar()==20){
                         alan[satir][sutun].setIcon(sgold20);
                    }
                }
            }
        }
        alan[0][0].setIcon(Aimg);
        alan[boyutSatir-1][boyutSutun-1].setIcon(Cimg);
        alan[0][boyutSutun-1].setIcon(Bimg);
        alan[boyutSatir-1][0].setIcon(Dimg);
        
    }
    
 
    
}
