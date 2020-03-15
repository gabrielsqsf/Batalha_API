package batalha;

public class Turno {
	private int index;
	private int[] iniciativas;
	private int[] ataques;
	private int[] defesas;
	private int[] danos;
	private int[] pdvInicial;
	private int[] pdvFinal;
	
	public Turno(int index, int[] pdvInicial, int s) {
		super();
		this.index = index;
		this.iniciativas = new int[s];
		this.ataques = new int[s];
		this.defesas = new int[s];
		this.danos = new int[s];
		this.pdvInicial = pdvInicial;
		this.pdvFinal = new int[s];
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int[] getIniciativas() {
		return iniciativas;
	}

	public void setIniciativas(int[] iniciativas) {
		this.iniciativas = iniciativas;
	}

	public int[] getAtaques() {
		return ataques;
	}

	public void setAtaques(int[] ataques) {
		this.ataques = ataques;
	}

	public int[] getDefesas() {
		return defesas;
	}

	public void setDefesas(int[] defesas) {
		this.defesas = defesas;
	}

	public int[] getDanos() {
		return danos;
	}

	public void setDanos(int[] danos) {
		this.danos = danos;
	}

	public int[] getPdvInicial() {
		return pdvInicial;
	}

	public int[] getPdvFinal() {
		return pdvFinal;
	}

	public void setPdvFinal(int[] pdvFinal) {
		this.pdvFinal = pdvFinal;
	}
}
