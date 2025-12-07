# Catalog Service

## Background
This is a simple web service application rebuilt on top of Quarkus 3 with a reactive stack (compatible with the Red Hat build of Quarkus). The main function of this app is to provide information about items in Catalog using non-blocking HTTP endpoints and Hibernate Reactive.

## Technology
Technology Stack used in this project:
1. Quarkus 3 LTS platform (${quarkus.platform.version})
2. Java 21
3. Reactive RESTEasy with Jackson
4. Hibernate Reactive with PostgreSQL

## Deployment
This app is packaged as a Quarkus runner JAR and can be deployed to OpenShift. We can use the web console or CLI to deploy.

Deployment Steps:
1. Create Project
   ```shell
      oc new-project commerce-prd
   ```
2. Create New Application
   Build and deploy the application using OpenJDK 21 and the Quarkus base image:
   ```shell
   oc new-app registry.access.redhat.com/ubi9/openjdk-21:1.20-1~https://github.com/rakhmad/catalog-service.git \
      -e QUARKUS_DATASOURCE_USERNAME=catalogsvc \
      -e QUARKUS_DATASOURCE_PASSWORD=r3dh4t1! \
      -e QUARKUS_DATASOURCE_REACTIVE_URL=postgresql://postgresql:5432/catalog \
      --name=catalog-service
   ```

## Development
Quarkus Dev Services can start PostgreSQL automatically during tests, but for local development you can still run Postgres with Docker:

```shell
docker run -p 5432:5432 --name localdb -e POSTGRES_PASSWORD=<POSTGRES_PASSWORD> -d postgres:16
```

Connect to database, then create database, user and grant the access.

```postgresql
CREATE DATABASE catalog;
CREATE USER catalogsvc WITH ENCRYPTED PASSWORD 'r3dh4t1!';
GRANT ALL PRIVILEGES ON DATABASE catalog TO catalogsvc;
```
