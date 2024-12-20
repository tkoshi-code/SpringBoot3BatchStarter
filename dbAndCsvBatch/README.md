# dbAndCsvBatch - Database and CSV Batch Processing

This module provides practical batch processing samples for:

1. Exporting data from a database to a CSV file (DB to CSV).  
2. Importing data from a CSV file into a database (CSV to DB).  

## 🐳 Docker Setup
If Docker is not available, download it from the [official Docker website](https://www.docker.com/get-started). Follow the installation instructions for your operating system.

## How to Run
Steps:
1. Build and run the MySQL container:
```bash
docker compose down && docker compose build && docker compose up -d
```

2. Connect to the MySQL container and verify the setup:
```bash
docker exec -it mysql-container mysql -u sampleuser -psamplepassword sampledb

mysql> show databases;

mysql> show tables;  

mysql> SELECT * FROM member;  

mysql> SELECT * FROM member WHERE delete_flag = 0 AND type IN (1, 2, 3) ORDER BY type ASC;

mysql> exit;
```
## 💻 Step-by-Step Guide:
1. Generate the JAR file
Execute the default task to generate the Spring Boot JAR file:
```bash
cd dbAndCsvBatch
../gradlew
```
	
2. Verify Generated Files
Confirm that the entity classes and JAR file were successfully created:

```bash
# Check generated entity classes
ls -R build/generated-src/jooq

# Verify the generated Spring Boot JAR
ls -ls build/libs/dbAndCsvBatch-*.jar
```

3. Run Batch Jobs
### For Local Environment
- Export data from DB to CSV:
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local
```

- Run batch with custom runtime arguments:
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --batch.types=2,4 --spring.profiles.active=local
```

- Import data from CSV to DB:
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local
```


### For Server Environment
- Export data from DB to CSV:
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=server
```

- Import data from CSV to DB:
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=server
```
With this module, you can seamlessly integrate database and CSV operations into your Spring Boot batch applications. Happy coding! 🎉
