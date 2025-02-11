
# starshipw2m
A Spring Rest API for CRUD operations on starships



### Test coverage with sonar
#### 1. docker run -d --name sonar -p 9000:9000 sonarqube:lts
#### 2. mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin


### Connect Broker Rabbit
#### 1. docker-compose up -d
#### 2. check console at http://localhost:15672/ (user = guest, password = guest) 
#### 3. mvn spring-boot:run
