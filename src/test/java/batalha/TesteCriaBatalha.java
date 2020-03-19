package batalha;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import batalha.persistencia.ErroPersistenciaBatalha;
import database.memory.BatalhaDAOImpl;
import database.memory.PersonagemDAOImpl;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class TesteCriaBatalha {

	@Test
	public void naoCriaBatalhaPersonagemNulo() {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		try {
			Batalha bat = log.criarBatalha("teste", null);		
			assertEquals(bat, "");
		} catch (PersonagemInexistenteException | SemMonstrosException | ErroPersistenciaBatalha | BatalhaEmCursoException e) {
			assertEquals(e.getClass().getName(), PersonagemInexistenteException.class.getName());
		} 
	}
	
	@Test
	public void naoCriaBatalhaPersonagemVazio() {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		try {
			Batalha bat = log.criarBatalha("teste", "");
			assertEquals(bat, "");
		} catch (PersonagemInexistenteException | SemMonstrosException | ErroPersistenciaBatalha | BatalhaEmCursoException e) {
			assertEquals(e.getClass().getName(), PersonagemInexistenteException.class.getName());
		} 
	}
	
	@Test
	public void naoCriaBatalhaPersonagemInexistente() {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		try {
			Batalha bat = log.criarBatalha("teste", "Inexistente");		
			assertEquals(bat, "");
		} catch (PersonagemInexistenteException | SemMonstrosException | ErroPersistenciaBatalha | BatalhaEmCursoException e) {
			assertEquals(e.getClass().getName(), PersonagemInexistenteException.class.getName());
		} 
	}
	
	@Test
	public void naoCriaBatalhaComOutraEmCurso() {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		String nome = "Guerreiro";
		try {
			Batalha bat = log.criarBatalha("teste", nome);
			Batalha bat2 = log.criarBatalha("teste", nome);
			assertEquals(bat2, "");
		} catch (PersonagemInexistenteException | SemMonstrosException | ErroPersistenciaBatalha | BatalhaEmCursoException e) {
			assertEquals(e.getClass().getName(), BatalhaEmCursoException.class.getName());
		} 
	}
	
	@Test
	public void criaBatalhaPersonagemPaladino() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		String nome = "Paladino";
		Batalha bat = log.criarBatalha("teste", nome);
		assertEquals(bat.getParticipantes().get(0).getNome(),nome);
	}
	
	@Test
	public void criaBatalhaPersonagemBarbaro() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		String nome = "Barbaro";
		Batalha bat = log.criarBatalha("teste", nome);
		assertEquals(bat.getParticipantes().get(0).getNome(),nome);
	}
	
	@Test
	public void criaBatalhaPersonagemGuerreiro() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		String nome = "Guerreiro";
		Batalha bat = log.criarBatalha("teste", nome);
		assertEquals(bat.getParticipantes().get(0).getNome(),nome);
	 
	}
	
	@Test
	public void criaBatalhaComOutraTerminada() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		String nome = "Guerreiro";
		Batalha bat = log.criarBatalha("teste", nome);
		bat.setTerminada(true);
		log.persisteBatalha(bat);
		Batalha bat2 = log.criarBatalha("teste", nome);	
	}
	
	@Test
	public void criaBatalhaMonstroNaoNulo() throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException {
		LogicaBatalha log = new LogicaBatalha(new BatalhaDAOImpl(), new PersonagemDAOImpl());
		BatalhaDAOImpl.limpaBatalhas();
		String nome = "Guerreiro";
		Batalha bat = log.criarBatalha("teste", nome);
		assertEquals(bat.getParticipantes().get(0).getNome(),nome);
	 
	}
}
