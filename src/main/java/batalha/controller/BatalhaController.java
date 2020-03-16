package batalha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import batalha.LogicaBatalha;
import batalha.LogicaTurno;
import batalha.persistencia.BatalhaInexistenteException;
import batalha.response.BatalhaResponse;
import batalha.response.ListaBatalhaResponse;
import batalha.response.LogicaBatalhaWrapper;
import batalha.response.LogicaTurnoWrapper;
import batalha.response.TurnoResponse;
import personagem.persistencia.PersonagemInexistenteException;
import personagem.persistencia.SemMonstrosException;

@RestController
public class BatalhaController {
	
	@GetMapping("/batalha")
	public ListaBatalhaResponse  listaBatalhas() {
		ListaBatalhaResponse resp = LogicaBatalhaWrapper.listaBatalhas();
		return resp; 
	}
	
	@PostMapping("/batalha")
	public BatalhaResponse criaBatalha(@RequestParam(name="nickname") String nickname, 
			@RequestParam(name="personagem") String personagem) {
		BatalhaResponse battle = null;
		try {
			battle = LogicaBatalhaWrapper.criarBatalha(nickname, personagem);
		} catch(PersonagemInexistenteException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "personagem inexistente!");
		} catch(SemMonstrosException e) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "não foram encontrados monstros na base!");
		}
		return battle;
	}
	
	@GetMapping("/batalha/{id}")
	public BatalhaResponse  listaBatalhasId(@RequestParam(name="id") long id) {
		BatalhaResponse resp = null;
		try {
			LogicaBatalha.getBatalha(id);
		} catch (BatalhaInexistenteException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa batalha não foi encontrada!");
		}
		return resp; 
	}
	
	@PostMapping("/batalha/{id}")
	public TurnoResponse CriarTurno(@RequestParam(name="id") long id) {
		TurnoResponse turno = null;
		try {
			turno = LogicaTurnoWrapper.criarTurno(id);
		} catch (Exception e) {
			
		}
		return turno;
	}
	
}
