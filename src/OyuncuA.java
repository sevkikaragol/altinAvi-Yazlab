

import com.sun.management.VMOption;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sevkikaragol
 */
public class OyuncuA {
    
    ImageIcon Aimg= new ImageIcon("src//gorseller//A.png");
    ImageIcon Bimg= new ImageIcon("src//gorseller//B.png");
    ImageIcon Cimg= new ImageIcon("src//gorseller//C.png");
    ImageIcon Dimg= new ImageIcon("src//gorseller//D.png");
   
    ImageIcon gold5= new ImageIcon("src//gorseller//gold5.png");
    ImageIcon gold10= new ImageIcon("src//gorseller//gold10.png");
    ImageIcon gold15= new ImageIcon("src//gorseller//gold15.png");
    ImageIcon gold20= new ImageIcon("src//gorseller//gold20.png");
  
    //static int [][]
    static public int [][] hedefKoordinat = null;
    static int degiskenx;
    static int degiskeny;
    static int oTurToplananAltinMiktari;
    static int oTurAtilanAdimSayisi;
    static int geciciInteger=0;
    
    private int adimSayisi;
    private int hamleBasiAdim;
    private int harcananAltin;
    private int kasadakiAltin;
    private int toplananAltin;
    private int hamle;
    private int hedefBelirleme;
    Kare[][] alan;
    static int kx,ky;
    static boolean ilkMi;
    static int toplananAltinAdedi;
    
    
    //bHedefBelirlemeMaliyeti,bHamleMaliyeti,baslangicAltini,hamleSayisi
    public OyuncuA(Kare[][] gelenAlan,int [][] mevcut_konum, int ac, int bc, int cc, int dc) {
        this.adimSayisi = 0;
        this.harcananAltin = 0;
        this.kasadakiAltin = cc;
        this.toplananAltin = 0;
        this.hamle = bc;
        this.hedefBelirleme = ac;
        this.alan=gelenAlan;
        this.kx=0;
        this.ky=0;
        this.ilkMi=true;
        degiskenx=0;
        degiskeny=0;
        this.hamleBasiAdim =dc;
        toplananAltinAdedi=0;
       
    }

   
   public void setHedefKoordinat(){
       this.hedefKoordinat = null;
   }

    public int getHamleBasiAdim() {
        return hamleBasiAdim;
    }

    public void setHamleBasiAdim(int hamleBasiAdim) {
        this.hamleBasiAdim = hamleBasiAdim;
    }
    public int mevcut_konum_x(){
        return kx;
    }
    public int mevcut_konum_y(){
        return ky;
    }
    
    public int getHedefKoordinatx(){
        return hedefKoordinat[0][0];
    }

    public int getHedefKoordinaty(){
        return hedefKoordinat[0][1];
    }
    public int getUzaklik(){
        return hedefKoordinat[0][2];
    }
    
    public int getKx() {
        return kx;
    }

    public void setKx(int kx) {
        this.kx = kx;
    }

    public int getKy() {
        return ky;
    }

    public void setKy(int ky) {
        this.ky = ky;
    }

    public boolean isIlkMi() {
        return ilkMi;
    }

    public void setIlkMi(boolean ilkMi) {
        this.ilkMi = ilkMi;
    }

    public int getAdimSayisi() {
        return adimSayisi;
    }

    public void setAdimSayisi(int adimSayisi) {
        this.adimSayisi += adimSayisi;
        this.oTurAtilanAdimSayisi = adimSayisi;
        
    }

    public int getHarcananAltin() {
        return harcananAltin;
    }

    public void setHarcananAltin(int harcananAltin) {
        this.harcananAltin += harcananAltin;
    }

    public int getKasadakiAltin() {
        return kasadakiAltin;
    }

    public void setKasadakiAltin(int kasadakiAltin) {
        this.kasadakiAltin += kasadakiAltin;
    }

    public int getToplananAltin() {
        return toplananAltin;
    }

    public void setToplananAltin(int toplananAltin) {
        this.toplananAltin += toplananAltin;
        this.oTurToplananAltinMiktari=toplananAltin;
        this.toplananAltinAdedi++;
    }

    public int getHamle() {
        return hamle;
    }

    public void setHamle(int hamle) {
        this.hamle = hamle;
    }

    public int getHedefBelirleme() {
        return hedefBelirleme;
    }

    public void setHedefBelirleme(int hedefBelirleme) {
        this.hedefBelirleme = hedefBelirleme;
    }
    
