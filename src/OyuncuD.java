import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sevkikaragol
 */
public class OyuncuD {
    ImageIcon Aimg= new ImageIcon("src//gorseller//A.png");
    ImageIcon Cimg= new ImageIcon("src//gorseller//C.png");
    ImageIcon Dimg= new ImageIcon("src//gorseller//D.png");
    ImageIcon Bimg= new ImageIcon("src//gorseller//B.png");
    ImageIcon gold5= new ImageIcon("src//gorseller//gold5.png");
    ImageIcon gold10= new ImageIcon("src//gorseller//gold10.png");
    ImageIcon gold15= new ImageIcon("src//gorseller//gold15.png");
    ImageIcon gold20= new ImageIcon("src//gorseller//gold20.png");
    static int [][] hedefKoordinat = null;
    static int degiskenx;
    static int degiskeny;
    static int oTurToplananAltinMiktari;
    static int oTurAtilanAdimSayisi;
    static int geciciInteger=0;
   
     
    private int adimSayisi;
    private int harcananAltin;
    private int kasadakiAltin;
    private int toplananAltin;
    private int hamle;
    private int hedefBelirleme;
    private int hamleBasiAdim;
    Kare[][] alan;
    static int kx,ky;
    static boolean ilkMi;
    static int toplananAltinAdedi;
    //bHedefBelirlemeMaliyeti,bHamleMaliyeti,baslangicAltini,hamleSayisi
    public OyuncuD(Kare[][] gelenAlan, int [][] mevcut_konum,int ac,int bc,int cc , int dc) {
        this.adimSayisi = 0;
        this.harcananAltin = 0;
        this.kasadakiAltin = cc;
        this.toplananAltin = 0;
        this.hamle = bc;
        this.hedefBelirleme = ac;
        this.alan = gelenAlan;
        this.kx= alan.length-1;
        this.ky= 0;
        this.ilkMi=true;
        this.hamleBasiAdim = dc;
        degiskenx=alan.length-1;
        degiskeny=0;
        toplananAltinAdedi=0;
        
    }
    public int getHedefKoordinatx(){
        return hedefKoordinat[0][0];
    }

    public int getHedefKoordinaty(){
        return hedefKoordinat[0][1];
    }
    
