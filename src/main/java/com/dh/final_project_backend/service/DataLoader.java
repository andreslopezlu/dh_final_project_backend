package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.AppUser;
import com.dh.final_project_backend.entity.AppUserRole;
import com.dh.final_project_backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IUserRepository userRepository;

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder0 = new BCryptPasswordEncoder();
        String hashedPassword0 = passwordEncoder0.encode("caneo");

        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String hashedPassword1 = passwordEncoder1.encode("fortuna");

        userRepository.save(new AppUser("caneo@dh.com", hashedPassword0, AppUserRole.ADMIN));
        userRepository.save(new AppUser("fortuna@dh.com", hashedPassword1, AppUserRole.USER));
    }

}
