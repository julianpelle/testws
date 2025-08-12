package com.academy.demo_ws.repository;

import com.academy.demo_ws.model.New;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<New,Long> {
}
