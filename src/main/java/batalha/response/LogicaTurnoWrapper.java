package batalha.response;

import batalha.Batalha;
import batalha.BatalhaTerminadaException;
import batalha.LogicaBatalha;
import batalha.LogicaTurno;
import batalha.Turno;
import batalha.persistencia.BatalhaInexistenteException;

public class LogicaTurnoWrapper {
	public static TurnoResponse criarTurno(long id) throws BatalhaTerminadaException, BatalhaInexistenteException {
		return converterParaResponse(LogicaTurno.criarTurno(id));
	}
	
	private static TurnoResponse converterParaResponse(Turno turno) {
		return null;
	}
}
