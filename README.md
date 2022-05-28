# Projeto Gamelog

Este projeto usa quarkus. website: https://quarkus.io/ .

## Configurações requeridas
Para execução deste projeto recomenda-se JDK 11, maven 3.8.1.

Configure no arquivo application.properties a propriedade 'log.file.path' com o path do arquivo de log.
Já existe um na raiz do projeto que sera usado por padrão.
## Executando aplicação em modo dev
```shell script
./mvnw compile quarkus:dev
```
Por padrão a aplicação será exposta na rota http://localhost:5000

## Lista de recursos
* lista de rodadas resumidas: http://localhost:5000/resumo/game
* rodada resumida: http://localhost:5000/resumo/game/game_{gameIndex}
* lista de placar de todos as rodadas: http://localhost:5000/resumo/score
* placar da rodada: http://localhost:5000/resumo/score/{indexGame}
* lista de games e lista de kills http://localhost:5000/v1/logger
* game com lista de kills http://localhost:5000/v1/logger/{indexGame}
## Testes
Para execução dos testes do pacote `com.integration` é necessário que a api esteja em funcionamento;
Os demais testes são testes unitários e os recursos são providos dentro das proprias classes.