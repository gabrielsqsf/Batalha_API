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
	
	public Batalha criarBatalha(String nickname, String jogador) throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha, BatalhaEmCursoException {
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		personagens.add(logPersonagem.selecionarPersonagemJogador(jogador));
		personagens.add(logPersonagem.sorteiaMonstro());
		List<Batalha> batalhas = dao.recuperaBatalhas(jogador, false);
		if(batalhas.size() > 0) {
			throw new BatalhaEmCursoException();
		}
		Batalha battle = new Batalha(nickname, personagens);
		persisteBatalha(battle);
		return battle;
	}
	
	public List<Batalha> listaBatalhas(String nome, boolean terminada) {
		return dao.recuperaBatalhas(nome, terminada);
	}

	public Batalha getBatalha(long id) throws BatalhaInexistenteException {
		return dao.recuperaBatalha(id);
	}

	public void persisteBatalha(Batalha batalha) throws ErroPersistenciaBatalha {
		dao.persisteBatalha(batalha);
	}
}
