# Price Service API

## Description

The **Price Service API** allows querying product prices based on parameters such as `productId`, `brandId`, and `date`.
The API is designed to retrieve the price of a product for a specific date range and brand. If no price is found for the
product in the specified date range, a 404 error is returned.

## Table of Contents

- [Description](#description)
- [Installation](#installation)
- [Requirements](#requirements)
- [Configuration](#configuration)
- [Usage](#usage)
    - [Example Requests](#example-requests)
- [Testing](#testing)
    - [Unit Tests](#unit-tests)
    - [E2E Tests](#e2e-tests)
- [Postman](#postman)
    - [Postman Collection](#postman-collection)
    - [Postman Environment](#postman-environment)
- [Deployment](#deployment)
- [License](#license)

---

## Installation

### 1. Clone the repository

First, clone this repository to your local machine using Git:

```bash
git clone https://github.com/your-user/price-service.git
```

### 2. Install dependencies

This project uses **Maven** to manage dependencies. If you don't have Maven installed, please follow
the [official Maven documentation](https://maven.apache.org/install.html).

To install the dependencies, run the following command:

```bash
mvn install
```

---

## Requirements

- **JDK 23** or higher
- **Maven 3.6.3** or higher
- **Postman** (for testing if you want to use the Postman collections)

---

## Configuration

1. **In-memory H2 Database**:
   The database is set up to use an in-memory H2 database by default, making it easy for quick development and local
   testing.

2. **Configuration properties**:
   You can configure the service properties in the `application.properties` file:

   ```properties
   server.port=8080
   spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   ```

3. **Swagger UI**:
   If you want to view the interactive API documentation, you can use Swagger. Simply go to the following URL after
   starting the application:

   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Usage

### Endpoints

#### `GET /prices`

Retrieves the price of a product for a specific brand within a date range.

##### Parameters:

- `productId` (required): The ID of the product.
- `brandId` (required): The ID of the brand.
- `date` (required): The date and time in ISO 8601 format.

##### Responses:

- **200 OK**: If the price is found.
- **400 Bad Request**: If the input parameters are invalid.
- **404 Not Found**: If no price is found for the product and brand within the specified dates.

#### Example Requests:

**Query the price of a product:**

```bash
GET http://localhost:8080/prices?productId=35455&brandId=1&date=2020-06-14T10:00:00
```

**Expected Response (200 OK):**

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50
}
```

**Error Response (404 Not Found):**

```json
{
  "error": "Price not found for product 35455 and brand 1 at the specified date."
}
```

---

## Testing

### Unit Tests

To run the unit tests, use the following Maven command:

```bash
mvn test
```

This will run all the unit tests in the project, ensuring that various components and functionality of the service work
as expected.

### E2E Tests

End-to-End (E2E) tests are available in the Postman collection. To run the E2E tests, import the provided Postman
collection from the `/postman` folder into Postman and execute the requests.

---

## Postman

### Postman Collection

The Postman collection for testing the API is included in the `/postman` folder of the repository. You can import it
directly into Postman.

### Postman Environment

An environment file for Postman can also be found in the `/postman` folder. This file contains all the necessary
variables for running the tests in your local environment.

---

## Deployment

To deploy the application, you can package it into a JAR file using Maven:

```bash
mvn clean package
```

Then, you can run the application using:

```bash
java -jar target/price-service-1.0.0.jar
```

The service will start on port 8080 by default.

---

## License

This project is licensed under the MIT License - see the [LICENSE] file for details.
