package personagem.persistencia;

import personagem.Personagem;

public interface PersonagemDAO {
	public Personagem selecionaPersonagem(String nome) throws PersonagemInexistenteException;
	public Personagem sorteiaMonstro() throws SemMonstrosException;
}
