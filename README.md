# Spring Batch Starter - DB to CSV and CSV to DB Example

## Overview / 概要
This project provides sample batch jobs using **Spring Batch** to demonstrate:
- Exporting data from a database to a CSV file (DB to CSV).
- Importing data from a CSV file into a database (CSV to DB).
- A skeleton batch structure (`skeletonBatch`) for quickly implementing custom batch logic.

このプロジェクトでは、**Spring Batch** を使用して以下のバッチ処理のサンプルコードを提供します:
- データベースからCSVファイルを生成する (DB to CSV)。
- CSVファイルからデータベースにデータを登録する (CSV to DB)。
- 業務ロジックを記述するだけで簡単にバッチを構築できるスケルトン (`skeletonBatch`) を提供します。

内容に誤りや改善点があれば、GitHubでIssueを作成するかPull Requestを送ってください。

---

## Features / 特徴
- **Spring Batch** framework for job and step management.
- **MySQL** for database interactions.
- **OpenCSV** for CSV file handling.
- Supports both local and server environments through profile-specific configurations.
- A skeleton structure (`skeletonBatch`) for developing custom batch jobs efficiently.

- **Spring Batch** フレームワークを使用したジョブとステップ管理。
- **MySQL** を使用したデータベース操作。
- **OpenCSV** を使用したCSVファイル操作。
- ローカル環境とサーバー環境をプロファイルごとに設定してサポート。
- カスタムバッチジョブを効率的に開発できるスケルトン構造 (`skeletonBatch`) を提供。

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
