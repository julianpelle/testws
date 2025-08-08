package com.academy.demo_ws.repository;

import com.academy.demo_ws.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<NewEntity,Long> {
}
