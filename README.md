# Credit Card Transaction Middleware Service

## Overview

This project is a middleware service built to govern, authorize, and log credit card transactions. Acting as a critical link between the client requests and transaction data storage, this service handles incoming transactions by validating and authorizing each request while ensuring data integrity and security. The middleware is designed to be highly scalable, capable of managing millions of transactions per second, and is built with resilience in mind to handle various failure scenarios.

## Project Purpose

In today’s high-frequency financial systems, managing transaction loads while ensuring security and accuracy is challenging. This project addresses this by providing a centralized transaction management system that:
- **Validates** each incoming transaction request.
- **Authorizes** transactions based on predefined rules and governance policies.
- **Records** each transaction with a status that includes information on authorization and success.
- **Logs and Monitors** all activities to ensure system health and to identify any irregularities promptly.

## Key Features

1. **Transaction Authorization and Validation**: Ensures that only legitimate and accurate transactions are recorded in the system.
2. **Scalability with Kafka**: Utilizes Apache Kafka to handle high-throughput transaction streams, allowing the system to scale to millions of transactions per second.
3. **Data Caching and Idempotency with Redis**: Redis caching is implemented to improve read speeds, prevent duplicate transactions, and apply rate limiting to manage transaction flow.
4. **Fault Tolerance and Monitoring**: Integrates a circuit breaker pattern to handle failures gracefully and uses the ELK stack for comprehensive monitoring and alerting.

## Architecture

The project follows a microservices architecture to separate concerns and enhance scalability. Each component—such as the transaction validator, authorization service, and data logger—can be developed, deployed, and scaled independently. Key components include:

- **Kafka** for asynchronous transaction queueing.
- **MySQL** as the primary database, set up with replication for fault tolerance.
- **Redis** for caching and ensuring idempotency.
- **Spring Boot** as the framework, enabling RESTful API services and dependency management.
- **ELK Stack (Elasticsearch, Logstash, Kibana)** for real-time monitoring and logging, allowing the team to quickly detect and resolve issues.

## Transaction Flow

1. **Transaction Request**: A client sends a credit card transaction request via the API.
2. **Validation and Authorization**: The service validates the request for format and content, and authorization is checked based on governance rules.
3. **Processing**: Kafka queues the transaction, allowing the system to handle a high volume of requests concurrently.
4. **Data Recording**: The transaction is recorded in MySQL, with a status indicating the outcome (authorized-successful, unauthorized-failed, etc.).
5. **Caching and Rate Limiting**: Redis handles idempotency checks and rate limiting to prevent duplicate transactions and mitigate abuse.

## Tech Stack

- **Java (Spring Boot)**: Primary language and framework for building the RESTful service.
- **MySQL**: Database for storing transaction records.
- **Kafka**: Manages the high-volume transaction queue.
- **Redis**: Implements caching, idempotency checks, and rate limiting.
- **ELK Stack**: Provides monitoring, logging, and alerting.
- **Resilience4j**: For implementing circuit breakers to handle faults.

