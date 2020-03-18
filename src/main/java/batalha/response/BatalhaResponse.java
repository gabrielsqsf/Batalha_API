package batalha.response;

import java.util.List;

import batalha.Batalha;
import batalha.Turno;
import personagem.Personagem;

public class BatalhaResponse {
	private final long id;
	private final String nickname;
	private final List<Personagem> participantes;
	private final List<TurnoResponse> turnos;
	private final boolean terminada;
	private final int ranking;
	
	public BatalhaResponse(long id, String nickname, List<Personagem> participantes, List<TurnoResponse> turnos,
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
	public List<Personagem> getParticipantes() {
		return participantes;
	}
	public List<TurnoResponse> getTurnos() {
		return turnos;
	}
	public boolean isTerminada() {
		return terminada;
	}
	public int getRanking() {
		return ranking;
	}
}
