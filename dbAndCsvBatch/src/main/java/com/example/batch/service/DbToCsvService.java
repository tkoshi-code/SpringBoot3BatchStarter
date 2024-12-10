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
   * データベースからデータを取得してCSVに出力する
   *
   * <p>Retrieve data from the database and export it to a CSV file.
   */
  public BatchResult execute(List<Byte> types) throws Exception {

    byte deleteFlag = 0;
    List<MemberRecord> memberEntityList =
        memberRepository.findMembersByTypeAndDeleteFlag(types, deleteFlag);

    if (!memberEntityList.isEmpty()) {
      String csvFileName = "members.csv";
      writeCsv(memberEntityList, csvFileName);
    } else {
      log.warn("No data found matching the criteria.");
      return BatchResult.NODATA;
    }

    return BatchResult.SUCCESS;
  }

  private void writeCsv(List<MemberRecord> members, String filePath) throws IOException {
    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
      String[] header = {
        "id", "type", "name", "email", "phone", "address", "deleteFlag", "createdAt", "updatedAt"
      };
      writer.writeNext(header);

      for (MemberRecord member : members) {
        writer.writeNext(
            new String[] {
              String.valueOf(member.getId()),
              String.valueOf(member.getType()),
              member.getName(),
              member.getEmail(),
              member.getPhone(),
              member.getAddress(),
              String.valueOf(member.getDeleteFlag()),
              String.valueOf(member.getCreatedAt()),
              String.valueOf(member.getUpdatedAt())
            });
      }
    }
  }
}
