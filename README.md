## Country Reader
Application works with example Postgres database.
https://github.com/ghusta/docker-postgres-world-db

## Run with Docker
To run application in Docker pass:

`mvn install`

Run database:

`docker run -d -p5432:5432 ghusta/postgres_world_db:2.4`

Build image with docker:

`docker build -t countryreader .`

To run application:

`docker run -d -p8080:8080 -e SPRING_DATASOURCE_URL='jdbc:postgresql://{container_name}:5432/world-db' countryreader`