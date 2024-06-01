package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

public class StregaTest {

	private Strega strega;
	private Partita partita;
	private Labirinto labirinto;

	@Before
	public void setUp() throws Exception {
		this.strega = new Strega("Befana", "HIHIHI");
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
		assertTrue(this.strega.equals(new Strega("Befana", "HIHIHI")));
	}

	@Test
	public void testEquals_nonUguali() {
		assertFalse(this.strega.equals(new Strega("Amelia", "la strega che ammalia")));
	}

	@Test
	public void testEquals_ClassiDiverseCane() {
		assertFalse(this.strega.equals(new Cane("Fidp", "BAUBAU")));
	}

	@Test
	public void testEquals_ClassiDiverseMago() {
		assertFalse(this.strega.equals(new Mago("Udini", "ABRAKADABRA", new Attrezzo("cilindro", 1))));
	}

	//AGISCI
	@Test
	public void testAgisci_saltuato() {
		this.partita.getStanzaCorrente().setPersonaggio(this.strega);
		this.strega.saluta();
		String msg = "Solo perche' mi hai salutato, non ti mando in uno scantinato!";
		
		assertEquals(msg, this.strega.agisci(this.partita));
		assertEquals(new Stanza("N10"), this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testAgisci_nonSaltuato() {
		this.partita.getStanzaCorrente().setPersonaggio(this.strega);
		String msg = "Sei proprio una brutta persona, vai nella stanza con meno attrezzi!";
		
		assertEquals(msg, this.strega.agisci(this.partita));
		assertEquals(new Stanza("N12"), this.partita.getStanzaCorrente());
	}
	
	//RICEVI REGALO
	@Test
	public void testRiceviRegalo() {
		this.partita.getStanzaCorrente().setPersonaggio(this.strega);
		assertEquals("AHAHAHAHAHAHHA", this.strega.riceviRegalo(new Attrezzo("martello", 10 ), partita));
	}

}
