# Projeto Microservices WEB - UEPB

## 🚀 Technologies

### This project was developed with the followings technologies:

<ul>
  <li>Java</li>
  <li>Spring Boot</li>
</ul>

### Other technologies:

<ul>
  <li>Microservices</li>
  <li>H2 DataBase</li>
  <li>JPA Repository</li>
  <li>Rabbit MQ</li>
</ul>

### Developed abilities:

- [x] Create Microservices
- [x] Send e-mail with RabbitMQ

## 💻 Project

Este projeto foi desenvolvimento a partir da arquitetura em microservices,  compartilhando o  bancos de dados, em conjunto com rabbitMQ.   
Considerando apenas dois dos 4 domínios (aluno, professor, turma e projeto), à sua escolha, de forma que o banco de dados será compartilhado entre os dois microsserviços, assim enviando email a partir do evento de criação e edição dos mesmos.

### O projeto consiste em duas bases principais os producer e consumers sendo:
<ul>
  <li>rabbitMq-Service -> producer do aluno </li>
  <li>professorConsumer -> producer do professor</li>
  <li>consumerService -> consumer do professor</li>
  <li>alunoConsumer -> consumer do aluno</li>
</ul>

Ao criar e editar um aluno/professor é gerado o evento que envia os dados para o rabbitMQ que é o intermediario do microServicos onde guardará as informações em uma lista para ser consumida:

![](https://github.com/Romenildo/projeto2-microservices/blob/master/professorListRabbit.PNG)

Ademais ao chamar os consumer, ele irá ler essa lista com as informações dos email do rabbitMQ para o envio da mensagem a cada solicitação.   

