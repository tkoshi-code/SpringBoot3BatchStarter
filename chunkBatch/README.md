# Skeleton Batch - Spring Batch Template 🚀

## 🌟 Overview
The SkeletonBatch module is a streamlined template designed to help you create custom batch jobs effortlessly with Spring Batch. Simply add your business logic, and you'll have a fully operational Spring Boot 3 batch application in no time.

---

## 🔧 How to Run

### 1️⃣ Using Gradle (Recommended)
```bash
# Build and Run from project root
./gradlew :skeletonBatch:bootRun
```

### 2️⃣ Using JAR File
Useful for running with cron jobs or job schedulers. Generate and execute the JAR file as follows:

### Generate JAR
```bash
# Generate executable JAR
cd skeletonBatch
../gradlew  

# Check generated JAR
ls -l build/libs/
```
### Execute JAR
```bash
# Basic execution (requires JDK 21)
java -jar build/libs/batch-skeleton-*.jar

# With Java options (if needed)
java -Xms512m -Xmx1g -jar build/libs/batch-skeleton-*.jar
```

## ✨ Highlights
- Simple Setup: Pre-configured tasks for quick builds
- Fast Execution: Minimal effort to start your batch job
- Customizable: Extend the template with your business logic
- H2 Database: In-memory database for zero configuration

## 📁 Project Structure
```text
src/
├── main/
│   ├── java/
│   │   └── com/example/batch/
│   │       ├── BatchApp.java           # Application entry point
│   │       ├── job/
│   │       │   └── SampleJob.java      # Job configuration
│   │       ├── logic/
│   │       │   └── SampleLogic.java    # Business logic
│   │       └── service/
│   │           └── SampleService.java   # Service layer
│   └── resources/
│       └── application.yml             # Application configuration
└── test/                               # Test files
```
