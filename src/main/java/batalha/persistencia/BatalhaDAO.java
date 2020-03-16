package batalha;

import java.util.List;

public interface BatalhaDAO {
	public boolean persisteBatalha(Batalha batalha) throws ErroPersistenciaBatalha; 
	public List<Batalha> recuperaBatalhas(String nickname);
	public List<Batalha> recuperaBatalhasStatus(String nickname, boolean terminada);
	public List<Batalha> recuperaBatalha(long id);
}
