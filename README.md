Sistema de Autenticação Distribuído com Microserviços, Kubernetes, AWS e Angular

🎯 Objetivo geral
Projetar, desenvolver e operar um sistema de autenticação seguro e escalável, utilizando microserviços, Kubernetes como base da arquitetura, testes automatizados, CI/CD e deploy real em AWS (EKS).

O foco não é apenas funcionar, mas demonstrar maturidade técnica, decisões arquiteturais e boas práticas de produção.

🧠 PERFIL AVALIADO

Este exercício avalia:

Arquitetura de software

Backend com Java / Spring

Kubernetes real (não apenas YAML básico)

Segurança

NOVA ETAPA — AUTORIZAÇÃO GRANULAR

Testes automatizados

Comunicação entre serviços

CI/CD

Cloud (AWS)

Integração com frontend

⏱️ CRONOGRAMA GERAL

Duração total: 6 semanas (≈ 45–50 dias)

Semana	Foco principal
1	Arquitetura + Kubernetes desde o início
2	Microserviços + Configuração + Segurança
3	Kubernetes intermediário + Testes
4	Frontend + Observabilidade
5	AWS (EKS)
6	CI/CD + Hardening
🔹 ETAPA 0 — ARQUITETURA ORIENTADA A KUBERNETES

📅 Dias 1 a 3

Objetivo

Pensar o sistema como algo que sempre rodará em Kubernetes.

Entregas

Documento (README ou markdown) explicando:

visão geral do sistema

responsabilidades dos serviços

por que microserviços

por que Kubernetes

Diagrama contendo:

Pods

Services

ConfigMaps

Secrets

Gateway

Critérios de avaliação

Clareza de comunicação

Decisões bem justificadas

Consistência com ambiente distribuído

🔹 ETAPA 1 — KUBERNETES LOCAL (BASE REAL)

📅 Dias 4 a 7

Objetivo

Ter Kubernetes funcionando antes do código de negócio.

Entregas

Cluster local (Minikube ou Docker Desktop)

Primeiro Pod (nginx ou app simples)

Services:

ClusterIP

NodePort

Testes:

kubectl exec

kubectl logs

kubectl describe

kubectl port-forward

Conceitos obrigatórios

Pod ≠ Container

Service ≠ IP fixo

DNS interno do Kubernetes

🔹 ETAPA 2 — MICROSSERVIÇOS BASE (SPRING)

📅 Dias 8 a 11

Serviços obrigatórios

eureka-server

config-server

api-gateway

auth-service

user-service

Entregas

Cada serviço com:

Spring Boot

Dockerfile

Health check (/actuator/health)

Todos os serviços rodando dentro do Kubernetes

Avaliação

Se algo roda fora do Kubernetes mas não dentro, não está pronto.

🔹 ETAPA 3 — CONFIGURAÇÃO CENTRALIZADA (SPRING CLOUD + K8S)

📅 Dias 12 a 14

Entregas

Spring Cloud Config Server

Configurações versionadas em Git

ConfigMaps:

URLs

perfis

Secrets:

senhas

chaves JWT

Conceitos avaliados

Separação entre código e configuração

Segurança de informações sensíveis

🔹 ETAPA 4 — SEGURANÇA (SPRING SECURITY + JWT)

📅 Dias 15 a 18

Entregas

Login funcional

JWT válido

Gateway validando token

Serviços protegidos

Secrets injetados via Kubernetes

Requisitos

REST stateless

Nenhuma sessão em memória

🔹 NOVA ETAPA — AUTORIZAÇÃO GRANULAR

📅 (Inserida após a etapa de segurança)

Objetivo

Garantir que não basta estar autenticado, é preciso ter permissão.

Entregas

JWT contendo:

roles

scopes

Regras no Gateway:

