package com.example.batch.service;

import com.example.batch.enums.BatchResult;
import com.example.batch.jooq.tables.records.MemberRecord;
import com.example.batch.repository.MemberRepository;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DbToCsvService {
  private final MemberRepository memberRepository;

  /**
   * DBのレコードをCSVに出力する
   *
   * <p>Retrieve data from the database and export it to a CSV file.
   */
  public BatchResult execute(List<Byte> types, String filePath) throws Exception {

    byte deleteFlag = 0;
    List<MemberRecord> memberEntityList =
        memberRepository.findMembersByTypeAndDeleteFlag(types, deleteFlag);

    if (!memberEntityList.isEmpty()) {
      writeCsv(memberEntityList, filePath);
    } else {
      log.warn("No data found matching the criteria.");
      return BatchResult.NODATA;
    }

    return BatchResult.SUCCESS;
  }

  private void writeCsv(List<MemberRecord> records, String filePath) throws IOException {
    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
      writer.writeNext(
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
      for (MemberRecord record : records) {
        writer.writeNext(
            new String[] {
              String.valueOf(record.getId()),
              String.valueOf(record.getType()),
              record.getName(),
              record.getEmail(),
              record.getPhone(),
              record.getAddress(),
              String.valueOf(record.getDeleteFlag()),
              String.valueOf(record.getCreatedAt()),
              String.valueOf(record.getUpdatedAt())
            });
      }
    }
  }
}
