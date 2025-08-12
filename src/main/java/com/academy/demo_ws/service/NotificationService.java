package com.academy.demo_ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

   private final SimpMessagingTemplate messagingTemplate; //Herramienta de Spring para enviar mensajes a través de STOMP.

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyNewEvent(String news) {
        // Siempre enviamos un objeto JSON con la noticia completa
        messagingTemplate.convertAndSend("/topic/news", news); //Destino de mensaje
    }
}
