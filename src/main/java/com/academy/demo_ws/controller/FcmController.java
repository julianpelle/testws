package com.academy.demo_ws.controller;

import com.academy.demo_ws.entity.FcmToken;
import com.academy.demo_ws.repository.FcmTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/fcm")
public class FcmController {

    private final FcmTokenRepository fcmTokenRepository;

    public FcmController(FcmTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    @PostMapping("/token")
    public void saveToken (@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        if (token != null && !token.isEmpty()) {
            FcmToken fcmToken = new FcmToken();
            fcmToken.setToken(token);
            fcmTokenRepository.save(fcmToken);
            System.out.println("Token guardado: " + token);
        }
    }


}
