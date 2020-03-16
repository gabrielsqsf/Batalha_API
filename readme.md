# Batalha

Essa é uma aplicação que visa montar uma batalha de RPG através de um webservice REST.

Segue uma ligeira especificação das Entitades:

Batalha {
	Id: long
	Nickname: String
	Personagens: [Jogador, Monstro],
	Turnos: [...]
	Ranking: int
	Concluída: bool
} 

Personagem {
	Nome: String,
	Tipo: int,
	Pdv: int,
	Força: int,
	Defesa: int,
	Agilidade: int,
	ladoDado: int,
	NumDados: int
}

Turno {
	Iniciativas: [int,int], 
	Ataques: [int, int], 
	Defesas: [int,int], 
	Danos: [int,int], 
	PdVInicial: [int,int], 
	PdVFinal: [int,int]
}

# Como começar?

As batalhas serão travadas sempre entre um Jogador e um Monstro, dessa maneira o primeiro passo para utilizar o sistema é a criação de uma batalha passando para o sistema o nickname que será utilizado pelo seu usuário e qual personagem você vai utilizar. A lista de personagens é a seguinte:

	| nome | Pontos de Vida | Força | Defesa | Agilidade | Fator de Dano |
	| --- | --- | --- | --- | --- | --- |
	| Guerreiro | 12 | 4 | 3 | 3 | 2d4 |
	| Barbaro | 13 | 6 | 1 | 3 | 2d6 |
	| Paladino | 15 | 2 | 5 | 1 | 2d4 |

O sistema receberá o seu pedido de criação de batalha ataravés de uma requisição POST para o endereço /batalha com os seguintes parâmetros: { nickname: String, personagem: String }. É importante salientar que o sistema não suporta a criação de múltiplas batalhas para o mesmo usuário (nickname).

Será retornado uma batalha com os seguintes parâmetros:

Batalha {
	Id: id da batalha criado no sistema
	Nickname: o nickname
	Personagens: [Jogador, Monstro],
	Turnos: []
	Ranking: 0
	Concluída: false
} 

# Iniciando um turno

A API irá receber uma chamada POST no Endpoint