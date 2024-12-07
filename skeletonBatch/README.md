# Skeleton Batch - Spring Batch Template

## Overview / 概要
The skeletonBatch module is a simple and efficient template for creating custom batch jobs using Spring Batch.
By adding your own business logic, you can quickly complete a fully functional Spring Boot 3 batch application.

skeletonBatch モジュールは、Spring Batch を使用してカスタムバッチジョブを簡単かつ効率的に作成するためのテンプレートです。
業務ロジックを追加するだけで、完成されたSpring Boot 3バッチアプリケーションを素早く構築できます。

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