    public void hamleYap(int [][] mevcut_konumlar) throws InterruptedException, IOException{
        

        
       geciciInteger = getHarcananAltin();
        System.out.println("A Oyuncusu");
     
        
        
        
        if(this.ilkMi==true){
            
          hedefKoordinat=aHedefBelirle();
          this.ilkMi=false;
          if(hedefKoordinat==null){
                  System.out.println("Hamle şansım yok, bu tur hamle yapmadım.");
                
                  return;
              }
          
        }
        else{
            
          if(hedefKoordinat != null && alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].isAltinKontrol()){
              System.out.println("");
             
             
              
          }else{
              hedefKoordinat=aHedefBelirle();
              if(hedefKoordinat==null){
                  System.out.println("Hamle şansım yok, bu tur hamle yapmadım.");
               
                  return;
              }
          }
          
        }
           alan[kx][ky].setIcon(Aimg);
           TimeUnit.SECONDS.sleep(1);
          // 4.bolge
      if(hedefKoordinat[0][0]>=kx && hedefKoordinat[0][1]>=ky){
          
            if(hedefKoordinat[0][2]<=this.hamleBasiAdim){
      
       
       this.setAdimSayisi(hedefKoordinat[0][2]);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);
       
       this.setToplananAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       this.setKasadakiAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
        
            
            for (int i = kx; i < hedefKoordinat[0][0]; i++) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }
                
                
                
                else{
                alan[i][ky].setIcon(null);
                
                }
                
