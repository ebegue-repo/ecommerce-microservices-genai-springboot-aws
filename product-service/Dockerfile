# Use Eclipse Temurin JDK 24 (when available) - fallback to 21
FROM eclipse-temurin:21-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY product-service/pom.xml ./product-service/

# Copy source code
COPY product-service/src ./product-service/src

# Build application
RUN ./mvnw clean package -DskipTests -f product-service/pom.xml

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

# Create application user
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Set working directory
WORKDIR /app

# Copy built application
COPY --from=build /app/product-service/target/product-service-*.jar app.jar

# Change ownership to application user
RUN chown -R appuser:appgroup /app

# Switch to application user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]