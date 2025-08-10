package com.academy.demo_ws.repository;

import com.academy.demo_ws.entity.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FcmTokenRepository extends JpaRepository<FcmToken,Long> {
    List<FcmToken> findAll();
}
