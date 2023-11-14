# Заготовка микросервиса
Реализация Crud для одной таблицы.

## Используемые технологии 
* Spring boot
* Spring JDBC
* Spring MVC
* Validation
* Embedded Postgres
* Lombok
* JUnit
* Mockito
* AssertJ
* Liquibase
* Jackson
* MockMVC


docker-compose.yml:

     version: '3.1'
     
     services:
     
       db:
         image: postgres
         restart: always
         environment:
           POSTGRES_PASSWORD: example
           POSTGRES_DB: postgres
           POSTGRES_USER: user
           POSTGRES_PASSWORD: postgres
         ports:
           - 5432:5432
     
       adminer:
         image: adminer
         restart: always
         ports:
           - 8080:8080

Для запуска приложения необходимо поднять базу черзе docker-compose            