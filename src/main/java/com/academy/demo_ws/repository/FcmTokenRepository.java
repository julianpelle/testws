package com.academy.demo_ws.repository;

import com.academy.demo_ws.model.FcmToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FcmTokenRepository extends JpaRepository<FcmToken,Long> {
    List<FcmToken> findAll();
    @Transactional //necesario para operaciones de modificacion como DELETE o UPDATE
    void deleteByToken(String token);
}
