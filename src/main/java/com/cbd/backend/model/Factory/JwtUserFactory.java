package com.cbd.backend.model.Factory;

import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.dbo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getIdSecurityNumber(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities( user.getAuthority() ),
                user.isEnabled(),
                new Date( user.getPasswordUpdateDate() - ( 60 * 1000 * 1000))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map( authority -> new SimpleGrantedAuthority( authority.getPermission().name() ) )
                .collect(Collectors.toList());
    }
}
