package com.dh.final_project_backend.security;

import com.dh.final_project_backend.entity.AppUserRole;
import com.dh.final_project_backend.entity.UserEntity;
import com.dh.final_project_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("caneo");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("fortuna");
        userRepository.save(new UserEntity("caneo", hashedPassword, "caneo@dh.com", false, false, AppUserRole.ADMIN));
        userRepository.save(new UserEntity("fortuna", hashedPassword2, "fortuna@dh.com", false, false, AppUserRole.USER));
    }
}
