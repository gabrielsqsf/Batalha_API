package batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import batalha.Turno;
import batalha.persistencia.BatalhaInexistenteException;
import batalha.response.BatalhaResponse;
import batalha.response.ListaBatalhaResponse;
import personagem.LogicaPersonagem;
import personagem.Personagem;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class LogicaBatalha {
	public static Batalha criarBatalha(String nickname, String jogador) throws SemMonstrosException, PersonagemInexistenteException {
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		personagens.add(LogicaPersonagem.selecionarPersonagemJogador(jogador));
		personagens.add(LogicaPersonagem.sorteiaMonstro());
		Batalha battle = new Batalha(nickname, personagens);
		return battle;
	}
	
	public static List<Batalha> listaBatalhas() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Batalha getBatalha(long id) throws BatalhaInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}
}
