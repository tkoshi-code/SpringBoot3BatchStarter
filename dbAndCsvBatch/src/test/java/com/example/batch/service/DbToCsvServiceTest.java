package com.example.batch.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.batch.enums.BatchResult;
import com.example.batch.jooq.tables.records.MemberRecord;
import com.example.batch.repository.MemberRepository;
import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Slf4j
class DbToCsvServiceTest {

  @Mock private MemberRepository memberRepository;

  @InjectMocks private DbToCsvService dbToCsvService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  String filePath = "members.csv";

  @Test
  void testExecute_Success() throws Exception {
    List<MemberRecord> mockData =
        List.of(
            createMockMember(
                1,
                (byte) 1,
                "John Doe",
                "john@example.com",
                "1234567890",
                "123 Main St, City, Country",
                (byte) 0),
            createMockMember(
                2,
                (byte) 2,
                "Jane Smith",
                "jane@example.com",
                "0987654321",
                "456 Oak St, Town, Country",
                (byte) 0),
            createMockMember(
                3,
                (byte) 3,
                "Alice Premium",
                "alice@example.com",
                "5678901234",
                "789 Pine St, Village, Country",
                (byte) 0));

    when(memberRepository.selectByTypeAndDeleteFlag(anyList(), eq((byte) 0))).thenReturn(mockData);

    BatchResult result = dbToCsvService.execute(List.of((byte) 1, (byte) 2, (byte) 3), filePath);

    assertThat(result).isEqualTo(BatchResult.SUCCESS);
    verify(memberRepository, times(1)).selectByTypeAndDeleteFlag(anyList(), eq((byte) 0));

    File csvFile = new File("members.csv");
    assertThat(csvFile.exists()).isTrue();
    assertThat(csvFile.length()).isGreaterThan(0);

    csvFile.delete();
  }

  @Test
  void testExecute_NoData() throws Exception {
    when(memberRepository.selectByTypeAndDeleteFlag(anyList(), eq((byte) 0))).thenReturn(List.of());

    BatchResult result = dbToCsvService.execute(List.of((byte) 1, (byte) 2, (byte) 3), filePath);

    assertThat(result).isEqualTo(BatchResult.NODATA);
    verify(memberRepository, times(1)).selectByTypeAndDeleteFlag(anyList(), eq((byte) 0));
  }

  private MemberRecord createMockMember(
      int id, byte type, String name, String email, String phone, String address, byte deleteFlag) {
    MemberRecord member = new MemberRecord();
    member.setId(id);
    member.setType(type);
    member.setName(name);
    member.setEmail(email);
    member.setPhone(phone);
    member.setAddress(address);
    member.setDeleteFlag(deleteFlag);
    member.setCreatedAt(java.time.LocalDateTime.now());
    member.setUpdatedAt(java.time.LocalDateTime.now());
    return member;
  }
}
