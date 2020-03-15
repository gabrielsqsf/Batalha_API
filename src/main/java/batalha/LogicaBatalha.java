package batalha;

import java.util.ArrayList;
import java.util.Random;

import batalha.Turno;
import personagem.LogicaPersonagem;
import personagem.Personagem;

public class LogicaBatalha {
	public static Turno criarTurno(Batalha batalha) throws BatalhaTerminadaException {
		
		if(batalha.isTerminada()) {
			throw new BatalhaTerminadaException();
		}
		
		int turnos = batalha.getTurnos().size();
		int numParticipantes = batalha.getParticipantes().size();
		int[] pdvInicial = null;
		if(turnos > 0) {
			pdvInicial = batalha.getTurnos().get(turnos-1).getPdvFinal();
		} else {
			pdvInicial = new int[numParticipantes];
			for(int i = 0; i < numParticipantes; i++) {
				Personagem p = batalha.getParticipantes().get(i);
				pdvInicial[i] = p.getPdv(); 
			}
		}
		
		Turno turno = new Turno(turnos+1, pdvInicial, numParticipantes);
		
		int[] ordem = rolarIniciativas(batalha, turno);
		
		//Vamos calcular os demais valores e só processar
		for(int i = 0; i < numParticipantes; i++) {
			Personagem p = batalha.getParticipantes().get(i);
			turno.getAtaques()[i] = LogicaPersonagem.getAtaque(p);
			turno.getDefesas()[i] = LogicaPersonagem.getDefesa(p);
			turno.getDanos()[i] = LogicaPersonagem.getDano(p); 
			turno.getPdvFinal()[i] = pdvInicial[i];
		}
		
		// Decisão do Dano de acordo com a ordem e valores de ataque e defesa;
		for(int i = 0; i < numParticipantes; i++) {
			int index = ordem[i];
			if(turno.getAtaques()[index] <= turno.getDefesas()[1-index]) {
				turno.getDanos()[index] = 0; 
			}
			turno.getPdvFinal()[1-index] = pdvInicial[1-index] - turno.getDanos()[i];
			if(turno.getPdvFinal()[1-index] < 0) {
				turno.getDanos()[1-index] = 0;
				batalha.setTerminada(true);
				break;
			}
		}
		
		return turno;
	}
	
	private static int[] rolarIniciativas(Batalha batalha, Turno turno) {
		//Estamos usando da premissa de que são somente dois personagens na batalha
		
		int[] iniciativas = turno.getIniciativas();
		
		while(iniciativas[0] == iniciativas[1]) {
			iniciativas[0] = LogicaPersonagem.getIniciativa(batalha.getParticipantes().get(0));
			iniciativas[1] = LogicaPersonagem.getIniciativa(batalha.getParticipantes().get(1));
		}
		
		int primeiro = 0, segundo = 1;
		
		if(iniciativas[0] < iniciativas[1]) {
			primeiro = 1;
			segundo = 0;
		}
		
		int[] ordem = {primeiro, segundo};
		
		return ordem;
	}

	public static Batalha criarBatalha(String nickname, String jogador) {
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		personagens.add(LogicaPersonagem.selecionarPersonagemJogador(jogador));
		personagens.add(LogicaPersonagem.sorteiaMonstro());
		Batalha battle = new Batalha(nickname, personagens);
		return battle;
	}
}
