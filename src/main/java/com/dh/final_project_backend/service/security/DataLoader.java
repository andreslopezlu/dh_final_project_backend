package com.dh.final_project_backend.service.security;

import com.dh.final_project_backend.entity.AppUser;
import com.dh.final_project_backend.entity.AppUserRole;
import com.dh.final_project_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
        userRepository.save(new AppUser("caneo", "caneo", "caneo@dh.com", hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("fortuna", "fortuna", "fortuna@dh.com", hashedPassword2, AppUserRole.USER));
    }
}

