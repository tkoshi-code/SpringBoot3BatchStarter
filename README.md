Spring Batch Starter - DB to CSV and CSV to DB Example

# Overview / 概要

This project provides sample batch jobs using Spring Batch and JOOQ to demonstrate:
1.	A skeleton batch structure (skeletonBatch) for quickly implementing custom batch logic.
2.	Exporting data from a database to a CSV file (DB to CSV).
3.	Importing data from a CSV file into a database (CSV to DB).

このプロジェクトでは、Spring Batch と JOOQ を使用して以下のバッチ処理のサンプルコードを提供します:
1.	業務ロジックを記述するだけで簡単にバッチを構築できるスケルトン (skeletonBatch) を提供します。
2.	データベースからCSVファイルを生成する (DB to CSV)。
3.	CSVファイルからデータベースにデータを登録する (CSV to DB)。

---

# Key Features / 特徴
1.	Spring Batch framework for job and step management.
2.	JOOQ for database interaction and entity generation, avoiding boilerplate SQL.
3.	MySQL for business data operations.
4.	H2 in-memory database for Spring Batch metadata management.
5.	OpenCSV for CSV file handling.
6.	Profile-specific configurations for local and server environments.
7.	A skeleton structure (skeletonBatch) for rapid custom batch development.

# Key Features / 特徴 (日本語)
1.	Spring Batch フレームワークを使用したジョブとステップ管理。
2.	JOOQ を使用してエンティティ生成とデータベース操作を簡素化。
3.	MySQL を業務データ操作用に使用。
4.	H2インメモリデータベース をSpring Batchメタデータ管理用に使用。
5.	OpenCSV を使用したCSVファイル操作。
6.	ローカル環境とサーバー環境をプロファイルごとに設定。
7.	カスタムバッチを迅速に開発するためのスケルトン構造 (skeletonBatch) を提供。

---

Multi-Database Setup / マルチデータベース設定

The application uses the following databases:
1.	H2 in-memory database for Spring Batch metadata (e.g., job executions, step executions).
2.	MySQL for handling business data.

アプリケーションでは、次のデータベースを使用します:
1.	H2インメモリデータベース をSpring Batchメタデータ管理用に使用 (例: ジョブ実行、ステップ実行)。
2.	MySQL を業務データ処理用に使用。

---

## Project Structure / プロジェクト構成
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
