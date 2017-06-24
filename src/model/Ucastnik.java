package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ucastnik implements Serializable
{
    private String identifikacniCislo;
    private String jmeno;
    
    private ArrayList<Zkouska> zkousky = null;
    
    
    public Ucastnik(String identifikacniCislo, String jmeno)
    {
        this.identifikacniCislo = identifikacniCislo;
        this.jmeno = jmeno;
        this.zkousky = new ArrayList();
    }
    
    
    public void pridejZkousku(Predmet predmet, int pocetDosazenychBodu)
    {
        zkousky.add(new Zkouska(this, predmet, pocetDosazenychBodu));
    }
    
    
    public ArrayList<Zkouska> ziskejZkousky()
    {
        return new ArrayList<Zkouska>(this.zkousky);
    }
    
    
    public boolean splnilZkousku()
    {
        boolean proselZkouskou = true;
        for (Zkouska z: this.zkousky) {
            if (z.ucastnikSplnilPredmet() == false) {
                proselZkouskou = false;
                break;
            }
        }
        
        return proselZkouskou;
    }
    

    public String getIdentifikacniCislo()
    {
        return identifikacniCislo;
    }
    

    public String getJmeno()
    {
        return jmeno;
    }
    
    
    
    
}
