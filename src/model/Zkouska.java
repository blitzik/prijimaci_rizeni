package model;

import java.io.Serializable;


public class Zkouska implements Serializable
{
    private Ucastnik ucastnik;
    private Predmet predmet;
    private int pocetDosazenychBodu;
    
    
    public Zkouska(Ucastnik ucastnik, Predmet predmet, int pocetDosazenychBodu)
    {
        this.ucastnik = ucastnik;
        this.predmet = predmet;
        this.pocetDosazenychBodu = pocetDosazenychBodu;
        predmet.pridejZkousku(this);
    }
    
    
    public String getIdentifikacniCisloUcastnika()
    {
        return ucastnik.getIdentifikacniCislo();
    }
    
    
    public String getJmenoUcastnika()
    {
        return ucastnik.getJmeno();
    }
    
    
    public int getMaxPocetBoduZaPredmet()
    {
        return predmet.getMaxPocetBodu();
    }
    
    
    public int getMinPocetBoduProPrijetiZaPredmet()
    {
        return predmet.getMinPocetBoduProPrijeti();
    }
    
    
    public String getNazevPredmetu()
    {
        return predmet.getNazev();
    }
    
    
    public boolean obsahujePredmet(Predmet predmet)
    {
        return this.predmet == predmet;
    }
    

    public int getPocetDosazenychBodu()
    {
        return pocetDosazenychBodu;
    }
    
    
    public boolean ucastnikSplnilPredmet()
    {
        return pocetDosazenychBodu >= predmet.getMinPocetBoduProPrijeti();
    }
    
    
}
