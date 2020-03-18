package database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import batalha.Batalha;
import batalha.persistencia.BatalhaDAO;
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
		Batalha existing = recuperaBatalha(batalha.getId());
		if(existing != null) {
			batalhas.remove(existing);
		}
		batalhas.add(batalha);
		return false;
	}

	@Override
	public List<Batalha> recuperaBatalhas(String nickname) {
		return batalhas.stream().filter(batalha -> nickname == null || batalha.getNickname().equals(nickname)).collect(Collectors.toList());
	}

	@Override
	public List<Batalha> recuperaBatalhasStatus(String nickname, boolean terminada) {
		return batalhas.stream().filter(batalha -> nickname == null || (batalha.getNickname().equals(nickname) && batalha.isTerminada() == terminada)).collect(Collectors.toList());
	}

	@Override
	public Batalha recuperaBatalha(long id) {
		List<Batalha> bats = batalhas.stream().filter(batalha -> batalha.getId() == id).collect(Collectors.toList());
		Batalha ret = null;
		if(bats.size() > 0) {
			ret = bats.get(0);
		}
		return ret;
	}

}
