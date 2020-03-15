package personagem;

public class Personagem {
	private int pdv;
	private int forca;
	private int defesa;
	private int agilidade;
	private int numDados;
	private int ladoDado;
	
	public Personagem(int pdv, int forca, int defesa, int agilidade, int numDados, int ladoDado) {
		super();
		this.pdv = pdv;
		this.forca = forca;
		this.defesa = defesa;
		this.agilidade = agilidade;
		this.numDados = numDados;
		this.ladoDado = ladoDado;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public int getDefesa() {
		return defesa;
	}

	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}

	public int getAgilidade() {
		return agilidade;
	}

	public void setAgilidade(int agilidade) {
		this.agilidade = agilidade;
	}

	public int getNumDados() {
		return numDados;
	}

	public void setNumDados(int numDados) {
		this.numDados = numDados;
	}

	public int getLadoDado() {
		return ladoDado;
	}

	public void setLadoDado(int ladoDado) {
		this.ladoDado = ladoDado;
	}
	
	
	
}
