# Realtime-statistics

    This project is Based on Spring Boot. I have ensured that Time complexity & Space Complexity of these API is O(1) 
    and APIs are thread safe.
    

The main use case for our API is to calculate realtime statistic from the last 60 seconds. There will be two APIs, one of them is
called every time a transaction is made. It is also the sole input of this rest API. The other one returns the statistic
based of the transactions of the last 60 seconds.

# API Description

POST /transactions

Every Time a new transaction happened, this endpoint will be called.

Body:

    {
  
      "amount": 12.3,

      "timestamp": 1478192204000
    }

Where:

    ● amount - transaction amount

    ● timestamp - transaction time in epoch in millis in UTC time zone (this is not current timestamp)


Returns: Empty body with either 201 or 204.

    ● 201 - in case of success
  
    ● 204 - if transaction is older than 60 seconds


Where:

    ● amount is a double specifying the amount
    ● time is a long specifying unix time format in milliseconds

GET /statistics

    This is the main endpoint of this task, this endpoint have to execute in constant time and
    memory (O(1)). It returns the statistic based on the transactions which happened in the last 60 seconds.
    
    returns
    
    {
      "sum": 1000,
      "avg": 100,
      "max": 200,
      "min": 50,
      "count": 10
    }
    
# Usage:

    1. Maven Build:
      
      mvn clean install
    
    2. To Start the application"
    
      mvn spring-boot:run
  
