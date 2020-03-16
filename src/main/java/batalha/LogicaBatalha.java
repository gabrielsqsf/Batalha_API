package batalha;

import java.util.ArrayList;
import java.util.Random;

import batalha.Turno;
import batalha.response.BatalhaResponse;
import personagem.LogicaPersonagem;
import personagem.Personagem;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class LogicaBatalha {
	public static Turno criarTurno(Batalha batalha) throws BatalhaTerminadaException {
		return LogicaTurno.criarTurno(batalha);
	}
	
	public static BatalhaResponse criarBatalha(String nickname, String jogador) throws SemMonstrosException, PersonagemInexistenteException {
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		personagens.add(LogicaPersonagem.selecionarPersonagemJogador(jogador));
		personagens.add(LogicaPersonagem.sorteiaMonstro());
		Batalha battle = new Batalha(nickname, personagens);
		return converteParaResponse(battle);
	}
	
	private static BatalhaResponse converteParaResponse (Batalha batalha) {
		return new BatalhaResponse(batalha.getId(), 
									batalha.getNickname(), 
									batalha.getParticipantes(), 
									batalha.getTurnos(), 
									batalha.isTerminada(), 
									batalha.isTerminada() ? 100 - batalha.getTurnos().size() : 0);
	}
}
