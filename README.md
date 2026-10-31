# stock-management-system-backend

- Student: IRAKOZE Tessy Mick
- Student ID: 27632
- Course: INSY 8322 Web Technology and Internet 
- Project: Stock Management System

A Spring Boot backend that keeps track of the goods entering the stores of a small business. A supplier places a stock order that is handled by a store keeper who is in charge of one warehouse and covers several sections. The order moves from PLACED to APPROVED and ends RECEIVED or CANCELLED. Once the order is received, the store keeper records the received items.

## Technologies or tools used

- Java 17
- Spring Boot 4 (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven (wrapper included)

## Steps to run my API

1. Install Java 17 and PostgreSQL.
2. Create the database in pgAdmin or psql:
   ```sql
   CREATE DATABASE "stock-management-system-db";
   ```
3. Open `src/main/resources/application.properties` and set your own PostgreSQL username and password in my case my username: postgres and password: 12345

4. Run the project from the project folder:
   ```bash
   ./mvnw spring-boot:run
   ```
5. The API runs on `http://localhost:8082`. The tables are created automatically.
6. Test the API with the Postman collection (see below).

## Testing with Postman

The Postman collection is in the root of this repository: `stock-management-system-midterm.postman_collection.json`.

1. Start the API (see the steps above).
2. Open Postman and click **Import**.
3. Choose the file `stock-management-system-midterm.postman_collection.json` and click **Import**.
4. The collection appears on the left with one folder per part (Part A to Part F and the bonus part).
5. Open a request and click **Send**.

The ids in the requests come from my own database. On a new database, first save your own data with the A2 save requests, then replace the ids in the other requests with the ids you get back.
