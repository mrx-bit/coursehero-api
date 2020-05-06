# coursehero-api
### Backend REST API для Задачи на «HackDay2020» от Детского Фонда ООН ЮНИСЕФ

Веб-платформа позволяющая проходить онлайн обучения родителям и детаям с особыми 
потребностями в обучении по специально заданной траектории на основе опросника 
при регистрации.

## Demo
<a target="_blank" href="https://yadi.sk/i/HMhcbIvrRopJ0A">Ссылка на видео-демо на yandex disk</a>

## Swagger API DOCUMENTATION
http://185.125.46.42:9292/api/swagger-ui.html

## DB Architecture (ER Diagram)
https://yadi.sk/i/B4b6BcpwsD0f1A

## Build
`mvn clean install`
Database scripts are migrated automatically using Flyway DB migrations.

## Run
`mvn spring-boot:run`

It runs application on http://localhost:9292
