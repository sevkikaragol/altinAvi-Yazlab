import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class OyuncuC {
    ImageIcon Aimg= new ImageIcon("src//gorseller//A.png");
    ImageIcon Cimg= new ImageIcon("src//gorseller//C.png");
    ImageIcon Dimg= new ImageIcon("src//gorseller//D.png");
    ImageIcon Bimg= new ImageIcon("src//gorseller//B.png");
    ImageIcon gold5= new ImageIcon("src//gorseller//gold5.png");
    ImageIcon gold10= new ImageIcon("src//gorseller//gold10.png");
    ImageIcon gold15= new ImageIcon("src//gorseller//gold15.png");
    ImageIcon gold20= new ImageIcon("src//gorseller//gold20.png");
    ImageIcon sgold5= new ImageIcon("src//gorseller//sgold5.png");
    ImageIcon sgold10= new ImageIcon("src//gorseller//sgold10.png");
    ImageIcon sgold15= new ImageIcon("src//gorseller//sgold15.png");
    ImageIcon sgold20= new ImageIcon("src//gorseller//sgold20.png");
    
    static int [][] hedefKoordinat = null;
    static int degiskenx;
    static int degiskeny;
    static int oTurToplananAltinMiktari;
    static int oTurAtilanAdimSayisi;
    static int geciciInteger=0;
    static int [][] acilanGizliAltinlar=null;
    static int [][] dHedefMatrisi=null;
    
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
    
    
    
    private int gizliAltinSayac;

    public int getGizliAltinSayac() {
        return gizliAltinSayac;
    }

    public void setGizliAltinSayac(int gizliAltinSayac) {
        this.gizliAltinSayac--;
    }
    //bHedefBelirlemeMaliyeti,bHamleMaliyeti,baslangicAltini,hamleSayisi,altınYuzde,gizliAltinYuzde
    public OyuncuC(Kare[][] gelenAlan, int [][] mevcut_konum,int ac,int bc,int cc,int dc,double ay,double gay) {
        this.adimSayisi = 0;
        this.harcananAltin = 0;
        this.kasadakiAltin = cc;
        this.toplananAltin = 0;
        this.hamle = bc;
        this.hedefBelirleme = ac;
        this.alan = gelenAlan;
        this.kx=alan.length-1;
        this.ky= alan[0].length-1;
        this.ilkMi=true;
        this.hamleBasiAdim = dc;
        degiskenx=alan.length-1;
        degiskeny=alan[0].length-1;
        ay=(double)ay/100;
        gay=(double)gay/100;
        this.gizliAltinSayac=(int) (Math.ceil((double)(this.alan.length*this.alan[0].length*ay*gay)));
        toplananAltinAdedi=0;
        
        
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
    public int getHedefKoordinatx(){
        return hedefKoordinat[0][0];
    }
    public int getHedefKoordinaty(){
        return hedefKoordinat[0][1];
    }
    public int getUzaklik(){
        return hedefKoordinat[0][2];
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

    public int getKasadakiAltin() {
        return kasadakiAltin;
    }

    public void setKasadakiAltin(int kasadakiAltin) {
        this.kasadakiAltin += kasadakiAltin;
    }

    public void setHedefBelirleme(int hedefBelirleme) {
        this.hedefBelirleme = hedefBelirleme;
    }
    
    public int [][] enYakiniBul(){
        int enYakin[][]= new int[1][2];
        int uzaklik;
        int eku=1000;
        int hedefx=0;
        int hedefy=0;
        
        
       
        for(int satir=0; satir< alan.length; satir++){
            for(int sutun=0;sutun<alan[0].length;sutun++){
                if(alan[satir][sutun].isAltinKontrol()&&alan[satir][sutun].isGizliAltinKontrol()==true){
            uzaklik=Math.abs(satir-kx)+Math.abs(sutun-ky);
            if(uzaklik<eku){
            eku=uzaklik;
            hedefx=satir;
            hedefy=sutun;
            }
           
                }
              
    }
 
        }
        enYakin[0][0]=hedefx;
        enYakin[0][1]=hedefy;
        
        if(hedefx==0 && hedefy==0){
            alan[hedefx][hedefy].setAltinKontrol(false);
        }
       
        alan[hedefx][hedefy].setGizliAltinKontrol(false);


        return enYakin;
    }
    
    public void gorselBas(int satir,int sutun) throws IOException{
       
        
        
        
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
    
    public void gizliAltinAc() throws InterruptedException, IOException{
        
       
        
        this.gizliAltinSayac=0;
        for(int satir=0; satir< alan.length; satir++){
            for(int sutun=0;sutun<alan[0].length;sutun++){
                if(alan[satir][sutun].isGizliAltinKontrol()==true){
                    gizliAltinSayac++;
                }
            }
            
          }
        
        
        if(gizliAltinSayac>0){
            
            if(gizliAltinSayac>=2){

            acilanGizliAltinlar = new int[2][2];
            for (int i = 0; i < 2; i++) {
            
            int gecici[][]= new int[1][2]; 
            gecici=enYakiniBul();
            TimeUnit.SECONDS.sleep(1);
 
            System.out.println("("+gecici[0][0]+","+gecici[0][1]+") koordinatındaki gizli altın açıldı.");
               
                
                    gorselBas(gecici[0][0],gecici[0][1]);
                    
                }
              
             
              return;
            }
            else if(this.gizliAltinSayac==1){

            acilanGizliAltinlar = new int[1][2];
             TimeUnit.SECONDS.sleep(1);
            int gecici[][]= new int[1][2]; 
            gecici=enYakiniBul();
         
                gorselBas(gecici[0][0],gecici[0][1]);
                
                System.out.println("("+gecici[0][0]+","+gecici[0][1]+") koordinatındaki gizli altın açıldı.");

            

                return;
            }
         
        }
        else return;
    
        
        
    }
    
    public int [][] cHedefBelirle() throws InterruptedException, IOException{
        
        gizliAltinAc();
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
                
        this.setHarcananAltin(this.hedefBelirleme);
       this.setKasadakiAltin(this.hedefBelirleme*-1);
        

 
        return returnS;
                
        
    }
    
     public void hamleYap(int [][] mevcut_konumlar) throws InterruptedException, IOException{
          
      
         
         
         
         geciciInteger = getHarcananAltin();
        System.out.println("C Oyuncusu");
    
        
        if(this.ilkMi==true){
            
           hedefKoordinat=cHedefBelirle();
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
              hedefKoordinat=cHedefBelirle();
              if(hedefKoordinat==null){
                  System.out.println("Hamle şansım yok, bu tur hamle yapmadım.");
           
                  return;
              } 
          }
          
        }
        
        
           alan[kx][ky].setIcon(Cimg);
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
                alan[i+1][ky].setIcon(Cimg);
                
        
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
                
             
                alan[degiskenx][i+1].setIcon(Cimg);
                  
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
                
                alan[i+1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i+1].setIcon(Cimg);
                  
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
                
                alan[i+1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i-1].setIcon(Cimg);
                  
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
                
                alan[i+1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i-1].setIcon(Cimg);
                  
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
                
                alan[i-1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i+1].setIcon(Cimg);
                  
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
                
                alan[i-1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i+1].setIcon(Cimg);
                  
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
                
                alan[i-1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i-1].setIcon(Cimg);
                  
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
                
                alan[i-1][ky].setIcon(Cimg);
        
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
                
             
                alan[degiskenx][i-1].setIcon(Cimg);
                  
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
      alan[mevcut_konumlar[3][0]][mevcut_konumlar[3][1]].setIcon(Dimg);
      alan[mevcut_konumlar[0][0]][mevcut_konumlar[0][1]].setIcon(Aimg);
      alan[kx][ky].setIcon(Cimg);
     
        
    
       
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
   
     
   public int dUzaklikBulma(int satir, int sutun){
        int uzaklik;
       
        uzaklik=Math.abs(satir-kx)+Math.abs(sutun-ky);
            
        return uzaklik;
    }
     
     
     
     
     
     
     
     
 public int [][] dHedef(){
     
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
        
        
        int enkUz1=1000,enkUz2=1000,enkUz3=1000;
        int kar1,kar2,kar3;
        if(gizliAltinSayac>0){
            if(gizliAltinSayac>=2){
                dHedefMatrisi = new int[2][4];
                 for(int satir=0; satir< alan.length; satir++){
                        for(int sutun=0;sutun<alan[0].length;sutun++){
                            if(alan[satir][sutun].isGizliAltinKontrol()==true){
                                uzaklik = dUzaklikBulma(satir, sutun);
                                if(uzaklik<enkUz1){
                                    dHedefMatrisi[0][0]=satir;
                                    dHedefMatrisi[0][1]=sutun;
                                    dHedefMatrisi[0][2]=uzaklik;
                                    int sefer= (int) Math.ceil((double)uzaklik/this.hamleBasiAdim);
                                    kar1= (alan[satir][sutun].getMiktar()- (sefer*this.hamle) - getHedefBelirleme());
                                    dHedefMatrisi[0][3]=kar1;
                                    enkUz1=uzaklik;
                                }
                            }
                        }
                 }
                 
                 
                  for(int satir=0; satir< alan.length; satir++){
                        for(int sutun=0;sutun<alan[0].length;sutun++){
                            if(alan[satir][sutun].isGizliAltinKontrol()==true && satir!=dHedefMatrisi[0][0] && sutun!=dHedefMatrisi[0][1]){
                                uzaklik = dUzaklikBulma(satir, sutun);
                                if(uzaklik<enkUz2){
                                    dHedefMatrisi[1][0]=satir;
                                    dHedefMatrisi[1][1]=sutun;
                                    dHedefMatrisi[1][2]=uzaklik;
                                    int sefer= (int) Math.ceil((double)uzaklik/this.hamleBasiAdim);
                                    kar2= (alan[satir][sutun].getMiktar()- (sefer*this.hamle) - getHedefBelirleme());
                                    dHedefMatrisi[1][3]=kar2;
                                    enkUz2=uzaklik;
                                }
                            }
                        }
                  }
                  
                  if(dHedefMatrisi[0][3]>dHedefMatrisi[1][3]){
                      if(dHedefMatrisi[0][3]>ebk){
                        returnS[0][0]=dHedefMatrisi[0][0];
                        returnS[0][1]=dHedefMatrisi[0][1];
                        returnS[0][2]=dHedefMatrisi[0][2];
                      }
                  }
                  else{
                      if(dHedefMatrisi[1][3]>ebk){
                        returnS[0][0]=dHedefMatrisi[1][0];
                        returnS[0][1]=dHedefMatrisi[1][1];
                        returnS[0][2]=dHedefMatrisi[1][2];
                  }
                 }

            }
            else if(gizliAltinSayac==1){
                dHedefMatrisi = new int[1][4];
                 for(int satir=0; satir< alan.length; satir++){
                        for(int sutun=0;sutun<alan[0].length;sutun++){
                            if(alan[satir][sutun].isGizliAltinKontrol()==true){
                                uzaklik = dUzaklikBulma(satir, sutun);
                                if(uzaklik<enkUz3){
                                    dHedefMatrisi[0][0]=satir;
                                    dHedefMatrisi[0][1]=sutun;
                                    dHedefMatrisi[0][2]=uzaklik;
                                    int sefer= (int) Math.ceil((double)uzaklik/this.hamleBasiAdim);
                                    kar3= (alan[satir][sutun].getMiktar()- (sefer*this.hamle) - getHedefBelirleme());
                                    dHedefMatrisi[0][3]=kar3;
                                    enkUz3=uzaklik;
                                }
                            }
                        }
                 }
                      if(dHedefMatrisi[0][3]>ebk){
                        returnS[0][0]=dHedefMatrisi[0][0];
                        returnS[0][1]=dHedefMatrisi[0][1];
                        returnS[0][2]=dHedefMatrisi[0][2];
                      }
                  
                 
            }
        }
        
        
        
        
        
        
            if(returnS[0][0]==kx && returnS[0][1]==ky){
                return null;
        }
                
       

 
        return returnS;
 }
    
}
    
    