package com.academy.demo_ws.controller;

import com.academy.demo_ws.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NotificacionController {

    private final NotificationService notificationService;

    public NotificacionController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notify-event")
    public String notifyNewEvent(@RequestParam String message) {
        notificationService.notifyNewEvent(message);
        return "Notificación de novedad enviada: " + message;
    }
}