# Retail Store Discounts

## Overview

This project implements a discount calculation system for a retail store using Java and Spring Boot. It includes unit tests to achieve good code coverage and follows object-oriented programming principles.

## Running the Application

### Prerequisites

- Java 11
- Maven

### Building the Project

To build the project, run the following command:

```bash
mvn clean install
```

### Running the Tests

To run the tests, use the following command:

```bash
mvn test
```


### Assumptions and Decisions
Only one percentage-based discount can be applied per bill.
Percentage-based discounts do not apply to groceries.
For every $100 on the bill, there is a $5 discount.


