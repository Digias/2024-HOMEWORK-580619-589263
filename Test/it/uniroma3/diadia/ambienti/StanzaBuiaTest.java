package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private StanzaBuia stanza1;
	private Stanza stanza2;
	String[] direzioni = {"nord", "est", "ovest", "sud"};

	private Attrezzo[] sequenzaAttrezzi(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i],i);

		return elenco;
	}
	
	
	@Before
	public void setUpStanza() {
		stanza1 = new StanzaBuia("Stanza1", "lanterna");
		stanza2 = new Stanza("Stanza1");
	}

	@Before
	public void setUpAttrezzi() {		
		new Attrezzo("spada", 5);
	}
	
	@Test
	public void testGetDescrizione_conLanterna() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("lanterna", "spada", "osso");
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanza1.addAttrezzo(elencoAttrezzi[i]);
			stanza2.addAttrezzo(elencoAttrezzi[i]);
		}
		assertEquals(stanza2.getDescrizione(), stanza1.getDescrizione());
	}

	@Test
	public void testGetDescrizione_Buio() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("spada", "osso");
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanza1.addAttrezzo(elencoAttrezzi[i]);
			stanza2.addAttrezzo(elencoAttrezzi[i]);
		}
		assertEquals("Qui c'è buio pesto", stanza1.getDescrizione());
	}
}
