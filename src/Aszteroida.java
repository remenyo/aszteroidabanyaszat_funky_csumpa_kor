package src;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Danesz
 */
public class Aszteroida extends Hely {
    private int reteg;
    private boolean napkozel;
    private ArrayList<Szereplo> szereplok; //elso helyen mindig egy telepes masodikon egy robot
    private ArrayList<Hely> szomszedok; //elso helyen mindig aszteroida masodikon portal
    private Nap nap;
    private Nyersanyag nyersanyag;
    private static NyersanyagKoltseg urbazisKoltseg;
    
    Aszteroida(int reteg,boolean napkozel,Nap nap,Nyersanyag nyersanyag){
        this.reteg = reteg;
        this.napkozel =  napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }
    
    public Hely getSzomszed(int i) {
    	return szomszedok.get(i);
    }
    
    public void Robbanas(){
        nyersanyag.Robbanas();
        szomszedok.get(0).szomszedRobbant(this);
        szomszedok.get(1).szomszedRobbant(this);
        szereplok.get(0).Robbanas();
        nap.torolAszteroida(this);
        
    }
    
    public void Napvihar(){
    	boolean valasz = Szkeleton().Kerdes("Ures az aszteroida es ki van furva?");
        if (valasz) {
        	szereplok.get(0).Napvihar();
        	szereplok.get(1).Napvihar();
        }
 
    }
    
    public void Furas(){
        boolean elfogyott = new Szkeleton().Kerdes("Elfogy a k�peny? (1:Igen 0:Nem)");
        if(elfogyott)
        	nyersanyag.felszinreKerul(this);
        
    }
    
    public void torolSzomszed(Hely h){
        szomszedok.remove(h);
        Scanner be = new Scanner(System.in);
        System.out.println("Maradt szomszed?");
        char valasz = be.next().charAt(0);
        if(valasz == 'n' || valasz == 'N') {
        	szereplok.get(0).Meghal();
            szereplok.get(1).Meghal();
            Robbanas();
        }
    }
    
    public void hozzaadSzomszed(Hely h){
        szomszedok.add(h);
    }
    
    public Nyersanyag Banyaszat(){
        Nyersanyag visszaAdando = nyersanyag;
        nyersanyag = null;
        return nyersanyag;
    }
    
    public void ellenorizNyert(){
    	ArrayList<Nyersanyag> 
        szereplok.get(0).getNyersanyagok();
    }
    
    public void hozzaadSzereplo(Szereplo sz){
        szereplok.add(sz);
    }
    
    public void torolSzereplo(Szereplo sz){
        szereplok.remove(sz);
    }
    
    public void torolNyersanyag(){
        nyersanyag = null;
    }
    
    public void hozzaadNyersanyag(Nyersanyag ny){
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }
    
    public void Utazas(Szereplo sz){
        sz.beallitAszteroida(this);
    }
    
    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k){
        urbazisKoltseg = k;
    }
    
    public void szomszedRobbant(Aszteroida a){
        torolSzomszed(a);
    }
    
    public boolean isNapkozelben() {
    	return new Szkeleton().Kerdes("Napk�zelben van az aszteroida?");
    }
    
    public void setNyersanyag(Nyersanyag ny) {
    	nyersanyag = ny;
    }
}


