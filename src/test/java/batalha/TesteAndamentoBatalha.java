package batalha;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;

import batalha.persistencia.BatalhaInexistenteException;
import batalha.persistencia.ErroPersistenciaBatalha;
import database.memory.BatalhaDAOImpl;
import database.memory.PersonagemDAOImpl;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class TesteAndamentoBatalha {
	
	private LogicaBatalha log;
	
	public TesteAndamentoBatalha() {
		BatalhaDAOImpl dao = new BatalhaDAOImpl();
		BatalhaDAOImpl.limpaBatalhas();
		log = new LogicaBatalha(dao, new PersonagemDAOImpl());
	}
	
	private void criarBatalhas(String classe, int amostras) throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException, BatalhaInexistenteException {
		for(int i = 0; i < amostras; i++) {
			Batalha bat = log.criarBatalha("teste", classe);
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
	
	
}
