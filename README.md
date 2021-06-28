# Catalog Service

## Background
This is a simple web service application built upon Spring Boot technology.
Main function of this app is to provide information about items in Catalog. 

## Technology
Technology Stack used in this project:
1. Spring Boot 2.5.1
2. PostgreSQL 12

## Deployment
This app will be deployed as standalone jar on top OCP.  We can use web console or CLI to deploy. 
We are assuming you are logged in into your cluster.

Deployment Steps:
1. Create Project
   ```shell
      oc new-project commerce-prd
   ```
2. Create New Application
   We deploy the application using `oc new-app` command:
   ```shell
   oc new-app openshift/java:openjdk-8-ubi8~https://github.com/rakhmad/catalog-service.git -e POSTGRESQL_HOST=postgresql --name=catalog-service 
    ```

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