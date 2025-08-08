package com.academy.demo_ws.controller;


import com.academy.demo_ws.entity.NewEntity;
import com.academy.demo_ws.model.New;
import com.academy.demo_ws.service.NewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewController {

    private final NewService newService;
    private final NewWebSocketController wsController;

    public NewController(NewService newService, NewWebSocketController wsController) {
        this.newService = newService;
        this.wsController = wsController;
    }

    @GetMapping
    public List<NewEntity> getAll() {
        return newService.getAll();
    }

    @PostMapping
    public NewEntity create(@RequestBody New news) {
        NewEntity saved = newService.save(news);
        wsController.sendNewCreated(saved); // Notificar a todos los clientes
        return saved;
    }
}


