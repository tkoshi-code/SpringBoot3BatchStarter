package com.example.batch.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.batch.jooq.tables.records.MemberRecord;
import java.util.List;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(
    properties = {
      "spring.batch.job.enabled=false",
      "spring.datasource.mysqlmain.url=jdbc:mysql://localhost:3306/sampledb",
      "spring.datasource.mysqlmain.username=sampleuser",
      "spring.datasource.mysqlmain.password=samplepassword"
    })
@SpringBootTest
class MemberRepositoryTest {

  @Autowired private DSLContext dslContext;

  private MemberRepository repository;

  @BeforeEach
  void setup() {
    repository = new MemberRepository(dslContext);
    cleanupRecordsByType((byte) 100);
  }

  @Test
  void testInsertAndSelect() {
    // Insert
    MemberRecord member =
        createMemberRecord("Test1", "test1@example.com", "1234567890", "Address 1", (byte) 100);
    repository.insert(member);

    // Select & Verify
    List<MemberRecord> selected =
        repository.selectByTypeAndDeleteFlag(List.of((byte) 100), (byte) 0);
    assertFalse(selected.isEmpty(), "Expected to find at least one record.");
    assertEquals("Test1", selected.get(0).getName(), "Member name mismatch.");
  }

  @Test
  void testUpdate() {
    // Insert
    MemberRecord member =
        createMemberRecord("Test1", "test1@example.com", "1234567890", "Address 1", (byte) 100);
    repository.insert(member);

    // Update
    List<MemberRecord> selected =
        repository.selectByTypeAndDeleteFlag(List.of((byte) 100), (byte) 0);
    member.setId(selected.get(0).getId());
    member.setName("Updated");
    repository.update(member);

    // Verify
    selected = repository.selectByTypeAndDeleteFlag(List.of((byte) 100), (byte) 0);
    assertEquals("Updated", selected.get(0).getName(), "Updated member name mismatch.");
  }

  @Test
  void testDelete() {
    // Insert
    MemberRecord member =
        createMemberRecord("Test1", "test1@example.com", "1234567890", "Address 1", (byte) 100);
    repository.insert(member);

    // Delete
    List<MemberRecord> selected =
        repository.selectByTypeAndDeleteFlag(List.of((byte) 100), (byte) 0);
    repository.delete(selected.get(0).getId());

    // Verify
    selected = repository.selectByTypeAndDeleteFlag(List.of((byte) 100), (byte) 0);
    assertTrue(selected.isEmpty(), "Expected no records after deletion.");
  }

  @Test
  void testBulkInsert() {
    // Prepare test data
    List<MemberRecord> members =
        List.of(
            createMemberRecord("Test1", "test1@example.com", "1234567890", "Address 1", (byte) 100),
            createMemberRecord(
                "Test2", "test2@example.com", "0987654321", "Address 2", (byte) 100));

    // Execute bulkInsert
    repository.bulkInsert(members);

    // Verify inserted data
    List<MemberRecord> selected =
        repository.selectByTypeAndDeleteFlag(List.of((byte) 100), (byte) 0);
    assertEquals(2, selected.size(), "Expected 2 records to be inserted.");

    assertEquals("Test1", selected.get(0).getName(), "First record name mismatch.");
    assertEquals("Test2", selected.get(1).getName(), "Second record name mismatch.");
    assertEquals("test1@example.com", selected.get(0).getEmail(), "First record email mismatch.");
    assertEquals("test2@example.com", selected.get(1).getEmail(), "Second record email mismatch.");

    // Clean up inserted records
    selected.forEach(record -> repository.delete(record.getId()));
  }

  private void cleanupRecordsByType(byte type) {
    List<MemberRecord> existingRecords =
        repository.selectByTypeAndDeleteFlag(List.of(type), (byte) 0);
    existingRecords.forEach(record -> repository.delete(record.getId()));
  }

  private MemberRecord createMemberRecord(
      String name, String email, String phone, String address, byte type) {
    MemberRecord record = new MemberRecord();
    record.setName(name);
    record.setEmail(email);
    record.setPhone(phone);
    record.setAddress(address);
    record.setType(type);
    return record;
  }
}
