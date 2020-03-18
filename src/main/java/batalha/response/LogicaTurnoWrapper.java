package batalha.response;

import java.util.List;
import java.util.stream.Collectors;

import batalha.Batalha;
import batalha.BatalhaTerminadaException;
import batalha.LogicaBatalha;
import batalha.LogicaTurno;
import batalha.Turno;
import batalha.persistencia.BatalhaInexistenteException;
import batalha.persistencia.ErroPersistenciaBatalha;

public class LogicaTurnoWrapper {
	LogicaTurno log;
	
	public LogicaTurnoWrapper(LogicaBatalha log) {
		this.log = new LogicaTurno(log);
	}
	
	public TurnoResponse criarTurno(long id) throws BatalhaTerminadaException, BatalhaInexistenteException, ErroPersistenciaBatalha {
		return converterParaResponse(log.criarTurno(id));
	}
	
	private static TurnoResponse converterParaResponse(Turno turno) {
		return new TurnoResponse(turno.getIndex(), turno.getIniciativas(), turno.getAtaques(), turno.getDefesas(), turno.getDanos(), turno.getPdvInicial(), turno.getPdvFinal());
	}

	public static List<TurnoResponse> converterParaResponse(List<Turno> turnos) {
		return turnos.stream().map(turno -> converterParaResponse(turno)).collect(Collectors.toList());
	}
}
