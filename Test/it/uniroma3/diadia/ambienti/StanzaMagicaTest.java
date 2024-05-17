package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	private StanzaMagica stanza1;
	private Attrezzo spada;
	String[] direzioni = {"nord", "est", "ovest", "sud"};

	private Attrezzo[] sequenzaAttrezzi(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i],i);

		return elenco;
	}
	
	
	@Before
	public void setUpStanza() {
		this.stanza1 = new StanzaMagica("Stanza1");
	}

	@Before
	public void setUpAttrezzi() {		
		this.spada = new Attrezzo("spada", 5);
	}
	
	//Oggetto modificato
	@Test
	public void testAddAttrezzo_oggettoModificatoNome() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("lanterna", "piccone", "osso");
		for(int i = 0; i < elencoAttrezzi.length; i++)
			stanza1.addAttrezzo(elencoAttrezzi[i]);
		
		stanza1.addAttrezzo(spada);
		assertEquals("adaps", stanza1.getAttrezzi().get(3).getNome());
	}
	
	@Test
	public void testAddAttrezzo_oggettoModificatoPeso() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("lanterna", "piccone", "osso");
		for(int i = 0; i < elencoAttrezzi.length; i++)
			stanza1.addAttrezzo(elencoAttrezzi[i]);
		
		stanza1.addAttrezzo(spada);
		assertEquals(10, stanza1.getAttrezzi().get(3).getPeso());
	}
	
	//oggetto non modificato
	@Test
	public void testAddAttrezzo_oggettoNonModificatoPeso() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("lanterna", "piccone");
		for(int i = 0; i < elencoAttrezzi.length; i++)
			stanza1.addAttrezzo(elencoAttrezzi[i]);
		
		stanza1.addAttrezzo(spada);
		assertEquals(5, stanza1.getAttrezzi().get(2).getPeso());
	}
	
	@Test
	public void testAddAttrezzo_oggettoNonModificatoNome() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("lanterna", "piccone");
		for(int i = 0; i < elencoAttrezzi.length; i++)
			stanza1.addAttrezzo(elencoAttrezzi[i]);
		
		stanza1.addAttrezzo(spada);
		assertEquals("spada", stanza1.getAttrezzi().get(2).getNome());
	}
	
}
