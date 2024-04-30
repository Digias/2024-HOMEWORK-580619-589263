package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	static final private String[] ELENCO_COMANDI = {"vai", "prendi", "posa", "guarda", "aiuto", "fine"};
	
	/*
	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro

		if (nomeComando == null)
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(ELENCO_COMANDI);
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else comando = new ComandoNonValido();
		comando.setParametro(parametro);
		return comando;
	}
	*/

	@Override
	public Comando costruisciComando(String istruzione) {
	    Scanner scannerDiParole = new Scanner(istruzione);
	    String nomeComando = null;
	    String parametro = null;
	    Comando comando = null;
	    
	    if (scannerDiParole.hasNext())
	        nomeComando = scannerDiParole.next();	// Prima parola: nome del comando
	    if (scannerDiParole.hasNext())
	        parametro = scannerDiParole.next();		// Seconda parola: eventuale parametro
	   
	    // Caso in cui il comando sia null
	    if (nomeComando == null) {
	    	comando = new ComandoNonValido();
	    }
	    
	    switch (nomeComando) {
	        case "vai":
	            comando = new ComandoVai();
	            break;
	        
	        case "prendi":
	            comando = new ComandoPrendi();
	            break;
	        
	        case "posa":
	            comando = new ComandoPosa();
	            break;
	        
	        case "aiuto":
	            comando = new ComandoAiuto(ELENCO_COMANDI);
	            break;
	        
	        case "fine":
	            comando = new ComandoFine();
	            break;
	        
	        case "guarda":
	            comando = new ComandoGuarda();
	            break;
	        
	        default:
	            comando = new ComandoNonValido();
	            break;
	    }
	    
	    comando.setParametro(parametro);
	    scannerDiParole.close();
	    return comando;
	}
}
