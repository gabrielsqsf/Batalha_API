package batalha.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import batalha.Batalha;
import batalha.LogicaBatalha;
import batalha.persistencia.BatalhaInexistenteException;
import batalha.persistencia.ErroPersistenciaBatalha;
import personagem.LogicaPersonagem;
import personagem.Personagem;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class LogicaBatalhaWrapper {
	
	private LogicaBatalha log;
	
	public LogicaBatalhaWrapper(LogicaBatalha log) {
		this.log = log;
	}
	
	public BatalhaResponse criarBatalha(String nickname, String jogador) throws SemMonstrosException, PersonagemInexistenteException, ErroPersistenciaBatalha {
		
		return converteParaResponse(log.criarBatalha(nickname, jogador));
	}
	
	public ListaBatalhaResponse listaBatalhas() {
		ListaBatalhaResponse lbr = converteParaResponse(log.listaBatalhas());
		return lbr;
	}

	public BatalhaResponse getBatalha(long id) throws BatalhaInexistenteException {
		return converteParaResponse(log.getBatalha(id));
	}
	
	private static BatalhaResponse converteParaResponse (Batalha batalha) {
		return new BatalhaResponse(batalha.getId(), 
									batalha.getNickname(), 
									batalha.getParticipantes(), 
									LogicaTurnoWrapper.converterParaResponse(batalha.getTurnos()), 
									batalha.isTerminada(), 
									batalha.isTerminada() ? 100 - batalha.getTurnos().size() : 0);
	}
	
	private static ListaBatalhaResponse converteParaResponse (List<Batalha> batalhas) {
		ListaBatalhaResponse l = new ListaBatalhaResponse();
		l.setBatalhas(batalhas.stream().map(batalha -> converteParaResponse(batalha)).collect(Collectors.toList()));
		return l; 
	}

}
