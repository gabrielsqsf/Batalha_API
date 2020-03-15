package personagem;

public interface PersonagemDAO {
	public Personagem selecionaPersonagem(String nome) throws PersonagemInexistenteException;
	public Personagem sorteiaMonstro() throws SemMonstrosException;
	public boolean PersistePersonagem(Personagem personagem) throws ErroPersistenciaPersonagem; 
}
