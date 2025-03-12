package com.patrones.jwt.logica;

import com.patrones.jwt.db.jpa.UserJPA;
import com.patrones.jwt.db.orm.UserORM;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService{

    private final UserJPA userJPA;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserORM user = userJPA.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(user.getUsername(), user.getPassword(), Collections.singleton(user.getRole()));
    }

    @Transactional
    public void saveUser(UserORM user) {
        userJPA.save(user);
    }

    public boolean existsByUsername(String username) {
        return userJPA.findByUsername(username).isPresent();
    }

}
