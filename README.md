
#  Arquitectura-Por-Eventos

### Implementación de Publish/Subscribe con Spring Boot y Redis

---

## Descripción General

Este proyecto demuestra una **arquitectura basada en eventos** implementada en **Java** utilizando el framework **Spring Boot** y **Redis** como *message broker*.
Redis actúa como intermediario entre un **productor (Publisher)** y múltiples **consumidores (Subscribers)** mediante el patrón **Publish/Subscribe**.

El flujo principal consiste en:

1. Un **Productor** publica mensajes en un canal de Redis.
2. Los **Receptores** escuchan el canal y reaccionan al evento recibido.
3. Spring Boot maneja la configuración, conexión y contenedores de escucha de manera automática.

Este ejemplo se basa en el tutorial *Spring Publish/Subscribe - Redis* adaptado al contexto del curso de Arquitecturas de Software (ARSW).

---

##  Estructura del Proyecto

```
Arquitectura-Por-Eventos/
├── src/main/java/co/edu/escuelaing/arquitecturaeventos/
│   ├── ArquitecturaPorEventosApplication.java
│   ├── connection/
│   │   ├── RedisConnectionConfig.java
│   │   ├── RedisListenerContainer.java
│   │   └── RedisTemplateService.java
│   ├── producer/
│   │   └── Producer.java
│   └── receiver/
│       └── Receiver.java
└── src/main/resources/
    └── application.properties
```

* **connection** → Configura la conexión a Redis y los contenedores de listeners.
* **producer** → Publica los mensajes en el canal Redis.
* **receiver** → Escucha los mensajes publicados y registra su contenido en consola.

---

## ️ Construcción del Proyecto

Ejecuta el siguiente comando Maven para compilar y empaquetar:

```bash
mvn -DskipTests package
```

Si el proceso finaliza correctamente verás:

```
[INFO] BUILD SUCCESS
```

---

## Ejecución de Redis con Docker

Para levantar una instancia local de Redis:

```bash
docker run --name redis-server -p 6379:6379 -d redis:latest
```

Verifica que Redis esté en funcionamiento:

```bash
docker ps
docker exec -it redis-server redis-cli ping
```

Debería responder:

```
PONG
```

---

## Ejecución de la Aplicación

Con Redis corriendo, ejecuta el proyecto con:

```bash
mvn spring-boot:run
```

Salida esperada:

```
Publicando mensaje: Hola desde Spring Boot!
Publicando mensaje: Arquitectura basada en eventos con Redis
Publicando mensaje: Mensaje de prueba #3
Mensaje recibido #1: Hola desde Spring Boot!
Mensaje recibido #2: Arquitectura basada en eventos con Redis
Mensaje recibido #3: Mensaje de prueba #3
Total de mensajes recibidos: 3
Producer finalizado
```

---

## Explicación Técnica — Patrón Publish/Subscribe

El patrón **Publish/Subscribe (Pub/Sub)** permite desacoplar los emisores y receptores de eventos.
En esta arquitectura:

* Los **productores** publican eventos en un canal común.
* Los **consumidores** se suscriben al canal y procesan los mensajes recibidos.
* Redis se encarga de distribuir los mensajes sin que el productor conozca a los suscriptores.

Spring Boot facilita este proceso mediante:

* `RedisMessageListenerContainer`: gestiona los hilos de escucha.
* `MessageListenerAdapter`: adapta métodos Java a manejadores de mensajes.
* `StringRedisTemplate`: simplifica el envío de mensajes tipo String.

De esta forma, la comunicación es **asíncrona**, **reactiva** y fácilmente escalable, cumpliendo con los principios de una **arquitectura por eventos**.

---



## Resultado Final

Con esta implementación, el sistema demuestra la integración entre:

* **Spring Boot** (configuración automática y manejo de beans),
* **Redis** (broker de mensajes de alto rendimiento),
* y el **patrón Publish/Subscribe** como núcleo de la arquitectura basada en eventos.

