# Spring Batch 5 Starter

**コピペで即完成! Spring Batch 5** 🚀

[![GitHub stars](https://img.shields.io/github/stars/kinto-technologies/SpringBoot3BatchStarter?style=social)](https://github.com/kinto-technologies/SpringBoot3BatchStarter/stargazers)
[![Build](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml/badge.svg)](https://github.com/kinto-technologies/SpringBoot3BatchStarter/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/kinto-technologies/SpringBoot3BatchStarter/branch/main/graph/badge.svg)](https://codecov.io/gh/kinto-technologies/SpringBoot3BatchStarter)
![Java 21](https://img.shields.io/badge/Java-21%2B-blue)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-CC2233.svg)](https://opensource.org/licenses/Apache-2.0)

Spring Boot 3 と Spring Batch 5 を使用した、すぐに使えるバッチ処理フレームワークです。DB-CSVデータ連携やスケルトンバッチなど、実用的なサンプルを提供します。

🇬🇧 英語版のREADMEは[こちら](README.md)

## 📋 前提条件
* JDK 21（Gradleのツールチェーンで自動ダウンロード）
* Docker Desktop
* Gradle 8.5+（gradlewを使用する場合は不要）


> 💡 JDK 21が必要ですが、Gradleのツールチェーン機能により、ローカルにインストールされていなくても自動的にダウンロードされます。

## 🚀 クイックスタート

### 1️⃣ スケルトンバッチを試す
```bash
# リポジトリのクローン
git clone https://github.com/kinto-technologies/SpringBoot3BatchStarter.git

# スケルトンバッチのビルドと実行
cd skeletonBatch
../gradlew
java -jar build/libs/skeletonBatch-*.jar
```

### 2️⃣ DBとCSVのバッチを試す

```bash
# MySQLコンテナの起動
cd ..
docker compose up -d

# ビルドと実行
cd dbAndCsvBatch
../gradlew

# DB→CSV出力の実行
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=DB_TO_CSV --spring.profiles.active=local

# CSV→DB登録の実行
java -jar build/libs/dbAndCsvBatch-*.jar --spring.batch.job.name=CSV_TO_DB --spring.profiles.active=local
```

---

## 📁 プロジェクト構造
```text
.
├── gradlew                # Gradleラッパー
├── settings.gradle
├── compose.yaml           # Docker Compose設定
├── init-scripts           # DB初期化スクリプト
│   ├── 1-create-table.sql
│   └── 2-insert-data.sql
├── dbAndCsvBatch          # DB-CSVバッチ
│   ├── README.md
│   ├── build.gradle
│   └── src/
└── skeletonBatch          # スケルトンバッチ
    ├── README.md
    ├── build.gradle
    └── src/
```

## 💡 主な機能

### 🏗️ 基盤機能
- **本番稼働可能なバッチフレームワーク**：
    - すぐに開発を始められる構成
    - 必要最小限の設定で動作
    - H2インメモリDBによる簡単セットアップ

### 🔄 データ操作
- **DB→CSV出力**：
    - 動的クエリのサポート
    - 柔軟なデータ抽出設定
- **CSV→DB登録**：
    - バルク操作による一括登録
    - 高性能なデータローディング

### 🛠️ 技術スタック
- **Spring連携**：
    - Spring Batchによるジョブ/ステップ管理
    - jOOQによる型安全なSQL実行
    - OpenCSVによるCSV処理
- **デュアルデータベース設定**：
    - バッチ管理用H2インメモリDB
    - 業務データ用MySQL

### 💻 開発者向け機能
- **動的設定**：
    - 環境別プロファイル（local/server）
    - 実行時設定可能なジョブ実行
    - クエリのカスタマイズ対応

### 🔍 品質管理
- Google Java Format（Spotless）
- 静的解析（SpotBugs）
- テストカバレッジ（Jacoco）
- GitHub Actions CIパイプライン

## 📌 バージョン情報
* Spring Boot: 3.4.1
* Spring Dependency Management: 1.1.7
* Spotless (Google Java Format): 6.22.0
* jOOQ: 9.0
* OpenCSV: 5.9
* SpotBugs: 6.0.27

## ❓ よくある問題と解決方法

### Entityクラスが見つからない
- **原因**：jOOQの自動生成が未実行
- **解決**：`../gradlew generateJooq` を実行
- **または**：`build/generated-src/jooq` を `src/main/java` にコピー

### データベース接続エラー
- **原因**：MySQLコンテナが未起動
- **解決**：`docker compose up -d` を実行
- **確認**：`docker ps` でコンテナの状態を確認

### 複数ジョブ存在エラー
- **症状**：「Job name must be specified」エラー
- **原因**：実行するジョブ名の指定が必要
- **解決**：`--spring.batch.job.name=DB_TO_CSV` を指定

## 🔄 継続的インテグレーション

このプロジェクトでは以下のCI/CDツールを活用しています：

### GitHub Actions
- 自動ビルドとテスト
- コードフォーマットチェック（Google Java Format）
- 静的解析（SpotBugs）
- テストカバレッジ計測（Jacoco）

### Codecov
- テストカバレッジの可視化
- プルリクエスト時のカバレッジレポート自動生成
- カバレッジ変更の追跡

### MySQL CI環境
- GitHub Actionsでの自動セットアップ
- テスト用データベースの構築
- jOOQによるエンティティ自動生成の検証

### ワークフロー
1. プッシュまたはプルリクエスト時に自動実行
2. MySQLコンテナのセットアップ
3. JDK 21環境の構築
4. Gradleによるビルドとテスト
5. カバレッジレポートの生成とアップロード

## 📄 ライセンス
Apache License 2.0  
Copyright © 2024 KINTO Technologies Corporation