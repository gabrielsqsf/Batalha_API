package database.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import batalha.Batalha;
import batalha.persistencia.BatalhaDAO;
import batalha.persistencia.BatalhaInexistenteException;
import batalha.persistencia.ErroPersistenciaBatalha;

@Service
public class BatalhaDAOImpl implements BatalhaDAO {

	private static List<Batalha> batalhas;
	
	public BatalhaDAOImpl() {
		if(batalhas == null) {
			batalhas = new ArrayList<Batalha>();
		}
	}
	
	@Override
	public boolean persisteBatalha(Batalha batalha) throws ErroPersistenciaBatalha {
		try {
			Batalha existing = recuperaBatalha(batalha.getId());
			if(existing != null) {
				batalhas.remove(existing);
			}
		} catch (Exception e) {
			
		}
		finally {
			batalha.setId(batalhas.size()+1);
			batalhas.add(batalha);
		}
		
		return true;
	}

	@Override
	public List<Batalha> recuperaBatalhas(String nickname) {
		return batalhas.stream().filter(batalha -> nickname == null || batalha.getNickname().equals(nickname)).collect(Collectors.toList());
	}

	@Override
	public List<Batalha> recuperaBatalhas(String nickname, boolean terminada) {
		return batalhas.stream().filter(batalha -> (nickname == null || batalha.getNickname().equals(nickname)) && batalha.isTerminada() == terminada).collect(Collectors.toList());
	}

	@Override
	public Batalha recuperaBatalha(long id) throws BatalhaInexistenteException {
		List<Batalha> bats = batalhas.stream().filter(batalha -> batalha.getId() == id).collect(Collectors.toList());
		Batalha ret = null;
		if(bats.size() > 0) {
			ret = bats.get(0);
		} else {
			throw new BatalhaInexistenteException();
		}
		return ret;
	}

	public static void limpaBatalhas() {
		batalhas.clear();
	}
}
