package com.academy.demo_ws.controller;

import com.academy.demo_ws.model.New;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

//Controlador que se encarga de enviar notificaiones en tiempo real a los clientes.
@Controller
public class NewWebSocketController {

    private final SimpMessagingTemplate messagingTemplate; //Componente de Spring que permite enviar mensajes a través de un broker de mensajes STOMP

    public NewWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNewCreated(New news) {
        System.out.println("Enviando noticia por WebSocket: " + news.getTitulo());
        messagingTemplate.convertAndSend("/topic/news", news); //Toma el objeto News y lo convierte en JSON para enviar a los clientes conectados.
    }
}
