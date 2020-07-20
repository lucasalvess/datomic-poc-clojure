# datomic-clojure

Teste de features do clojure

## Configuração com Datomic starter

- Criar de conta
- Pedir licença
- Baixar datomic

## Transactor (servidor)
O transactor é um programa que tem a permissão de gravar arquivos no banco.

#### Configuração do transactor

Em config/samples localizar arquivo `dev-transactor-template.properties`. Em licence-key colar sua licença e mover o arquivo para fora da pasta samples;

#### Rodando o Transactor

Na pasta raiz rodamos `bin/transactor config/dev-transactor-template.properties` Teremos essa resposta:

```
Launching with Java options -server -Xms1g -Xmx1g -XX:+UseG1GC -XX:MaxGCPauseMillis=50
Starting datomic:dev://localhost:4334/<DB-NAME>, storing data in: data ...
System started datomic:dev://localhost:4334/<DB-NAME>, storing data in: data
Os dados serão salvos do diretório /data
```

## Adicionando repositório ao leiningen

Em nosso arquivo `~/lein/credentials.clj.gpg` teremos que colocar alguma informação. Para isso, vamos no terminal, abrimos uma nova aba, vamos no diretório e criamos um novo diretório chamado `.lein`.

No arquivo `credentials.clj.gpg` adicionaremos o via copiando as linhas de código encontradas na página.

```
{#'my\.datomic\.com" {:username "guilherme.silveira@caelum.com.br"
    password "781e9538-97e0-43fd-ba36-840c06080743'}} 
```
Salvamos o arquivo de credenciais.

## Integração via Peer Library
Logo após baixar as dependências devemos rodar a biblioteca do datomic rodando o seguinte comando na pasta raiz contendo o jar

```sh
bin/maven-install
```

#### Criando um database
Inicie Clojure REPL que inclua o Datomic Peer library no classpath:
`bin/repl`
Usando o Datomic Peer API, crie um database:

```shell script
(require '[datomic.api :as d])
(def db-uri "datomic:dev://localhost:4334/poc")
(d/create-database db-uri)
```

## License

Copyright © 2020 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
