# Spring Batch 5 Starter

**コピペで即完成! Spring Batch 5** 🚀

[![GitHub stars](https://img.shields.io/github/stars/KTC-YoheiMiyashita/SpringBoot3BatchStarter?style=social)](https://github.com/KTC-YoheiMiyashita/SpringBoot3BatchStarter/stargazers)
[![Build](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml/badge.svg)](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml)
![Java 21](https://img.shields.io/badge/Java-21%2B-blue)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-CC2233.svg)](https://opensource.org/licenses/Apache-2.0)

## 🌐 言語サポート

🇬🇧 英語版のREADMEは[こちら](README.md)

## 🔍 概要

このリポジトリは**Spring Boot 3**と**Spring Batch 5**に特化した、エンタープライズ向けバッチ処理アプリケーションの基盤となるスターターキットです。業界のベストプラクティスを取り入れ、最新のSpringフレームワーク機能を活用してバッチ開発プロセスを加速します。

### ⭐ 主要機能

#### 🏗️ 基本コンポーネント
- **本番対応バッチフレームワーク**: 高速開発のための事前設定済み基本構造
- **データベース操作**:
  - 動的クエリ対応のDB-to-CSVエクスポート
  - バルク処理によるハイパフォーマンスなCSV-to-DBインポート
- **エンタープライズグレードのアーキテクチャ**: スケーラビリティとメンテナンス性を考慮した設計
 
### 🛠️ 技術的な特徴
- **Springフレームワークの活用**
  - 堅牢なSpring Batchのジョブとステップ管理
  - JOOQ ORMによる型安全なSQLクエリ
  - OpenCSVによるシームレスなCSV処理
- **デュアルデータベース設定**
  - バッチメタデータ用のH2インメモリデータベース
  - ビジネスデータ処理用のMySQL

### 💻 開発環境の特徴
- **動的設定**
  - 環境別プロファイル（ローカル/サーバー）
  - 実行時引数による実行するジョブの選択
  - 実行時引数による柔軟なクエリカスタマイズ
- **コード品質ツール**
  - Google Java Format（Spotless）による自動フォーマット
  - Spotbugsによる静的解析
  - Jacocoによるテストカバレッジレポート
  - パイプライン

### 🚢 DevOps対応
- コンテナ化: 使用準備完了のMySQL設定を含むDocker対応
- CI/CD統合: GitHub Actionsワークフロー搭載
- シンプルなビルドプロセス: 本番対応JARを生成するシングルコマンドビルド

---

## 📁 プロジェクト構造
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

## 🚀 クイックスタートガイド

### 1.	リポジトリのクローン
```bash
git clone https://github.com/kinto-technologies/SpringBoot3BatchStarter.git
```

### 2. スケルトンバッチのビルド
```bash
cd skeletonBatch
../gradlew
```

### 3. スケルトンバッチの実行
```bash
java -jar build/libs/skeletonBatch-*.jar
```

### 4. データベースの初期化
```bash
docker compose up -d
```

### 5. DB と CSV バッチジョブをビルドする
```bash
cd ../dbAndCsvBatch
../gradlew
```

### 6. DB to CSV バッチを実行する
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local
```

### 7. CSV to DB バッチを実行する
```bash
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local
```

> **注意**: 	データベースセットアップにはDockerのインストールが必要です。

## ♻️ カスタマイズ性
このスターターキットは簡単なカスタマイズを想定して設計されています。データベース設定とCSVマッピングを特定の要件に合わせて変更するだけで、すぐにビジネスデータの処理を開始できます。

## 📜 ライセンス

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)でライセンスされています

Copyright © 2024 KINTO Technologies Corporation
