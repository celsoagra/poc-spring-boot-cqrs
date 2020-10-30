# spring-boot-interview

Projeto referente ao [Case Spring Boot Interview](https://github.com/gustavodallanora/spring-boot-interview). Referente ao processo de avaliação do colaborador Celso Agra.

### Features

* Cadastrar cidade
* Cadastrar cliente
* Consultar cidade pelo nome
* Consultar cidade pelo estado
* Consultar cliente pelo nome
* Consultar cliente pelo Id
* Remover cliente
* Alterar o nome do cliente

### Getting Started

Executar o projeto através do comando

```
$ mvn spring-boot:run
```
Este comando deverá executar o projeto localmente: http://localhost:8080

***APIs:*** As informações referentes as APIs podem ser acessadas através da página do swagger: http://localhost:8080/swagger-ui.html

### Testes

```
$ mvn test
```

### build

Executar o comando:

```
$ mvn clean package
```

em seguida, executar a aplicação:

```
# para ambientes de produção
java -jar -Dspring.profiles.active=prod target/app-0.0.1-SNAPSHOT.jar

# Para ambiente de desenvolvimento, executar o comando sem o parametro "spring.profiles.active"
```

### Produção

Para realizar a implantação em um ambiente de produção, você pode realizar inicialmente a seguinte configuração:

* configurar banco de dados (PostgreSQL)

```
# OBS.: Para simular um banco, você pode utilizar um container docker, ou instalar localmente:
docker run --name interview -e "POSTGRES_PASSWORD=postgres" -e "POSTGRES_USER=postgres" -e "POSTGRES_DB=postgres" -p 5432:5432 -d postgres:9.17
```

* Configurar login e senha do banco de dados no arquivo pom.xml (necessita de um usuario administrador):

```
<plugin>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-maven-plugin</artifactId>
	<version>7.0.0</version>
	<configuration>
		<url>jdbc:postgresql://localhost:5432/postgres</url>
		<user>postgres</user>
		<password>postgres</password>
	</configuration>
</plugin>
```
**OBS.:** esta configuração pode também ser realizada dentro do arquivo settings.xml do maven (na pasta .m2) para evitar que login e senha permaneçam no codigo fonte.

em seguida, executar o comando, para realizar a execução dos arquivos DDL:

```
$ mvn flyway:migrate
```

para executar a aplicação, deve-se utilizar o profile `prod`:

```
$ mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Docker

Para realizar o build de uma imagem docker da aplicação, utilizar o seguinte comando (precisa estar na pasta raiz):

```
$ docker build -t <nome_da_imagem>:<versao> .

```

Após realizar o build da imagem docker, pode utilizá-la executando os seguintes comandos:

```
ENV DB_IP 127.0.0.1
ENV DB_PORT 5432
ENV DB_BASE postgres
ENV DB_USER postgres
ENV DB_PASS postgres

docker run --name interviewdb -e "DB_USER=postgres" -e "DB_PASS=postgres" -e "DB_BASE=postgres" -e "DB_POR=postgres" -e "DB_IP=127.0.0.1" -p 8080:8080 -d <nome_da_imagem>:<versao>
```

**OBS.:** Em ambientes de produção, opte pelo uso de um orquestrador.

### Licença

[GPLv3](https://tldrlegal.com/license/gnu-general-public-license-v3-(gpl-3))