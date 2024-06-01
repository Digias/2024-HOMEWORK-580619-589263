package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
	private Scanner scanner;
	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}
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
		//Scanner scannerDiLinee = new Scanner(System.in);
		String riga = this.scanner.nextLine();
		//scannerDiLinee.close();
		return riga;
	}


}
