package batalha.persistencia;

import java.util.List;

import batalha.Batalha;

public interface BatalhaDAO {
	public boolean persisteBatalha(Batalha batalha) throws ErroPersistenciaBatalha; 
	public List<Batalha> recuperaBatalhas(String nickname);
	public List<Batalha> recuperaBatalhas(String nickname, boolean terminada);
	public Batalha recuperaBatalha(long id);
}
