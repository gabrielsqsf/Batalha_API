package batalha.response;

import java.util.List;

public class ListaBatalhaResponse {
	private List<BatalhaResponse> batalhas;
	public List<BatalhaResponse> getBatalhas() {
		return batalhas;
	}
	public void setBatalhas(List<BatalhaResponse> batalhas) {
		this.batalhas = batalhas;
	}
}