                alan[i+1][ky].setIcon(Aimg);
               
        
                degiskenx=i+1;
                
                
                
                
                
                
            }
            
            for (int i = ky; i < hedefKoordinat[0][1]; i++) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                } 
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Aimg);
                  
                degiskeny=i+1;
                
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            alan[kx][ky].setAltinKontrol(false);
            
            
        
    
        
        
       
            }
            else if(hedefKoordinat[0][2]>this.hamleBasiAdim){
      
       this.setAdimSayisi(this.hamleBasiAdim);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);
       
       
       
       int sayac=0;
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
            
        
            
            for (int i = kx; i < hedefKoordinat[0][0]&& sayac<this.hamleBasiAdim; i++) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }
                
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i+1][ky].setIcon(Aimg);
        
                degiskenx=i+1;
                
                
                
                
                
              sayac++;
            }
            
            for (int i = ky; i < hedefKoordinat[0][1] &&sayac<this.hamleBasiAdim; i++) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }	
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Aimg);
                  
                degiskeny=i+1;
                sayac++;
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            
            
            
        
                
          
         hedefKoordinat[0][2]-=this.hamleBasiAdim;
            }
      
    }
      
      // 3. bolge
      else if(hedefKoordinat[0][0]>=kx && hedefKoordinat[0][1]<=ky){
          
            if(hedefKoordinat[0][2]<=this.hamleBasiAdim){
      
       
       this.setAdimSayisi(hedefKoordinat[0][2]);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);
       this.setToplananAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       this.setKasadakiAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
        
            
            for (int i = kx; i < hedefKoordinat[0][0]; i++) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i+1][ky].setIcon(Aimg);
        
                degiskenx=i+1;
                
                
                
                
                
                
            }
            
            for (int i = ky; i > hedefKoordinat[0][1]; i--) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }	
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Aimg);
                  
                degiskeny=i-1;
                
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            alan[kx][ky].setAltinKontrol(false);
            
            
        
    
        
        
       
            }
            else if(hedefKoordinat[0][2]>this.hamleBasiAdim){
      
       this.setAdimSayisi(this.hamleBasiAdim);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);

       
       int sayac=0;
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
            
        
            
            for (int i = kx; i < hedefKoordinat[0][0]&& sayac<this.hamleBasiAdim; i++) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i+1][ky].setIcon(Aimg);
        
                degiskenx=i+1;
                
                
                
                
                
              sayac++;
            }
            
            for (int i = ky; i > hedefKoordinat[0][1] &&sayac<this.hamleBasiAdim; i--) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Aimg);
                  
                degiskeny=i-1;
                sayac++;
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            
            
            
        
                
          
         hedefKoordinat[0][2]-=this.hamleBasiAdim;
            }
      
    }
      //1.bolge
      
      else if(hedefKoordinat[0][0]<=kx && hedefKoordinat[0][1]>=ky){
          
            if(hedefKoordinat[0][2]<=this.hamleBasiAdim){
      
       
       this.setAdimSayisi(hedefKoordinat[0][2]);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);
    
       this.setToplananAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       this.setKasadakiAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
        
            
            for (int i = kx; i > hedefKoordinat[0][0]; i--) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Aimg);
        
                degiskenx=i-1;
                
                
                
                
                
                
            }
            
            for (int i = ky; i < hedefKoordinat[0][1]; i++) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Aimg);
                  
                degiskeny=i+1;
                
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            alan[kx][ky].setAltinKontrol(false);
            
            
        
    
        
        
       
            }
            else if(hedefKoordinat[0][2]>this.hamleBasiAdim){
      
       this.setAdimSayisi(this.hamleBasiAdim);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);
       
      
       
       int sayac=0;
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
            
        
            
            for (int i = kx; i > hedefKoordinat[0][0]&& sayac<this.hamleBasiAdim; i--) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Aimg);
        
                degiskenx=i-1;
                
                
                
                
                
              sayac++;
            }
            
            for (int i = ky; i < hedefKoordinat[0][1] &&sayac<this.hamleBasiAdim; i++) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Aimg);
                  
                degiskeny=i+1;
                sayac++;
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            
            
            
        
                
          
         hedefKoordinat[0][2]-=this.hamleBasiAdim;
            }
      
    }
      
        //2.bolge
         else if(hedefKoordinat[0][0]<=kx && hedefKoordinat[0][1]<=ky){
          
            if(hedefKoordinat[0][2]<=this.hamleBasiAdim){
      
       
       this.setAdimSayisi(hedefKoordinat[0][2]);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);

       
       this.setToplananAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       this.setKasadakiAltin(alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].getMiktar());
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
        
            
            for (int i = kx; i > hedefKoordinat[0][0]; i--) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Aimg);
        
                degiskenx=i-1;
                
                
                
                
                
                
            }
            
            for (int i = ky; i > hedefKoordinat[0][1]; i--) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Aimg);
                  
                degiskeny=i-1;
                
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            alan[kx][ky].setAltinKontrol(false);
            
            
        
    
        
        
       
            }
            else if(hedefKoordinat[0][2]>this.hamleBasiAdim){
      
       this.setAdimSayisi(this.hamleBasiAdim);
       this.setHarcananAltin(this.hamle);
       this.setKasadakiAltin(this.hamle*-1);

       
       
       int sayac=0;
       
                
       // TimeUnit.SECONDS.sleep(1);
       
        
            
        
            
            for (int i = kx; i > hedefKoordinat[0][0]&& sayac<this.hamleBasiAdim; i--) {
                if(hedefKoordinat[0][0]==kx) break;
                TimeUnit.SECONDS.sleep(1);  
                
                if(alan[i][ky].isGizliAltinKontrol()){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    alan[i][ky].setGizliAltinKontrol(false);
                }else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Aimg);
        
                degiskenx=i-1;
                
                
                
                
                
              sayac++;
            }
            
            for (int i = ky; i > hedefKoordinat[0][1] &&sayac<this.hamleBasiAdim; i--) {
                if(hedefKoordinat[0][1]==ky) break;
                TimeUnit.SECONDS.sleep(1);  
                //alan[degiskenx][i].setIcon(null);
                if(alan[degiskenx][i].isGizliAltinKontrol()){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                    alan[degiskenx][i].setGizliAltinKontrol(false);
                    
                }else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Aimg);
                  
                degiskeny=i-1;
                sayac++;
            }
            //burda
            kx=degiskenx;
            ky=degiskeny;
            
            
            
        
                
          
         hedefKoordinat[0][2]-=this.hamleBasiAdim;
            }
      
    }
      
      
      alan[mevcut_konumlar[1][0]][mevcut_konumlar[1][1]].setIcon(Bimg);
      alan[mevcut_konumlar[2][0]][mevcut_konumlar[2][1]].setIcon(Cimg);
      alan[mevcut_konumlar[3][0]][mevcut_konumlar[3][1]].setIcon(Dimg);
      alan[kx][ky].setIcon(Aimg);
      
        
        int a = getHarcananAltin()-geciciInteger;
        System.out.println("Gidilen hedefin koordinatları : ("+getHedefKoordinatx()+","+getHedefKoordinaty()+")");
        System.out.println("Bu tur atılan adım sayısı : "+oTurAtilanAdimSayisi);
        System.out.println("Toplam atılan adım sayısı : "+getAdimSayisi());
        System.out.println("Bu tur harcanan altın miktarı : "+a);
        System.out.println("Toplam harcanan altın miktarı : "+getHarcananAltin());
        System.out.println("Bu tur toplanan altın miktarı : "+oTurToplananAltinMiktari);
        System.out.println("Toplam toplanan altın miktarı : "+getToplananAltin());
        System.out.println("Kasadaki altın miktarı : "+getKasadakiAltin());
        System.out.println("------------------------------");    
        
       
    }
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int [][] aHedefBelirle(){
        
        
        int uzaklik;
        int eku=1000;
        int hedefx=kx;
        int hedefy=ky;
        int [][] returnS=new int [1][3];
        
        
       
        for(int satir=0; satir< alan.length; satir++){
            for(int sutun=0;sutun<alan[0].length;sutun++){
                if(alan[satir][sutun].isAltinKontrol()&&alan[satir][sutun].isGizliAltinKontrol()==false){
            uzaklik=Math.abs(satir-kx)+Math.abs(sutun-ky);
            if(uzaklik<eku){
            eku=uzaklik;
            hedefx=satir;
            hedefy=sutun;
            }
           
                }
              
    }
 
        }
        returnS[0][0]=hedefx;
        returnS[0][1]=hedefy;
        returnS[0][2]=eku;
        
        if(returnS[0][0]==kx && returnS[0][1]==ky){
            return null;
        }
        this.setHarcananAltin(this.hedefBelirleme);
        this.setKasadakiAltin(this.hedefBelirleme*-1);
        
        return returnS;
                
        
    }
        
 
      
}