package personagem;

import java.time.LocalDateTime;
import java.util.Random;

import dados.Dados;

public class LogicaPersonagem {
	public static Personagem selecionarPersonagemJogador(String nome_personagem) {
		Personagem p = null;
		return p;
	}

	public static Personagem sorteiaMonstro() {
		Personagem p = null;
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