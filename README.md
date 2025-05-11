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
- [Warning about Mockito](#warning-about-mockito)
- [License](#license)

---

## Installation

### 1. Clone the repository

First, clone this repository to your local machine using Git:

```bash
git clone https://github.com/imanolvr96/price-service.git
```

### 2. Install dependencies

This project uses **Maven** to manage dependencies. If you don't have Maven installed, you don't need to worry, as this project includes the **Maven Wrapper**. 

To install the dependencies, run the following command:

```bash
./mvnw install
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
   You can configure the service properties in the `application.yml` file:

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
GET localhost:8080/api/prices?brandId=1&productId=35455&applicationDate=2020-06-14T00:00:00
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
   "message": "No price found for productId=99999, brandId=1, date=2020-06-14T00:00",
   "status": 404,
   "timestamp": "2025-05-11T17:34:24.392325",
   "error": null
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

## Warning about Mockito

When running the tests, you might encounter the following warning:
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build as described in Mockito's documentation.
This warning appears because, starting with JDK 17, inline mocking using **Mockito** is no longer supported out of the box. To resolve this and avoid warnings, you should add 

**Mockito as a Java Agent** during the test execution.

---

## License

This project is licensed under the MIT License - see the [LICENSE] file for details.
