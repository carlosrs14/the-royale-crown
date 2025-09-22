# The Royale Crown

**Proyecto:** conjunto base de microservicios para juegos (The Royale Crown).

> **Estado actual:** Mono-repo Maven con módulos:
>
> * `microservice-config` — servidor de configuración
> * `microservice-gateway` — API Gateway
> * `microservice-game` — lógica del/los juegos
> * `pom.xml` (parent multi-module)

> **Objetivo a futuro:** añadir Service Discovery, Auth y Wallet como microservicios independientes y docker + docker compose.

---

## Índice

* [Visión general](#visión-general)
* [Arquitectura propuesta](#arquitectura-propuesta)
* [Requisitos](#requisitos)
* [Estructura del repositorio](#estructura-del-repositorio)
* [Cómo ejecutar localmente](#cómo-ejecutar-localmente)
* [TODO](#todo)

---

## Visión general

Este repositorio es la base para una plataforma de juegos online basada en una arquitectura de microservicios. La idea es mantener la lógica de cada juego desacoplada, exponerla mediante APIs y orquestar tráfico a través de un gateway + un balanceador de cargas.

## Arquitectura propuesta

* **API Gateway** (`microservice-gateway`): enrutamiento, autenticación a nivel de borde, throttling y punto único de entrada.
* **Config Server** (`microservice-config`): centraliza la configuración para todos los microservicios (Spring Cloud Config o similar).
* **Game Service(s)** (`microservice-game`): contiene la lógica de juego; en el futuro cada juego puede convertirse en su propio microservicio.
* **(Futuro)** **Discovery** (Eureka/Consul), **Auth** (Keycloak / Spring Authorization Server) y **Wallet** (servicio financiero interno para manejar monedas/transacciones).

## Requisitos

* Java 17+
* Maven 3.6+
* Docker & Docker Compose (opcional, recomendado para integración local)
* Git
* IDE recomendado: IntelliJ IDEA / VSCode con extensiones Java

## Estructura del repositorio

```
the-royale-crown/
├─ .github/
├─ microservice-config/
├─ microservice-gateway/
├─ microservice-game/
└─ pom.xml (parent)
```

Cada módulo es un submódulo Maven con su propio `pom.xml`.

## Cómo ejecutar localmente

1. Clonar el repositorio:

```bash
git clone https://github.com/carlosrs14/the-royale-crown.git
cd the-royale-crown
```

2. Buildar todo (desde la raíz):

```bash
mvn clean install
```

3. Ejecutar un módulo, ejemplo: `microservice-game` (antes de esto debe estar ejecutarse el config-server y el discovery-server):

```bash
cd microservice-game
mvn spring-boot:run
# o
java -jar target/microservice-game.jar
```

## TODO

* [ ] Crear microservicio `auth` (Keycloak o Authorization Server)
* [ ] Añadir Service Discovery (Eureka/Consul)
* [ ] Crear microservicio `wallet` con persistencia y tests
* [ ] Añadir Dockerfile por módulo y pipelines CI/CD (GitHub Actions)
* [ ] Documentar endpoints con OpenAPI y añadir ejemplos en Postman
* [ ] Integrar observabilidad (metrics + tracing)
