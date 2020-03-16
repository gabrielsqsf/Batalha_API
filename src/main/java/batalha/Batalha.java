package batalha;

import java.util.ArrayList;

import batalha.Turno;
import personagem.Personagem;

public class Batalha {
	private final long id;
	private final String nickname;
	private final ArrayList<Personagem> participantes;
	private final ArrayList<Turno> turnos;
	private boolean terminada;
	
	public Batalha(String nickname, ArrayList<Personagem> personagens) {
		super();
		this.id = 0L;
		this.nickname = nickname;
		this.participantes = personagens;
		this.turnos = new ArrayList<Turno>();
		this.terminada = false; 
	}

	public long getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public ArrayList<Personagem> getParticipantes() {
		return participantes;
	}

	public ArrayList<Turno> getTurnos() {
		return turnos;
	}
	
	public boolean isTerminada() {
		return terminada;
	}

	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}

}
