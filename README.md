# Loan Management System

A console-based Java application for managing customers, loans, assets, payments, and loan eligibility checks. Built as a modular, layered Java project (model / repository / service / util / exception) and run from a single entry point.

## Features

- Customer management (create, look up, and track customers)
- Loan application and processing, including personal loans
- Asset tracking tied to customers (used for eligibility/collateral checks)
- Loan eligibility evaluation before approval
- Payment recording and payment operations (e.g., installments)
- Interest and penalty calculation utilities
- Loan status tracking (e.g., pending, approved, active, closed)
- Custom exception handling for invalid operations (invalid loans, missing customers, insufficient assets)

## Project Structure

```
LoanManagementSystem/
└── src/
    ├── main/
    │   └── LoanManagementApp.java        # Application entry point
    │
    ├── model/
    │   ├── Asset.java
    │   ├── Customer.java
    │   ├── Loan.java
    │   ├── LoanStatus.java
    │   ├── Payment.java
    │   ├── PaymentOperations.java
    │   └── PersonalLoan.java
    │
    ├── repository/
    │   ├── CustomerRepository.java       # In-memory storage/lookup for customers
    │   └── LoanRepository.java           # In-memory storage/lookup for loans
    │
    ├── service/
    │   ├── CustomerService.java          # Business logic for customer operations
    │   ├── EligibilityService.java       # Loan eligibility checks
    │   ├── LoanService.java              # Loan creation/processing logic
    │   └── PaymentService.java           # Payment processing logic
    │
    ├── util/
    │   ├── InterestCalculator.java
    │   └── PenaltyCalculator.java
    │
    └── exception/
        ├── CustomerNotFoundException.java
        ├── InsufficientAssetException.java
        └── InvalidLoanException.java
```

## Architecture

The project follows a simple layered design:

- **model** – Plain data classes representing core entities (Customer, Loan, Asset, Payment, etc.)
- **repository** – Handles storage and retrieval of entities (currently in-memory)
- **service** – Contains business logic; services depend on repositories and other services
- **util** – Stateless helper classes for calculations (interest, penalties)
- **exception** – Custom checked/unchecked exceptions for domain-specific error handling
- **main** – Wires everything together and drives the application via console input (`Scanner`)

`LoanManagementApp.java` is the composition root — it constructs the repositories first, then the services (injecting repositories/services as needed), for example:

```java
CustomerRepository customerRepository = new CustomerRepository();
LoanRepository loanRepository = new LoanRepository();

CustomerService customerService = new CustomerService(customerRepository);
EligibilityService eligibilityService = new EligibilityService();
LoanService loanService = new LoanService(loanRepository, ...);
```

## Requirements

- Java (JDK) — the project is configured for **JavaSE-21**
- Eclipse IDE (or any Java IDE) — optional, any environment that supports standard Java projects will work
- No external dependencies (pure Java / `java.util.Scanner` for console I/O)

## Getting Started

### Using Eclipse
1. Import the project into Eclipse (`File > Import > Existing Projects into Workspace`).
2. Ensure the JRE System Library is set to JavaSE-21 (or compatible).
3. Right-click `LoanManagementApp.java` → `Run As` → `Java Application`.

### Using the command line
```bash
# From the project root
cd src
javac -d ../bin main/*.java model/*.java repository/*.java service/*.java util/*.java exception/*.java
java -cp ../bin main.LoanManagementApp
```

## Usage

Run `LoanManagementApp`. The application starts a console session (via `Scanner`) where you can interact with the system to manage customers, apply for loans, check eligibility, and process payments, depending on the menu/flow implemented in `main`.

## Exception Handling

The system uses custom exceptions to enforce valid domain operations:

| Exception | Thrown When |
|---|---|
| `CustomerNotFoundException` | An operation references a customer that doesn't exist |
| `InsufficientAssetException` | A customer's assets don't meet loan/collateral requirements |
| `InvalidLoanException` | A loan request or operation is invalid |

## Roadmap / Possible Improvements

- Persist data (file/database) instead of in-memory repositories
- Add unit tests for services and utility calculators
- Support additional loan types beyond `PersonalLoan`
- Add a build tool (Maven/Gradle) for dependency and build management
