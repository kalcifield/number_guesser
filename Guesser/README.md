# Guesser service documentation

### Prerequisites
you will need a postgresql database (localhost:5432/guesser)
create a table called "guesser"
you shall modify application.yml 
if your credentials are not on default postgres:postgres

### In order to start the service in the terminal:
cd to this folder
run ./gradlew bootRun
    
### The usage of the api
The api will be accessible on port 8080 by default
You can call /api/guess/start to obtain a sessionId
Then call /api/guess/{sessionId}/{number}
where number is your guess 