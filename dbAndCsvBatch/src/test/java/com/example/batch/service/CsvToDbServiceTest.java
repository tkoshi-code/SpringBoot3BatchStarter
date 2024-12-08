package com.example.batch.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.batch.enums.BatchResult;
import com.example.batch.repository.MemberRepository;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CsvToDbServiceTest {

  @InjectMocks private CsvToDbService csvToDbService;

  @Mock private MemberRepository memberRepository;

  @TempDir Path tempDir; // JUnitが一時ディレクトリを提供

  private File tempCsvFile;

  @BeforeEach
  void setUp() throws IOException {
    tempCsvFile = tempDir.resolve("members.csv").toFile();
    log.info("Temporary CSV file created at: {}", tempCsvFile.getAbsolutePath());

    // テスト用のCSVファイルを作成
    try (FileWriter fileWriter = new FileWriter(tempCsvFile);
        CSVWriter csvWriter = new CSVWriter(fileWriter)) {

      // ヘッダー行を追加
      csvWriter.writeNext(
          new String[] {
            "id",
            "type",
            "name",
            "email",
            "phone",
            "address",
            "deleteFlag",
            "createdAt",
            "updatedAt"
          });

      // データ行を追加
      csvWriter.writeNext(
          new String[] {
            "1",
            "1",
            "John Doe",
            "john@example.com",
            "1234567890",
            "123 Main St",
            "0",
            "2024-12-07T09:46:07",
            "2024-12-07T09:46:07"
          });
    }
    log.info("Temporary CSV file content written successfully.");
  }

  @Test
  void testExecuteSuccess() throws Exception {
    // テスト実行
    BatchResult result = csvToDbService.execute(tempCsvFile.getAbsolutePath());

    // 検証
    assertThat(result).isEqualTo(BatchResult.SUCCESS);
    Mockito.verify(memberRepository, Mockito.times(1)).bulkInsert(Mockito.anyList());
  }

  @Test
  void testExecuteNoData() throws Exception {
    // 空のCSVファイルを作成
    File emptyCsvFile = tempDir.resolve("empty.csv").toFile();
    try (FileWriter fileWriter = new FileWriter(emptyCsvFile);
        CSVWriter csvWriter = new CSVWriter(fileWriter)) {
      csvWriter.writeNext(
          new String[] {
            "id",
            "type",
            "name",
            "email",
            "phone",
            "address",
            "deleteFlag",
            "createdAt",
            "updatedAt"
          }); // ヘッダーのみ
    }

    // テスト実行
    BatchResult result = csvToDbService.execute(emptyCsvFile.getAbsolutePath());

    // 検証
    assertThat(result).isEqualTo(BatchResult.NODATA);
    Mockito.verify(memberRepository, Mockito.never()).bulkInsert(Mockito.anyList());
  }
}
