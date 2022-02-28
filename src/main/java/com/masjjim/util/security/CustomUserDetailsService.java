package com.masjjim.util.security;

import com.masjjim.member.domain.response.ResMember;
import com.masjjim.member.mapper.MemberMapper;
import com.masjjim.util.security.domain.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    //사용자상세조회
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("=== LOGIN ID : " + userName);

        ResMember resMember = memberMapper.readMemberByMemId(userName);

        return resMember == null ? null : new CustomUser(resMember);
    }
}
