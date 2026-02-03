package com.mealgo.member.domain.entity;

import com.mealgo.common.converter.MemberConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.mealgo.common.code.MemberEnum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memNo;

    @Column(nullable = false, unique = true)
    private String memId;

    @Column(nullable = false)
    private String memPw;

    @Column(nullable = false)
    private String memNm;

    @Convert(converter = MemberConverter.class)
    private MemberEnum memStat;

    private LocalDateTime memRegDt;

    private LocalDateTime memUpDt;

    private String memAuth;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> memAuth);
    }


    @Override
    public String getUsername() {
        return memId;
    }

    @Override
    public String getPassword() {
        return memPw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
