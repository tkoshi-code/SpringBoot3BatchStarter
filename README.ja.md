# Spring Batch 5 Starter

**コピペで即完成! Spring Batch 5** 🚀

[![GitHub stars](https://img.shields.io/github/stars/KTC-YoheiMiyashita/SpringBoot3BatchStarter?style=social)](https://github.com/KTC-YoheiMiyashita/SpringBoot3BatchStarter/stargazers)
[![Build](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml/badge.svg)](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml)
![Java 21](https://img.shields.io/badge/Java-21%2B-blue)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-CC2233.svg)](https://opensource.org/licenses/Apache-2.0)

## 🇬🇧 English

The English version of the README can be found [here](README.md).

## 🔍 概要

このリポジトリは、**Spring Boot 3** と **Spring Batch 5** のために特化した**Spring Batchスターターキット**です。  
最新機能を活用し、Spring Boot 3との完全な互換性を持つことで、バッチジョブ開発を効率化します。  
**Spring Boot 2** からの移行や新規プロジェクトで、**Spring Batchの強化された機能**を簡単に活用できます。

### 📝 主な特徴
- **スケルトンバッチフレームワーク**: 最小限の設定でカスタムバッチジョブを簡単に開発できます。
- **DB to CSV バッチ**: MySQLデータをCSVファイルにエクスポート。動的なWHERE句や柔軟な出力設定に対応しています。
- **CSV to DB バッチ**: CSVデータをMySQLに効率的にインポートします。バルク処理とエラーハンドリング機能を備えています。

### ♻️ 再利用性
データベース接続設定やCSVのカラム定義を業務要件に合わせて変更するだけで、すぐにプロジェクトに適用できます。

---



## 💡 特徴

### バッチ開発を簡単に
- **Spring Batch フレームワーク**: ジョブとステップの管理を効率化。
- **jooq ORM**: SQLライクなクエリ記述とエンティティ生成機能。
- **OpenCSV**: CSVファイルの簡単な取り扱い。
- **マルチデータベースサポート**:
  - H2 : バッチメタデータ管理にH2
  - MySQL : 業務データ処理にMySQL

### 柔軟性と最適化
- **動的な設定**: 環境ごとの設定（ローカル/サーバー）をプロファイルで管理。
- **複数ジョブの動的実行**: 実行時の引数で複数のジョブを動的に実行。
- **カスタマイズ可能なバッチ**: 実行時の引数により、バッチ内のクエリのWHERE句を動的に変更。
- **Google Java Format**: Spotlessを使用した自動コード整形。
- **品質チェックとカバレッジ**: Spotbugsによる静的解析、Jacocoによるテストカバレッジの生成、CIツールでのチェック。

### 将来に向けた設計
- **スケルトンバッチフレームワーク**: バッチジョブを迅速に開発できる事前設定されたテンプレート。
- **Docker環境設定**: ローカルのMySQLデータベースを簡単にセットアップできる`compose.yaml`ファイルを提供。
- **CI/CD**: Github Actions で 継続的インテグレーションと継続的デリバリーを実現。
- **デフォルトタスク**: Gradleのデフォルトタスクでビルド、テスト、品質チェックを一度に実行し、実行可能なSpring Boot JARファイルが生成されるため、開発プロセスを簡素化。Gradleの設定を変更する必要はありません。

---

## 🗂️ プロジェクト構成
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

## 🛠️ はじめに

### 1.	リポジトリをクローンする
```bash
git clone https://github.com/kinto-technologies/SpringBoot3BatchStarter.git
```

### 2. スケルトンバッチをビルドする
```bash
cd skeletonBatch
../gradlew
```

### 3. スケルトンバッチを実行する
```bash
java -jar build/libs/skeletonBatch-*.jar
```

### 4. MySQLデータベースを設定する（Docker）
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

> **注意**: 	ステップ4を実行する前にDockerがインストールされていて、実行中であることを確認してください。


#### 🎉 このSpring Batchスターターキットを使用すれば、すでに土台が構築されているので、ビジネスロジックに集中できます。
**楽しいコーディングを！** 🚀

## 📄 ライセンス

このプロジェクトは、**[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)** の下でライセンスされています。

Copyright © 2024 KINTO Technologies Corporation