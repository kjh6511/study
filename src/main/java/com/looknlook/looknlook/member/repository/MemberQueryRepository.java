package com.looknlook.looknlook.member.repository;

import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;

import java.util.List;

public interface MemberQueryRepository{
    List<ResMember> findAllMemberBy();

}
