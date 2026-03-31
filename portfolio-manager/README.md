# Portfolio Manager - Backend API

Enterprise-grade REST API for managing financial portfolio assets.

## Tech Stack

- Java 17
- Spring Boot 3.2.0
- MyBatis-Plus 3.5.5
- MySQL 8.0
- Lombok
- MapStruct
- Knife4j (Swagger)
- Maven

## Project Structure

```
portfolio-manager/
├── src/main/java/com/portfolio/
│   ├── config/           # Configuration classes
│   ├── controller/       # REST Controllers
│   ├── dto/             # Data Transfer Objects
│   ├── vo/              # View Objects
│   ├── entity/          # JPA Entities
│   ├── mapper/          # MyBatis Mappers
│   ├── service/         # Service Interfaces
│   ├── service/impl/    # Service Implementations
│   ├── exception/       # Exception Handling
│   └── PortfolioManagerApplication.java
├── src/main/resources/
│   ├── application.yml  # Application Configuration
│   └── mapper/          # MyBatis XML Mappers (optional)
└── sql/
    └── schema.sql       # Database Schema
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

## Database Setup

```bash
# Login to MySQL
mysql -u root -p

# Run the schema script
source sql/schema.sql
```

Or manually:

```sql
CREATE DATABASE portfolio_db;
USE portfolio_db;

CREATE TABLE portfolio_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ticker VARCHAR(20) NOT NULL,
    asset_type VARCHAR(20),
    quantity INT NOT NULL,
    purchase_price DECIMAL(10,2),
    current_price DECIMAL(10,2),
    purchase_date DATE,
    created_at DATETIME,
    updated_at DATETIME
);
```

## Configuration

Edit `src/main/resources/application.yml` to configure database credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/portfolio_db
    username: root
    password: root
```

## Build & Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Knife4j Swagger UI: http://localhost:8080/doc.html

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/portfolio | Create new asset |
| GET | /api/portfolio/list | List all assets |
| GET | /api/portfolio/{id} | Get asset by ID |
| PUT | /api/portfolio/{id} | Update asset |
| DELETE | /api/portfolio/{id} | Delete asset |
| GET | /api/portfolio/value | Get total portfolio value |
| GET | /api/portfolio/profit | Get total portfolio profit |

## API Examples

### Create Asset
```bash
curl -X POST http://localhost:8080/api/portfolio \
  -H "Content-Type: application/json" \
  -d '{
    "ticker": "AAPL",
    "assetType": "STOCK",
    "quantity": 10,
    "purchasePrice": 150.00,
    "purchaseDate": "2024-01-15"
  }'
```

### Get All Assets
```bash
curl http://localhost:8080/api/portfolio/list
```

### Get Portfolio Value
```bash
curl http://localhost:8080/api/portfolio/value
```

### Get Portfolio Profit
```bash
curl http://localhost:8080/api/portfolio/profit
```

## Asset Types

- STOCK
- BOND
- CASH
- ETF
- CRYPTO
