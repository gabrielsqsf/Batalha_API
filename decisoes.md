O termo usuário é utilizado de maneira ambígua ao longo da especificação, podendo se referir a pessoa que usa o sistema ou a um cadastro da mesma. Como o sistema de cadastro de pessoas não faz parte do escopo do serviço solicitado, vamos trabalhar com o nickname como sendo o identificador do usuário. Assim sendo, não será possível ter duas batalhas rodando com o mesmo nickname.

Também não é definido se o serviço deve receber uma solicitação de batalha e receber uma request post para criar cada Turno, ou calcular a batalha inteira e responder com o resultado para cada turno através de requests get. 

Uma terceira opção seria cada passo do turno ter uma request específica, mas me parece uma abordagem que deve ser utilizada somente se nosso objetivo for permitir que um moderador (mestre) possa administrar o andamento da batalha para permitir um grau maior de roleplay do usuário.

Dessa maneira julguei razoável, em uma primeira abordagem, seguir a lógica de cada turno ser calculado através de uma requisição post.

Como o cadastro e recuperação de personagens não faz parte da especificação, implementaremos uma entidade personagem, e faremos uma interface DAO para recuperar os mesmos de um armazenamento. Esse armazenamento será simulado por uma classe armazenando os personagens citados na especificação em uma lista e os recuperando.

Segue uma especificação da batalha e suas diversas partes:

Batalha {
Id: id da batalha no sistema
Nickname: Nome_Usuario
Personagens: [Jogador, Monstro] (Personagem {Nome,Tipo,Pdv,Força,Defesa,Agilidade,FdD}),
Turnos: [...] (Turno {Iniciativas: [int,int], Ataques: [int, int], Defesas: [int,int], Danos:[int,int], PdVInicial:[int,int], PdVFinal:[int,int])*
Ranking: int
Concluída: bool
}

*Alguns dos campos listados podem ser omitidos caso não sejam necessários em uma interface futura.

Dentro da abordagem REST:
GET /batalha 
retorna uma lista de todas as batalhas existentes

GET /batalha/i
retorna a batalha com id i ou um erro caso não exista

GET /batalha?usuario=abc
retorna as batalhas do usuário abc ou um array vazio

GET /batalha?usuario=abc&concluida=0
retorna a batalha atual do usuário abc ou um array vazio

GET /batalha?usuario=abc&concluida=1
retorna as batalhas concluidas do usuário abc ou um array vazio

GET /batalha/i/j
retorna o turno j (se existir) da batalha de id i do contrário retorna um erro

POST /batalha {nickname, tipo_heroi}
retorna a batalha criada ou um erro caso já exista uma batalha em curso para esse nickname

POST /batalha/id/
Avança a batalha para o próximo turno e retorna esse turno, caso a batalha não tenha sido concluída. Caso a batalha esteja concluída, retorna um erro.