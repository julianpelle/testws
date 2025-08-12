package com.academy.demo_ws.service;

import com.academy.demo_ws.model.FcmToken;
import com.academy.demo_ws.repository.FcmTokenRepository;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.*;
import java.util.List;

//Clase Service que contiene la lógica de negocio principal de la aplicación.
@Service
public class FcmNotificationService {

    private final FcmTokenRepository fcmTokenRepository;

    public FcmNotificationService(FcmTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    public void sendPushNotification(String title, String body) { //Método encargado de enviar las notificaciones
        List<String> tokens = fcmTokenRepository.findAll().stream()
                .map(FcmToken::getToken)
                .toList();

        if (tokens.isEmpty()) {
            System.out.println("No hay tokens registrados para enviar la notificación.");
            return;
        }

        System.out.println("Tokens encontrados: " + tokens.size());

        for (String token : tokens) { //Construye el objeto Message, que es el formato que Firebase requiere para las notificaciones
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setToken(token) //Especifica a qué token se debe enviar esta notificación
                    .build();

            try {
                String response = FirebaseMessaging.getInstance().send(message); //Envía la notificación a través del Firebase
                System.out.println("Notificación push enviada a token " + token + ": " + response);
            } catch (FirebaseMessagingException e) {
                if (e.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED) { //puede pasar que haya token viejo que ya no es válido, entonces se controla esto
                    System.err.println("Token no válido o no registrado. Eliminando de la base de datos: " + token);
                    fcmTokenRepository.deleteByToken(token);
                } else {
                    System.err.println("Error al enviar la notificación a token " + token + ": " + e.getMessage());
                    e.printStackTrace();
            }
        }
        }
    }
}