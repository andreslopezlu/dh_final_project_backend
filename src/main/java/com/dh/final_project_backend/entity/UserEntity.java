package com.dh.final_project_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    public UserEntity(String username, String password, String email, Boolean locked, Boolean disabled, AppUserRole appUserRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.locked = locked;
        this.disabled = disabled;
        this.appUserRole = appUserRole;
    }
}

