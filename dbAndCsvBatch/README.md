# dbAndCsvBatch / データベースとCSVのバッチ処理

This module provides sample batch processing implementations for:  
このモジュールは以下のバッチ処理サンプルを提供します:
1. Exporting data from a database to a CSV file (DB to CSV).  
   データベースからCSVファイルを生成します (DB to CSV)。
2. Importing data from a CSV file into a database (CSV to DB).  
   CSVファイルからデータベースにデータを登録します (CSV to DB)。

## Docker
Note: If you cannot use Docker, please download and install Docker from Docker's official website.
Follow the instructions provided for your operating system.

注釈: Dockerコマンドが使用できない場合は、[Dockerの公式サイト](https://www.docker.com/get-started)からダウンロードしてインストールしてください。

## How to Run / 実行方法
```bash
cd dbAndCsvBatch

## Local環境にMySQLコンテナを作成する  
## Create a MySQL container for the local environment  
docker compose down && docker compose build && docker compose up -d

## MySQLコンテナに接続して、テーブルとレコードの確認  
## Connect to the MySQL container and check tables and records  
docker exec -it mysql-container mysql -u sampleuser -psamplepassword sampledb

mysql> show tables;  
mysql> SELECT * FROM member;  
mysql> SELECT * FROM member WHERE delete_flag = 0 AND type IN (1, 2, 3);

# DBからEntityクラスを生成した後に実行可能JARを作成するように、default taskを設定している  
# Generate the JAR file by executing the default task after creating entity classes from the database  
../gradlew

# EntityクラスがDBから自動生成された事を確認
# Verify that the Entity classes were generated from the database
ls -R build/generated-src/jooq

# spring boot jarファイルが生成されていることを確認  
# Verify that the Spring Boot JAR file has been generated  
ls -ls build/libs/dbAndCsvBatch-*.jar

## for local  
## DBからCSVファイルを生成するバッチを起動  
## Run the batch job to generate a CSV file from the database  
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local

## CSVファイルからDBに登録するバッチを起動  
## Run the batch job to import data from a CSV file into the database  
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local

## for server  
## DBからCSVファイルを生成するバッチを起動  
## Run the batch job to generate a CSV file from the database  
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=server

## CSVファイルからDBに登録するバッチを起動  
## Run the batch job to import data from a CSV file into the database  
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=server
```
