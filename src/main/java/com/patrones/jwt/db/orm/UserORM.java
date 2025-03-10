package com.patrones.jwt.db.orm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Table(name = "usuarios")
@Entity
@Data
@NoArgsConstructor
public class UserORM {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(nullable = false)
    private String role; // "ROLE_ADMIN" o "ROLE_USER"

    public GrantedAuthority getRole() {
        return new SimpleGrantedAuthority(role);
    }

}
