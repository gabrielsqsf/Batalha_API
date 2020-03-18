package batalha.response;

import java.util.List;

public class ListaBatalhaResponse {
	private String status;
	private List<BatalhaResponse> batalhas;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BatalhaResponse> getBatalhas() {
		return batalhas;
	}
	public void setBatalhas(List<BatalhaResponse> batalhas) {
		this.batalhas = batalhas;
	}
}
