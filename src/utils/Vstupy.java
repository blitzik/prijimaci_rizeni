package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Pomocna trida urcena pro jednoduche nacitani dat z konzole
 * @author Filip Maly
 *
 */
public class Vstupy {
		
	
	/**
	 * Metoda pro nacteni retezce z konzole
	 * 
	 * @param vyzva
	 *            text, ktery se zobrazi jako vyzva pro zadani v konzoli
	 * @return nacteny retezec v pripade uspechu, jinak vraci prazdny retezec ""
	 */
	public static String ctiString(String vyzva) {
		String s;
		System.out.print(vyzva);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			s = br.readLine();
		} catch (Exception e) {
			s = "";
			System.out.println("Chyba vstupu");
		}
		return s;
	}
	
	
	/**
	 * Metoda pro nacteni realneho cisla z konzole
	 * @param vyzva text, ktery se zobrazi jako vyzva pro zadani v konzoli
	 * @return nactene realne cislo v pripade uspechu, jinak vraci 0
	 */
	public static double ctiDouble(String vyzva) {
		double cislo;
		System.out.print(vyzva);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			cislo = Double.parseDouble(s);
		} catch (Exception e) {
			cislo = 0;
			System.out.println("Chyba vstupu");
		}
		return cislo;
	}

	
	
	
	/**
	 * Metoda pro nacteni jednoho znaku z konzole
	 * @param vyzva text, ktery se zobrazi jako vyzva pro zadani v konzoli
	 * @return nacteny znak v pripade uspechu, jinak vraci znak mezera ' '
	 */
	public static char ctiChar(String vyzva) {
		char znak;
		System.out.print(vyzva);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			znak = s.charAt(0);
		} catch (Exception e) {
			znak = ' ';
			System.out.println("Chyba vstupu");
		}
		return znak;
	}

}
