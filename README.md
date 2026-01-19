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

📦 ETAPA 2 — MICROSSERVIÇOS BASE (SPRING)

📅 Dias 8 a 11

🎯 Objetivo

Ter a arquitetura de microsserviços funcional, rodando exclusivamente dentro do Kubernetes, sem foco ainda em regras de negócio complexas.

⚠️ Regra-chave de avaliação
Se roda fora do Kubernetes, mas não roda dentro, não está pronto.

🔹 Serviços obrigatórios
Serviço	Responsabilidade
eureka-server	Service Discovery
config-server	Configuração centralizada
api-gateway	Ponto único de entrada
auth-service	Autenticação
user-service	Gerenciamento de usuários
🔹 Entregas obrigatórias (para cada serviço)

Cada serviço deve conter:

Spring Boot

Porta configurável via variável de ambiente

Endpoint:

/actuator/health


Dockerfile funcional

Deployment Kubernetes

Service Kubernetes (ClusterIP)

🔹 Testes esperados
kubectl get pods
kubectl get svc
kubectl logs <pod>
kubectl exec <pod>
curl http://<service>:<port>/actuator/health


✔ Todos os serviços Running
✔ Comunicação via DNS interno do Kubernetes

🔹 Avaliação

Arquitetura correta

Serviços desacoplados

Nada rodando fora do cluster

Gateway chamando serviços via nome DNS

📦 ETAPA 3 — CONFIGURAÇÃO CENTRALIZADA

(Spring Cloud Config + Kubernetes)

📅 Dias 12 a 14

🎯 Objetivo

Separar código de configuração, seguindo boas práticas de microsserviços.

🔹 Entregas obrigatórias
Spring Cloud Config Server

Configurações versionadas em Git

Perfis (dev, k8s, prod)

ConfigMaps (Kubernetes)

Usados para:

URLs de serviços

Portas

Perfis ativos

Configurações não sensíveis

Exemplo:

SPRING_PROFILES_ACTIVE=k8s
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka

Secrets (Kubernetes)

Usados para:

Senhas

Tokens

Chaves JWT

Credenciais de banco

❗ Nenhum segredo no application.yml

🔹 Conceitos avaliados

Separação entre código e configuração

Versionamento de config

Segurança básica

Uso correto de ConfigMap vs Secret

📦 ETAPA 4 — SEGURANÇA

(Spring Security + JWT)

📅 Dias 15 a 18

🎯 Objetivo

Implementar autenticação moderna e stateless.

🔹 Entregas obrigatórias

Login funcional (/auth/login)

JWT válido retornado

Gateway validando token

Serviços protegidos

Secrets injetados via Kubernetes

🔹 Requisitos técnicos

REST stateless

Nenhuma sessão em memória

Token passado via:

Authorization: Bearer <token>

🔹 Avaliação

✔ Token inválido → acesso negado
✔ Token válido → acesso permitido

📦 NOVA ETAPA — AUTORIZAÇÃO GRANULAR

(Após Segurança)

📅 Dias 19 a 20

🎯 Objetivo

Garantir que autenticação ≠ autorização.

🔹 Entregas obrigatórias
JWT contendo:

roles

scopes

Exemplo:

{
  "sub": "diogo",
  "roles": ["ADMIN"],
  "scopes": ["users.read", "users.write"]
}

