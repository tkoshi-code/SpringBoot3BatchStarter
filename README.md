# Spring Batch Starter - Accelerate Your Batch Development

## Overview / 概要

This repository is a **comprehensive Spring Batch Starter Kit** designed to simplify batch job development using **Spring Batch** and **JOOQ**. Whether you’re a beginner or an experienced developer, this project provides everything you need to quickly implement and execute batch jobs.

### Key Highlights
- **Skeleton Batch Framework**: Quickly develop custom batch jobs with minimal setup.
- **DB to CSV Batch**: Export data from MySQL to CSV files seamlessly.
- **CSV to DB Batch**: Import CSV data into MySQL efficiently.

このリポジトリは、**Spring Batch** と **JOOQ** を活用したバッチ処理のスターターキットです。  
バッチ開発初心者から経験豊富なエンジニアまで、誰もが迅速にバッチジョブを作成して実行できるように設計されています。

### 主な特徴
- **スケルトンバッチ**: 業務ロジックを追加するだけでバッチを簡単に構築可能。
- **DB to CSVバッチ**: MySQLからCSVファイルへのデータ出力をサポート。
- **CSV to DBバッチ**: CSVデータをMySQLに効率的に登録可能。

---

## 💡 Key Features / 特徴

### 🚀 Batch Development Made Simple
- **Spring Batch Framework**: Streamlined job and step management.
- **JOOQ ORM**: SQL-like query writing and entity generation, eliminating boilerplate code.
- **OpenCSV Integration**: Hassle-free CSV file handling.
- **Multi-Database Support**: H2 for metadata management and MySQL for business data.

### ⚙️ Flexibility and Optimization
- **Dynamic Configurations**: Environment-specific setups with profiles (local/server).
- **Customizable Batches**: Execute multiple jobs within a single JAR by passing runtime arguments.
- **Google Java Format**: Automated code formatting with Spotless.

### 💼 Future-Proof Design
- **Skeleton Batch Framework**: A template for creating new batch jobs.
- **Pre-configured Docker Environment**: Quickly set up a local MySQL database with Docker Compose.

---

## 🗂️ Project Structure / プロジェクト構成
```plaintext
.
├── dbAndCsvBatch       # DB to CSV and CSV to DB batch jobs
│   ├── src
│   │   ├── main
│   │   └── test
│   ├── build.gradle    # Gradle configuration for dbAndCsvBatch module
│   ├── compose.yaml    # Docker Compose file for MySQL container
│   └── init-scripts    # SQL scripts to initialize the database
└── skeletonBatch       # A skeleton batch example for future extensions
    ├── src
    │   ├── main
    │   └── test
    ├── build.gradle    # Gradle configuration for skeletonBatch module
    └── README.md       # Detailed documentation for skeletonBatch
```

## 🚀 Getting Started / はじめに
Prerequisites
- Java 17+ for Spring Boot 3.
- Docker for setting up the MySQL environment.
- Gradle for building and running the project.

### 1.	Clone the repository
```shell
git clone https://github.com/kinto-technologies/SpringBoot3BatchStarter.git
```

### 2. Build the skeleton batch
```shell
cd skeletonBatch
../gradlew
```

### 3. Run the skeleton batch
```shell
java -jar build/libs/skeletonBatch-*.jar
```

### 4. Set up the MySQL database for DB and CSV batch
```shell
cd ../dbAndCsvBatch
docker compose up -d
```
### 5. Build the DB and CSV batch jobs
```shell
cd ../dbAndCsvBatch
../gradlew
```

### 6. Run Db to CSV Batch
```shell
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local
```

### 7. Run CSV to DB Batch
```shell
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local
```
