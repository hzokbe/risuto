package com.hzokbe.risuto.repository.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hzokbe.risuto.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
