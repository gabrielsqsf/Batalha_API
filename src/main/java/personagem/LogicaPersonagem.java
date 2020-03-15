package personagem;

import java.time.LocalDateTime;
import java.util.Random;

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
		
		return p.getAgilidade() + rolaDado(10);
	}
	
	public static int getAtaque(Personagem p) {
		return p.getAgilidade() + p.getForca() + rolaDado(10);
	}
	
	public static int getDefesa(Personagem p) {
		return p.getAgilidade() + p.getDefesa() + rolaDado(10);
	}
	
	public static int getDano(Personagem p) {
		int danoDados = 0;
		for(int j = 0; j < p.getNumDados(); j++) {
			danoDados += rolaDado(p.getLadoDado());
		}
		return p.getForca() + danoDados;
	}
	
	private static int rolaDado(int faces) {
		Random r = new Random(System.currentTimeMillis());
		return 1+ r.nextInt(faces);
	}
}
