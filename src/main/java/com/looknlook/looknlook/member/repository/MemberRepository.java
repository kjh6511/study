package com.looknlook.looknlook.member.repository;

import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>,MemberQueryRepository {

    @Query("SELECT m FROM Member m")
    List<ResMember> findAllList();

    Optional<Member> findByMemId(String memId);
}
