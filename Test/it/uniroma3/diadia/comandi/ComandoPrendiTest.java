package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private Partita partita;
	private Attrezzo attrezzoPesante;
	private Comando comando;
	private IO io;
	Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		partita = new Partita(labirinto);
		attrezzoPesante = new Attrezzo("incudine", 11);
		comando = new ComandoPrendi();
		io = new IOConsole();
		comando.setIo(io);
	}


	@After
	public void tearDown() throws Exception {
	}
	
	public boolean attrezzoPresente(String s) {
		//Set<Attrezzo> set = partita.getStanzaCorrente().getAttrezzi();
		if(partita.getStanzaCorrente().getAttrezzo(s)==null)
			return false;
		return true;
		}
	
	@Test
	//da controllare
	public void testAttrezzoPreso() {
		Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo("martello");
		partita.getStanzaCorrente().addAttrezzo(a);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(attrezzoPresente("martello"));
	}
	
	@Test
	public void testAttrezzoNonPresente() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(attrezzoPresente("martello"));
	}
	
	@Test
	public void testAttrezzoPesante() {
		partita.getStanzaCorrente().addAttrezzo(attrezzoPesante);
		comando.setParametro("incudine");
		comando.esegui(partita);
		assertTrue(attrezzoPresente("incudine"));
	}
	
}
