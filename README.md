Spring Batch3 Starter - Accelerate Your Spring Boot 3 Batch Development🚀
=============================
[![Github](https://img.shields.io/github/stars/KTC-YoheiMiyashita/SpringBoot3BatchStarter?logo=github&style=flat)](https://github.com/KTC-YoheiMiyashita/SpringBoot3BatchStarter)
[![Actions Status](https://github.com/KTC-YoheiMiyashita/SpringBoot3BatchStarter/workflows/Java%20CI/badge.svg)](https://github.com/KTC-YoheiMiyashita/SpringBoot3BatchStarter/actions)
![Java 21](https://img.shields.io/badge/Java-21%2B-blue)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

# SpringBoot3BatchStarter

## Overview / 概要

This repository serves as a Spring Batch Starter Kit, specifically tailored for Spring Boot 3 and Spring Batch 5. It streamlines batch job development by leveraging the latest features and ensuring full compatibility with Spring Boot 3. Whether you’re upgrading from Spring Boot 2 or starting a new project, this starter kit enables you to harness the enhanced capabilities of Spring Batch with ease.

このリポジトリは、Spring Boot 3 と Spring Batch 5 の最新機能を活用して、バッチ処理開発を効率化するためのスターターキットです。動作する事が保証されたテンプレート上に業務ロジックを追加するだけで、簡単にバッチ処理を構築できます。Spring Boot 2 からの移行や新規プロジェクトにおいて、すぐに最新のバッチフレームワークを活用できます。

### Key Highlights
- **Skeleton Batch Framework**: Quickly develop custom batch jobs with minimal setup.
- **DB to CSV Batch**: Export data from MySQL to CSV files seamlessly, with runtime arguments for dynamic WHERE clauses and flexible output configurations.
- **CSV to DB Batch**: Import CSV data into MySQL efficiently with bulk operations and error handling.

### 主な特徴
- **スケルトンバッチ**: 業務ロジックを追加するだけでバッチを簡単に構築する事が可能です。
- **DB to CSVバッチ**: MySQLからCSVファイルへデータを出力します。where句を実行時引数で指定する事が可能です。
- **CSV to DBバッチ**: CSVデータをMySQLにバルクで登録します。

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

## 🚀 Getting Started / はじめに
### Prerequisites
- Java 21+ for Spring Boot 3.
- Docker for setting up the MySQL environment.

### 1.	Clone the repository
```bash
git clone https://github.com/kinto-technologies/SpringBoot3BatchStarter.git
```

### 2. Build the skeleton batch
```bash
cd skeletonBatch
../gradlew
```

### 3. Run the skeleton batch
```bash
java -jar build/libs/skeletonBatch-*.jar
```

### 4. Set up the MySQL database for DB and CSV batch
```bash
docker compose up -d
```

### 5. Build the DB and CSV batch jobs
```bash
cd ../dbAndCsvBatch
../gradlew
```

### 6. Run Db to CSV Batch
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local
```

### 7. Run CSV to DB Batch
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local
```

#### With this Spring Batch Starter Kit, you can focus on your business logic while the framework handles the heavy lifting. Happy coding! 🎉
