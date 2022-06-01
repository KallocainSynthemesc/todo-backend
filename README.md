# Todo Backend

## DAO
For the database access I used a DAO abstraction layer that makes calls to the persistance context. The JPA implementation used is Hibernate

## REST Web Service
The REST endpoints are implemented using the standart javax.ws.rs library.

## Test
Tests are being written with help of the JUnit Framework. Tests are only written for the Endpoints since testing the DAO would come close to testing the hibernate implementation.
Calls in the DAO are all delegated to JPA methods. 
