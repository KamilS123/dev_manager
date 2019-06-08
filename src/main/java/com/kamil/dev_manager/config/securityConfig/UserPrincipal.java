package com.kamil.dev_manager.config.securityConfig;

import com.kamil.dev_manager.entity.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Student student;

    public UserPrincipal(Student student) {
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*return student.getRolesList().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());*/
       List<GrantedAuthority> authorities = new ArrayList<>();
        this.student.getRolesList().forEach(r->{
            GrantedAuthority authority = new SimpleGrantedAuthority(r);
            authorities.add(authority);
        });
        this.student.getPermisionsList().forEach(r->{
            GrantedAuthority authorityPermit = new SimpleGrantedAuthority(r);
            authorities.add(authorityPermit);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.student.getPassword();
    }

    @Override
    public String getUsername() {
        return this.student.getUsername();
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
        /*return this.student.getActive()==1;*/
        return true;
    }
}
