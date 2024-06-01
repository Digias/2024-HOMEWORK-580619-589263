package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

public class MagoTest {

	private Attrezzo bacchetta;
	private Mago mago;
	private Partita partita;
	private Labirinto labirinto;

	@Before
	public void setUp(){
		this.bacchetta = new Attrezzo("bacchetta", 3);
		this.mago = new Mago("Merlino", "SBEM", this.bacchetta);

		try {
			this.labirinto = Labirinto.newBuilder("labirintoTest.txt").getLabirinto();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
		this.partita = new Partita(this.labirinto);
	}

	//EQUALS
	@Test
	public void testEquals_uguali() {
		assertTrue(this.mago.equals(new Mago("Merlino", "SBEM", this.bacchetta)));
	}

	@Test
	public void testEquals_nonUguali() {
		assertFalse(this.mago.equals(new Mago("Udini", "ABRAKADABRA", new Attrezzo("cilindro", 1))));
	}

	@Test
	public void testEquals_ClassiDiverseStrega() {
		assertFalse(this.mago.equals(new Strega("Befana", "HIHIHIHI")));
	}

	@Test
	public void testEquals_ClassiDiverseCane() {
		assertFalse(this.mago.equals(new Cane("Fido", "BAUBAU")));
	}

	//MODIFICA ATTREZZO
	@Test
	public void testModificaAttrezzo() {
		assertEquals(new Attrezzo("spada", 2), this.mago.modificaAttrezzo(new Attrezzo("spada", 4)));
	}
	
	//AGISCI
	@Test
	public void testAgisci_PrimaVolta() {
		String msg = "Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!\n";
		this.partita.getStanzaCorrente().setPersonaggio(this.mago);
		
		assertEquals(msg, this.mago.agisci(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.bacchetta.getNome()));
	}
	
	@Test
	public void testAgisci_SecondaVolta() {
		this.partita.getStanzaCorrente().setPersonaggio(this.mago);
		this.mago.agisci(partita);
		
		assertEquals("Mi spiace, ma non ho piu' nulla...\n", this.mago.agisci(partita));
	}
	
	//RICEVI REGALO
	@Test
	public void testRiceviRegalo() {
		this.partita.getStanzaCorrente().setPersonaggio(this.mago);
		this.mago.riceviRegalo(new Attrezzo("spada", 8), this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals(new Attrezzo("spada", 4), this.partita.getStanzaCorrente().getAttrezzo("spada"));
	}
	
}
