package com.academy.demo_ws.service;

import com.academy.demo_ws.entity.FcmToken;
import com.academy.demo_ws.repository.FcmTokenRepository;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FcmNotificationService {

    private final FcmTokenRepository fcmTokenRepository;

    public FcmNotificationService(FcmTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    public void sendPushNotification(String title, String body) {
        List<String> tokens = fcmTokenRepository.findAll().stream()
                .map(FcmToken::getToken)
                .collect(Collectors.toList());

        if (tokens.isEmpty()) {
            System.out.println("No hay tokens registrados para enviar la notificación.");
            return;
        }

        System.out.println("Tokns encontrados: " + tokens.size());

        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(body).build())
                .addAllTokens(tokens)
                .build();

        try {
            FirebaseMessaging.getInstance().sendMulticast(message);
            System.out.println("Notificación push enviada con éxito.");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}

