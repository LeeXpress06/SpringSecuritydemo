package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerEntity.Worker;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerRepository.WorkerRepository;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerSecurity.WorkerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class WorkerUserDetailService implements UserDetailsService {
    @Autowired
    private WorkerRepository workerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Worker> workerInfo = workerRepository.findByWorkerName(username);

        UserDetails workerDetails = workerInfo.map(WorkerUserDetails::new)
                .orElseThrow(
                        ( ) -> new UsernameNotFoundException("User not found")
                );

        return workerDetails;
    }
}
