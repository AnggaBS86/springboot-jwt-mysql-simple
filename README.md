# springboot-jwt-mysql-simple
Simple example of springboot jwt authentication with MySQL database


# Database and table setup
```
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` int DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `roles` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
```

# How to run?
1. `mvn clean install`
2. `mvn spring-boot:run`
3. using postman or others tools, open : `http://localhost:8088/hello-world`

# JWT auth example and some URL : 
- JWT Auth : `http://localhost:8088/auth`
- Job List : `http://localhost:8088/jobs`
- Job Details : `http://localhost:8088/jobs/{jobId}`

