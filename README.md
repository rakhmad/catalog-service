# Catalog Service

## Background
This is a simple reactive web service application built upon the Red Hat build of Quarkus.
Main function of this app is to provide information about items in Catalog.

## Technology
Technology Stack used in this project:
1. Red Hat build of Quarkus 3.27 (OpenJDK 21)
2. PostgreSQL 12

## Deployment
The application now uses a reactive PostgreSQL client. Set the following environment variables to configure database connectivity:

```
POSTGRESQL_HOST=<database host>
POSTGRESQL_USERNAME=catalogsvc
POSTGRESQL_PASSWORD=<password>
```

Quarkus Dev Services can also provision PostgreSQL automatically during development and testing when Docker is available.

## Development
We use docker for database:

```shell
docker run -p 5432:5432 --name localdb -e POSTGRES_PASSWORD=<POSTGRES_PASSWORD> -d postgres:11
```

Connect to database, then create database, user and grant the access.

```postgresql
CREATE DATABASE catalog;
CREATE USER catalogsvc WITH ENCRYPTED PASSWORD 'r3dh4t1!';
GRANT ALL PRIVILEGES ON DATABASE catalog TO catalogsvc;
```