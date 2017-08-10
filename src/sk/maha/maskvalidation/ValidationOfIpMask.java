package sk.maha.maskvalidation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationOfIpMask {

	/**
	 * Variables containing regular expressions patter for validation.
	 */
	private static String subnetMaskRange = "0|128|192|224|240|248|252|254";
	private static String dotsNull = "[.]0";
	private static String fullOctetPlusDot = "255[.]";

	private static String regexPattern = "((" + subnetMaskRange + ")(" + dotsNull + ")" + "{3})|" + "(" + fullOctetPlusDot
			+ "(" + subnetMaskRange + ")(" + dotsNull + ")" + "{2})|" + "((" + fullOctetPlusDot + "){2}(" + subnetMaskRange + ")"
			+ dotsNull + ")|" + "(" + fullOctetPlusDot + "){3}(" + subnetMaskRange + "|255)";

	/**
	 * Variable containing IP mask from user as input.
	 */
	private static String obtainedIpMask;


	/**
	 * Get IP mask from user.
	 * 
	 * @param s
	 * @return IP mask
	 */
	public static String getIpMask(Scanner s) {

		System.out.print("Enter IP mask: ");
		obtainedIpMask = s.nextLine();
		return obtainedIpMask;
	}

	/**
	 * Validate IP mask.
	 * 
	 * @param obtainedIpMask
	 * @return result of mask validation
	 */
	public static boolean validateIpMask(String obtainedIpMask) {

		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(obtainedIpMask);

		return matcher.matches() ? true : false;
	}

	/**
	 * Terminating program or continue with entering IP masks.
	 * 
	 * @param s
	 * @return answer to continue or not
	 */
	public static boolean continueWithProgram(Scanner s) {

		char answer;
		do {
			System.out.println();
			System.out.println("Do you want to continue?");
			System.out.print("Yes or No? y/n : ");
			answer = s.next().charAt(0);
			s.nextLine();
			System.out.println();
		} while (answer != 'y' & answer != 'n');

		return (answer == 'y') ? true : false;

	}
}
