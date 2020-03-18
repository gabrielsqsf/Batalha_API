package personagem;

import java.time.LocalDateTime;
import java.util.Random;

import dados.Dados;
import personagem.persistencia.PersonagemDAO;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

public class LogicaPersonagem {
	PersonagemDAO dao;
	
	public LogicaPersonagem(PersonagemDAO dao) {
		this.dao = dao;
	}
	
	public Personagem selecionarPersonagemJogador(String nome_personagem) throws PersonagemInexistenteException {
		Personagem p = dao.selecionaPersonagem(nome_personagem);
		return p;
	}

	public Personagem sorteiaMonstro() throws SemMonstrosException {
		Personagem p = dao.sorteiaMonstro();
		return p;
	}
	
	public static int getIniciativa(Personagem p) {
		return p.getAgilidade() + Dados.rolarDados(10);
	}
	
	public static int getAtaque(Personagem p) {
		return p.getAgilidade() + p.getForca() + Dados.rolarDados(10);
	}
	
	public static int getDefesa(Personagem p) {
		return p.getAgilidade() + p.getDefesa() + Dados.rolarDados(10);
	}
	
	public static int getDano(Personagem p) {
		return p.getForca() + Dados.rolarDados(p.getLadoDado(), p.getNumDados());
	}
	
	
}
