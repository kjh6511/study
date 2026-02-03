package com.mealgo.member.repository;

import com.mealgo.member.domain.dto.ResponseMember;
import com.mealgo.member.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>, MemberRepositoryQuerydsl {

    Optional<Member> findByMemId(String memId);

    @Query(value = "SELECT new com.mealgo.member.domain.dto.ResponseMember(m.memNo AS memNo," +
            "m.memNm AS memNm, m.memId AS memId) FROM Member m",
            countQuery = "SELECT COUNT(m) FROM Member m")
    Page<ResponseMember> findAllPage(Pageable pageable);

    @Query(value = "SELECT new com.mealgo.member.domain.dto.ResponseMember(m.memNo AS memNo, " +
            "m.memId AS memId, m.memNm As memNm) FROM Member m")
    List<ResponseMember> findAllList();
}
