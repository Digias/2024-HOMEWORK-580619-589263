package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole {
	/**
	 * 
	 * @param msg stringa da stampare in output
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	/**
	 * 
	 * @return stringa con il messaggio di input
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}

}
