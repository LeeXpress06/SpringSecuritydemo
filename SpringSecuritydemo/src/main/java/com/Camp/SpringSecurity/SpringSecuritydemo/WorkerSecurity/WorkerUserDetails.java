package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerSecurity;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerEntity.Worker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WorkerUserDetails implements UserDetails {

    private String workerName;
    private String password;
    private GrantedAuthority authorities;


    public WorkerUserDetails(Worker worker) {
        this.workerName = worker.getWorkerName();
        this.password = worker.getPassword();
        this.authorities = new SimpleGrantedAuthority(worker.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return workerName;
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
