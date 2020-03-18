package batalha;

import batalha.persistencia.BatalhaInexistenteException;
import personagem.LogicaPersonagem;
import personagem.Personagem;

public class LogicaTurno {
	
	LogicaBatalha logBatalha;
	
	public LogicaTurno(LogicaBatalha logBatalha) {
		this.logBatalha = logBatalha;
	}
	
	public Turno criarTurno(long id) throws BatalhaTerminadaException, BatalhaInexistenteException {
		Batalha b = logBatalha.getBatalha(id);
		return criarTurno(b);
	}
	
	protected Turno criarTurno(Batalha batalha) throws BatalhaTerminadaException {
		
		if(batalha.isTerminada()) {
			throw new BatalhaTerminadaException();
		}
		
		int turnos = batalha.getTurnos().size();
		int numParticipantes = batalha.getParticipantes().size();
		int[] pdvInicial = recuperarPdvInicial(batalha);
		
		Turno turno = new Turno(turnos+1, pdvInicial, numParticipantes);
		
		inicializarTurno(batalha, turno);
		
		calcularDano(batalha, turno);
		
		batalha.getTurnos().add(turno);
					
		return turno;
	}
	
	private static void inicializarTurno(Batalha batalha, Turno turno) {
		int numParticipantes = batalha.getParticipantes().size();
		int[] pdvInicial = turno.getPdvInicial();
		
		for(int i = 0; i < numParticipantes; i++) {
			Personagem p = batalha.getParticipantes().get(i);
			turno.getAtaques()[i] = LogicaPersonagem.getAtaque(p);
			turno.getDefesas()[i] = LogicaPersonagem.getDefesa(p);
			turno.getPdvFinal()[i] = pdvInicial[i];
		}
		
	}

	private static void calcularDano(Batalha batalha, Turno turno) {
		int[] ordem = rolarIniciativas(batalha, turno);
		int numParticipantes = batalha.getParticipantes().size();
		int[] pdvInicial = turno.getPdvInicial();
		
		// Decisão do Dano de acordo com a ordem e valores de ataque e defesa;
		for(int i = 0; i < numParticipantes; i++) {
			int index = ordem[i];
			if(turno.getAtaques()[index] <= turno.getDefesas()[1-index]) {
				turno.getDanos()[index] = 0; 
			} else {
				Personagem p = batalha.getParticipantes().get(index);
				turno.getDanos()[index] = LogicaPersonagem.getDano(p); 
			}
			turno.getPdvFinal()[1-index] = pdvInicial[1-index] - turno.getDanos()[index];
			if(turno.getPdvFinal()[1-index] < 0) {
				turno.getDanos()[1-index] = 0;
				batalha.setTerminada(true);
				break;
			}
		}
	}

	private static int[] recuperarPdvInicial(Batalha batalha) {
		int[] pdvInicial = null;
		int turnos = batalha.getTurnos().size();
		int numParticipantes = batalha.getParticipantes().size();
		
		if(turnos > 0) {
			pdvInicial = batalha.getTurnos().get(turnos-1).getPdvFinal();
		} else {
			pdvInicial = new int[numParticipantes];
			for(int i = 0; i < numParticipantes; i++) {
				Personagem p = batalha.getParticipantes().get(i);
				pdvInicial[i] = p.getPdv(); 
			}
		}
		return pdvInicial;
		
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

}
