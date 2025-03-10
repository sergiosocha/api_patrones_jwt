package com.patrones.jwt.db.jpa;

import com.patrones.jwt.db.orm.UserORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPA extends JpaRepository<UserORM, Long> {

    Optional<UserORM> findByUsername(String username);
}
