# Projeto Gamelog

Este projeto usa quarkus. website: https://quarkus.io/ .

## Configurações requeridas
Para execução deste projeto recomenda-se recomenda-se JDK 11.
## Executando aplicação em modo dev
```shell script
./mvnw compile quarkus:dev
```
## Acessando o recurso
* lista de rodadas resumidos: http://localhost:5000/resumo/game
* rodada resumida: http://localhost:5000/resumo/game/game_{gameIndex}
* lista de placar de todos as rodadas: http://localhost:5000/resumo/score
* placar da rodada: http://localhost:5000/resumo/score/{indexGame}
* lista de games e lista de kills http://localhost:5000/v1/logger
* game com lista de kills http://localhost:5000/v1/logger/{indexGame}
