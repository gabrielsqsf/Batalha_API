package dados;

import java.util.Random;

public class Dados {
	
	public static int rolarDados(int lados) { 
		return rolarDados(lados, 1);
	}
	public static int rolarDados(int lados, int quantidade) {
		Random r = new Random(System.currentTimeMillis());
		int resultadoDados = 0;
		for(int j = 0; j < quantidade; j++) {
			resultadoDados += 1+r.nextInt(lados);
		}
		return resultadoDados;
	}
}
