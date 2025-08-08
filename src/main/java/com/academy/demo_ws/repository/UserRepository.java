package com.academy.demo_ws.repository;

import com.academy.demo_ws.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
