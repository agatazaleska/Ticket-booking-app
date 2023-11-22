#!/bin/bash

# find and move into the project directory
PROJECT_DIR="../Ticket-booking-spring-app"
cd "$PROJECT_DIR"

echo "Starting MySQL container..."
docker-compose up -d mysql

echo "Waiting for MySQL..."
sleep 10

echo "Starting spring application..."
./mvnw spring-boot:run

