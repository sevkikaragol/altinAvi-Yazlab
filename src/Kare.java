
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sevkikaragol
 */
public class Kare extends JButton{
    private int satir,sutun,miktar;
    private boolean altinKontrol,gizliAltinKontrol;

    public Kare(int satir, int sutun) {
        this.satir = satir;
        this.sutun = sutun;
        this.miktar = 0;
        this.altinKontrol = false;
        this.gizliAltinKontrol=false;
    }

    public boolean isGizliAltinKontrol() {
        return gizliAltinKontrol;
    }

    public void setGizliAltinKontrol(boolean gizliAltinKontrol) {
        this.gizliAltinKontrol = gizliAltinKontrol;
    }

    public int getSatir() {
        return satir;
    }

    public void setSatir(int satir) {
        this.satir = satir;
    }

    public int getSutun() {
        return sutun;
    }

    public void setSutun(int sutun) {
        this.sutun = sutun;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public boolean isAltinKontrol() {
        return altinKontrol;
    }

    public void setAltinKontrol(boolean altinKontrol) {
        this.altinKontrol = altinKontrol;
    }
    
    
    
    
    
    
    
}
