package sk.maha.maskvalidation;

import java.util.Scanner;

public class ValidaciaMaskyIPDemo {

	private static Scanner sc = new Scanner(System.in);
	private static char opakovanieZiskaniaMasky;

	public static void main(String[] args) {

		String maskaSiete;
		boolean odpovedPokracovania;

		for (;;) {

			do {
				maskaSiete = ValidaciaMaskyIP.ziskajMaskuSiete(sc);

				System.out.println();
				System.out.println("Zadana maska (" + maskaSiete + ") "
						+ (ValidaciaMaskyIP.kontrolaFormyMaskySiete(maskaSiete) ? "MA" : "NEMA") + " spravny format");
				System.out.println();

				if (ValidaciaMaskyIP.kontrolaFormyMaskySiete(maskaSiete)) {
					System.out.println("Maska postupila do procesu validacie.");
				} else {
					System.out.println("Chcete pokracovat v zadavani masky alebo ukoncit program?");
					System.out.print("Pre ukoncenie programu stlacte 'n',\npre pokracovanie stlacte 'y': ");
					opakovanieZiskaniaMasky = sc.next().charAt(0);
					sc.nextLine();
				}
				System.out.println();

			} while (!ValidaciaMaskyIP.kontrolaFormyMaskySiete(maskaSiete) && opakovanieZiskaniaMasky == 'y');


			if (opakovanieZiskaniaMasky == 'n') {
				System.out.println();
				System.out.println("Koniec programu !!!");
				sc.close();
				break;
			}

			System.out.println();
			System.out.println("Maska (" + maskaSiete + ") "
					+ (ValidaciaMaskyIP.validaciaMaskySiete(maskaSiete) ? "JE" : "NIE JE") + " validna");

			odpovedPokracovania = ValidaciaMaskyIP.pokracovat(sc);
			if (!odpovedPokracovania) {
				System.out.println();
				System.out.println("Program ukonceny.");
				sc.close();
				break;
			}

		}

	}
}
