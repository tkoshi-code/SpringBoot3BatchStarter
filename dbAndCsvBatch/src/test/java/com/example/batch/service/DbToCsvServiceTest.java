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

  @Test
  void testExecute_Success() throws Exception {
    // Arrange: モックデータの準備
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

    when(memberRepository.findByType(anyList(), eq((byte) 0))).thenReturn(mockData);

    // Act: メソッドの実行
    BatchResult result = dbToCsvService.execute();

    // Assert: 検証
    assertThat(result).isEqualTo(BatchResult.SUCCESS);
    verify(memberRepository, times(1)).findByType(anyList(), eq((byte) 0));

    // CSVファイルが生成されていることを確認
    File csvFile = new File("members.csv");
    assertThat(csvFile.exists()).isTrue();
    assertThat(csvFile.length()).isGreaterThan(0);

    // クリーンアップ
    csvFile.delete();
  }

  @Test
  void testExecute_NoData() throws Exception {
    // Arrange: モックデータを空に設定
    when(memberRepository.findByType(anyList(), eq((byte) 0))).thenReturn(List.of());

    // Act: メソッドの実行
    BatchResult result = dbToCsvService.execute();

    // Assert: 検証
    assertThat(result).isEqualTo(BatchResult.NODATA);
    verify(memberRepository, times(1)).findByType(anyList(), eq((byte) 0));
  }

  @Test
  void testExecute_Exception() {
    // Arrange: 例外をスローするモックの設定
    when(memberRepository.findByType(anyList(), eq((byte) 0)))
        .thenThrow(new RuntimeException("Database error"));

    // Act & Assert: メソッドが例外をスローすることを確認
    try {
      dbToCsvService.execute();
    } catch (Exception e) {
      assertThat(e).isInstanceOf(RuntimeException.class);
      assertThat(e.getMessage()).isEqualTo("Database error");
    }

    verify(memberRepository, times(1)).findByType(anyList(), eq((byte) 0));
  }

  // ヘルパーメソッド: モックデータを生成
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
