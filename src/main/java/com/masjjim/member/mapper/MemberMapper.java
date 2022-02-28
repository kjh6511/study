package com.masjjim.member.mapper;

import com.masjjim.member.domain.response.ResMember;

public interface MemberMapper {
    ResMember readMemberByMemId(String memId);
}
