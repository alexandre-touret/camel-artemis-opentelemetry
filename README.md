# How to start it

## Distributed Tracing with OpenTelemetry

Start the infrastructure:

```bash
cd containers/
docker compose up
```

Start the producer:

```bash
cd camel-producer/
mvn clean spring-boot:run -Popentelemetry
```


Start the consumer:

```bash
cd camel-producer/
mvn clean spring-boot:run -Popentelemetry
```
Start the gateway:

```bash
cd camel-producer/
mvn clean spring-boot:run -Popentelemetry
```

Run then tests:
```bash
http :9080/camel/test
```

Now you [can browse Jaeger to see your traces](http://localhost:16686/).
