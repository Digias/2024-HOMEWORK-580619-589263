package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;
	Labirinto labirinto;

	@Before
	public void setUp() throws Exception {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("seghetto", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		partita = new Partita(labirinto);
		attrezzo = new Attrezzo("martello", 2);
		comando = new ComandoPosa();
		io = new IOConsole();
		comando.setIo(io);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}


	public void creatoreAttrezzi() {
		for(int i= 0; i<10;i++) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("utensile"+i, 1));
		}
	}
	
	@Test
	public void testTroppiAttrezzi() {
		this.creatoreAttrezzi();
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

}
