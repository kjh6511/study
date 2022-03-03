package com.masjjim.member.mapper;

import com.masjjim.member.domain.entity.Member;
import com.masjjim.member.domain.entity.MemberStore;
import com.masjjim.member.domain.response.ResMember;

public interface MemberMapper {

    void createMember(Member member);

    int checkMemId(String memId);

    ResMember readMemberByMemId(String memId);

    ResMember readMember(Integer memNo);

    void createMemberStore(MemberStore memberStore);
}
