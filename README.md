***База***

docker-compose up -d

***Миграция***

mvn liquibase:update -Dapp.liquibase.url=jdbc:postgresql://localhost:5432/postgres -Dapp.postgres.username='postgres' -Dapp.postgres.password='postgres'

***Jooq***

mvn jooq-codegen:generate -Dapp.postgres.url='jdbc:postgresql://localhost:5432/' -Dapp.postgres.username='postgres' -Dapp.postgres.password='postgres'

***Swagger***

http://localhost:8080/swagger-ui/index.html#/

***Grafana***

http://localhost:3000/?orgId=1