    public void setHedefKoordinat(){
       this.hedefKoordinat = null;
   }
    public int mevcut_konum_x(){
        return kx;
    }
    public int mevcut_konum_y(){
        return ky;
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
        this.oTurToplananAltinMiktari = toplananAltin;
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
    
    
    public int baskasininHedefiMi(int [][] digerHedefler,int [][] returnS){
        if(digerHedefler[0][0]==returnS[0][0] && digerHedefler[0][1]==returnS[0][1]) return 1;
        if(digerHedefler[1][0]==returnS[0][0] && digerHedefler[1][1]==returnS[0][1]) return 2;
        if(digerHedefler[2][0]==returnS[0][0] && digerHedefler[2][1]==returnS[0][1]) return 3;
        else return 0;
    
    }
    
    public boolean dahaYakinMi(int who, int [][] digerHedefler, int [][] returnS){
        if(who==1){
            if(Math.ceil((double)digerHedefler[0][2]/this.hamleBasiAdim) < Math.ceil((double)returnS[0][2]/this.hamleBasiAdim)) return false;   
            
            else return true;
        }
        else if(who==2){
            if(Math.ceil((double)digerHedefler[1][2]/this.hamleBasiAdim) < Math.ceil((double)returnS[0][2]/this.hamleBasiAdim)) return false;
            else return true;
        }
        else if(who==3){
            if(Math.ceil((double)digerHedefler[2][2]/this.hamleBasiAdim) < Math.ceil((double)returnS[0][2]/this.hamleBasiAdim)) return false;
            else return true;
        }
        return true;
    }
    
    public int [][] dHedefBelirle(int [][] digerHedefler) throws IOException, InterruptedException{
        

        
        
        int uzaklik;
        int sonUzaklik =0;
        int ebk=-1000;
        int kar;
        int hedefx=kx;
        int hedefy=ky;
        int [][] returnS=new int [1][3];
        
        
       
        for(int satir=0; satir< alan.length; satir++){
            for(int sutun=0;sutun<alan[0].length;sutun++){
                if(alan[satir][sutun].isAltinKontrol()&&alan[satir][sutun].isGizliAltinKontrol()==false){
                    
            uzaklik= Math.abs(satir-kx)+Math.abs(sutun-ky);
            int sefer= (int) Math.ceil((double)uzaklik/this.hamleBasiAdim);
            
          
                    
            kar= (alan[satir][sutun].getMiktar()- (sefer*this.hamle) - getHedefBelirleme());
        
            if(kar==ebk){
                if(uzaklik<sonUzaklik){
                    ebk=kar;
                    hedefx=satir;
                    hedefy=sutun;
                    sonUzaklik=uzaklik;
                }
            }
            else if(kar>ebk){
            ebk=kar;
            hedefx=satir;
            hedefy=sutun;
            sonUzaklik=uzaklik;
            }
           
                }
              
    }
 
        }
        returnS[0][0]=hedefx;
        returnS[0][1]=hedefy;
        returnS[0][2]=sonUzaklik;
        
        if(returnS[0][0]==kx && returnS[0][1]==ky){
            return null;
        }
        
        int temp;
        
        
        if(baskasininHedefiMi(digerHedefler,returnS)==0) return returnS;
            else {
                temp = baskasininHedefiMi(digerHedefler,returnS);
                if(dahaYakinMi(temp,digerHedefler,returnS)==false) return null;
                else{
                    if(temp==1){
                        System.out.println("A'nın hedefine gidiyorum.");
                      
                        returnS[0][0] = digerHedefler[0][0];
                        returnS[0][1] = digerHedefler[0][1];
                        returnS[0][2] = digerHedefler[0][2];
                        return returnS;
                }
                    else if(temp==2){
                        System.out.println("B'nin hedefine gidiyorum.");
                       
                        returnS[0][0] = digerHedefler[1][0];
                        returnS[0][1] = digerHedefler[1][1];
                        returnS[0][2] = digerHedefler[1][2];
                        return returnS;
                }
                    else if(temp==3){
                        if(alan[digerHedefler[2][0]][digerHedefler[2][1]].isGizliAltinKontrol()==true){
                            alan[digerHedefler[2][0]][digerHedefler[2][1]].setGizliAltinKontrol(false);
                            TimeUnit.SECONDS.sleep(1);
                            if(alan[digerHedefler[2][0]][digerHedefler[2][1]].getMiktar()==5){
                        alan[digerHedefler[2][0]][digerHedefler[2][1]].setIcon(gold5);
                    }
                            else if(alan[digerHedefler[2][0]][digerHedefler[2][1]].getMiktar()==10){
                         alan[digerHedefler[2][0]][digerHedefler[2][1]].setIcon(gold10);
                    }
                            else if(alan[digerHedefler[2][0]][digerHedefler[2][1]].getMiktar()==15){
                         alan[digerHedefler[2][0]][digerHedefler[2][1]].setIcon(gold15);
                    }
                            else if(alan[digerHedefler[2][0]][digerHedefler[2][1]].getMiktar()==20){
                         alan[digerHedefler[2][0]][digerHedefler[2][1]].setIcon(gold20);
                    }
                            
                            
                        }
                            
                        System.out.println("C'nin hedefine gidiyorum.");
                  
                        returnS[0][0] = digerHedefler[2][0];
                        returnS[0][1] = digerHedefler[2][1];
                        returnS[0][2] = digerHedefler[2][2];
                        return returnS;
                }
            }
             
        }
       
     
        return returnS;
        
    }
    
    public void hamleYap(int [][] digerHedefler, int [][] mevcut_konumlar) throws InterruptedException, IOException{
        
        

        
        
        geciciInteger = getHarcananAltin();
        System.out.println("D Oyuncusu");
     
        
        if(this.ilkMi==true){
            
           hedefKoordinat=dHedefBelirle(digerHedefler);
          this.ilkMi=false;
          if(hedefKoordinat==null){
                  System.out.println("Hamle şansım yok, bu tur hamle yapmadım.");
 
                  return;
              }
          this.setHarcananAltin(this.hedefBelirleme);
         this.setKasadakiAltin(this.hedefBelirleme*-1);
        }
        else{
            
          if(hedefKoordinat != null && alan[hedefKoordinat[0][0]][hedefKoordinat[0][1]].isAltinKontrol()){
              System.out.println("");
             
              
          }else{
              hedefKoordinat=dHedefBelirle(digerHedefler);
              if(hedefKoordinat==null){
                  System.out.println("Hamle şansım yok, bu tur hamle yapmadım.");
        
                  return;
              }
              this.setHarcananAltin(this.hedefBelirleme);
              this.setKasadakiAltin(this.hedefBelirleme*-1);
              
          }
          
        }
           alan[kx][ky].setIcon(Dimg);
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
                    
                }else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                    
                }
                else{
                    alan[i][ky].setIcon(null);
                }
                alan[i+1][ky].setIcon(Dimg);
                
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }
                
                else{
                    alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Dimg);
                  
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
                else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i+1][ky].setIcon(Dimg);
        
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
                
                else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }	
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Dimg);
                  
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
                else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i+1][ky].setIcon(Dimg);
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Dimg);
                  
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
                }else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i+1][ky].setIcon(Dimg);
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }	
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Dimg);
                  
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
                }else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Dimg);
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }	
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Dimg);
                  
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
                }else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Dimg);
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }		
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i+1].setIcon(Dimg);
                  
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
                }
                else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Dimg);
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }	
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Dimg);
                  
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
                }else if(alan[i][ky].isAltinKontrol()&&alan[i][ky].isGizliAltinKontrol()==false){
                    if(alan[i][ky].getMiktar()==5) alan[i][ky].setIcon(gold5);
                    if(alan[i][ky].getMiktar()==10) alan[i][ky].setIcon(gold10);
                    if(alan[i][ky].getMiktar()==15) alan[i][ky].setIcon(gold15);
                    if(alan[i][ky].getMiktar()==20) alan[i][ky].setIcon(gold20);
                }
				
                else{
                alan[i][ky].setIcon(null);
                }
                
                alan[i-1][ky].setIcon(Dimg);
        
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
                    
                }else if(alan[degiskenx][i].isAltinKontrol()&&alan[degiskenx][i].isGizliAltinKontrol()==false){
                    if(alan[degiskenx][i].getMiktar()==5) alan[degiskenx][i].setIcon(gold5);
                    if(alan[degiskenx][i].getMiktar()==10) alan[degiskenx][i].setIcon(gold10);
                    if(alan[degiskenx][i].getMiktar()==15) alan[degiskenx][i].setIcon(gold15);
                    if(alan[degiskenx][i].getMiktar()==20) alan[degiskenx][i].setIcon(gold20);
                }		
                else{
                alan[degiskenx][i].setIcon(null);
                }
                
             
                alan[degiskenx][i-1].setIcon(Dimg);
                  
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
      alan[mevcut_konumlar[0][0]][mevcut_konumlar[0][1]].setIcon(Aimg);
      alan[kx][ky].setIcon(Dimg);
        
      
      
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
 
}
