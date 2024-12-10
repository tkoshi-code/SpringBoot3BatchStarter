package com.example.batch.repository;

import static com.example.batch.jooq.tables.Member.MEMBER;

import com.example.batch.jooq.tables.records.MemberRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Slf4j
@Repository
public class MemberRepository {
  private final DSLContext dslContext;

  public List<MemberRecord> findMembersByTypeAndDeleteFlag(List<Byte> types, Byte deleteFlag) {
    log.info("Fetching members with types = {}", types);

    return dslContext
        .selectFrom(MEMBER)
        .where(MEMBER.DELETE_FLAG.eq(deleteFlag))
        .and(MEMBER.TYPE.in(types))
        .orderBy(MEMBER.TYPE)
        .fetchInto(MemberRecord.class);
  }

  public void bulkInsert(List<MemberRecord> memberRecords) {
    var insertStep =
        dslContext.insertInto(
            MEMBER, MEMBER.NAME, MEMBER.EMAIL, MEMBER.PHONE, MEMBER.ADDRESS, MEMBER.TYPE);

    memberRecords.forEach(
        member ->
            insertStep.values(
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAddress(),
                member.getType()));

    insertStep.execute();
  }
}
