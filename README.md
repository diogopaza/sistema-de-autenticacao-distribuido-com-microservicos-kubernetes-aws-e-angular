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
