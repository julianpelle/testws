package com.academy.demo_ws.service;

import com.academy.demo_ws.entity.NewEntity;
import com.academy.demo_ws.model.New;
import com.academy.demo_ws.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewService {

    private final NewRepository newRepository;
    private final NotificationService notificationService;

    @Autowired
    public NewService(NewRepository newRepository, NotificationService notificationService) {
        this.newRepository = newRepository;
        this.notificationService = notificationService;
    }

    public List<NewEntity> getAll() {
        return newRepository.findAll();
    }

    public NewEntity getById(Long id) {
        return newRepository.findById(id).orElse(null);
    }

    public NewEntity save(New news) {
        NewEntity newEntity = new NewEntity();
        newEntity.setId(news.getId());
        newEntity.setActivo(news.isActivo());
        newEntity.setDescripcion(news.getDescripcion());
        newEntity.setTitulo(news.getTitulo());
        newEntity.setPrioridad(news.getPrioridad());

        NewEntity saved = newRepository.save(newEntity);

        // Notificar en tiempo real con el objeto completo
        notificationService.notifyNewEvent(String.valueOf(saved));

        return saved;
    }

    public void delete(Long id) {
        newRepository.deleteById(id);
    }
}
