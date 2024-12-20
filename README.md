# Spring Batch 5 Starter

**Accelerate Your Spring Boot 3 Batch Development** 🚀


[![GitHub stars](https://img.shields.io/github/stars/KTC-YoheiMiyashita/SpringBoot3BatchStarter?style=social)](https://github.com/KTC-YoheiMiyashita/SpringBoot3BatchStarter/stargazers)
[![Build](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml/badge.svg)](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml)
![Java 21](https://img.shields.io/badge/Java-21%2B-blue)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-CC2233.svg)](https://opensource.org/licenses/Apache-2.0)

## 🇯🇵 Japanese

The Japanese version of the README can be found [here](README.ja.md).

## 🔍 Overview

This repository serves as a **Spring Batch Starter Kit**, specifically tailored for **Spring Boot 3** and **Spring Batch 5**. It streamlines batch job development by leveraging the **latest features** and ensuring full compatibility with Spring Boot 3.  
Whether you’re upgrading from **Spring Boot 2** or starting a new project, this starter kit enables you to **harness the enhanced capabilities** of Spring Batch with ease.

### 📝 Key Highlights
- **Skeleton Batch Framework**: Quickly develop custom batch jobs with minimal setup.
- **DB to CSV Batch**: Export data from MySQL to CSV files seamlessly, with runtime arguments for dynamic WHERE clauses and flexible output configurations.
- **CSV to DB Batch**: Import CSV data into MySQL efficiently with bulk operations and error handling.

### ♻️ Reusability

This kit is highly adaptable. By simply updating the database connection settings and modifying the CSV column definitions to match your specific business needs, you can immediately start using it for your projects.

---



## 💡 Key Features

### Batch Development Made Simple
- **Spring Batch Framework**: Streamlined job and step management.
- **JOOQ ORM**: SQL-like query writing and entity generation.
- **OpenCSV Integration**: Hassle-free CSV file handling.
- **Multi-Database Support**:
  - H2 In-Memory Database: Used for batch metadata management.
  - MySQL Database: Used for business data processing.

### Flexibility and Optimization
- **Dynamic Configurations**: Environment-specific setups with profiles (local/server).
- **Dynamic Execution of Multiple Jobs**: Execute multiple jobs dynamically with runtime arguments.
- **Customizable Batches**: Modify WHERE clauses of queries inside batches dynamically based on runtime arguments.
- **Google Java Format**: Automated code formatting with Spotless.
- **Quality Checks and Coverage**: Static analysis with Spotbugs, test coverage generation with Jacoco, and CI tools for checks.

### Future-Proof Design
- **Skeleton Batch Framework**: A pre-configured template for quickly developing batch jobs.
- **Docker Environment Setup**: Provides a `compose.yaml` file to easily set up a local MySQL database.
- **CI/CD**: Achieve Continuous Integration and Continuous Delivery with Github Actions.

---

## 🗂️ Project Structure
```text
.
├── gradlew
├── settings.gradle
├── compose.yaml
├── init-scripts
│   ├── 1-create-table.sql
│   └── 2-insert-data.sql
├── dbAndCsvBatch
│   ├── README.md
│   ├── build.gradle
│   └── src
│       ├── main
│       └── test
└── skeletonBatch
    ├── README.md
    ├── build.gradle
    └── src
        ├── main
        └── test
```

## 🛠️ Getting Started

### 1.	Clone the repository
```bash
git clone https://github.com/kinto-technologies/SpringBoot3BatchStarter.git
```

### 2. Build the Skeleton Batch
```bash
cd skeletonBatch
../gradlew
```

### 3. Run the Skeleton Batch
```bash
java -jar build/libs/skeletonBatch-*.jar
```

### 4. Set up MySQL database (Docker)
```bash
docker compose up -d
```

### 5. Build the DB and CSV Batch jobs
```bash
cd ../dbAndCsvBatch
../gradlew
```

### 6. Run DB to CSV Batch
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local
```

### 7. Run CSV to DB Batch
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local
```

> **Note**: Ensure Docker is installed and running before executing step 4.

#### 🎉 **With this Spring Batch Starter Kit, you can focus on your business logic while the framework handles the heavy lifting.**
**Happy coding!** 🚀

## 📄 License

This project is licensed under the **[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)**.  
Copyright © 2024 KINTO Technologies Corporation