/admin/** → ADMIN

/users/** → USER

Regras nos serviços:

@PreAuthorize

validação de permissões

Avaliação

Token válido ≠ acesso liberado

Autorização consistente em múltiplas camadas

🔹 ETAPA 5 — TESTES AUTOMATIZADOS (OBRIGATÓRIO)

📅 Dias 19 a 23

Objetivo

Garantir qualidade, segurança e estabilidade.

🧪 Estratégia de Testes
🔹 Testes Unitários

Regras de negócio

Serviços

Validação de token

Ferramentas:
JUnit 5, Mockito

🔹 Testes de Controller

Endpoints REST

Status HTTP

Segurança (401 / 403)

Ferramentas:
Spring MockMvc

🔹 Testes de Integração

Comunicação entre serviços

Config Server

Banco de dados

🧪 Testes de Autorização (OBRIGATÓRIO)
Exemplos:

Usuário sem role ADMIN tentando acessar endpoint admin → 403

Token inválido → 401

Token válido + role correta → 200

Ferramentas:
Spring Boot Test, Testcontainers

🔹 Testes em Kubernetes

Simular restart de Pods

Validar self-healing

Testar comportamento com falhas

Critério eliminatório

❌ Projeto sem testes não passa

🔹 ETAPA 6 — KUBERNETES INTERMEDIÁRIO

📅 Dias 24 a 27

Entregas

Deployments (não Pods soltos)

Réplicas

Rolling update

Teste de escalabilidade

Conceitos avaliados

Desired State

Self-healing

Escalonamento horizontal

🔹 ETAPA 7 — COMUNICAÇÃO ASSÍNCRONA

📅 Dias 28 a 30

Entregas

Evento de domínio (ex: usuário criado)

Serviço consumidor

Simulação de falha

Avaliação

Quando usar REST

Quando usar eventos

🔹 ETAPA 8 — RESILIÊNCIA E OBSERVABILIDADE

📅 Dias 31 a 33

Entregas

Circuit Breaker

Actuator

Liveness e Readiness Probes

Logs estruturados

🔹 ETAPA 9 — FRONTEND (ANGULAR)

📅 Dias 34 a 37

Telas obrigatórias

Login

Dashboard simples

Entregas

Interceptor JWT

Comunicação via Gateway

Build containerizado

🔹 ETAPA 10 — KUBERNETES AVANÇADO

📅 Dias 38 a 40

Entregas

Ingress Controller

Load balancing interno

DNS via Service

Teste com múltiplas réplicas

🔹 ETAPA 11 — AWS (EKS REAL)

📅 Dias 41 a 45

Entregas

Cluster EKS

Deploy completo

LoadBalancer AWS

Frontend público

🔹 ETAPA 12 — CI/CD + HARDENING

📅 Dias 46 a 50

CI/CD

Testes automatizados

Build de imagens

Push para registry

Deploy automático

Hardening

Resource requests / limits

Fail-fast

README final explicando decisões

🏁 RESULTADO ESPERADO

Ao final, você terá:

✔️ Kubernetes real desde o início

✔️ Microserviços seguros

✔️ Testes automatizados sólidos

✔️ AWS prática

✔️ CI/CD funcional

✔️ Projeto nível mercado

🔥 NÍVEL DO EXERCÍCIO

📈 Pleno forte → Sênior inicial

## 📊 Avaliação Final do Exercício

### 🔹 Parte 0 — ARQUITETURA ORIENTADA A KUBERNETES 

**Explicação do aluno:**  

  Visao geral do sistema:

O sistema deve fazer autenticacao e autorizacao de usuarios. Endpoints para entender e usar a arquitetura com microsservicos e Kubernetes,
 alem de um frontend para entender de maneira mais proxima a realidade dos softwares em ambiente de producao e usados no mercado. 

Responsabilidades dos serviços:

eureka-server: todos os servicos irao se registrar no eureka-server, permitindo que os microservicos 
se encontrem dinamicamente sem depender de enderecos IP

config-server: as configuracoes ficarao centralizadas no config-server em um repositorio remoto, 
dessa forma centralizando todos os arquivos de configuracao/propriedades dos microservicos

api-gateway: ponto de entrada para os clientes acessarem os microservicos, serve tambem como 
load balance para o escalonamento dos microservicos com o Spring

auth-service: servico responsavel por autenticar os usuarios vindo das requisicoes 
do cliente 

user-service: servico responsavel pelo gerenciamento 
dos usuario (criacao, exclusao, alteracao dos usuarios)


Por que microserviços:
O uso de microservicos busca a separacao de responsabilidades no nível de negocio e tambem 
na arquitetura. Facilitando o entendimento das regras de negocio e divisao das mesmas. Os microsservicos podem 
ser dividos por times por exemplo a medida que o projeto aumenta de tamanho e de dificuldades. 
Fornece deploy independente de outras partes do sistema, bem como tambem torna mais simplificada a evolucao do sistema e da 
    arquitetura como um todo, pois apenas esse servico e afetado por novas features ou correcoes de bugs.
Como trade-off os microservicos trazem desafios como a orquestracao via Kubernetes, observabilidade e 
automacao de deploy.

Separacao em dois bancos de dados (auth / user):
 Cada microservico possui seu proprio banco de dados, seguindo o prinicpio de autonomia dos microservicos. Essa separacao evita
 acoplametno direto, permite escalabilidade independente e melhora a seguranca ao separar dados sensiveis. 
 O time de autenticacao pode ser uma equipe mais madura, com menos restricoes de acesso enquanto o banco de cadastro de usuarios pode ser um pouco mais acessivel por exemplo.


Por que Kubernetes:
Os containers sao muito utilizados no mercado atual, principalmente usando praticas de DevOps, 
aonde automacao, observabilidade e facilidades para um deploy mais rapido sao essenciais. Neste
sentido o  Kubernetes vem como um orquestrador dos containers, permitindo escalonar a aplicacao
de forma rapida, tanto vertical com horizontalmente. Permite o monitoramento das aplicacoes com o uso de tecnicas de 
observabilidade entre outros. Kubernetes e utilizado desde o inicio do projeto como ambiente padrao de execucao, evitando
diferencas entre ambientes de desenvolvimento e producao.

Diagrama:

                               ┌─────────────────────────┐
                               │        Kubernetes        │
                               │         Cluster          │
                               └────────────┬────────────┘
                                            │
                                  Entrada Única (NodePort)
                                            │
                                   ┌──────────────────┐
                                   │   svc-gateway    │
                                   │   (NodePort)     │
                                   └─────────┬────────┘
                                             │
                                    ┌─────────────────┐
                                    │   pod-gateway   │
                                    │                │
                                    │ ┌────────────┐ │
                                    │ │ api-gateway│ │
                                    │ │ Spring GW  │ │
                                    │ └────────────┘ │
                                    │      │          │
                                    │      ▼          │
                                    │  gateway-config │ (ConfigMap)
                                    └───────┬─────────┘
                                            │
               ┌────────────────────────────┴────────────────────────────┐
               │                                                           │A
        ┌───────────────┐                                      ┌───────────────┐
        │   svc-auth    │                                      │   svc-user    │
        │  (NodePort)  │                                      │  (NodePort)  │
        └───────┬──────┘                                      └──────┬────────┘
                │                                                           │
      ┌──────────────────┐                                     ┌──────────────────┐
      │    pod-auth      │                                     │    pod-user      │
      │                 │                                     │                 │
      │ ┌─────────────┐ │                                     │ ┌─────────────┐ │
      │ │ auth-service│ │                                     │ │ user-service│ │
      │ │ Spring Boot │ │                                     │ │ Spring Boot │ │
      │ └──────┬──────┘ │                                     │ └──────┬──────┘ │
      │        │         │                                     │        │         │
      │ ┌─────────────┐ │                                     │ ┌─────────────┐ │
      │ │  auth-db    │ │                                     │ │  user-db    │ │
      │ │ credentials │ │                                     │ │ user data  │ │
      │ └─────────────┘ │                                     │ └─────────────┘ │
      │        │         │                                     │        │         │
      │   auth-config   │ (ConfigMap)                          │   user-config   │ (ConfigMap)
      │   auth-secrets  │ (Secret)                             │   user-secrets  │ (Secret)
      └──────────────────┘                                     └──────────────────┘




**Nota do aluno:** `10/10`

**Análise do avaliador:**  
O projeto base foi criado corretamente utilizando o Spring Initializr, com Java 17 e estrutura padrão do Spring Boot. Apesar dos problemas iniciais relacionados ao Maven e à diferença de versões do Java (Java 8 no sistema e Java 17 no projeto), o aluno demonstrou boa capacidade de diagnóstico e resolução de problemas de ambiente. A execução do comando `mvn clean test` ocorreu com sucesso utilizando o Maven configurado pelo IntelliJ, validando que o projeto compila e que o contexto Spring sobe corretamente. A etapa atende completamente aos requisitos propostos.

Parte 1 — KUBERNETES LOCAL (BASE REAL)
Explicação do aluno

Para execução da Parte 1 do exercício, o ambiente Kubernetes foi configurado em um servidor Ubuntu, acessado remotamente via SSH, simulando um cenário mais próximo de um ambiente real.

O objetivo desta etapa foi garantir que o cluster Kubernetes estivesse completamente funcional antes da implementação de qualquer código de negócio, validando os conceitos fundamentais da plataforma.

Após a configuração do ambiente, foram executados os comandos definidos para esta etapa, conforme descrito abaixo.

Verificação dos Pods
kubectl get pods


Saída:

NAME              READY   STATUS    RESTARTS   AGE
nginx-pod         1/1     Running   0          26m
test-pod-ubuntu   1/1     Running   0          27m


Nesta etapa foram criados dois Pods:

nginx-pod: responsável por disponibilizar o servidor web nginx.

test-pod-ubuntu: utilizado como Pod cliente para testes de conectividade e DNS interno.

Inspeção do Pod nginx
kubectl describe pod nginx-pod


Trechos relevantes:

Name:       nginx-pod
Node:       diogo-linux/192.168.100.130
Status:     Running
IP:         10.42.0.13
Containers:
  nginx:
    Image: nginx:latest
    Port: 80/TCP


Este comando evidencia que:

O Pod possui IP próprio, independente do container.

O container nginx está contido dentro do Pod.

O ciclo de vida do container é gerenciado pelo Kubernetes.

Services configurados
kubectl get svc


Saída:

NAME              TYPE        CLUSTER-IP     PORT(S)
clusterip-nginx   ClusterIP   10.43.72.56    80/TCP
nodeport-nginx    NodePort    10.43.202.50   80:30080/TCP


Foram criados dois Services apontando para o mesmo Pod nginx:

ClusterIP: utilizado para comunicação interna entre Pods.

NodePort: utilizado para acesso externo ao serviço a partir da rede.

Testes de DNS interno do Kubernetes

A partir do Pod de teste:

kubectl exec -it test-pod-ubuntu -- bash


Teste de acesso via nome do Service:

curl clusterip-nginx


Resultado:

Welcome to nginx!


Teste de resolução DNS:

nslookup clusterip-nginx


Saída:

clusterip-nginx.default.svc.cluster.local
Address: 10.43.72.56


Esses testes comprovam o funcionamento do DNS interno do Kubernetes, permitindo que os Pods se comuniquem utilizando o nome do Service, sem dependência de IPs fixos.

Verificação de logs do Pod
kubectl logs nginx-pod


Trechos relevantes:

"GET / HTTP/1.1" 200


O comando confirma que o Pod nginx está recebendo requisições HTTP corretamente.

Teste com kubectl port-forward
kubectl port-forward pod/nginx-pod 7070:80


Em outro terminal:

curl localhost:7070


Resultado:

Welcome to nginx!


Este teste demonstra o acesso direto ao Pod utilizando port-forward, sem necessidade de exposição via Service externo.

Validação do NodePort
kubectl get svc nodeport-nginx


Saída:

80:30080/TCP


O serviço nginx pode ser acessado externamente utilizando o IP do nó Kubernetes e a porta configurada no NodePort.

Conceitos demonstrados

Pod ≠ Container

Service ≠ IP fixo

DNS interno do Kubernetes

Comunicação interna via ClusterIP

Exposição externa via NodePort

Diagnóstico com kubectl exec, logs, describe e port-forward

Avaliação do avaliador

A Parte 1 foi executada de forma completa e consistente, atendendo a todos os requisitos propostos.
O aluno demonstrou domínio prático do Kubernetes, com testes reais que comprovam o entendimento dos conceitos fundamentais da plataforma.

Nota final — Parte 1

Nota: 9,5 / 10

Justificativa:

Implementação correta de Pods e Services

Testes completos e bem executados

Conceitos fundamentais demonstrados na prática

Pequenos ajustes possíveis apenas na padronização de texto e boas práticas operacionais


