package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	private IO io;
	
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}

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
	            comando = new ComandoAiuto();
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
	    comando.setIo(this.io);
	    scannerDiParole.close();
	    return comando;
	}
}
