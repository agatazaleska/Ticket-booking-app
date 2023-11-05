#!/bin/bash

# find and move into the project directory
PROJECT_DIR="../Ticket-booking-spring-app"
cd "$PROJECT_DIR"

# create maven package
./mvnw package

# move into the target directory
cd ./target

# finding the .jar file
jarfile=$(find . -maxdepth 1 -type f -name "*.jar" | head -n 1)

# running the app
if [[ -f "$jarfile" ]]; then
    echo "Running $jarfile..."
    java -jar "$jarfile"
else
    echo "Could not find the .jar file."
fi

# initializing the database

# filling the tables with data
