package com.academy.demo_ws.controller;

import com.academy.demo_ws.entity.NewEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NewWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public NewWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNewCreated(NewEntity news) {
        System.out.println("Enviando noticia por WebSocket: " + news.getTitulo());

        messagingTemplate.convertAndSend("/topic/news", news);
    }
}
