package personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import personagem.Personagem;
import personagem.PersonagemDAO;
import personagem.PersonagemInexistenteException;
import personagem.SemMonstrosException;

public class PersonagemDAOImpl implements PersonagemDAO {
	List<Personagem> personagens;
	
	PersonagemDAOImpl() {
		personagens = new ArrayList<Personagem>();
		Personagem guerreiro = new Personagem(1, 12, 4, 3, 3, 2, 4, "Guerreiro");
		personagens.add(guerreiro);
				
		Personagem barbaro = new Personagem(1, 13, 6, 1, 3, 2, 6, "Barbaro");
		personagens.add(barbaro);
		
		Personagem paladino = new Personagem(1, 15, 2, 5, 1, 2, 4, "Paladino");
		personagens.add(paladino);
		
		Personagem morto = new Personagem(2, 25, 4, 0, 1, 2, 4, "Morto-Vivo");
		personagens.add(morto);
		
		Personagem orc = new Personagem(2, 20, 6, 2, 2, 1, 8, "Orc");
		personagens.add(orc);
		
		Personagem kobold = new Personagem(2, 20, 4, 2, 4, 3, 2, "Kobold");
		personagens.add(kobold);
	}

	public Personagem selecionaPersonagem(String nome) throws PersonagemInexistenteException {
		Optional<Personagem> p = personagens.stream().filter(psg -> psg.getTipo() == 1 && psg.getNome().equals(nome)).findFirst();
		if(!p.isPresent()) {
			throw new PersonagemInexistenteException();
		} else {
			return p.get();
		}
	}

	public Personagem sorteiaMonstro() throws SemMonstrosException {
		List<Personagem> pList = personagens.stream().filter(psg -> psg.getTipo() == 2).collect(Collectors.toList());
		if(pList.size() == 0) {
			throw new SemMonstrosException();
		}
		Random r = new Random(System.currentTimeMillis());
		return pList.get(r.nextInt(pList.size()));
	}

}
