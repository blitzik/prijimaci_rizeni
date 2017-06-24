package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Predmet implements Serializable
{
    private String nazev;
    private int maxPocetBodu;
    private int minPocetBoduProPrijeti;
    
    private ArrayList<Zkouska> zkousky = null;
    
    
    public Predmet(String nazev, int maxPocetBodu, int minPocetBoduProPrijeti)
    {
        this.nazev = nazev;
        this.maxPocetBodu = maxPocetBodu;
        this.minPocetBoduProPrijeti = minPocetBoduProPrijeti;
        this.zkousky = new ArrayList();
    }
    
    
    public void pridejZkousku(Zkouska zkouska)
    {
        zkousky.add(zkouska);
    }
    
    
    public void odstranZkousku(Zkouska zkouska)
    {
        this.zkousky.remove(zkouska);
    }
    
    
    public double getCelkovyPrumer()
    {
        if (zkousky.size() == 0) {
            return 0d;
        }
        
        int celkovyPocetBodu = 0;
        for (Zkouska z: this.zkousky) {
            celkovyPocetBodu += z.getPocetDosazenychBodu();
        }
        
        return celkovyPocetBodu / zkousky.size();
    }
    

    public String getNazev()
    {
        return nazev;
    }
    

    public int getMaxPocetBodu()
    {
        return maxPocetBodu;
    }
    

    public int getMinPocetBoduProPrijeti()
    {
        return minPocetBoduProPrijeti;
    }
    
    
}
