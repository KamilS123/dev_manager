package com.kamil.dev_manager.config.securityConfig;

import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Student student = this.studentRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(student);

        return userPrincipal;
    }
}
