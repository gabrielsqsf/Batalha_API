package batalha.response;

import java.util.ArrayList;

import batalha.Batalha;
import batalha.Turno;
import personagem.Personagem;

public class BatalhaResponse {
	private final long id;
	private final String nickname;
	private final ArrayList<Personagem> participantes;
	private final ArrayList<Turno> turnos;
	private final boolean terminada;
	private final int ranking;
	
	public BatalhaResponse(long id, String nickname, ArrayList<Personagem> participantes, ArrayList<Turno> turnos,
			boolean terminada, int ranking) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.participantes = participantes;
		this.turnos = turnos;
		this.terminada = terminada;
		this.ranking = ranking;
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
	public int getRanking() {
		return ranking;
	}
}
