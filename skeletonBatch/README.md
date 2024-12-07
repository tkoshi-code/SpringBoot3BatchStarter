# Skeleton Batch - Spring Batch Template

## Overview / 概要
The `skeletonBatch` module provides a template for quickly creating custom batch jobs using **Spring Batch**.  
This module includes placeholder logic and services, which can be replaced with your own business logic to implement batch processing efficiently.

`skeletonBatch` モジュールは、**Spring Batch** を使用してカスタムバッチジョブを迅速に作成するためのテンプレートを提供します。  
業務ロジックやサービスのプレースホルダーが含まれており、これらを置き換えることで効率的にバッチ処理を実装できます。

---

## How to Run / 実行方法
```bash
cd skeletonBatch

# 実行可能JARを作成するように、default taskを設定している  
# Generate the JAR file by executing the default task.  
../gradlew

# spring boot jarファイルが生成されていることを確認  
# Verify that the Spring Boot JAR file has been generated  
ls -ls build/libs/skeletonBatch-*.jar

## スケルトンのバッチを起動  
## Run the batch job to skeleton  
java -jar build/libs/skeletonBatch-*.jar
```
