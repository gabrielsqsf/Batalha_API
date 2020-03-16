package batalha.response;

import java.util.ArrayList;
import java.util.List;

import batalha.Batalha;
import batalha.LogicaBatalha;
import batalha.persistencia.BatalhaInexistenteException;
import personagem.LogicaPersonagem;
import personagem.Personagem;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class LogicaBatalhaWrapper {
	public static BatalhaResponse criarBatalha(String nickname, String jogador) throws SemMonstrosException, PersonagemInexistenteException {
		return converteParaResponse(LogicaBatalha.criarBatalha(nickname, jogador));
	}
	
	public static ListaBatalhaResponse listaBatalhas() {
		ListaBatalhaResponse lbr = new ListaBatalhaResponse();
		return lbr;
	}

	public static BatalhaResponse getBatalha(long id) throws BatalhaInexistenteException {
		return converteParaResponse(LogicaBatalha.getBatalha(id));
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
