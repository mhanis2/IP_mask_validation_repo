package sk.maha.maskvalidation;

import java.util.Scanner;

public class ValidationOfIpMaskDemo {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		String maskIp;
		boolean answerOfContinueMethod;

		for (;;) {


			maskIp = ValidationOfIpMask.getIpMask(sc);


			System.out.println();
			System.out.println("Mask (" + maskIp + ") " + (ValidationOfIpMask.validateIpMask(maskIp) ? "IS" : "IS NOT")
					+ " valid");

			answerOfContinueMethod = ValidationOfIpMask.continueWithProgram(sc);
			if (!answerOfContinueMethod) {
				System.out.println();
				System.out.println("Program terminated !!!");
				sc.close();
				break;
			}

		}

	}
}
