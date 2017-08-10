package sk.maha.maskvalidation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaciaMaskyIP {

	/**
	 * Pole stringov obsahujece jednotlive regularne vyrazy pre vsetky validne
	 * masky sieti.
	 */
	private static String[] mnozinaPaternovValidacie = { "1*0*\\.0+\\.0+\\.0+", "1{8}\\.1*0*\\.0+\\.0+", "1{8}\\.1{8}\\.1*0*\\.0+",
			"1{8}\\.1{8}\\.1{8}\\.1*0*" };

	/**
	 * Premenna pre metodu ziskajMaskuSiete().
	 */
	private static String zadanaMaska;

	/**
	 * Premenne pre metodu validaciaMaskySiete().
	 */
	private static String[] maskaCastiStringPole;
	private static Integer[] maskaCastiIntegerPole;
	private static String[] maskaCastiStringBinarnePole;
	private static String maskaStringBinarnejPodoby;

	/**
	 * Ziskanie masky siete od uzivatela.
	 * 
	 * @param s
	 * @return maska siete
	 */
	public static String ziskajMaskuSiete(Scanner s) {

		System.out.print("Zadajte masku siete: ");
		zadanaMaska = s.nextLine();
		return zadanaMaska;
	}

	/**
	 * Kontrolua formy masky.
	 * 
	 * @param zadanaMaska
	 * @return vysledok validacie formy masky
	 */
	public static boolean kontrolaFormyMaskySiete(String zadanaMaska) {
		Pattern pat = Pattern.compile(
				"(1?\\d\\d?|2[0-4]\\d|25[0-5])\\.(1?\\d\\d?|2[0-4]\\d|25[0-5])\\.(1?\\d\\d?|2[0-4]\\d|25[0-5])\\.(1?\\d\\d?|2[0-4]\\d|25[0-5])");
		Matcher mat = pat.matcher(zadanaMaska);

		return mat.matches() ? true : false;
	}

	/**
	 * Validacia masky siete.
	 * 
	 * @param zadanaMaska
	 * @return vysledok validacie masky
	 */
	public static boolean validaciaMaskySiete(String zadanaMaska) {

		Pattern pat;
		Matcher mat;
		maskaStringBinarnejPodoby = new String();
		boolean vysledokValidacie = false;

		/**
		 * Rozdelenie casti masky do pola Stringov.
		 */
		maskaCastiStringPole = zadanaMaska.split("\\.");
		
		/**
		 * Prevod jednotlivych casti stringov do integer pola.
		 */
		maskaCastiIntegerPole = new Integer[maskaCastiStringPole.length];
		for (int i = 0; i < maskaCastiStringPole.length; i++) {
			maskaCastiIntegerPole[i] = Integer.parseInt(maskaCastiStringPole[i]);
		}
		
		/**
		 * Prevod jednotlivych prvkov integer pola do bitovej formy.
		 */
		maskaCastiStringBinarnePole = new String[maskaCastiIntegerPole.length];
		for (int i = 0; i < maskaCastiIntegerPole.length; i++) {
			// maskaCastiStringBinarnePole[i] =
			// Integer.toBinaryString(maskaCastiIntegerPole[i]);
			maskaCastiStringBinarnePole[i] = String
					.format("%" + Integer.toString(8) + "s", Integer.toBinaryString(maskaCastiIntegerPole[i]))
					.replace(" ", "0");
		}

		/**
		 * Vytvorenie binarnej podoby masky siete.
		 */
		for (int i = 0; i < maskaCastiStringBinarnePole.length; i++) {
			if (i == (maskaCastiStringBinarnePole.length - 1)) {
				maskaStringBinarnejPodoby += maskaCastiStringBinarnePole[i];
				break;
			}
			maskaStringBinarnejPodoby += maskaCastiStringBinarnePole[i] + ".";
		}

		/**
		 * Validacia masky siete.
		 */
		for (String pattern : mnozinaPaternovValidacie) {

			pat = Pattern.compile(pattern);
			mat = pat.matcher(maskaStringBinarnejPodoby);

			if (mat.matches()) {
				vysledokValidacie = true;
			}
		}
		return vysledokValidacie ? true : false;
	}

	/**
	 * Pokracovat alebo ukoncit program.
	 * 
	 * @param s
	 * @return odpoved uzivatela pre pokracovanie ci ukoncenie
	 */
	public static boolean pokracovat(Scanner s) {

		char odpoved;
		do {
			System.out.println();
			System.out.println("Chcete pokracovat?");
			System.out.print("Ano alebo nie? y/n : ");
			odpoved = s.next().charAt(0);
			s.nextLine();
			System.out.println();
		} while (odpoved != 'y' & odpoved != 'n');

		return (odpoved == 'y') ? true : false;

	}
}
