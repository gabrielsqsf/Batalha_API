package batalha;

import java.util.ArrayList;
import java.util.Random;

import batalha.Turno;
import personagem.LogicaPersonagem;
import personagem.Personagem;

public class LogicaBatalha {
	public static Turno criarTurno(Batalha batalha) throws BatalhaTerminadaException {
		return LogicaTurno.criarTurno(batalha);
	}
	
	public static Batalha criarBatalha(String nickname, String jogador) {
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		personagens.add(LogicaPersonagem.selecionarPersonagemJogador(jogador));
		personagens.add(LogicaPersonagem.sorteiaMonstro());
		Batalha battle = new Batalha(nickname, personagens);
		return battle;
	}
}
