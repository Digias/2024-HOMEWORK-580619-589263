package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaneTest {
	
	private Cane cane;
	private Partita partita;
	private Labirinto labirinto;

	@Before
	public void setUp() throws Exception {
		this.cane = new Cane("Fido", "BAUBAU");
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
			assertTrue(this.cane.equals(new Cane("Fido", "BAUBAU")));
		}

		@Test
		public void testEquals_nonUguali() {
			assertFalse(this.cane.equals(new Cane("Jack", "SNIF-SNIF")));
		}

		@Test
		public void testEquals_ClassiDiverseStrega() {
			assertFalse(this.cane.equals(new Strega("Befana", "HIHIHIHI")));
		}

		@Test
		public void testEquals_ClassiDiverseMago() {
			assertFalse(this.cane.equals(new Mago("Udini", "ABRAKADABRA", new Attrezzo("cilindro", 1))));
		}

		//AGISCI
		@Test
		public void testAgisci_PrimaVolta() {
			String msg = "Adesso ti mordo!!!!\n";
			this.partita.getStanzaCorrente().setPersonaggio(this.cane);
			
			assertEquals(msg, this.cane.agisci(partita));
			assertEquals(19, this.partita.getGiocatore().getCfu());
		}
		
		@Test
		public void testAgisci_MolteVolte() {
			String msg = "Adesso ti mordo!!!!\n";
			this.partita.getStanzaCorrente().setPersonaggio(this.cane);
			
			assertEquals(msg, this.cane.agisci(partita));
			assertEquals(19, this.partita.getGiocatore().getCfu());
			
			assertEquals(msg, this.cane.agisci(partita));
			assertEquals(18, this.partita.getGiocatore().getCfu());
			
			assertEquals(msg, this.cane.agisci(partita));
			assertEquals(17, this.partita.getGiocatore().getCfu());
		}
		
		
		//RICEVI REGALO
		@Test
		public void testRiceviRegalo_preferito() {
			this.partita.getStanzaCorrente().setPersonaggio(this.cane);
			String msg = "Ho fatto cadere un attrezzo!!\n";
			
			assertEquals(msg, this.cane.riceviRegalo(new Attrezzo("osso", 2), this.partita));
			assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("collare"));
			assertEquals(new Attrezzo("collare", 2), this.partita.getStanzaCorrente().getAttrezzo("collare"));
		}
		
		@Test
		public void testRiceviRegalo_nonPreferito() {
			this.partita.getStanzaCorrente().setPersonaggio(this.cane);
			String msg = "La prossima volta dammi qualcosa di buono\n";
			
			assertEquals(msg, this.cane.riceviRegalo(new Attrezzo("bastone", 2), this.partita));
			assertEquals(19, this.partita.getGiocatore().getCfu());
		}
		
		@Test
		public void testRiceviRegalo_null() {
			this.partita.getStanzaCorrente().setPersonaggio(this.cane);
			String msg = "La prossima volta dammi qualcosa di buono\n";
			
			assertEquals(msg, this.cane.riceviRegalo(null, this.partita));
			assertEquals(19, this.partita.getGiocatore().getCfu());
		}
}
