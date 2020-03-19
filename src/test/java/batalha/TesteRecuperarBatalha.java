package batalha;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import batalha.persistencia.BatalhaInexistenteException;
import batalha.persistencia.ErroPersistenciaBatalha;
import database.memory.BatalhaDAOImpl;
import database.memory.PersonagemDAOImpl;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class TesteRecuperarBatalha {
	
	private LogicaBatalha log;
	private boolean init = false;
	
	public TesteRecuperarBatalha() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException, BatalhaInexistenteException {
		if(init == false) {
			init();
			init = true;
		}
	}
	
	private void init() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException, BatalhaInexistenteException {
		//Vamos criar varias batalhas
		BatalhaDAOImpl dao = new BatalhaDAOImpl();
		BatalhaDAOImpl.limpaBatalhas();
		log = new LogicaBatalha(dao, new PersonagemDAOImpl());
		String nick = "teste";
		String personagem = "Guerreiro";
		
		for(int i = 0; i < 10; i++) {
			log.criarBatalha("teste"+i, personagem);
		}
		
		for(int i = 0; i < 10; i++) {
			Batalha bat = log.criarBatalha("teste", personagem);
			LogicaTurno logTurno = new LogicaTurno(log);
			int iteracoes = 0;
			while(!bat.isTerminada() && iteracoes < 100) {
				try {
					logTurno.criarTurno(bat.getId());
				} catch (BatalhaTerminadaException e) {
					break;
				}
				iteracoes++;
			}
		}
	}
	
	@Test
	public void recupararTodasBatalhas() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas(null);		
		assertEquals(batalhas.size(), 20);
	}
	
	@Test
	public void buscarBatalhasConcluidas() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas(null, true);		
		assertEquals(batalhas.size(), 10);
	}
	
	@Test
	public void buscarBatalhasNaoConcluidas() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas(null, false);	
		assertEquals(batalhas.size(), 10);
	}
	
	@Test
	public void buscarBatalhasDoUsuario() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas("teste");	
		assertEquals(batalhas.size(), 10);
	}
	
	@Test
	public void buscarBatalhasDoUsuarioConcluidas() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas("teste", true);	
		assertEquals(batalhas.size(), 10);
	}
	
	@Test
	public void buscarBatalhasDoUsuarioNaoConcluidas() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas("teste", false);	
		assertEquals(batalhas.size(), 0);
	}
	
	@Test
	public void buscarBatalhasDoUsuarioInexistente() throws BatalhaInexistenteException {
		List<Batalha> batalhas = log.listaBatalhas("inexistente");	
		assertEquals(batalhas.size(), 0);
	}
	
	@Test
	public void recupararUmaBatalha() throws BatalhaInexistenteException {
		Batalha batalha = log.getBatalha(1);		
		assertNotEquals(batalha, null);
		assertEquals(batalha.getId(), 1);
	}
	
	@Test
	public void buscarBatalhaInexistente() throws BatalhaInexistenteException {
		Batalha batalha = log.getBatalha(-1);		
		assertEquals(batalha, null);
	}
	
}
