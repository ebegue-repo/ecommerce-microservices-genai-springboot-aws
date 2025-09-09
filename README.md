# E-commerce Microservices Platform

A modern e-commerce web application implemented using microservices architecture, designed for deployment on AWS EKS (Elastic Kubernetes Service). This platform demonstrates best practices for cloud-native development using Java, Spring Boot, and containerization technologies.

## Architecture Overview

This e-commerce platform follows a **microservices architecture pattern** where the application is decomposed into small, independently deployable services. Each service is responsible for a specific business domain and communicates with others through well-defined APIs.

### Microservices Components

- **Product Service** - Manages product catalog, inventory, and product-related operations
- **User Service** - Handles user authentication, authorization, and profile management (planned)
- **Order Service** - Processes customer orders and order lifecycle management (planned)
- **Gateway Service** - API Gateway for routing, load balancing, and cross-cutting concerns (planned)

### Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17+ (configured for 24) | Programming language |
| **Spring Boot** | 3.3.6 | Application framework |
| **Spring Security** | Latest | Authentication & authorization |
| **Spring Data JPA** | Latest | Data persistence layer |
| **Maven** | 3.9.x | Build tool and dependency management |
| **Docker** | Latest | Containerization |
| **Kubernetes** | Latest | Container orchestration |
| **AWS EKS** | Latest | Managed Kubernetes service |
| **PostgreSQL** | 16+ | Production database |
| **H2** | Latest | Development/testing database |

## Features

### ✅ Implemented
- **Product Service**: Complete CRUD operations for product management
- **Spring Security**: Authentication and role-based authorization
- **Data Persistence**: JPA with H2 (dev) and PostgreSQL (prod) support
- **RESTful APIs**: Well-designed REST endpoints with proper HTTP status codes
- **Docker**: Multi-stage Dockerfile for optimized container images
- **Health Checks**: Spring Boot Actuator for monitoring and health checks
- **Testing**: Unit tests with Spring Boot Test framework
- **CI/CD**: GitHub Actions for automated build, test, and deployment

### 🚧 Planned
- User Service implementation
- Order Service implementation
- API Gateway with Spring Cloud Gateway
- Service discovery and load balancing
- Distributed tracing and monitoring
- Event-driven communication between services

## Project Structure

```
ecommerce-microservices/
├── product-service/           # Product management microservice
│   ├── src/main/java/
│   │   └── com/ecommerce/product/
│   │       ├── controller/    # REST controllers
│   │       ├── service/       # Business logic
│   │       ├── repository/    # Data access layer
│   │       ├── model/         # Domain entities
│   │       └── config/        # Configuration classes
│   ├── src/main/resources/    # Application configuration
│   ├── src/test/             # Test classes
│   ├── Dockerfile            # Container configuration
│   └── pom.xml              # Maven dependencies
├── user-service/            # User management (placeholder)
├── order-service/           # Order management (placeholder)
├── gateway-service/         # API Gateway (placeholder)
├── k8s/                     # Kubernetes deployment manifests
│   ├── production/          # Production environment
│   └── staging/             # Staging environment
├── .github/workflows/       # CI/CD pipelines
├── docker-compose.yml       # Local development setup
└── pom.xml                 # Parent Maven configuration
```

## Quick Start

### Prerequisites

- Java 17+ (JDK)
- Maven 3.9+
- Docker
- Docker Compose (for local development)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/ebegue-repo/ecommerce-microservices-genai-springboot-aws.git
   cd ecommerce-microservices-genai-springboot-aws
   ```

2. **Build the application**
   ```bash
   ./mvnw clean package -f product-service/pom.xml
   ```

3. **Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

4. **Access the application**
   - Product Service API: http://localhost:8080/api/v1/products
   - H2 Database Console: http://localhost:8080/h2-console
   - Health Check: http://localhost:8080/actuator/health

### Running Individual Services

**Product Service:**
```bash
cd product-service
../mvnw spring-boot:run
```

## API Documentation

### Product Service Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/products` | Get all products | No |
| GET | `/api/v1/products/{id}` | Get product by ID | No |
| GET | `/api/v1/products/category/{category}` | Get products by category | No |
| GET | `/api/v1/products/search?name={name}` | Search products by name | No |
| GET | `/api/v1/products/available` | Get available products | No |
| POST | `/api/v1/products` | Create new product | Admin |
| PUT | `/api/v1/products/{id}` | Update product | Admin |
| DELETE | `/api/v1/products/{id}` | Delete product | Admin |

### Authentication

The application uses HTTP Basic Authentication with the following default users:

- **Admin User**: `admin/admin` (ROLE_ADMIN)
- **Regular User**: `user/user` (ROLE_USER)

## Deployment

### AWS EKS Deployment

The application is designed for deployment on AWS EKS using the provided Kubernetes manifests.

#### Prerequisites
- AWS CLI configured
- kubectl installed
- EKS cluster running
- ECR repositories created

#### Deploy to Production

1. **Build and push Docker images**
   ```bash
   # This is handled automatically by GitHub Actions
   # Manual deployment:
   docker build -t your-ecr-repo/product-service:latest -f product-service/Dockerfile .
   docker push your-ecr-repo/product-service:latest
   ```

2. **Deploy to Kubernetes**
   ```bash
   kubectl apply -f k8s/production/
   ```

#### Environment Configuration

- **Staging**: Deployed automatically on push to `develop` branch
- **Production**: Deployed automatically on push to `main` branch

## Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `default` |
| `DB_URL` | Database URL | `jdbc:h2:mem:productdb` |
| `DB_USERNAME` | Database username | `sa` |
| `DB_PASSWORD` | Database password | (empty) |

### Database Configuration

- **Development**: H2 in-memory database
- **Production**: PostgreSQL with connection pooling

## Monitoring and Observability

### Health Checks
- **Endpoint**: `/actuator/health`
- **Kubernetes**: Liveness and readiness probes configured
- **Docker**: HEALTHCHECK instruction included

### Metrics
- **Endpoint**: `/actuator/metrics`
- **Prometheus**: Compatible metrics format
- **Custom Metrics**: Application-specific business metrics

## Security

### Implementation
- **Spring Security**: Role-based access control
- **Authentication**: HTTP Basic (JWT planned)
- **Authorization**: Method-level security with `@PreAuthorize`
- **HTTPS**: SSL/TLS termination at load balancer
- **Secrets**: Kubernetes secrets for sensitive data

### Security Best Practices
- Non-root user in Docker containers
- Minimal base images (Alpine Linux)
- Regular dependency updates
- Environment-specific configurations

## Development

### Code Style
- **Formatting**: Standard Java conventions
- **Architecture**: Clean Architecture principles
- **Testing**: Unit and integration tests
- **Documentation**: JavaDoc for public APIs

### Adding New Services

1. Create new module directory
2. Add Maven module to parent `pom.xml`
3. Implement service following existing patterns
4. Add Dockerfile and Kubernetes manifests
5. Update CI/CD pipeline

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration and deployment:

- **Build**: Compile and package applications
- **Test**: Run unit and integration tests  
- **Security**: Dependency vulnerability scanning
- **Deploy**: Automated deployment to staging and production

## Support

For questions and support:
- Create an issue in the GitHub repository
- Review the documentation in the `/docs` folder
- Check the troubleshooting guide

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

*This e-commerce microservices platform demonstrates modern cloud-native development practices and serves as a foundation for scalable, production-ready applications.*
