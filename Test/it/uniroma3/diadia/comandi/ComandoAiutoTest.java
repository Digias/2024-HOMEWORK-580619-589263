package it.uniroma3.diadia.comandi;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.*;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoAiutoTest {

    private ComandoAiuto comandoAiuto;
    private StringBuilder output;
    private IO io;
    private Partita partita;
    private Labirinto labirinto;

    @Before
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        this.comandoAiuto = new ComandoAiuto();
        this.output = new StringBuilder();
        this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita = new Partita(labirinto);
        this.io = new IOConsole(new Scanner(System.in));
        this.comandoAiuto.setIo(io);
        
    }

    @Test
    public void testEsegui() throws Exception {
        comandoAiuto.esegui(this.partita);

        this.output.append("Puoi digitare i seguenti comandi: \n"
                + "\033[0;1mvai\033[0m: Inserisci una direzione e ti sposti nella stanza della direzione indicata\n"
                + "\033[0;1maiuto\033[0m: Descrizione di tutti i comandi che puoi utilizzare\n"
                + "\033[0;1mprendi\033[0m: Inserisci un oggetto presente nella stanza e metti l'oggetto nella tua borsa\n"
                + "\033[0;1mposa\033[0m: Inserisci un oggetto presente nella borsa e poggi l'oggetto nella stanza\n"
                + "\033[0;1mguarda\033[0m: Inserisci quali informazioni vuoi avere e ricevi una descrizione\n"
                + "\033[0;1msaluta\033[0m: Saluta un personaggio nella stanza\n"
                + "\033[0;1minteragisci\033[0m: Interagisce con un personaggio presente nella stanza\n"
                + "\033[0;1mregala\033[0m: Regala un attrezzo ad un personaggio\n"
                + "\033[0;1mfine\033[0m: Esci dal gioco\n");
        
        IOSimulator io = Fixture.creaSimulazionePartitaEGiocaEasy(Arrays.asList("aiuto", "fine"));
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(this.output.toString(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!", io.nextMessaggio());
    }

    @Test
    public void testGetElencoComandi() {
        List<String> elencoComandi = comandoAiuto.getElencoComandi();
        assertEquals(9, elencoComandi.size());
        assertTrue(elencoComandi.contains("vai"));
        assertTrue(elencoComandi.contains("prendi"));
        assertTrue(elencoComandi.contains("posa"));
        assertTrue(elencoComandi.contains("guarda"));
        assertTrue(elencoComandi.contains("aiuto"));
        assertTrue(elencoComandi.contains("fine"));
        assertTrue(elencoComandi.contains("saluta"));
        assertTrue(elencoComandi.contains("interagisci"));
        assertTrue(elencoComandi.contains("regala"));

    }
}
