
# starshipw2m
### A Spring Rest API for CRUD operations on starships

<br>

### Check open api documentation at: 
http://localhost:8091/swagger-ui/index.html

<br>

### Test coverage with sonar
#### 1. docker run -d --name sonar -p 9000:9000 sonarqube:lts
#### 2. mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin

<br>

### Connect Broker Rabbit
#### 1. docker-compose up -d
#### 2. check console at http://localhost:15672/ (user = guest, password = guest)

<br>

### Docker

<br>

#### Option 1: Start rabbit with docker compose && create starshipw2mdocker image manually
#### 1. move to infra/rabbitmq/ and run docker-compose up -d
#### 2. move again to root and run mvn clean package 
#### 3. docker build -t starshipw2m .
#### 4. docker run -p 8091:8091 starshipw2m

<br>

#### Option 2: build jar && run docker compose
#### 1. mvn clean package
#### 2. docker-compose up

<br>

### Start application
#### 3. mvn spring-boot:run
