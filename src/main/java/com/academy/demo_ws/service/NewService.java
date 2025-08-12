package com.academy.demo_ws.service;

import com.academy.demo_ws.model.New;
import com.academy.demo_ws.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewService {

    private final NewRepository newRepository;
    private final NotificationService notificationService;
    private final FcmNotificationService fcmNotificationService;

    @Autowired
    public NewService(NewRepository newRepository, NotificationService notificationService,FcmNotificationService fcmNotificationService) {
        this.newRepository = newRepository;
        this.notificationService = notificationService;
        this.fcmNotificationService = fcmNotificationService;
    }

    public List<New> getAll() {
        return newRepository.findAll();
    }

    public New getById(Long id) {
        return newRepository.findById(id).orElse(null);
    }

    public New save(New news) {
        New newAdded = new New();
        newAdded.setId(news.getId());
        newAdded.setActivo(news.isActivo());
        newAdded.setDescripcion(news.getDescripcion());
        newAdded.setTitulo(news.getTitulo());
        newAdded.setPrioridad(news.getPrioridad());

        New saved = newRepository.save(newAdded);

        //Notificar en tiempo real con el objeto completo
        notificationService.notifyNewEvent(String.valueOf(saved)); //Para WebSocket
        fcmNotificationService.sendPushNotification("¡Nueva Novedad!", saved.getTitulo()); //Para WebPush

        return saved;
    }

    public void delete(Long id) {
        newRepository.deleteById(id);
    }
}
