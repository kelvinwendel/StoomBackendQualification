## About
A Restful API with CRUD (Create, Read, Update, Delete) operations for an Address, builded using Spring Boot.

# Installation
* First of all clone the repository, after that compile this maven project in your IDE.
* After the project has been compiled, run script of folder "scripts" in your PostGreSQL Server to create database, tables and insert a initial payload.
* For default the credentials in "application.properties" is "postgres" for user and "supertux" for password.

## Endpoints and Methods
* Method: Get <br>
  Route: localhost:8080/addresses/id

* Method: GetAll <br>
  Route: localhost:8080/addresses/

* Method: Put <br>
  Route: localhost:8080/addresses/1 <br>
  Payload:
  ```javascript
  {
    "streetName": "Rua Tiradentes",
    "number": 508,
    "complement": "Loja Magazine",
    "neighbourhood": "Centro",
    "city": "Araras",
    "state": "Sao Paulo",
    "country": "Brasil",
    "zipcode": "13606-070",
    "latitude": null,
    "longitude": null
  }
 
* Method: Post <br>
  Route: localhost:8080/addresses/ <br>
  Payload:
  ```javascript
  {
    "streetName": "Rua Tiradentes",
    "number": 508,
    "complement": "Loja Magazine",
    "neighbourhood": "Centro",
    "city": "Araras",
    "state": "Sao Paulo",
    "country": "Brasil",
    "zipcode": "13606-070",
    "latitude": null,
    "longitude": null
  }
 
* Method: Delete <br>
  Route: localhost:8080/addresses/1


### Built With
* [Java](https://www.java.com/pt-BR/)
* [Spring](https://spring.io)


[![LinkedIn][linkedin-shield]][linkedin-url]

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/kelvin-wendel-543372b9/

