package com.academy.demo_ws.service;

import com.academy.demo_ws.entity.UserEntity;
import com.academy.demo_ws.model.User;
import com.academy.demo_ws.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity save(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setFullname(user.getFullname());

        return userRepository.save(userEntity);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