Regras no Gateway
/admin/** → ADMIN
/users/** → USER

Regras nos serviços
@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasAuthority('users.read')")

🔹 Avaliação

Token válido ≠ acesso liberado

Autorização consistente:

Gateway

Serviço

🗄️ ETAPA EXTRA (AVANÇADA) — FLYWAY (VERSIONAMENTO DE BANCO)

📅 Inserida entre ETAPA 3 e ETAPA 4

👉 Essa é a melhor etapa para incluir Flyway, porque:

Configuração já está centralizada

Secrets já existem

Segurança ainda não bloqueia fluxo

🎯 Objetivo

Garantir versionamento e controle do schema do banco, alinhado a microsserviços.

🔹 Entregas obrigatórias
Flyway configurado em:

auth-service

user-service

Scripts de migração
db/migration/
V1__create_users_table.sql
V2__add_roles_table.sql
V3__add_user_roles.sql

Banco rodando no Kubernetes

PostgreSQL ou MySQL

Credenciais via Secrets

URL via ConfigMap

🔹 Avaliação (nível avançado)

Schema criado automaticamente

Migrações idempotentes

Rollout sem perda de dados

Flyway executando ao subir o Pod

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

**Nota do aluno:** `10/10`

Avaliação do Avaliador
✅ Visão geral da entrega

A Parte 1 do exercício foi executada com sucesso, atendendo integralmente aos objetivos propostos.
O aluno demonstrou domínio prático do Kubernetes em ambiente real, utilizando um servidor Ubuntu acessado via SSH, o que aproxima a execução de um cenário de produção e vai além de ambientes puramente locais.

Todos os itens obrigatórios foram implementados, testados e comprovados por meio de comandos e saídas reais.

🔹 Ambiente Kubernetes

Ambiente Kubernetes configurado em servidor Ubuntu

Acesso realizado via SSH

Cluster operacional antes da implementação de qualquer código de negócio

✔ Critério atendido

🔹 Pods criados

O aluno criou dois Pods distintos:

nginx-pod — responsável por prover o serviço web

test-pod-ubuntu — utilizado como cliente interno para testes de conectividade e DNS

Evidência
kubectl get pods


Saída apresentada:

nginx-pod         1/1     Running
test-pod-ubuntu   1/1     Running


✔ Demonstra entendimento de que Pod é a unidade básica do Kubernetes, independente do container.

🔹 Conceito: Pod ≠ Container

O comando:

kubectl describe pod nginx-pod


foi utilizado corretamente para demonstrar:

O Pod possui IP próprio

O container (nginx) está contido dentro do Pod

O ciclo de vida é gerenciado pelo Kubernetes

Trechos relevantes analisados:

IP do Pod (10.42.0.13)

Definição explícita do container nginx

Estado Running sem reinicializações

✔ Conceito claramente demonstrado

🔹 Services criados

Foram criados dois Services distintos apontando para o mesmo Pod, o que demonstra domínio da abstração de rede do Kubernetes:

ClusterIP
clusterip-nginx   ClusterIP   10.43.72.56   80/TCP

NodePort
nodeport-nginx    NodePort    10.43.202.50   80:30080/TCP


✔ O aluno demonstrou corretamente que:

Services não são IPs fixos de Pod

O Service abstrai o acesso ao Pod

Um mesmo Pod pode ser exposto por múltiplos Services

🔹 Conceito: Service ≠ IP fixo

A separação entre Pod IP e Service IP foi evidenciada por:

IP do Pod: 10.42.0.13

IP do Service ClusterIP: 10.43.72.56

✔ Demonstra entendimento de que:

O Service mantém estabilidade enquanto os Pods podem ser recriados com IPs diferentes.

🔹 DNS interno do Kubernetes

O aluno comprovou o funcionamento do DNS interno utilizando:

nslookup clusterip-nginx


Saída analisada:

clusterip-nginx.default.svc.cluster.local
Address: 10.43.72.56


Além disso, o acesso via nome do Service foi validado com sucesso:

curl clusterip-nginx


✔ Demonstra domínio de:

DNS interno

Descoberta de serviços

Comunicação entre Pods sem uso de IP direto

🔹 Testes com kubectl exec

O comando foi utilizado corretamente para acessar o Pod cliente:

kubectl exec -it test-pod-ubuntu -- bash


✔ Confirma acesso interativo ao container
✔ Permitiu execução de testes de rede internos

🔹 Testes com kubectl logs

O comando:

kubectl logs nginx-pod


foi utilizado corretamente para:

Verificar inicialização do nginx

Confirmar requisições HTTP recebidas

Trechos importantes:

"GET / HTTP/1.1" 200


✔ Demonstra rastreabilidade e observabilidade do Pod

🔹 Testes com kubectl port-forward

O aluno utilizou corretamente:

kubectl port-forward pod/nginx-pod 7070:80


E validou o acesso externo local:

curl localhost:7070


✔ Demonstra entendimento de:

Encaminhamento direto para Pod

Acesso sem necessidade de Service externo

🔹 NodePort (acesso externo)

O Service NodePort foi validado corretamente:

kubectl get svc nodeport-nginx


Saída:

80:30080/TCP


✔ Demonstra entendimento de exposição externa do serviço via porta do nó

📌 Avaliação Final da Parte 1
Critério	Status
Cluster Kubernetes funcional	✅
Pod nginx criado	✅
Pod cliente para testes	✅
Service ClusterIP	✅
Service NodePort	✅
DNS interno validado	✅
kubectl exec	✅
kubectl logs	✅
kubectl describe	✅
kubectl port-forward	✅
Conceitos explicados na prática	✅
🏁 Conclusão do Avaliador

A Parte 1 foi executada de forma completa e correta, com excelente nível técnico para a etapa proposta.
O aluno demonstrou não apenas execução de comandos, mas compreensão dos conceitos fundamentais do Kubernetes, especialmente rede, abstração de serviços e diagnóstico


