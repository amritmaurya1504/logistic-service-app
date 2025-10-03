# Shipping & Logistic Microservices Application

A full-fledged **Shipping & Logistic System** built with **Spring Boot**, **Kafka**, **H2**, **Redis**, and **MERN stack integration for frontend**. This project demonstrates **event-driven architecture**, **microservices**, and real-time logistics management.

---

## 🏗 Project Structure / Microservices

| Service | Purpose |
|---------|---------|
| **Order Service** | Create, update, and track orders. Publishes order events. |
| **Inventory Service** | Checks stock, reserves items, and publishes inventory events. |
| **Shipment Service** | Creates shipments, assigns drivers/vehicles, updates shipment status. Publishes shipment events. |
| **Transport Service** | Assigns vehicles and drivers (optional separate from Shipment Service). |
| **Tracking Service** | Updates shipment progress and notifies customers. |
| **Notification Service** | Sends emails or messages on order/shipment status. |
| **API Gateway** | Routes requests to respective services. |
| **Service Registry (Eureka)** | Manages microservice discovery and registration. |
| **Zipkin (Distributed Tracing)** | Tracks requests across microservices and visualizes end-to-end flow. |

---

## 🗺 Application Flow Diagram

You can view the complete application flow diagram of the Shipping & Logistic microservices system at:

[Eraser.io Workspace - Application Flow](https://app.eraser.io/workspace/KYq1RIHiwVIJb9BtGCSp)

## ⚙️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3** (Spring Web, Spring Data JPA, Spring Kafka, Spring Cloud Config, Spring Cloud Eureka)
- **H2 Database** (file-based for development)
- **Redis** (for caching)
- **Kafka** (for event-driven communication between services)
- **Lombok** (for boilerplate reduction)


### Messaging & Event Handling
- Kafka Producer/Consumer for **Order, Inventory, and Shipment events**
- Event wrapper DTO for multiple event types

### DevOps / Others
- **Docker** (for running microservices)
- **Maven** (for build and dependency management)
- **Git/GitHub** (version control)
- **Postman** (API testing)

---

## 📝 Features

### Order Service
- Create new orders with validation
- Update order status (`CREATED`, `CONFIRMED`, `FAILED`, etc.)
- Fetch order by ID
- Publish order events to Kafka

### Inventory Service
- Check stock availability
- Reserve and release stock
- Publish inventory events to Kafka

### Shipment Service
- Create shipments for confirmed orders
- Assign drivers and vehicles
- Update shipment status (`CREATED`, `IN_TRANSIT`, `OUT_FOR_DELIVERY`, `DELIVERED`)
- Publish shipment events for tracking and notifications

### Transport Service
- Optional: manage vehicle/driver assignment
- Integrates with Shipment Service for operational management

### Tracking Service
- Real-time tracking of shipments
- Integrates with Shipment Service and Kafka events

### Notification Service
- Email and/or message notifications to customers
- Subscribes to order/inventory/shipment events

---

## 🔧 Running the Application

### Prerequisites
- Java 17+
- Maven 3+
- Docker (optional for running multiple services)
- Kafka & Zookeeper (or Docker Compose for Kafka cluster)
- Redis (optional for caching/locking)

### Steps
1. Clone the repository:
```bash
git clone https://github.com/amritmaurya1504/logistic-service-app.git
cd logistic-service-app
