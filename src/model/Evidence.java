package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;


public class Evidence implements Serializable
{
    // Předměty, ze kterých se zkouší
    private ArrayList<Predmet> predmety = null;
    private ArrayList<Ucastnik> ucastnici = null;
    
 
    public Evidence()
    {
        this.predmety = new ArrayList<Predmet>();
        this.ucastnici = new ArrayList<Ucastnik>();
    }
    
    
    public Predmet pridejPredmet(String nazev, int maxPocetBodu, int minPocetBoduProPrijeti)
    {
        Predmet predmet = new Predmet(nazev, maxPocetBodu, minPocetBoduProPrijeti);
        this.predmety.add(predmet);
        
        return predmet;
    }
    
    
    public Ucastnik pridejUcastnika(String identifikacniCislo, String jmeno)
    {
        Ucastnik ucastnik = new Ucastnik(identifikacniCislo, jmeno);
        this.ucastnici.add(ucastnik);
        
        return ucastnik;
    }
    
    
    public void odstranUcastnika(int index)
    {
        Ucastnik ucastnik = this.ucastnici.get(index);
        ArrayList<Zkouska> zkouskyUcastnika = ucastnik.ziskejZkousky();
        for (Predmet p: this.ziskejPredmety()) {
            for (Zkouska z: zkouskyUcastnika) {
                if (z.obsahujePredmet(p)) {
                    p.odstranZkousku(z);
                }
            }
        }
        
        this.ucastnici.remove(ucastnik);
    }
    
    
    public ArrayList<Ucastnik> ziskejUcastniky()
    {
        return new ArrayList<Ucastnik>(this.ucastnici);
    }
    
    
    public ArrayList<Predmet> ziskejPredmety()
    {
        return new ArrayList<Predmet>(this.predmety);
    }
    
    
    public int ziskejPocetUcastniku()
    {
        return this.ucastnici.size();
    }
    
    
    public void export()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("export.txt"))) {
            for (Ucastnik u: ucastnici) {
                bw.write(u.getJmeno() + " - " + (u.splnilZkousku() == true ? "Splnil zkousku" : "Nesplnil zkousku"));
                bw.newLine();
                
                for (Zkouska z: u.ziskejZkousky()) {
                    bw.write(z.getNazevPredmetu() + " - (" + z.getPocetDosazenychBodu() + " / " + z.getMaxPocetBoduZaPredmet() + ") " + (z.ucastnikSplnilPredmet() == true ? "Splnil predmet" : "Nesplnil predmet"));
                    bw.newLine();
                }
                bw.newLine();
                bw.newLine();
            }
            bw.flush();
            
            System.out.println("Soubor ulozen");
            
        } catch (Exception e) {
            
            System.out.println("Nelze ulozit." + e.getMessage());
        }
    }
    
}
