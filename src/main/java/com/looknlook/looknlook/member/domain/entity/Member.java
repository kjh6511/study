package com.looknlook.looknlook.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.looknlook.looknlook.common.codeEnum.CodeConverter;
import com.looknlook.looknlook.common.codeEnum.CodeStatus;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "member")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNo;

    private String memId;

    @JsonIgnore
    private String memPw;

    private String memName;

    private String memType;

    @Convert(converter = CodeConverter.class)
    @Column(name = "mem_stu")
    private CodeStatus memStu;

    private LocalDateTime regDt;

    private String auth;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Shop shop;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
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
