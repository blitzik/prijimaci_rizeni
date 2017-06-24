package app;

import model.*;
import utils.Vstupy;
import java.io.*;
import java.util.*;


public class EvidenceApp
{
    private Evidence evidence = null;

    
    public void start() {
        this.evidence = new Evidence();
        
        this.evidence.pridejPredmet("Matematika", 100, 60);
        this.evidence.pridejPredmet("Informatika", 100, 60);
        this.evidence.pridejPredmet("Ekonomie", 100, 60);
        
        zobrazMenu();
    }
    
    
    public void zobrazMenu() {
        char volba = ' ';
        Ucastnik u = null;
        int dosazeneBody = 0;

        System.out.println("<<<------------------ Evidence ucastniku prijimaciho rizeni ------------------>>>");

        do {
            System.out.println();
            System.out.println();
            System.out.println("--------- MENU ---------");
            System.out.println("P - Pridat ucastnika");
            System.out.println("V - Vypsat ucastniky");
            System.out.println("Z - Zobrazit prumerny pocet bodu za predmet");
            System.out.println("S - Smazat ucastnika");
            System.out.println("N - Nacist evidenci");
            System.out.println("U - Ulozit evidenci");
            System.out.println("K - Konec");
            System.out.println();
            volba = Character.toUpperCase(Vstupy.ctiChar("Vase volba: "));
            System.out.println();

            switch (volba) {
                case 'P':
                    u = this.evidence.pridejUcastnika(
                            Vstupy.ctiString("Zadejte identifikacni cislo: "),
                            Vstupy.ctiString("Zadejte jmeno ucastnika: ")
                    );
                    
                    for (Predmet p: this.evidence.ziskejPredmety()) {
                        dosazeneBody = Integer.parseInt(Vstupy.ctiString("Zadejte pocet dosazenych bodu v predmetu " + p.getNazev() + "(0 - " + p.getMaxPocetBodu() + "): "));
                        u.pridejZkousku(p, dosazeneBody);
                    }
                    
                    break;
                    
                case 'V':
                    this.vypisVse();
                    break;
                
                case 'Z':
                    this.vypisPrumer();
                    
                    break;
                    
                case 'S':
                    this.smazUcastnika();
                    break;
                    
                case 'N':
                    this.nacti();
                    break;
                    
                case 'U':
                    this.uloz();
                    break;

                default:
                    break;
            }

        } while (volba != 'K');

    }
    
    
    private void vypisVse()
    {
        if (this.evidence.ziskejPocetUcastniku() < 1) {
            System.out.println("Evidence neobsahuje zadne zaznamy.");
            return;
        }
        
        System.out.println("---------------SEZNAM UCASTNIKU---------------");
        for (Ucastnik u: this.evidence.ziskejUcastniky()) {
            System.out.println("----------------------------------------------");
            System.out.println(u.getJmeno() + ": " + (u.splnilZkousku() == true ? "byl" : "nebyl") + " prijat");
        
            System.out.println("Predmety:");
            for (Zkouska z: u.ziskejZkousky()) {
                System.out.println(z.getNazevPredmetu() + " - " + (z.ucastnikSplnilPredmet() == true ? "splnil" : "nesplnil") + " zkousku");
            }
            System.out.println("----------------------------------------------");
        }
    }
    
    
    private void vypisPrumer()
    {
        if (this.evidence.ziskejPocetUcastniku() < 1) {
            System.out.println("Evidence neobsahuje zadne zaznamy.");
            return;
        }
        
        for (Predmet p: this.evidence.ziskejPredmety()) {
            System.out.println(p.getNazev() + ": " + p.getCelkovyPrumer());
        }
    }
    
    
    private void smazUcastnika()
    {
        if (this.evidence.ziskejPocetUcastniku() < 1) {
            System.out.println("Evidence neobsahuje zadne zaznamy.");
            return;
        }
        
        System.out.println("---------------SEZNAM UCASTNIKU---------------");
        ArrayList<Ucastnik> ucastnici = this.evidence.ziskejUcastniky();
        for (int i = 0; i < ucastnici.size(); i++) {
            System.out.println((i + 1) + ". " + ucastnici.get(i).getJmeno());
        }
            
        char v = Vstupy.ctiChar("Zadejte cislo ucastnika nebo Z pro navrat zpet:");
        v = Character.toUpperCase(v);
        if (v == 'Z') {
            return;
        }
                
        this.evidence.odstranUcastnika(Character.getNumericValue(v) - 1);
    }
    
    
    private void nacti() {
        try {
            ObjectInputStream input = new ObjectInputStream(
                                      new FileInputStream("evidence.dat"));
            this.evidence = (Evidence) input.readObject();
        } catch (Exception e) {
            System.out.println("CHYBA: Nelze nacist soubor. " + e.getMessage());
        }
    }
    
    
    private void uloz() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                                        new FileOutputStream("evidence.dat"));
            output.writeObject(this.evidence);
            
        } catch (Exception e) {
            System.out.println("CHYBA: Nelze ulozit soubor. " + e.getMessage());
        }
    }


    public static void main(String[] args)
    {
        new EvidenceApp().start();
    }
    
}
