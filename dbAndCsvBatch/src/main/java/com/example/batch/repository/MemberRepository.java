package com.example.batch.repository;

import static com.example.batch.jooq.tables.Member.MEMBER;

import com.example.batch.jooq.tables.records.MemberRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberRepository {
  private final DSLContext dslContext;

  public List<MemberRecord> selectByTypeAndDeleteFlag(List<Byte> types, Byte deleteFlag) {
    return dslContext
        .selectFrom(MEMBER)
        .where(MEMBER.DELETE_FLAG.eq(deleteFlag))
        .and(MEMBER.TYPE.in(types))
        .orderBy(MEMBER.TYPE)
        .fetchInto(MemberRecord.class);
  }

  public void insert(MemberRecord member) {
    dslContext
        .insertInto(MEMBER)
        .set(MEMBER.NAME, member.getName())
        .set(MEMBER.EMAIL, member.getEmail())
        .set(MEMBER.PHONE, member.getPhone())
        .set(MEMBER.ADDRESS, member.getAddress())
        .set(MEMBER.TYPE, member.getType())
        .execute();
  }

  public void update(MemberRecord member) {
    dslContext
        .update(MEMBER)
        .set(MEMBER.NAME, member.getName())
        .set(MEMBER.EMAIL, member.getEmail())
        .set(MEMBER.PHONE, member.getPhone())
        .set(MEMBER.ADDRESS, member.getAddress())
        .set(MEMBER.TYPE, member.getType())
        .where(MEMBER.ID.eq(member.getId()))
        .execute();
  }

  public void delete(Integer id) {
    dslContext.deleteFrom(MEMBER).where(MEMBER.ID.eq(id)).execute();
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
