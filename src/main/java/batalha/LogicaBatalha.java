package batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import batalha.Turno;
import batalha.persistencia.BatalhaDAO;
import batalha.persistencia.BatalhaInexistenteException;
import batalha.persistencia.ErroPersistenciaBatalha;
import batalha.response.BatalhaResponse;
import batalha.response.ListaBatalhaResponse;
import personagem.LogicaPersonagem;
import personagem.Personagem;
import personagem.persistencia.PersonagemDAO;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class LogicaBatalha {
	
	BatalhaDAO dao;
	LogicaPersonagem logPersonagem;
	
	public LogicaBatalha(BatalhaDAO dao, PersonagemDAO personagemDAO) {
		this.dao = dao;
		logPersonagem = new LogicaPersonagem(personagemDAO);
	}
	
	public Batalha criarBatalha(String nickname, String jogador) throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha {
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		personagens.add(logPersonagem.selecionarPersonagemJogador(jogador));
		personagens.add(logPersonagem.sorteiaMonstro());
		Batalha battle = new Batalha(nickname, personagens);
		dao.persisteBatalha(battle);
		return battle;
	}
	
	public List<Batalha> listaBatalhas() {
		return dao.recuperaBatalhas(null);
	}

	public Batalha getBatalha(long id) throws BatalhaInexistenteException {
		return dao.recuperaBatalha(id);
	}
}
