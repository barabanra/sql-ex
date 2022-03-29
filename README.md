``
Build
``
````
docker-compose up -d
mvn liquibase:update -Dapp.liquibase.url=jdbc:postgresql://localhost:5432/postgres -Dapp.postgres.username='postgres' -Dapp.postgres.password='postgres'
mvn jooq-codegen:generate -Dapp.postgres.url='jdbc:postgresql://localhost:5432/' -Dapp.postgres.username='postgres' -Dapp.postgres.password='postgres'

````
``
Swagger
``
http://localhost:8080/swagger-ui/index.html#/

``
Grafana
``
http://localhost:3000

``
Jaeger
``
http://localhost:16686
---
``
todo
``
````
graylog
docker, app limits
stress testing 
````